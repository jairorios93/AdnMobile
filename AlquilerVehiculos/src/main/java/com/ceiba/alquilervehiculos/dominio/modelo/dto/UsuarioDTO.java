package com.ceiba.alquilervehiculos.dominio.modelo.dto;

import java.util.Date;

public class UsuarioDTO {

	private Long cedula;

	private String nombres;

	private String apellidos;

	private Date fechaNacimiento;

	public UsuarioDTO() {
	}

	public UsuarioDTO(Long cedula, String nombres, String apellidos, Date fechaNacimiento) {
		this.cedula = cedula;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setCedula(Long cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public Long getCedula() {
		return cedula;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

}
