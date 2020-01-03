package com.example.alquilervehiculosfront.dominio.repositorio;

import androidx.lifecycle.MutableLiveData;

import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioGet;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioPost;
import com.example.alquilervehiculosfront.dominio.modelo.Usuario;

public interface RepositorioUsuario {

    MutableLiveData<RespuestaServicioPost> registrar(Usuario usuario);

    MutableLiveData<RespuestaServicioGet> buscar(Long cedula);
}
