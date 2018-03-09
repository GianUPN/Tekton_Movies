package com.giancarlo.tektonlabs.tekton_movies;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giancarlo on 09/03/2018.
 */

public class DAO_Peliculas {

    public List<Peliculas> get_peliculas_populares(Context context, int pagina){
        // COLA de Request
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = Codes.CABECERA + Codes.GET_PELICULAS + Codes.API_KEY+ "&page="+pagina;
        final List<Peliculas> peliculasList = new ArrayList<>();
        // LISTENER
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Response",response);
                        // PARSE a JSON
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            JSONArray jsonArray = new JSONArray(jsonObj.getJSONArray("results"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject item = jsonArray.getJSONObject(i);
                                Peliculas pelicula = new Peliculas();
                                pelicula.setId((String) item.get("id"));
                                pelicula.setNombre((String) item.get("title"));
                                pelicula.setDetalle((String) item.get("overview"));
                                pelicula.setCalificacion((double) item.get("vote_average"));
                                peliculasList.add(pelicula);
                            }
                        }catch (Exception e){
                            Log.i("Error",e.toString());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error",error.toString());
            }
        });
        // Agregamos el request a la cola
        queue.add(stringRequest);
        return peliculasList;
    }
}
