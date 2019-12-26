package com.ceiba.alquilervehiculos.aplicacion.manejadores.usuario;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.alquilervehiculos.dominio.modelo.dto.UsuarioDTO;
import com.ceiba.alquilervehiculos.dominio.servicio.usuario.ServicioBuscarUsuario;

@Service
public class ManejadorBuscarUsuario {

	private final ServicioBuscarUsuario servicioBuscarUsuario;

	public ManejadorBuscarUsuario(ServicioBuscarUsuario servicioBuscarUsuario) {
		this.servicioBuscarUsuario = servicioBuscarUsuario;
	}

	@Transactional
	public UsuarioDTO buscar(Long cedula) {
		return servicioBuscarUsuario.buscar(cedula);
	}
}
