package com.ceiba.alquilervehiculos.databuilder;

import java.util.Calendar;
import java.util.Date;

import com.ceiba.alquilervehiculos.infrastructura.entidades.UsuarioEntidad;

public class UsuarioEntidadDataBuilder {

	private Long cedula;

	private String nombres;

	private String apellidos;

	private Date fechaNacimiento;

	public UsuarioEntidadDataBuilder() {
		cedula = Long.valueOf("1094935130");
		nombres = "Jairo Andres";
		apellidos = "Rios Franco";
		fechaNacimiento = Calendar.getInstance().getTime();
	}

	public UsuarioEntidad build() {
		return new UsuarioEntidad(cedula, nombres, apellidos, fechaNacimiento);
	}
}
