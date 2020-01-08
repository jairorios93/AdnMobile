package com.example.alquilervehiculosfront.dominio.inyeccion;

import com.example.alquilervehiculosfront.datos.repositorioimpl.RepositorioAlquilerImpl;
import com.example.alquilervehiculosfront.datos.repositorioimpl.RepositorioUsuarioImpl;
import com.example.alquilervehiculosfront.datos.repositorioimpl.RepositorioVehiculoImpl;
import com.example.alquilervehiculosfront.dominio.repositorio.RepositorioAlquiler;
import com.example.alquilervehiculosfront.dominio.repositorio.RepositorioUsuario;
import com.example.alquilervehiculosfront.dominio.repositorio.RepositorioVehiculo;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ConfiguracionModulos {

    @Binds
    abstract RepositorioUsuario repositorioUsuario(RepositorioUsuarioImpl repositorioUsuarioImpl);

    @Binds
    abstract RepositorioVehiculo repositorioVehiculo(RepositorioVehiculoImpl repositorioVehiculoImpl);

    @Binds
    abstract RepositorioAlquiler repositorioAlquiler(RepositorioAlquilerImpl repositorioAlquilerImpl);
}