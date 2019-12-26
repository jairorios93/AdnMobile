package com.ceiba.alquilervehiculos.aplicacion.manejadores.alquiler;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.alquilervehiculos.aplicacion.comando.ComandoAlquilarVehiculo;
import com.ceiba.alquilervehiculos.aplicacion.fabrica.FabricaAlquilarVehiculo;
import com.ceiba.alquilervehiculos.dominio.modelo.AlquilarVehiculo;
import com.ceiba.alquilervehiculos.dominio.servicio.alquiler.ServicioAlquilarVehiculo;

@Component
public class ManejadorAlquilarVehiculo {

	private final FabricaAlquilarVehiculo fabricaAlquilarVehiculo;
	private final ServicioAlquilarVehiculo servicioAlquilarVehiculo;

	public ManejadorAlquilarVehiculo(FabricaAlquilarVehiculo fabricaAlquilarVehiculo,
			ServicioAlquilarVehiculo servicioAlquilarVehiculo) {
		this.fabricaAlquilarVehiculo = fabricaAlquilarVehiculo;
		this.servicioAlquilarVehiculo = servicioAlquilarVehiculo;
	}

	@Transactional
	public void alquilar(ComandoAlquilarVehiculo comandoAlquilarVehiculo) {
		AlquilarVehiculo alquilarVehiculo = fabricaAlquilarVehiculo.crearAlquiler(comandoAlquilarVehiculo);
		servicioAlquilarVehiculo.alquilar(alquilarVehiculo);
	}
}
