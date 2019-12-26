package com.ceiba.alquilervehiculos.aplicacion.manejadores.vehiculo;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.alquilervehiculos.aplicacion.comando.ComandoVehiculo;
import com.ceiba.alquilervehiculos.aplicacion.fabrica.FabricaVehiculo;
import com.ceiba.alquilervehiculos.dominio.modelo.Vehiculo;
import com.ceiba.alquilervehiculos.dominio.servicio.vehiculo.ServicioCrearVehiculo;

@Component
public class ManejadorCrearVehiculo {

	private final FabricaVehiculo fabricaVehiculo;
	private final ServicioCrearVehiculo servicioCrearVehiculo;

	public ManejadorCrearVehiculo(FabricaVehiculo fabricaVehiculo, ServicioCrearVehiculo servicioCrearVehiculo) {
		this.fabricaVehiculo = fabricaVehiculo;
		this.servicioCrearVehiculo = servicioCrearVehiculo;
	}

	@Transactional
	public void registrar(ComandoVehiculo comandoVehiculo) {
		Vehiculo vehiculo = fabricaVehiculo.crearVehiculo(comandoVehiculo);
		servicioCrearVehiculo.registrar(vehiculo);
	}
}