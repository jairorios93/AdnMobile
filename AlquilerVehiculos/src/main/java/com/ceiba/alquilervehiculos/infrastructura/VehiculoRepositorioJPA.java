package com.ceiba.alquilervehiculos.infrastructura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ceiba.alquilervehiculos.infrastructura.entidades.VehiculoEntidad;

@Repository
public interface VehiculoRepositorioJPA extends JpaRepository<VehiculoEntidad, Long> {

	@Query("select v from VehiculoEntidad v where v.placa = ?1")
	public VehiculoEntidad buscar(String placa);
}
