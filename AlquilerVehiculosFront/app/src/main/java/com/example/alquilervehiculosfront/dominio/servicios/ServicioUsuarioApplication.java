package com.example.alquilervehiculosfront.dominio.servicios;

import com.example.alquilervehiculosfront.dominio.modelo.Usuario;
import com.example.alquilervehiculosfront.servicios.Endpoint;
import com.example.alquilervehiculosfront.servicios.ServicioUsuario;
import com.example.alquilervehiculosfront.servicios.dto.UsuarioDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicioUsuarioApplication {

    private ServicioUsuario servicioUsuario;

    public ServicioUsuarioApplication() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Endpoint.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicioUsuario = retrofit.create(ServicioUsuario.class);
    }

    public void registrar(Usuario usuario) {
        servicioUsuario.registrar(usuario).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }

    public void buscar(Long cedulaUsuario) {
        servicioUsuario.buscar(cedulaUsuario).enqueue(new Callback<UsuarioDTO>() {
            @Override
            public void onResponse(Call<UsuarioDTO> call, Response<UsuarioDTO> response) {
            }

            @Override
            public void onFailure(Call<UsuarioDTO> call, Throwable t) {
            }
        });
    }
}