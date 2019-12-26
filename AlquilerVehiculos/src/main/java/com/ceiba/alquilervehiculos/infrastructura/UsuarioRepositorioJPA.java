package com.ceiba.alquilervehiculos.infrastructura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceiba.alquilervehiculos.infrastructura.entidades.UsuarioEntidad;

@Repository
public interface UsuarioRepositorioJPA extends JpaRepository<UsuarioEntidad, Long> {

}
