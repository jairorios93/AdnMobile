package com.example.alquilervehiculosfront.presentacion.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioGet;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioPost;
import com.example.alquilervehiculosfront.dominio.modelo.Usuario;
import com.example.alquilervehiculosfront.dominio.servicios.ServicioUsuarioDominio;

public class AdministrarUsuarioViewModel extends ViewModel {

    private ServicioUsuarioDominio servicioUsuarioDominio;
    private MutableLiveData<RespuestaServicioPost> mutableLiveDataPost;
    private MutableLiveData<RespuestaServicioGet> mutableLiveDataGet;

    public void registrar(Long cedulaUsuario, String nombresUsuario, String apellidosUsuario, String fechaNacimientoUsuario) {
        servicioUsuarioDominio = new ServicioUsuarioDominio();
        Usuario usuario = new Usuario(cedulaUsuario, nombresUsuario, apellidosUsuario, fechaNacimientoUsuario);
        mutableLiveDataPost = servicioUsuarioDominio.registrar(usuario);
    }

    public void buscar(Long cedulaUsuario){
        servicioUsuarioDominio = new ServicioUsuarioDominio();
        mutableLiveDataGet = servicioUsuarioDominio.buscar(cedulaUsuario);
    }

    public LiveData<RespuestaServicioPost> getResultPost() {
        return mutableLiveDataPost;
    }

    public LiveData<RespuestaServicioGet> getResultGet() {
        return mutableLiveDataGet;
    }
}