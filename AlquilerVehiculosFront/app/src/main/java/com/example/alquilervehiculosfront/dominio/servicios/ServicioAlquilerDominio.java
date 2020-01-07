package com.example.alquilervehiculosfront.dominio.servicios;

import androidx.lifecycle.MutableLiveData;

import com.example.alquilervehiculosfront.datos.configuracion.AppComponent;
import com.example.alquilervehiculosfront.datos.configuracion.DaggerAppComponent;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioGet;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioPost;
import com.example.alquilervehiculosfront.dominio.repositorio.RepositorioAlquiler;
import com.example.alquilervehiculosfront.dominio.modelo.AlquilarVehiculo;

import javax.inject.Inject;

public class ServicioAlquilerDominio {

    @Inject
    public RepositorioAlquiler repositorioAlquiler;

    public ServicioAlquilerDominio() {
        AppComponent appComponent = DaggerAppComponent.builder().build();
        appComponent.inject(this);
    }

    public MutableLiveData<RespuestaServicioPost> alquilar(AlquilarVehiculo alquilarVehiculo) {
        return repositorioAlquiler.alquilar(alquilarVehiculo);
    }

    public MutableLiveData<RespuestaServicioGet> devolver(String placaVehiculo) {
        return repositorioAlquiler.devolver(placaVehiculo);
    }
}