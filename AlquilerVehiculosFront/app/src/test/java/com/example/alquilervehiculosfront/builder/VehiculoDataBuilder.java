package com.example.alquilervehiculosfront.builder;

import com.example.alquilervehiculosfront.dominio.modelo.Vehiculo;

public class VehiculoDataBuilder {

    private String placa;

    private String modelo;

    private String marca;

    private String color;

    private double precio;

    public VehiculoDataBuilder() {
        placa = "ASF12";
        modelo = "Z3";
        marca = "BMW";
        color = "Gris";
        precio = 45000;
    }

    public Vehiculo build() {
        return new Vehiculo(placa, modelo, marca, color, precio);
    }
}