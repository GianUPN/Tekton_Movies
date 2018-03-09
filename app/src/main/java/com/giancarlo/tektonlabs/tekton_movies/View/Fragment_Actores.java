package com.giancarlo.tektonlabs.tekton_movies.View;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giancarlo.tektonlabs.tekton_movies.Adapters.Adapter_Actores;
import com.giancarlo.tektonlabs.tekton_movies.Adapters.Adapter_Peliculas;
import com.giancarlo.tektonlabs.tekton_movies.DAO.DAO_Actores;
import com.giancarlo.tektonlabs.tekton_movies.DAO.DAO_Peliculas;
import com.giancarlo.tektonlabs.tekton_movies.Entities.Actores;
import com.giancarlo.tektonlabs.tekton_movies.Entities.Peliculas;
import com.giancarlo.tektonlabs.tekton_movies.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Actores extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    public Fragment_Actores() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_actores, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.lista_actores);
        //recyclerView.setHasFixedSize(true);
        lManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(lManager);
        final Context context = getContext();
        DAO_Actores dao_actores = new DAO_Actores();
        dao_actores.get_actores_populares(getContext(), 1, new DAO_Actores.Actores_Callback() {
            @Override
            public void onSuccess(List<Actores> lista_Actores) {
                try {
                    Log.i("lista", lista_Actores.get(1).toString());
                    adapter = new Adapter_Actores(lista_Actores,context);
                    recyclerView.setAdapter(adapter);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        return view;

    }

}
