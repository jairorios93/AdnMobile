package com.ceiba.alquilervehiculos.infrastructura.adaptador.repositorio;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import com.ceiba.alquilervehiculos.dominio.modelo.Vehiculo;
import com.ceiba.alquilervehiculos.dominio.modelo.dto.VehiculoDTO;
import com.ceiba.alquilervehiculos.dominio.puerto.repositorio.RepositorioVehiculo;
import com.ceiba.alquilervehiculos.infrastructura.VehiculoRepositorioJPA;
import com.ceiba.alquilervehiculos.infrastructura.entidades.VehiculoEntidad;

@Repository
public class RepositorioVehiculoPersistente implements RepositorioVehiculo {

	private final VehiculoRepositorioJPA vehiculoRepositorioJPA;
	private ModelMapper modelMapper = new ModelMapper();

	public RepositorioVehiculoPersistente(VehiculoRepositorioJPA vehiculoRepositorioJPA) {
		this.vehiculoRepositorioJPA = vehiculoRepositorioJPA;
	}

	@Override
	public void registrar(Vehiculo vehiculo) {
		VehiculoEntidad vehiculoEntidad = modelMapper.map(vehiculo, VehiculoEntidad.class);
		vehiculoRepositorioJPA.save(vehiculoEntidad);
	}

	@Override
	public VehiculoDTO buscar(String placa) {
		VehiculoEntidad vehiculoEntidad = vehiculoRepositorioJPA.buscar(placa);
		if (vehiculoEntidad != null) {
			return modelMapper.map(vehiculoEntidad, VehiculoDTO.class);
		} else {
			return null;
		}
	}
}