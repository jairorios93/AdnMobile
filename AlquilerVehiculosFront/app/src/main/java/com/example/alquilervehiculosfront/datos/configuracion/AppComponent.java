package com.example.alquilervehiculosfront.datos.configuracion;

import com.example.alquilervehiculosfront.dominio.repositorio.RepositorioAlquiler;
import com.example.alquilervehiculosfront.dominio.repositorio.RepositorioUsuario;
import com.example.alquilervehiculosfront.dominio.repositorio.RepositorioVehiculo;
import com.example.alquilervehiculosfront.dominio.servicios.ServicioAlquilerDominio;
import com.example.alquilervehiculosfront.dominio.servicios.ServicioUsuarioDominio;
import com.example.alquilervehiculosfront.dominio.servicios.ServicioVehiculoDominio;
import com.example.alquilervehiculosfront.presentacion.actividades.MainActivity;

import dagger.Component;

@Component(
        modules = {
                AppModule.class, ConfiguracionModulos.class
        })
public interface AppComponent {

    void inject(ServicioUsuarioDominio servicioUsuarioDominio);

    void inject(ServicioVehiculoDominio servicioVehiculoDominio);

    void inject(ServicioAlquilerDominio servicioAlquilerDominio);

    RepositorioUsuario repositorioUsuario();

    RepositorioVehiculo repositorioVehiculo();

    RepositorioAlquiler repositorioAlquiler();
}
