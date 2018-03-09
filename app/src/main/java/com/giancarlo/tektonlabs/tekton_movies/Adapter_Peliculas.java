package com.giancarlo.tektonlabs.tekton_movies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Giancarlo on 08/03/2018.
 */

public class Adapter_Peliculas extends RecyclerView.Adapter<Adapter_Peliculas.ListaViewHolder> {

    private List<Peliculas> items;
    @Override
    public ListaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_movie, parent, false);
        return new Adapter_Peliculas.ListaViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public  Adapter_Peliculas(List<Peliculas> items){
        //CONTRUCTOR
        this.items = items;
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
            imagen = (ImageView) v.findViewById(R.id.img_pelicula);
            nombre = (TextView) v.findViewById(R.id.txt_nombre);
            calificacion = (TextView) v.findViewById(R.id.txt_calificacion);
            detalle = (TextView) v.findViewById(R.id.txt_descripcion);

        }
    }

    @Override
    public void onBindViewHolder(ListaViewHolder viewholder, int position) {

        viewholder.nombre.setText(items.get(position).getNombre());
        viewholder.calificacion.setText(items.get(position).getCalificacion()+"");
        viewholder.detalle.setText(items.get(position).getDetalle());
    }

}
