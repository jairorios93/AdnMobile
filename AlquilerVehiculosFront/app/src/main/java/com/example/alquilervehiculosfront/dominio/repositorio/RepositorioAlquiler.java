package com.example.alquilervehiculosfront.dominio.repositorio;

import androidx.lifecycle.MutableLiveData;

import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioGet;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioPost;
import com.example.alquilervehiculosfront.dominio.modelo.AlquilarVehiculo;

public interface RepositorioAlquiler {

    MutableLiveData<RespuestaServicioPost> alquilar(AlquilarVehiculo alquilarVehiculo);

    MutableLiveData<RespuestaServicioGet> devolver(String placaVehiculo);
}