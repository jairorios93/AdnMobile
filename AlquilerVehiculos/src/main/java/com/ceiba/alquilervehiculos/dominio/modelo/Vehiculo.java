package com.ceiba.alquilervehiculos.dominio.modelo;

public class Vehiculo {

	private Long id;

	private String placa;

	private String modelo;

	private String marca;

	private String color;

	private double precio;

	public Vehiculo() {
	}

	public Vehiculo(String placa, String modelo, String marca, String color, double precio) {
		this.placa = placa;
		this.modelo = modelo;
		this.marca = marca;
		this.color = color;
		this.precio = precio;
	}

	public String getPlaca() {
		return placa;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getColor() {
		return color;
	}

	public double getPrecio() {
		return precio;
	}

}