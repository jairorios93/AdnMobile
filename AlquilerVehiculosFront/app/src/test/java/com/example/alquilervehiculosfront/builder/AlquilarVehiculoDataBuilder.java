package com.example.alquilervehiculosfront.builder;

import com.example.alquilervehiculosfront.dominio.modelo.AlquilarVehiculo;
import com.example.alquilervehiculosfront.dominio.modelo.Usuario;
import com.example.alquilervehiculosfront.dominio.modelo.Vehiculo;

public class AlquilarVehiculoDataBuilder {

    private Usuario usuario;

    private Vehiculo vehiculo;

    private String fechaInicio;

    private String fechaFin;

    private boolean estado;

    private double valor;

    public AlquilarVehiculoDataBuilder() {
        usuario = new UsuarioDataBuilder().build();
        vehiculo = new VehiculoDataBuilder().build();
        fechaInicio = "2019-12-27";
        fechaFin = "2019-12-27";
        estado = true;
        valor = 1050000;
    }

    public AlquilarVehiculo build() {
        return new AlquilarVehiculo(usuario, vehiculo, fechaInicio, fechaFin, estado, valor);
    }
}