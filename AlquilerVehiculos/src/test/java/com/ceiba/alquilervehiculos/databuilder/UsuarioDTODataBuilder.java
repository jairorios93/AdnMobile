package com.ceiba.alquilervehiculos.databuilder;

import java.util.Calendar;
import java.util.Date;

import com.ceiba.alquilervehiculos.dominio.modelo.dto.UsuarioDTO;

public class UsuarioDTODataBuilder {

	private Long cedula;

	private String nombres;

	private String apellidos;

	private Date fechaNacimiento;

	public UsuarioDTODataBuilder() {
		cedula = Long.valueOf("109493513");
		nombres = "Jairo Andres";
		apellidos = "Rios Franco";
		fechaNacimiento = Calendar.getInstance().getTime();
	}

	public UsuarioDTO build() {
		return new UsuarioDTO(cedula, nombres, apellidos, fechaNacimiento);
	}
}
