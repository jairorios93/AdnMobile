package com.ceiba.alquilervehiculos.dominio.modelo.dto;

import java.util.Date;

public class AlquilarVehiculoDTO {

	private Long id;

	private UsuarioDTO usuario;

	private VehiculoDTO vehiculo;

	private Date fechaInicio;

	private Date fechaFin;

	private boolean estado;

	private double valor;

	public AlquilarVehiculoDTO() {
	}

	public AlquilarVehiculoDTO(UsuarioDTO usuario, VehiculoDTO vehiculo, Date fechaInicio, Date fechaFin,
			boolean estado, double valor) {
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

	public VehiculoDTO getVehiculo() {
		return vehiculo;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVehiculo(VehiculoDTO vehiculo) {
		this.vehiculo = vehiculo;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getValor() {
		return valor;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}
