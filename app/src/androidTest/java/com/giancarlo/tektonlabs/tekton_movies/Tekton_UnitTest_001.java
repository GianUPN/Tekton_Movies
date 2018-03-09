package com.giancarlo.tektonlabs.tekton_movies;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.giancarlo.tektonlabs.tekton_movies.DAO.DAO_Actores;
import com.giancarlo.tektonlabs.tekton_movies.DAO.DAO_Peliculas;
import com.giancarlo.tektonlabs.tekton_movies.DAO.DAO_Series;
import com.giancarlo.tektonlabs.tekton_movies.Entities.Actores;
import com.giancarlo.tektonlabs.tekton_movies.Entities.Peliculas;
import com.giancarlo.tektonlabs.tekton_movies.Entities.Series;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class Tekton_UnitTest_001 {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        InstrumentationRegistry.getTargetContext();
        assertEquals("com.giancarlo.tektonlabs.tekton_movies", appContext.getPackageName());
    }

    @Test
    public void get_peliculas_populares() throws Exception {
        DAO_Peliculas peliculas = new DAO_Peliculas();
        final Object syncObject = new Object();
        peliculas.get_peliculas_populares(InstrumentationRegistry.getTargetContext(), 1, new DAO_Peliculas.Peliculas_Callback() {
                    @Override
                    public void onSuccess(List<Peliculas> lista_peliculas) {
                        assertEquals(20, lista_peliculas.size());
                        syncObject.notify();
                    }
                }
        );
        synchronized (syncObject){
            syncObject.wait();

        }

    }
    @Test
    public void get_actores_populares() throws Exception {
        DAO_Actores daoactores = new DAO_Actores();
        final Object syncObject = new Object();
        daoactores.get_actores_populares(InstrumentationRegistry.getTargetContext(), 1, new DAO_Actores.Actores_Callback() {
                    @Override
                    public void onSuccess(List<Actores> list) {
                        assertEquals(20, list.size());
                        syncObject.notify();
                    }
                }
        );
        synchronized (syncObject){
            syncObject.wait();

        }

    }
    @Test
    public void get_series_populares() throws Exception {
        DAO_Series series = new DAO_Series();
        final Object syncObject = new Object();

        series.get_series_populares(InstrumentationRegistry.getTargetContext(), 999, new DAO_Series.Series_Callback() {
                    @Override
                    public void onSuccess(List<Series> list) {
                        Log.i("TEST","OK");
                        synchronized (syncObject){
                            assertEquals(20, list.size());
                            syncObject.notify();

                        }

                    }
                }
        );

        synchronized (syncObject){
            syncObject.wait();

        }

    }
}
