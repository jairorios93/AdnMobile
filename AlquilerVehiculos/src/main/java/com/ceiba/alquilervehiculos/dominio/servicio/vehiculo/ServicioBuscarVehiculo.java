package com.ceiba.alquilervehiculos.dominio.servicio.vehiculo;

import com.ceiba.alquilervehiculos.dominio.modelo.dto.VehiculoDTO;
import com.ceiba.alquilervehiculos.dominio.puerto.repositorio.RepositorioVehiculo;

public class ServicioBuscarVehiculo {

	private RepositorioVehiculo repositorioVehiculo;

	public ServicioBuscarVehiculo(RepositorioVehiculo repositorioVehiculo) {
		this.repositorioVehiculo = repositorioVehiculo;
	}

	public VehiculoDTO buscar(String placa) {
		return repositorioVehiculo.buscar(placa);
	}
}