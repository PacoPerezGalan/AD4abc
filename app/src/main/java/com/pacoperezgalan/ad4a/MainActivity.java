package com.pacoperezgalan.ad4a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.attr.start;
import static android.R.id.edit;

public class MainActivity extends AppCompatActivity {

    Button alumno;
    Button profesor;
    Button borrarAlumno;
    Button borrarProfesor;
    Button borrarTodo;
    EditText idAlumno;
    EditText idProfesor;
    Button consultar;
    static MyDBAdapter dbAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbAdapter=new MyDBAdapter(this);
        alumno=(Button) findViewById(R.id.btn_alumno);
        profesor=(Button) findViewById(R.id.btn_profesor);
        borrarAlumno=(Button) findViewById(R.id.btn_borrarAlumno);
        borrarProfesor=(Button) findViewById(R.id.btn_borrarProfesor);
        borrarTodo=(Button) findViewById(R.id.btn_borrartot);
        idAlumno=(EditText) findViewById(R.id.et_id_alumno);
        idProfesor=(EditText) findViewById(R.id.et_id_profesor);
        consultar=(Button) findViewById(R.id.btn_consultar);

        dbAdapter.open();

        alumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),RegistrarAlumno.class);
                startActivity(i);
            }
        });

        profesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),RegistrarProfesor.class);
                startActivity(i);
            }
        });
        borrarAlumno.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dbAdapter.borrarAlumno(idAlumno.getText().toString());
                Toast.makeText(getApplicationContext(),"Se ha borrado el alumno",Toast.LENGTH_SHORT);
                return false;
            }
        });

        borrarProfesor.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dbAdapter.borrarProfesor(idProfesor.getText().toString());
                Toast.makeText(getApplicationContext(),"Se ha borrado el profesor",Toast.LENGTH_SHORT);
                return false;
            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Consultar.class);
                startActivity(i);
            }
        });

        borrarTodo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dbAdapter.borrarTodo();
                Toast.makeText(getApplicationContext(),"Se ha borrado todo",Toast.LENGTH_SHORT);
                return false;
            }
        });
    }
}
