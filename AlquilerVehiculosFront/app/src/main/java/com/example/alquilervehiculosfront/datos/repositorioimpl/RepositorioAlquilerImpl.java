package com.example.alquilervehiculosfront.datos.repositorioimpl;

import com.example.alquilervehiculosfront.datos.restutil.Endpoint;
import com.example.alquilervehiculosfront.datos.llamadorest.LlamadoAlquilerRest;
import com.example.alquilervehiculosfront.dominio.modelo.AlquilarVehiculo;
import com.example.alquilervehiculosfront.dominio.repositorio.RepositorioAlquiler;

import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepositorioAlquilerImpl implements RepositorioAlquiler {

    private LlamadoAlquilerRest servicioAlquiler;

    public RepositorioAlquilerImpl(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Endpoint.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicioAlquiler = retrofit.create(LlamadoAlquilerRest.class);
    }

    @Override
    public void alquilar(AlquilarVehiculo alquilarVehiculo) {
        try {
            servicioAlquiler.alquilar(alquilarVehiculo).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void devolver(String placa) {

    }
}
