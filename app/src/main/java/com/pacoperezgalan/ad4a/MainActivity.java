package com.pacoperezgalan.ad4a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button alumno;
    Button profesor;
    Button borrar;
    static MyDBAdapter dbAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbAdapter=new MyDBAdapter(this);
        alumno=(Button) findViewById(R.id.btn_alumno);
        profesor=(Button) findViewById(R.id.btn_profesor);
        borrar=(Button) findViewById(R.id.btn_borrar);

        dbAdapter.open();

        alumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),RegistrarAlumno.class);
                startActivityForResult(i,1);
            }
        });

        profesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),RegistrarProfesor.class);
                startActivityForResult(i,1);
            }
        });

    }
}
