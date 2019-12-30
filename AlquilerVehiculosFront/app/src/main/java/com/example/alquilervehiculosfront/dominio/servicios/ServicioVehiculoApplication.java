package com.example.alquilervehiculosfront.dominio.servicios;

import com.example.alquilervehiculosfront.dominio.modelo.Vehiculo;
import com.example.alquilervehiculosfront.servicios.Endpoint;
import com.example.alquilervehiculosfront.servicios.ServicioVehiculo;
import com.example.alquilervehiculosfront.servicios.dto.VehiculoDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicioVehiculoApplication {

    private ServicioVehiculo servicioVehiculo;

    public ServicioVehiculoApplication() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Endpoint.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicioVehiculo = retrofit.create(ServicioVehiculo.class);
    }

    public void registrar(Vehiculo vehiculo) {
        servicioVehiculo.registrar(vehiculo).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }

    public void buscar(String placaVehiculo) {
        servicioVehiculo.buscar(placaVehiculo).enqueue(new Callback<VehiculoDTO>() {
            @Override
            public void onResponse(Call<VehiculoDTO> call, Response<VehiculoDTO> response) {
            }

            @Override
            public void onFailure(Call<VehiculoDTO> call, Throwable t) {
            }
        });
    }
}
