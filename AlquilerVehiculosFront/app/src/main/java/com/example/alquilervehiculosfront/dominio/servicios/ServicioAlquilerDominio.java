package com.example.alquilervehiculosfront.dominio.servicios;

import androidx.lifecycle.MutableLiveData;

import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioGet;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioPost;
import com.example.alquilervehiculosfront.dominio.inyeccion.ComponenteApp;
import com.example.alquilervehiculosfront.dominio.inyeccion.DaggerComponenteApp;
import com.example.alquilervehiculosfront.dominio.repositorio.RepositorioAlquiler;
import com.example.alquilervehiculosfront.dominio.modelo.AlquilarVehiculo;

import javax.inject.Inject;

public class ServicioAlquilerDominio {

    @Inject
    public RepositorioAlquiler repositorioAlquiler;

    public ServicioAlquilerDominio() {
        ComponenteApp componenteApp = DaggerComponenteApp.builder().build();
        componenteApp.inject(this);
    }

    public MutableLiveData<RespuestaServicioPost> alquilar(AlquilarVehiculo alquilarVehiculo) {
        return repositorioAlquiler.alquilar(alquilarVehiculo);
    }

    public MutableLiveData<RespuestaServicioGet> devolver(String placaVehiculo) {
        return repositorioAlquiler.devolver(placaVehiculo);
    }
}