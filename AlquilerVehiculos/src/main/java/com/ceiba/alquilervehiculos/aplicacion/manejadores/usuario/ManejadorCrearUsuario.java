package com.ceiba.alquilervehiculos.aplicacion.manejadores.usuario;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.alquilervehiculos.aplicacion.comando.ComandoUsuario;
import com.ceiba.alquilervehiculos.aplicacion.fabrica.FabricaUsuario;
import com.ceiba.alquilervehiculos.dominio.modelo.Usuario;
import com.ceiba.alquilervehiculos.dominio.servicio.usuario.ServicioCrearUsuario;

@Component
public class ManejadorCrearUsuario {

	private final FabricaUsuario fabricaUsuario;
	private final ServicioCrearUsuario servicioCrearUsuario;

	public ManejadorCrearUsuario(FabricaUsuario fabricaUsuario, ServicioCrearUsuario servicioCrearUsuario) {
		this.fabricaUsuario = fabricaUsuario;
		this.servicioCrearUsuario = servicioCrearUsuario;
	}

	@Transactional
	public void registrar(ComandoUsuario comandoUsuario) {
		Usuario usuario = fabricaUsuario.crearUsuario(comandoUsuario);
		servicioCrearUsuario.registrar(usuario);
	}
}
