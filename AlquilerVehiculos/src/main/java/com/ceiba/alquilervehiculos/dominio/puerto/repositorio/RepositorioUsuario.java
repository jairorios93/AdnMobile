package com.ceiba.alquilervehiculos.dominio.puerto.repositorio;

import com.ceiba.alquilervehiculos.dominio.modelo.Usuario;
import com.ceiba.alquilervehiculos.dominio.modelo.dto.UsuarioDTO;

public interface RepositorioUsuario {

	public void registrar(Usuario usuario);

	public UsuarioDTO buscar(Long cedula);
}
