package com.example.alquilervehiculosfront.dominio.repositorio;

import androidx.lifecycle.MutableLiveData;

import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioGet;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioPost;
import com.example.alquilervehiculosfront.dominio.modelo.Vehiculo;

public interface RepositorioVehiculo {

    MutableLiveData<RespuestaServicioPost> registrar(Vehiculo vehiculo);

    MutableLiveData<RespuestaServicioGet> buscar(String placaVehiculo);
}