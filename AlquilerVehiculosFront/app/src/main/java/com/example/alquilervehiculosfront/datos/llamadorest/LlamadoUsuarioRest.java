package com.example.alquilervehiculosfront.datos.llamadorest;

import com.example.alquilervehiculosfront.datos.dto.UsuarioDTO;
import com.example.alquilervehiculosfront.dominio.modelo.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LlamadoUsuarioRest {

    @POST("usuario")
    Call<Void> registrar(@Body Usuario usuario);

    @GET("usuario/{CEDULA}")
    Call<UsuarioDTO> buscar(@Path("CEDULA") Long cedula);
}