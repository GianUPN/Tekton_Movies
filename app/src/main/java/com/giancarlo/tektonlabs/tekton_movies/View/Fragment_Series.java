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

import com.giancarlo.tektonlabs.tekton_movies.Adapters.Adapter_Peliculas;
import com.giancarlo.tektonlabs.tekton_movies.Adapters.Adapter_Series;
import com.giancarlo.tektonlabs.tekton_movies.DAO.DAO_Peliculas;
import com.giancarlo.tektonlabs.tekton_movies.DAO.DAO_Series;
import com.giancarlo.tektonlabs.tekton_movies.Entities.Peliculas;
import com.giancarlo.tektonlabs.tekton_movies.Entities.Series;
import com.giancarlo.tektonlabs.tekton_movies.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Series extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    public Fragment_Series() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_series, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.lista_series);
        //recyclerView.setHasFixedSize(true);
        lManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(lManager);
        final Context context = getContext();
        DAO_Series dao_series = new DAO_Series();
        dao_series.get_series_populares(getContext(), 1, new DAO_Series.Series_Callback() {
            @Override
            public void onSuccess(List<Series> lista_series) {
                try {
                    Log.i("lista", lista_series.get(1).toString());
                    adapter = new Adapter_Series(lista_series,context);
                    recyclerView.setAdapter(adapter);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

}
