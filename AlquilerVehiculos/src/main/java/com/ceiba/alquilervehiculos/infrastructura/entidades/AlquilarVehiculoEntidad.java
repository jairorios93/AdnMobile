package com.ceiba.alquilervehiculos.infrastructura.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "AlquilerVehiculos")
public class AlquilarVehiculoEntidad {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private UsuarioEntidad usuario;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private VehiculoEntidad vehiculo;

	@Column(name = "fechaInicio")
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;

	@Column(name = "fechaFin")
	@Temporal(TemporalType.DATE)
	private Date fechaFin;

	@Column(name = "estado")
	private boolean estado;

	@Column(name = "valor")
	private double valor;

	public AlquilarVehiculoEntidad() {
	}

	public AlquilarVehiculoEntidad(UsuarioEntidad usuario, VehiculoEntidad vehiculo, Date fechaInicio, Date fechaFin,
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

	public void setId(Long id) {
		this.id = id;
	}

	public UsuarioEntidad getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntidad usuario) {
		this.usuario = usuario;
	}

	public VehiculoEntidad getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(VehiculoEntidad vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
