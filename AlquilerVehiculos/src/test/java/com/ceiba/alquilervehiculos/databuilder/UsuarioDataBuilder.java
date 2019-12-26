package com.ceiba.alquilervehiculos.databuilder;

import java.util.Calendar;
import java.util.Date;

import com.ceiba.alquilervehiculos.dominio.modelo.Usuario;

public class UsuarioDataBuilder {

	private Long cedula;

	private String nombres;

	private String apellidos;

	private Date fechaNacimiento;

	public UsuarioDataBuilder() {
		cedula = Long.valueOf("10949351");
		nombres = "Jairo Andres";
		apellidos = "Rios Franco";
		fechaNacimiento = Calendar.getInstance().getTime();
	}
	
	public Usuario build() {
		return new Usuario(cedula, nombres, apellidos, fechaNacimiento);
	}
}
