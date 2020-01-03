package com.example.alquilervehiculosfront.datos.repositorioimpl;

import com.example.alquilervehiculosfront.datos.restutil.Endpoint;
import com.example.alquilervehiculosfront.datos.restutil.StatusResponse;
import com.example.alquilervehiculosfront.datos.llamadorest.LlamadoUsuarioRest;
import com.example.alquilervehiculosfront.dominio.modelo.Usuario;
import com.example.alquilervehiculosfront.dominio.repositorio.RepositorioUsuario;

import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepositorioUsuarioImpl implements RepositorioUsuario {

    private LlamadoUsuarioRest llamadoUsuarioRest;

    public RepositorioUsuarioImpl() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Endpoint.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        llamadoUsuarioRest = retrofit.create(LlamadoUsuarioRest.class);
    }

    @Override
    public boolean registrar(Usuario usuario) {
        try {
            return (llamadoUsuarioRest.registrar(usuario).execute().code() == StatusResponse.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Usuario buscar(Long cedula) {
        return null;
    }
}
