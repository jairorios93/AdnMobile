package com.ceiba.alquilervehiculos.databuilder;

import java.util.Calendar;
import java.util.Date;

import com.ceiba.alquilervehiculos.dominio.modelo.AlquilarVehiculo;
import com.ceiba.alquilervehiculos.dominio.modelo.Usuario;
import com.ceiba.alquilervehiculos.dominio.modelo.Vehiculo;

public class AlquilarVehiculoDataBuilder {

	private Long id;

	private Usuario usuario;

	private Vehiculo vehiculo;

	private Date fechaInicio;

	private Date fechaFin;

	private boolean estado;

	private double valor;

	public AlquilarVehiculoDataBuilder() {
		usuario = new UsuarioDataBuilder().build();
		vehiculo = new VehiculoDataBuilder().build();
		fechaInicio = Calendar.getInstance().getTime();
		fechaFin = Calendar.getInstance().getTime();
		estado = true;
		valor = 1050000;
		id = 1L;
	}

	public AlquilarVehiculo build() {
		return new AlquilarVehiculo(id, usuario, vehiculo, fechaInicio, fechaFin, estado, valor);
	}
}
