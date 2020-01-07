package com.example.alquilervehiculosfront.presentacion.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioGet;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioPost;
import com.example.alquilervehiculosfront.dominio.modelo.AlquilarVehiculo;
import com.example.alquilervehiculosfront.dominio.modelo.Usuario;
import com.example.alquilervehiculosfront.dominio.modelo.Vehiculo;
import com.example.alquilervehiculosfront.dominio.servicios.ServicioAlquilerDominio;

public class AdministrarAlquilerViewModel extends ViewModel {

    private ServicioAlquilerDominio servicioAlquilerDominio;
    private MutableLiveData<RespuestaServicioPost> mutableLiveDataPost;
    private MutableLiveData<RespuestaServicioGet> mutableLiveDataGet;

    public void alquilar(Usuario usuario, Vehiculo vehiculo, String fechaInicio, String fechaFin, boolean estado, double valor) {
        servicioAlquilerDominio = new ServicioAlquilerDominio();
        AlquilarVehiculo alquilarVehiculo = new AlquilarVehiculo(usuario, vehiculo, fechaInicio, fechaFin, estado, valor);
        mutableLiveDataPost = servicioAlquilerDominio.alquilar(alquilarVehiculo);
    }

    public void devolver(String placaVehiculo) {
        servicioAlquilerDominio = new ServicioAlquilerDominio();
        mutableLiveDataGet = servicioAlquilerDominio.devolver(placaVehiculo);
    }

    public LiveData<RespuestaServicioPost> getResultadoPost() {
        return mutableLiveDataPost;
    }

    public LiveData<RespuestaServicioGet> getResultadoGet() {
        return mutableLiveDataGet;
    }
}