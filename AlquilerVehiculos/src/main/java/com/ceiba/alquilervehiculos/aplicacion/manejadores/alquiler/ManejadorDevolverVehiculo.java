package com.ceiba.alquilervehiculos.aplicacion.manejadores.alquiler;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.alquilervehiculos.dominio.servicio.alquiler.ServicioDevolverVehiculo;

@Component
public class ManejadorDevolverVehiculo {

	private final ServicioDevolverVehiculo servicioDevolverVehiculo;

	public ManejadorDevolverVehiculo(ServicioDevolverVehiculo servicioDevolverVehiculo) {
		this.servicioDevolverVehiculo = servicioDevolverVehiculo;
	}

	@Transactional
	public void devolver(String placa) {
		servicioDevolverVehiculo.devolver(placa);
	}
}
