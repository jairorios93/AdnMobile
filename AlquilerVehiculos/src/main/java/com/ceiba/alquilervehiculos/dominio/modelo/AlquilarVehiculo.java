package com.ceiba.alquilervehiculos.dominio.modelo;

import java.util.Date;

public class AlquilarVehiculo {

	private Long id;

	private Usuario usuario;

	private Vehiculo vehiculo;

	private Date fechaInicio;

	private Date fechaFin;

	private boolean estado;

	private double valor;

	public AlquilarVehiculo() {
	}

	public AlquilarVehiculo(Long id, Usuario usuario, Vehiculo vehiculo, Date fechaInicio, Date fechaFin,
			boolean estado, double valor) {
		this.id = id;
		this.usuario = usuario;
		this.vehiculo = vehiculo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estado = estado;
		this.valor = valor;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getValor() {
		return valor;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

}