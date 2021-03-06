package com.pacoperezgalan.ad4a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by 2dam on 16/12/2016.
 */

public class MyDBAdapter {
    private static final String DATABASE_NAME = "dbEscuela.db";
    private static final String DATABASE_TABLE1 = "Alumnos";
    private static final String DATABASE_TABLE2 = "Profesores";
    private static final int DATABASE_VERSION = 1;

    private static final String NOMBRE = "nombre";
    private static final String EDAD = "edad";
    private static final String CICLO = "ciclo";
    private static final String CURSO = "curso";
    private static final String NOTA = "nota";
    private static final String DESPACHO = "despacho";

    private static final String DATABASE_CREATE1 = "CREATE TABLE IF NOT EXISTS "+DATABASE_TABLE1+" (id integer primary key autoincrement, nombre text, edad integer, ciclo text, curso integer, nota float ); ";

    private static final String DATABASE_CREATE2 ="CREATE TABLE IF NOT EXISTS "+DATABASE_TABLE2+" (id integer primary key autoincrement, nombre text, edad integer, ciclo text, curso integer, despacho integer );";

    private static final String DATABASE_DROP = "DELETE FROM "+DATABASE_TABLE1+";";
    private static final String DATABASE_DROP2 = "DELETE FROM "+DATABASE_TABLE2+";";

    // Contexto de la aplicación que usa la base de datos
    private final Context context;
    // Clase SQLiteOpenHelper para crear/actualizar la base de datos
    private MyDbHelper dbHelper;
    // Instancia de la base de datos
    private SQLiteDatabase db;

    public MyDBAdapter (Context c){
        context = c;
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public void open(){

        try{
            db = dbHelper.getWritableDatabase();
        }catch(SQLiteException e){
            db = dbHelper.getReadableDatabase();
        }

    }

    public void insertarAlumno(String nom, int eda , String cic, int cur, Float not ){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(NOMBRE,nom);
        newValues.put(EDAD,eda);
        newValues.put(CICLO,cic);
        newValues.put(CURSO,cur);
        newValues.put(NOTA,not);

        db.insert(DATABASE_TABLE1,null,newValues);
    }

    public void insertarProfesor(String nom, int eda , String cic, int cur, int des ){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(NOMBRE,nom);
        newValues.put(EDAD,eda);
        newValues.put(CICLO,cic);
        newValues.put(CURSO,cur);
        newValues.put(DESPACHO,des);

        db.insert(DATABASE_TABLE2,null,newValues);
    }

    public void borrarAlumno(String id){
        db.delete(DATABASE_TABLE1,"id=?",new String[]{id});
    }

    public void borrarProfesor(String id){
        db.delete(DATABASE_TABLE2,"id=?",new String[]{id});
    }

    public void borrarTodo(){
        db.execSQL(DATABASE_DROP);
    }


    public ArrayList<Item> consultar(boolean alum, boolean prof, String cic, String cur){
        ArrayList<Item> result=new ArrayList<>();
        Cursor cursor=null;

        if(alum) {
            if(cic!=null && cur!=null){
                cursor = db.query(DATABASE_TABLE1,null,"ciclo=? and curso=?",new String[]{cic,cur},null,null,null);
            }else if(cic!=null){
                cursor = db.query(DATABASE_TABLE1,null,"ciclo=?",new String[]{cic},null,null,null);
            }else if(cur!=null){
                cursor = db.query(DATABASE_TABLE1,null,"curso=?",new String[]{cur},null,null,null);
            }else {
                cursor = db.query(DATABASE_TABLE1, null, null, null, null, null, null);
            }

            if(cursor != null && cursor.moveToFirst()){
                do{
                    result.add(new Item("Alum",cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
                }while (cursor.moveToNext());
            }
        }

        if(prof) {
            if(cic!=null && cur!=null){
                cursor = db.query(DATABASE_TABLE2,null,"ciclo=? and curso=?",new String[]{cic,cur},null,null,null);
            }else if(cic!=null){
                cursor = db.query(DATABASE_TABLE2,null,"ciclo=?",new String[]{cic},null,null,null);
            }else if(cur!=null){
                cursor = db.query(DATABASE_TABLE2,null,"curso=?",new String[]{cur},null,null,null);
            }else {
                cursor = db.query(DATABASE_TABLE2, null, null, null, null, null, null);
            }

            if(cursor != null && cursor.moveToFirst()){
                do{
                    result.add(new Item("Prof",cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
                }while (cursor.moveToNext());
            }
        }



        return result;
    }

    public void modificar(String es,String nom1,String cic1,String cur1,String nom2,String cic2,String cur2){
        if(es.compareTo("Alum")==0) {
            db.execSQL("UPDATE "+DATABASE_TABLE1+" SET nombre='"+nom2+"',ciclo='"+cic2+"',curso='"+cur2+"' WHERE nombre LIKE '"+nom1+"' AND ciclo LIKE '"+cic1+"' AND curso LIKE '"+cur1+"'" );
        }else{
            db.execSQL("UPDATE "+DATABASE_TABLE2+" SET nombre='"+nom2+"',ciclo='"+cic2+"',curso='"+cur2+"' WHERE nombre LIKE '"+nom1+"' AND ciclo LIKE '"+cic1+"' AND curso LIKE '"+cur1+"'" );
        }
    }

    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name,factory,version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE1);
            db.execSQL(DATABASE_CREATE2);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DROP);
            onCreate(db);
        }

    }
}
