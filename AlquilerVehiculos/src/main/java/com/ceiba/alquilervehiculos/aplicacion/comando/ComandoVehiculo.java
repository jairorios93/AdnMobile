package com.ceiba.alquilervehiculos.aplicacion.comando;

public class ComandoVehiculo {

	private String placa;

	private String modelo;

	private String marca;

	private String color;

	private double precio;

	public ComandoVehiculo() {
	}

	public ComandoVehiculo(String placa, String modelo, String marca, String color, double precio) {
		this.placa = placa;
		this.modelo = modelo;
		this.marca = marca;
		this.color = color;
		this.precio = precio;
	}

	public String getPlaca() {
		return placa;
	}

	public String getModelo() {
		return modelo;
	}

	public String getMarca() {
		return marca;
	}

	public String getColor() {
		return color;
	}

	public double getPrecio() {
		return precio;
	}

}