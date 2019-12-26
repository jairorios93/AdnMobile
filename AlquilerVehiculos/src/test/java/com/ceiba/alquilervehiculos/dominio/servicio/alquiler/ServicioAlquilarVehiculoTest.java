package com.ceiba.alquilervehiculos.dominio.servicio.alquiler;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import com.ceiba.alquilervehiculos.databuilder.AlquilarVehiculoDataBuilder;
import com.ceiba.alquilervehiculos.dominio.modelo.AlquilarVehiculo;
import com.ceiba.alquilervehiculos.dominio.puerto.repositorio.RepositorioAlquilarVehiculo;
import com.ceiba.alquilervehiculos.dominio.puerto.repositorio.RepositorioVehiculo;

public class ServicioAlquilarVehiculoTest {

	@Test
	void alquilarVehiculo() {
		AlquilarVehiculo alquilarVehiculo = new AlquilarVehiculoDataBuilder().build();
		RepositorioAlquilarVehiculo repositorioAlquilarVehiculo = mock(RepositorioAlquilarVehiculo.class);
		RepositorioVehiculo repositorioVehiculo = mock(RepositorioVehiculo.class);

		ServicioAlquilarVehiculo service = new ServicioAlquilarVehiculo(repositorioAlquilarVehiculo,
				repositorioVehiculo);
		assertDoesNotThrow(() -> service.alquilar(alquilarVehiculo));
	}
}
