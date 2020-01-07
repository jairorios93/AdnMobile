package com.example.alquilervehiculosfront.presentacion.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioGet;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioPost;
import com.example.alquilervehiculosfront.dominio.modelo.Vehiculo;
import com.example.alquilervehiculosfront.dominio.servicios.ServicioVehiculoDominio;

public class AdministrarVehiculoViewModel extends ViewModel {

    private ServicioVehiculoDominio servicioVehiculoDominio;
    private MutableLiveData<RespuestaServicioPost> mutableLiveDataPost;
    private MutableLiveData<RespuestaServicioGet> mutableLiveDataGet;

    public void registrar(String placa, String modelo, String marca, String color, double precio) {
        servicioVehiculoDominio = new ServicioVehiculoDominio();
        Vehiculo vehiculo = new Vehiculo(placa, modelo, marca, color, precio);
        mutableLiveDataPost = servicioVehiculoDominio.registrar(vehiculo);
    }

    public void buscar(String placaVehiculo) {
        servicioVehiculoDominio = new ServicioVehiculoDominio();
        mutableLiveDataGet = servicioVehiculoDominio.buscar(placaVehiculo);
    }

    public LiveData<RespuestaServicioPost> getResultadoPost() {
        return mutableLiveDataPost;
    }

    public LiveData<RespuestaServicioGet> getResultadoGet() {
        return mutableLiveDataGet;
    }
}