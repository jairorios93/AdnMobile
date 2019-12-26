package com.ceiba.alquilervehiculos.infrastructura.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Usuarios")
public class UsuarioEntidad {

	@Id
	@Column(name = "cedula")
	private Long cedula;

	@Column(name = "nombres")
	private String nombres;

	@Column(name = "apellidos")
	private String apellidos;

	@Column(name = "fechaNacimiento")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;

	public UsuarioEntidad() {
	}

	public UsuarioEntidad(Long cedula, String nombres, String apellidos, Date fechaNacimiento) {
		this.cedula = cedula;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
	}

	public Long getCedula() {
		return cedula;
	}

	public void setCedula(Long cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

}