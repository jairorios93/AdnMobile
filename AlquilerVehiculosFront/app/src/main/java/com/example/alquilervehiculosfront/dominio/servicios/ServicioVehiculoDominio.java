package com.example.alquilervehiculosfront.dominio.servicios;

import androidx.lifecycle.MutableLiveData;

import com.example.alquilervehiculosfront.datos.repositorioimpl.RepositorioVehiculoImpl;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioGet;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioPost;
import com.example.alquilervehiculosfront.dominio.modelo.Vehiculo;
import com.example.alquilervehiculosfront.dominio.repositorio.RepositorioVehiculo;

public class ServicioVehiculoDominio {

    private RepositorioVehiculo repositorioVehiculo;

    public ServicioVehiculoDominio() {
        repositorioVehiculo = new RepositorioVehiculoImpl();
    }

    public MutableLiveData<RespuestaServicioPost> registrar(Vehiculo vehiculo) {
        return repositorioVehiculo.registrar(vehiculo);
    }

    public MutableLiveData<RespuestaServicioGet> buscar(String placaVehiculo) {
        return repositorioVehiculo.buscar(placaVehiculo);
    }
}