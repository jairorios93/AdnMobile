package com.ceiba.alquilervehiculos.dominio.servicio.vehiculo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.ceiba.alquilervehiculos.databuilder.VehiculoDTODataBuilder;
import com.ceiba.alquilervehiculos.dominio.modelo.dto.VehiculoDTO;
import com.ceiba.alquilervehiculos.dominio.puerto.repositorio.RepositorioVehiculo;

public class ServicioBuscarVehiculoTest {

	@Test
	void obtenerVehiculo() {
		RepositorioVehiculo repositorioVehiculo = mock(RepositorioVehiculo.class);
		VehiculoDTO vehiculoDTO = new VehiculoDTODataBuilder().build();

		Mockito.when(repositorioVehiculo.buscar("ASF13")).thenReturn(vehiculoDTO);
		ServicioBuscarVehiculo service = new ServicioBuscarVehiculo(repositorioVehiculo);

		VehiculoDTO resultado = service.buscar("ASF13");

		assertEquals(resultado.getPlaca(), vehiculoDTO.getPlaca());
	}
}
