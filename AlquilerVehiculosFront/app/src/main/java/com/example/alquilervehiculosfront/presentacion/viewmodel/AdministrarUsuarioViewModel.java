package com.example.alquilervehiculosfront.presentacion.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicio;
import com.example.alquilervehiculosfront.dominio.modelo.Usuario;
import com.example.alquilervehiculosfront.dominio.servicios.ServicioUsuarioDominio;

public class AdministrarUsuarioViewModel extends ViewModel {

    private ServicioUsuarioDominio servicioUsuarioDominio;
    private MutableLiveData<RespuestaServicio> mutableLiveData;

    public void registrar(Long cedulaUsuario, String nombresUsuario, String apellidosUsuario, String fechaNacimientoUsuario) {
        servicioUsuarioDominio = new ServicioUsuarioDominio();
        Usuario usuario = new Usuario(cedulaUsuario, nombresUsuario, apellidosUsuario, fechaNacimientoUsuario);
        mutableLiveData = servicioUsuarioDominio.registrar(usuario);
    }

    public LiveData<RespuestaServicio> getResult() {
        return mutableLiveData;
    }
}
