package com.ceiba.alquilervehiculos.dominio.servicio.vehiculo;

import com.ceiba.alquilervehiculos.dominio.excepciones.ExcepcionNegocio;
import com.ceiba.alquilervehiculos.dominio.modelo.Vehiculo;
import com.ceiba.alquilervehiculos.dominio.modelo.dto.VehiculoDTO;
import com.ceiba.alquilervehiculos.dominio.puerto.repositorio.RepositorioVehiculo;

public class ServicioCrearVehiculo {

	private RepositorioVehiculo repositorioVehiculo;

	private static final String VEHICULO_EXISTE = "Ya existe un vehiculo con la placa ";

	public ServicioCrearVehiculo(RepositorioVehiculo repositorioVehiculo) {
		this.repositorioVehiculo = repositorioVehiculo;
	}

	public void registrar(Vehiculo vehiculo) {
		VehiculoDTO vehiculoDTO = repositorioVehiculo.buscar(vehiculo.getPlaca());
		if (vehiculoDTO != null) {
			throw new ExcepcionNegocio(VEHICULO_EXISTE + vehiculo.getPlaca());
		} else {
			repositorioVehiculo.registrar(vehiculo);
		}
	}
}