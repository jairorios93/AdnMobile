package com.ceiba.alquilervehiculos.aplicacion.fabrica;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ceiba.alquilervehiculos.aplicacion.comando.ComandoAlquilarVehiculo;
import com.ceiba.alquilervehiculos.dominio.modelo.AlquilarVehiculo;
import com.ceiba.alquilervehiculos.dominio.modelo.Usuario;
import com.ceiba.alquilervehiculos.dominio.modelo.Vehiculo;

@Component
public class FabricaAlquilarVehiculo {

	private ModelMapper modelMapper = new ModelMapper();

	public AlquilarVehiculo crearAlquiler(ComandoAlquilarVehiculo comandoAlquilarVehiculo) {
		return new AlquilarVehiculo(comandoAlquilarVehiculo.getId(),
				modelMapper.map(comandoAlquilarVehiculo.getUsuario(), Usuario.class),
				modelMapper.map(comandoAlquilarVehiculo.getVehiculo(), Vehiculo.class),
				comandoAlquilarVehiculo.getFechaInicio(), comandoAlquilarVehiculo.getFechaFin(),
				comandoAlquilarVehiculo.isEstado(), comandoAlquilarVehiculo.getValor());
	}
}
