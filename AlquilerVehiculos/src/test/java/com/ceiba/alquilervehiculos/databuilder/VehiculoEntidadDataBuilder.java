package com.ceiba.alquilervehiculos.databuilder;

import com.ceiba.alquilervehiculos.infrastructura.entidades.VehiculoEntidad;

public class VehiculoEntidadDataBuilder {

	private String placa;

	private String modelo;

	private String marca;

	private String color;

	private double precio;

	public VehiculoEntidadDataBuilder() {
		placa = "ASF12";
		modelo = "Z3";
		marca = "BMW";
		color = "Gris";
		precio = 45000;
	}

	public VehiculoEntidad build() {
		return new VehiculoEntidad(placa, modelo, marca, color, precio);
	}
}
