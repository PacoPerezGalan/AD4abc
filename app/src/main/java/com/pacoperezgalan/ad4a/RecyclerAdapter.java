package com.pacoperezgalan.ad4a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by pacoperezgalan on 6/1/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.elementViewHolder> {
    private  ArrayList<Item> items;
    Bundle b;
    public static class elementViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView es;
        public TextView id;
        public TextView nom;

        public Button ver;
        public Button mod;


        public elementViewHolder(View v) {
            super(v);
            es = (TextView) v.findViewById(R.id.es_item);
            id = (TextView) v.findViewById(R.id.id_item);
            nom = (TextView) v.findViewById(R.id.nom_item);

            ver=(Button) v.findViewById(R.id.btn_ver);
            mod=(Button) v.findViewById(R.id.btn_modificar);


        }
    }

    public RecyclerAdapter(ArrayList<Item> items) {
        this.items = items;

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public elementViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_item, viewGroup, false);
        return new elementViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final elementViewHolder viewHolder, int p) {

        viewHolder.es.setText(items.get(p).getEs());
        viewHolder.id.setText(items.get(p).getId());
        viewHolder.nom.setText(items.get(p).getNombre());

        viewHolder.ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(view.getContext(),VerModificar.class);
                b=new Bundle();

                b.putString("es",items.get(viewHolder.getAdapterPosition()).getEs());
                b.putString("nom",items.get(viewHolder.getAdapterPosition()).getNombre());
                b.putString("ciclo",items.get(viewHolder.getAdapterPosition()).getCiclo());
                b.putString("curso",items.get(viewHolder.getAdapterPosition()).getCurso());

                b.putString("accion","ver");
                i.putExtras(b);

                Toast.makeText(view.getContext(),"ver",Toast.LENGTH_SHORT).show();
                view.getContext().startActivity(i);

            }
        });

        viewHolder.mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(view.getContext(),VerModificar.class);
                b=new Bundle();

                b.putString("es",items.get(viewHolder.getAdapterPosition()).getEs());
                b.putString("nom",items.get(viewHolder.getAdapterPosition()).getNombre());
                b.putString("ciclo",items.get(viewHolder.getAdapterPosition()).getCiclo());
                b.putString("curso",items.get(viewHolder.getAdapterPosition()).getCurso());

                b.putString("accion","mod");
                i.putExtras(b);

                Toast.makeText(view.getContext(),"modificar",Toast.LENGTH_SHORT).show();
                view.getContext().startActivity(i);

            }
        });


    }

}