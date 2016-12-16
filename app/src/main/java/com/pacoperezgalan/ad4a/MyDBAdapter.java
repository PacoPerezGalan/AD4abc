package com.pacoperezgalan.ad4a;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

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

    private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS "+DATABASE_TABLE1+" (_id integer primary key autoincrement, nombre text, edad integer, ciclo text, curso integer, nota float ); " +
            "CREATE TABLE IF NOT EXISTS "+DATABASE_TABLE2+" (_id integer primary key autoincrement, nombre text, edad integer, ciclo text, curso integer, despacho integer );";
    private static final String DATABASE_DROP = "DROP TABLE IF EXISTS "+DATABASE_TABLE1+"; " +
            "DROP TABLE IF EXISTS "+DATABASE_TABLE2+";";

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

        db.insert(DATABASE_TABLE1,null,newValues);
    }

    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name,factory,version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DROP);
            onCreate(db);
        }

    }
}
