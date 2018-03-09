package com.giancarlo.tektonlabs.tekton_movies.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.giancarlo.tektonlabs.tekton_movies.Entities.Actores;
import com.giancarlo.tektonlabs.tekton_movies.Entities.Peliculas;
import com.giancarlo.tektonlabs.tekton_movies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Giancarlo on 09/03/2018.
 */

public class Adapter_Actores extends RecyclerView.Adapter<Adapter_Actores.ListaViewHolder> {

    private List<Actores> items;
    Context context;
    @Override
    public Adapter_Actores.ListaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_actor, parent, false);
        return new Adapter_Actores.ListaViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public  Adapter_Actores(List<Actores> items, Context context){
        //CONTRUCTOR
        this.items = items;
        this.context = context;
    }

    public class ListaViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagen;
        public TextView nombre;
        public TextView detalle;
        public TextView calificacion;
        public TextView estado;
        public boolean bool_seleccion;

        public ListaViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.img_actor);
            nombre = (TextView) v.findViewById(R.id.txt_nombre);
            calificacion = (TextView) v.findViewById(R.id.txt_calificacion);
            detalle = (TextView) v.findViewById(R.id.txt_descripcion);

        }
    }

    @Override
    public void onBindViewHolder(Adapter_Actores.ListaViewHolder viewholder, int position) {

        viewholder.nombre.setText(items.get(position).getNombre());
        viewholder.calificacion.setText(items.get(position).getCalificacion()+"");
        viewholder.detalle.setText(items.get(position).getDetalle());
        Picasso.with(context).load("https://image.tmdb.org/t/p/w500/"+ items.get(position).getUrl_imagen()
        ).into(viewholder.imagen);
    }

}