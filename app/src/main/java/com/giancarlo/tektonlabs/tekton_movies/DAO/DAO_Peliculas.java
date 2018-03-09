package com.giancarlo.tektonlabs.tekton_movies.DAO;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.giancarlo.tektonlabs.tekton_movies.Entities.Peliculas;
import com.giancarlo.tektonlabs.tekton_movies.Utils.Codes;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giancarlo on 09/03/2018.
 */



public class DAO_Peliculas {

    public interface Peliculas_Callback{
        void onSuccess(List<Peliculas> lista_peliculas);
    }

    public void get_peliculas_populares(Context context, int pagina,final Peliculas_Callback callback){
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
                            JSONArray jsonArray = (JSONArray)jsonObj.get("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject item = jsonArray.getJSONObject(i);
                                Peliculas pelicula = new Peliculas();
                                pelicula.setId(item.get("id")+"");
                                pelicula.setNombre((String) item.get("title"));
                                pelicula.setDetalle((String) item.get("overview"));
                                pelicula.setUrl_imagen((String) item.get("poster_path"));
                                pelicula.setCalificacion(Double.parseDouble(item.get("vote_average")+""));
                                peliculasList.add(pelicula);
                            }
                            callback.onSuccess(peliculasList);
                        }catch (Exception e){
                            e.printStackTrace();
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
    }

    //&language=eu-ES&query=fin&page=1

    public void search_peliculas(Context context, String palabra ,int pagina,final Peliculas_Callback callback){
        // COLA de Request
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = Codes.CABECERA + Codes.GET_PELICULAS + Codes.API_KEY+ "&language=eu-ES&query="+ palabra+ "&page="+pagina;
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
                            JSONArray jsonArray = (JSONArray)jsonObj.get("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject item = jsonArray.getJSONObject(i);
                                Peliculas pelicula = new Peliculas();
                                pelicula.setId(item.get("id")+"");
                                pelicula.setNombre((String) item.get("title"));
                                pelicula.setDetalle((String) item.get("overview"));
                                pelicula.setUrl_imagen((String) item.get("poster_path"));
                                pelicula.setCalificacion(Double.parseDouble(item.get("vote_average")+""));
                                peliculasList.add(pelicula);
                            }
                            callback.onSuccess(peliculasList);
                        }catch (Exception e){
                            e.printStackTrace();
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
    }

}
