package com.example.alquilervehiculosfront.dominio.servicios;

import androidx.lifecycle.MutableLiveData;

import com.example.alquilervehiculosfront.datos.repositorioimpl.RepositorioAlquilerImpl;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioGet;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioPost;
import com.example.alquilervehiculosfront.dominio.repositorio.RepositorioAlquiler;
import com.example.alquilervehiculosfront.dominio.modelo.AlquilarVehiculo;

public class ServicioAlquilerDominio {

    private RepositorioAlquiler repositorioAlquiler;

    public ServicioAlquilerDominio() {
        repositorioAlquiler = new RepositorioAlquilerImpl();
    }

    public MutableLiveData<RespuestaServicioPost> alquilar(AlquilarVehiculo alquilarVehiculo) {
        return repositorioAlquiler.alquilar(alquilarVehiculo);
    }

    public MutableLiveData<RespuestaServicioGet> devolver(String placaVehiculo) {
        return repositorioAlquiler.devolver(placaVehiculo);
    }
}