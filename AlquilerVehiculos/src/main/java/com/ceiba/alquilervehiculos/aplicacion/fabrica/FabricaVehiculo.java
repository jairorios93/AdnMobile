package com.ceiba.alquilervehiculos.aplicacion.fabrica;

import org.springframework.stereotype.Component;

import com.ceiba.alquilervehiculos.aplicacion.comando.ComandoVehiculo;
import com.ceiba.alquilervehiculos.dominio.modelo.Vehiculo;

@Component
public class FabricaVehiculo {

	public Vehiculo crearVehiculo(ComandoVehiculo comandoVehiculo) {
		return new Vehiculo(comandoVehiculo.getPlaca(), comandoVehiculo.getModelo(), comandoVehiculo.getMarca(),
				comandoVehiculo.getColor(), comandoVehiculo.getPrecio());
	}
}