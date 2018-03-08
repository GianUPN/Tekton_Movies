package com.giancarlo.tektonlabs.tekton_movies;

import android.app.AppOpsManager;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by Giancarlo on 08/03/2018.
 */

public class Conexion_volley {

    final String CABECERA ="https://api.themoviedb.org/3";
    final String API_KEY ="?api_key=28a0d9072466fb61d9c60ead94c48450";
    final String GET_PELICULAS = "/movie/popular";

    public void get_peliculas_populares(Context context,int pagina){
        // COLA de Request
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = CABECERA + GET_PELICULAS + API_KEY+ "&page="+pagina;

        // LISTENER
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Response",response);
                        // PARSE a JSON
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error",error.toString());
            }
        });
        // Agregamos el request a la cola
        queue.add(stringRequest);
    }
}
