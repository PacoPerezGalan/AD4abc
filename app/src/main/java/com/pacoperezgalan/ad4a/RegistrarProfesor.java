package com.pacoperezgalan.ad4a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistrarProfesor extends AppCompatActivity {

    EditText nombre;
    EditText edad;
    EditText ciclo;
    EditText curso;
    EditText despacho;
    Button registrar;

    String nom;
    int eda;
    String cic;
    int cur;
    int des;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_profesor);

        nombre=(EditText) findViewById(R.id.et_nombre);
        edad=(EditText) findViewById(R.id.et_edad);
        ciclo=(EditText) findViewById(R.id.et_ciclo);
        curso=(EditText) findViewById(R.id.et_curso);
        despacho=(EditText) findViewById(R.id.et_despacho);

        registrar=(Button) findViewById(R.id.btn_registrar);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nom=nombre.getText().toString();
                eda=Integer.parseInt(edad.getText().toString());
                cic=ciclo.getText().toString();
                cur=Integer.parseInt(curso.getText().toString());
                des=Integer.parseInt(despacho.getText().toString());
                MainActivity.dbAdapter.insertarProfesor(nom,eda,cic,cur,des);
                finish();
            }
        });
    }
}