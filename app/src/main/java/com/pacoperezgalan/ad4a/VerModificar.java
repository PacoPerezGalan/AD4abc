package com.pacoperezgalan.ad4a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class VerModificar extends AppCompatActivity {
    String accion;
    String es;
    String nom;
    String ciclo;
    String curso;

    TextView tvEs;
    EditText etNom;
    EditText etCiclo;
    EditText etCurso;
    Button mod;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_modificar);

        savedInstanceState=getIntent().getExtras();

        tvEs=(TextView) findViewById(R.id.tv_es);
        etNom=(EditText) findViewById(R.id.et_nombre);
        etCiclo=(EditText) findViewById(R.id.et_ciclo);
        etCurso=(EditText) findViewById(R.id.et_curso);
        mod=(Button) findViewById(R.id.btn_mod);

        accion=savedInstanceState.getString("accion");
        es=savedInstanceState.getString("es");
        nom=savedInstanceState.getString("nom");
        ciclo=savedInstanceState.getString("ciclo");
        curso=savedInstanceState.getString("curso");

        if(es.compareTo("Alum")==0){
            tvEs.setText("Alumno");
        }else{
            tvEs.setText("Profesor");
        }

        etNom.setText(nom);
        etCiclo.setText(ciclo);
        etCurso.setText(curso);



        if(accion.compareTo("ver")==0) {
            etNom.setEnabled(false);
            etCiclo.setEnabled(false);
            etCurso.setEnabled(false);
            mod.setClickable(false);
            mod.setVisibility(View.INVISIBLE);
        }else{
            mod.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.dbAdapter.modificar(es,nom,ciclo,curso,etNom.getText().toString(),etCiclo.getText().toString(),etCurso.getText().toString());
                    Toast.makeText(getApplicationContext(),"Se ha modificado",Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }



    }
}
