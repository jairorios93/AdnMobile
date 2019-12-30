package com.example.alquilervehiculosfront.dominio.servicios;

import com.example.alquilervehiculosfront.dominio.modelo.AlquilarVehiculo;
import com.example.alquilervehiculosfront.servicios.Endpoint;
import com.example.alquilervehiculosfront.servicios.ServicioAlquiler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicioAlquilerApplication {

    private ServicioAlquiler servicioAlquiler;

    public ServicioAlquilerApplication() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Endpoint.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        servicioAlquiler = retrofit.create(ServicioAlquiler.class);
    }

    public void alquilar(AlquilarVehiculo alquilarVehiculo) {
        servicioAlquiler.alquilar(alquilarVehiculo).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }

    public void devolver(String placaVehiculo) {
        servicioAlquiler.devolver(placaVehiculo).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }
}