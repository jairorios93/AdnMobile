package com.ceiba.alquilervehiculos.databuilder;

import com.ceiba.alquilervehiculos.dominio.modelo.dto.VehiculoDTO;

public class VehiculoDTODataBuilder {

	private Long id;

	private String placa;

	private String modelo;

	private String marca;

	private String color;

	private double precio;

	public VehiculoDTODataBuilder() {
		placa = "ASF13";
		modelo = "Z3";
		marca = "BMW";
		color = "Gris";
		precio = 45000;
		id = 1L;
	}

	public VehiculoDTO build() {
		return new VehiculoDTO(id, placa, modelo, marca, color, precio);
	}
}
