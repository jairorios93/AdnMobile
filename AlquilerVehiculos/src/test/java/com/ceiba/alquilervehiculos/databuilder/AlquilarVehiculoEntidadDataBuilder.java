package com.ceiba.alquilervehiculos.databuilder;

import java.util.Calendar;
import java.util.Date;

import com.ceiba.alquilervehiculos.infrastructura.entidades.AlquilarVehiculoEntidad;
import com.ceiba.alquilervehiculos.infrastructura.entidades.UsuarioEntidad;
import com.ceiba.alquilervehiculos.infrastructura.entidades.VehiculoEntidad;

public class AlquilarVehiculoEntidadDataBuilder {

	private UsuarioEntidad usuario;

	private VehiculoEntidad vehiculo;

	private Date fechaInicio;

	private Date fechaFin;

	private boolean estado;

	private double valor;

	public AlquilarVehiculoEntidadDataBuilder() {
		usuario = new UsuarioEntidadDataBuilder().build();
		vehiculo = new VehiculoEntidadDataBuilder().build();
		fechaInicio = Calendar.getInstance().getTime();
		fechaFin = Calendar.getInstance().getTime();
		estado = true;
		valor = 10500000;
	}

	public AlquilarVehiculoEntidad build() {
		return new AlquilarVehiculoEntidad(usuario, vehiculo, fechaInicio, fechaFin, estado, valor);
	}

}
