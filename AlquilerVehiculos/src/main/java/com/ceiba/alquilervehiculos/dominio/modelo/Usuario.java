package com.ceiba.alquilervehiculos.dominio.modelo;

import java.util.Date;

public class Usuario {

	private Long cedula;

	private String nombres;

	private String apellidos;

	private Date fechaNacimiento;

	public Usuario() {
	}

	public Usuario(Long cedula, String nombres, String apellidos, Date fechaNacimiento) {
		this.cedula = cedula;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setCedula(Long cedula) {
		this.cedula = cedula;
	}

	public Long getCedula() {
		return cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getApellidos() {
		return apellidos;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
}