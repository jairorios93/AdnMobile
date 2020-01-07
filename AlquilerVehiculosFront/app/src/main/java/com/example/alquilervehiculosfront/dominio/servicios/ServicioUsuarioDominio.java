package com.example.alquilervehiculosfront.dominio.servicios;

import androidx.lifecycle.MutableLiveData;

import com.example.alquilervehiculosfront.datos.configuracion.AppComponent;
import com.example.alquilervehiculosfront.datos.configuracion.DaggerAppComponent;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioGet;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioPost;
import com.example.alquilervehiculosfront.dominio.modelo.Usuario;
import com.example.alquilervehiculosfront.dominio.repositorio.RepositorioUsuario;

import javax.inject.Inject;

public class ServicioUsuarioDominio {

    @Inject
    public RepositorioUsuario repositorioUsuario;

    public ServicioUsuarioDominio(){
        AppComponent appComponent = DaggerAppComponent.builder().build();
        appComponent.inject(this);
    }

    public MutableLiveData<RespuestaServicioPost> registrar(Usuario usuario) {
        return repositorioUsuario.registrar(usuario);
    }

    public MutableLiveData<RespuestaServicioGet> buscar(Long cedulaUsuario) {
        return repositorioUsuario.buscar(cedulaUsuario);
    }
}