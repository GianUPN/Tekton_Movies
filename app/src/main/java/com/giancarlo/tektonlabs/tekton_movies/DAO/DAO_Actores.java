package com.giancarlo.tektonlabs.tekton_movies.DAO;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.giancarlo.tektonlabs.tekton_movies.Entities.Actores;
import com.giancarlo.tektonlabs.tekton_movies.Entities.Peliculas;
import com.giancarlo.tektonlabs.tekton_movies.Utils.Codes;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giancarlo on 09/03/2018.
 */

public class DAO_Actores {
    public interface Actores_Callback{
        void onSuccess(List<Actores> lista_actores);
    }

    public void get_actores_populares(Context context, int pagina, final Actores_Callback callback){
        // COLA de Request
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = Codes.CABECERA + Codes.GET_ACTORES + Codes.API_KEY+ "&language=eu-ES&page="+pagina;
        final List<Actores> actoresList = new ArrayList<>();
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
                                Actores actor = new Actores();
                                actor.setId(item.get("id")+"");
                                actor.setNombre((String) item.get("name"));
                                actor.setDetalle("");
                                actor.setUrl_imagen((String) item.get("profile_path"));
                                actor.setCalificacion(Double.parseDouble(item.get("popularity")+""));
                                actoresList.add(actor);
                            }
                            callback.onSuccess(actoresList);
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
