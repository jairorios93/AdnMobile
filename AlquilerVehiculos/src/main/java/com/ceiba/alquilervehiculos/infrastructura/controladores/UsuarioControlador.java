package com.ceiba.alquilervehiculos.infrastructura.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.alquilervehiculos.aplicacion.comando.ComandoUsuario;
import com.ceiba.alquilervehiculos.aplicacion.manejadores.usuario.ManejadorBuscarUsuario;
import com.ceiba.alquilervehiculos.aplicacion.manejadores.usuario.ManejadorCrearUsuario;
import com.ceiba.alquilervehiculos.dominio.modelo.dto.UsuarioDTO;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {

	private final ManejadorCrearUsuario manejadorCrearUsuario;
	private final ManejadorBuscarUsuario manejadorBuscarUsuario;

	public UsuarioControlador(ManejadorCrearUsuario manejadorCrearUsuario,
			ManejadorBuscarUsuario manejadorBuscarUsuario) {
		this.manejadorCrearUsuario = manejadorCrearUsuario;
		this.manejadorBuscarUsuario = manejadorBuscarUsuario;
	}

	@PostMapping
	public void registrar(@RequestBody ComandoUsuario usuario) {
		manejadorCrearUsuario.registrar(usuario);
	}

	@GetMapping("/{CEDULA}")
	public UsuarioDTO buscar(@PathVariable(name = "CEDULA") Long cedula) {
		return this.manejadorBuscarUsuario.buscar(cedula);
	}

}
