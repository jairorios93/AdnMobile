package com.example.alquilervehiculosfront.dominio.repositorio;

import androidx.lifecycle.MutableLiveData;

import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicio;
import com.example.alquilervehiculosfront.dominio.modelo.Usuario;

public interface RepositorioUsuario {

    MutableLiveData<RespuestaServicio> registrar(Usuario usuario);

    Usuario buscar(Long cedula);
}
