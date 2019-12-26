package com.ceiba.alquilervehiculos.dominio.puerto.repositorio;

import com.ceiba.alquilervehiculos.dominio.modelo.Vehiculo;
import com.ceiba.alquilervehiculos.dominio.modelo.dto.VehiculoDTO;

public interface RepositorioVehiculo {

	public void registrar(Vehiculo vehiculo);

	public VehiculoDTO buscar(String placa);
}