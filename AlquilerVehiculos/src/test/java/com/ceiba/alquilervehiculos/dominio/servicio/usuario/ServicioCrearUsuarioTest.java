package com.ceiba.alquilervehiculos.dominio.servicio.usuario;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import com.ceiba.alquilervehiculos.databuilder.UsuarioDataBuilder;
import com.ceiba.alquilervehiculos.dominio.modelo.Usuario;
import com.ceiba.alquilervehiculos.dominio.puerto.repositorio.RepositorioUsuario;

public class ServicioCrearUsuarioTest {

	@Test
	void crearUsuario() {
		Usuario usuario = new UsuarioDataBuilder().build();
		RepositorioUsuario repositorioUsuario = mock(RepositorioUsuario.class);

		ServicioCrearUsuario service = new ServicioCrearUsuario(repositorioUsuario);
		assertDoesNotThrow(() -> service.registrar(usuario));
	}
}
