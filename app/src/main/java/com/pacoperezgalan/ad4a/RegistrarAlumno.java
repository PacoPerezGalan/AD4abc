package com.pacoperezgalan.ad4a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistrarAlumno extends AppCompatActivity {

    EditText nombre;
    EditText edad;
    EditText ciclo;
    EditText curso;
    EditText nota;
    Button registrar;

    String nom;
    int eda;
    String cic;
    int cur;
    Float not;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_alumno);

        nombre=(EditText) findViewById(R.id.et_nombre);
        edad=(EditText) findViewById(R.id.et_edad);
        ciclo=(EditText) findViewById(R.id.et_ciclo);
        curso=(EditText) findViewById(R.id.et_curso);
        nota=(EditText) findViewById(R.id.et_nota);

        registrar=(Button) findViewById(R.id.btn_registrar);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nom=nombre.getText().toString();
                eda=Integer.parseInt(edad.getText().toString());
                cic=ciclo.getText().toString();
                cur=Integer.parseInt(curso.getText().toString());
                not=Float.parseFloat(nota.getText().toString());
                MainActivity.dbAdapter.insertarAlumno(nom,eda,cic,cur,not);
                finish();
            }
        });
    }
}
