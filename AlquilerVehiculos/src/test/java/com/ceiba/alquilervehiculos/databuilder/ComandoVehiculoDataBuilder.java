package com.ceiba.alquilervehiculos.databuilder;

import com.ceiba.alquilervehiculos.aplicacion.comando.ComandoVehiculo;

public class ComandoVehiculoDataBuilder {

	private String placa;

	private String modelo;

	private String marca;

	private String color;

	private double precio;

	public ComandoVehiculoDataBuilder() {
		placa = "ASF13";
		modelo = "Z3";
		marca = "BMW";
		color = "Gris";
		precio = 45000;
	}

	public ComandoVehiculo build() {
		return new ComandoVehiculo(placa, modelo, marca, color, precio);
	}

	public ComandoVehiculo conPlaca(String placa) {
		return new ComandoVehiculo(placa, modelo, marca, color, precio);
	}
	
	public ComandoVehiculo conId(Long id) {
		return new ComandoVehiculo(placa, modelo, marca, color, precio);
	}
}