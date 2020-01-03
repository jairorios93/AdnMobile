package com.example.alquilervehiculosfront.dominio.servicios;

import androidx.lifecycle.MutableLiveData;

import com.example.alquilervehiculosfront.datos.repositorioimpl.RepositorioUsuarioImpl;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioGet;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioPost;
import com.example.alquilervehiculosfront.dominio.modelo.Usuario;
import com.example.alquilervehiculosfront.dominio.repositorio.RepositorioUsuario;

public class ServicioUsuarioDominio {

    private RepositorioUsuario repositorioUsuario;

    public ServicioUsuarioDominio() {
        repositorioUsuario = new RepositorioUsuarioImpl();
    }

    public MutableLiveData<RespuestaServicioPost> registrar(Usuario usuario) {
        return repositorioUsuario.registrar(usuario);
    }

    public MutableLiveData<RespuestaServicioGet> buscar(Long cedulaUsuario) {
        return repositorioUsuario.buscar(cedulaUsuario);
    }
}