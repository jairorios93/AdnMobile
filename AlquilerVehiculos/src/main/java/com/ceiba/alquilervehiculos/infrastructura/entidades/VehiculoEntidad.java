package com.ceiba.alquilervehiculos.infrastructura.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Vehiculos")
public class VehiculoEntidad {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "placa")
	private String placa;

	@Column(name = "modelo")
	private String modelo;

	@Column(name = "marca")
	private String marca;

	@Column(name = "color")
	private String color;

	@Column(name = "precio")
	private double precio;

	public VehiculoEntidad() {
	}

	public VehiculoEntidad(String placa, String modelo, String marca, String color, double precio) {
		this.placa = placa;
		this.modelo = modelo;
		this.marca = marca;
		this.color = color;
		this.precio = precio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

}