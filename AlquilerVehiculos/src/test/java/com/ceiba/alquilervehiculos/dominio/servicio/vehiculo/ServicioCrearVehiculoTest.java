package com.ceiba.alquilervehiculos.dominio.servicio.vehiculo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import com.ceiba.alquilervehiculos.databuilder.VehiculoDataBuilder;
import com.ceiba.alquilervehiculos.dominio.modelo.Vehiculo;
import com.ceiba.alquilervehiculos.dominio.puerto.repositorio.RepositorioVehiculo;

public class ServicioCrearVehiculoTest {

	@Test
	void crearVehiculo() {
		Vehiculo vehiculo = new VehiculoDataBuilder().build();
		RepositorioVehiculo repositorioVehiculo = mock(RepositorioVehiculo.class);
		ServicioCrearVehiculo service = new ServicioCrearVehiculo(repositorioVehiculo);

		assertDoesNotThrow(() -> service.registrar(vehiculo));
	}
}
