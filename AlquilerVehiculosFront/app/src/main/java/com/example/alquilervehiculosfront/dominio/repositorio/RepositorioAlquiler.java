package com.example.alquilervehiculosfront.dominio.repositorio;

import com.example.alquilervehiculosfront.dominio.modelo.AlquilarVehiculo;

public interface RepositorioAlquiler {

    void alquilar(AlquilarVehiculo alquilarVehiculo);
    void devolver(String placa);
}
