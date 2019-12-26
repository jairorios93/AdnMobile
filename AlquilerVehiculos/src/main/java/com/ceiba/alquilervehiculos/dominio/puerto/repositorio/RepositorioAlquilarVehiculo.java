package com.ceiba.alquilervehiculos.dominio.puerto.repositorio;

import java.util.Date;

import com.ceiba.alquilervehiculos.dominio.modelo.AlquilarVehiculo;
import com.ceiba.alquilervehiculos.dominio.modelo.dto.AlquilarVehiculoDTO;

public interface RepositorioAlquilarVehiculo {

	public void alquilar(AlquilarVehiculo alquilarVehiculo);

	public AlquilarVehiculoDTO buscar(String placa);

	public void devolver(Long id, boolean estado, Date fecha, double valor);

}