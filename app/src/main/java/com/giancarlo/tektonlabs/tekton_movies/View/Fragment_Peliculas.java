package com.giancarlo.tektonlabs.tekton_movies.View;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giancarlo.tektonlabs.tekton_movies.Adapters.Adapter_Peliculas;
import com.giancarlo.tektonlabs.tekton_movies.DAO.DAO_Peliculas;
import com.giancarlo.tektonlabs.tekton_movies.Entities.Peliculas;
import com.giancarlo.tektonlabs.tekton_movies.R;

import java.util.List;

public class Fragment_Peliculas extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    public Fragment_Peliculas() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_peliculas, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.lista_peliculas);
        //recyclerView.setHasFixedSize(true);
        lManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(lManager);
        final Context context = getContext();
        DAO_Peliculas dao_peliculas = new DAO_Peliculas();
        dao_peliculas.get_peliculas_populares(getContext(), 1, new DAO_Peliculas.Peliculas_Callback() {
            @Override
            public void onSuccess(List<Peliculas> lista_peliculas) {
                try {
                    Log.i("lista", lista_peliculas.get(1).toString());
                    adapter = new Adapter_Peliculas(lista_peliculas,context);
                    recyclerView.setAdapter(adapter);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });



        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }


}
