package com.example.alquilervehiculosfront.dominio.servicios;

import androidx.lifecycle.MutableLiveData;

import com.example.alquilervehiculosfront.datos.configuracion.AppComponent;
import com.example.alquilervehiculosfront.datos.configuracion.DaggerAppComponent;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioGet;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioPost;
import com.example.alquilervehiculosfront.dominio.modelo.Vehiculo;
import com.example.alquilervehiculosfront.dominio.repositorio.RepositorioVehiculo;

import javax.inject.Inject;

public class ServicioVehiculoDominio {

    @Inject
    public RepositorioVehiculo repositorioVehiculo;

    public ServicioVehiculoDominio() {
        AppComponent appComponent = DaggerAppComponent.builder().build();
        appComponent.inject(this);
    }

    public MutableLiveData<RespuestaServicioPost> registrar(Vehiculo vehiculo) {
        return repositorioVehiculo.registrar(vehiculo);
    }

    public MutableLiveData<RespuestaServicioGet> buscar(String placaVehiculo) {
        return repositorioVehiculo.buscar(placaVehiculo);
    }
}