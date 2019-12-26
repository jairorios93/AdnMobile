package com.ceiba.alquilervehiculos.aplicacion.comando;

import java.util.Date;

public class ComandoAlquilarVehiculo {

	private Long id;

	private ComandoUsuario usuario;

	private ComandoVehiculo vehiculo;

	private Date fechaInicio;

	private Date fechaFin;

	private boolean estado;

	private double valor;

	public ComandoAlquilarVehiculo() {
	}

	public ComandoAlquilarVehiculo(Long id, ComandoUsuario usuario, ComandoVehiculo vehiculo, Date fechaInicio,
			Date fechaFin, boolean estado, double valor) {
		this.id = id;
		this.usuario = usuario;
		this.vehiculo = vehiculo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estado = estado;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public ComandoUsuario getUsuario() {
		return usuario;
	}

	public ComandoVehiculo getVehiculo() {
		return vehiculo;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public boolean isEstado() {
		return estado;
	}

	public double getValor() {
		return valor;
	}

}