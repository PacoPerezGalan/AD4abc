package com.pacoperezgalan.ad4a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import static com.pacoperezgalan.ad4a.MainActivity.dbAdapter;

public class Consultar extends AppCompatActivity {

    CheckBox alumno;
    CheckBox profesor;
    CheckBox ciclo;
    CheckBox curso;
    EditText etCiclo;
    EditText etCurso;
    Button consultar;
    TextView text;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        alumno=(CheckBox) findViewById(R.id.cb_alumnos);
        profesor=(CheckBox) findViewById(R.id.cb_profesores);
        ciclo=(CheckBox) findViewById(R.id.cb_ciclo);
        curso=(CheckBox) findViewById(R.id.cb_curso);
        etCiclo=(EditText) findViewById(R.id.et_ciclo);
        etCurso=(EditText) findViewById(R.id.et_curso);
        consultar=(Button) findViewById(R.id.btn_consultar);
        text=(TextView) findViewById(R.id.tv_consulta);

        ciclo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(ciclo.isChecked()){
                    etCiclo.setVisibility(View.VISIBLE);
                }else{
                    etCiclo.setVisibility(View.INVISIBLE);
                }
            }
        });

        curso.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(curso.isChecked()){
                    etCurso.setVisibility(View.VISIBLE);
                }else{
                    etCurso.setVisibility(View.INVISIBLE);
                }
            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean alum;
                boolean prof;
                String cic;
                String cur;

                if (alumno.isChecked() ){
                    alum=true;
                }else{
                    alum=false;
                }

                if(profesor.isChecked()){
                    prof=true;
                }else{
                    prof=false;
                }

                if(ciclo.isChecked()){
                    cic=etCiclo.getText().toString();
                }else{
                    cic=null;
                }

                if(curso.isChecked()){
                    cur=etCurso.getText().toString();
                }else{
                    cur=null;
                }

                ArrayList<String> result=MainActivity.dbAdapter.consultar(alum,prof,cic,cur);
                String texto="";
                for(int i=0;i<result.size();i++){
                    texto=texto+result.get(i)+"\n";
                }
                text.setText(texto);
            }
        });
    }
}
