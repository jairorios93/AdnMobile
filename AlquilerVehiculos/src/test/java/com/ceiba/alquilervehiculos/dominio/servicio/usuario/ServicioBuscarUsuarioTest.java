package com.ceiba.alquilervehiculos.dominio.servicio.usuario;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.ceiba.alquilervehiculos.databuilder.UsuarioDTODataBuilder;
import com.ceiba.alquilervehiculos.dominio.modelo.dto.UsuarioDTO;
import com.ceiba.alquilervehiculos.dominio.puerto.repositorio.RepositorioUsuario;

public class ServicioBuscarUsuarioTest {

	@Test
	void obtenerUsuario() {
		RepositorioUsuario repositorioUsuario = mock(RepositorioUsuario.class);
		UsuarioDTO usuarioDTO = new UsuarioDTODataBuilder().build();
		Mockito.when(repositorioUsuario.buscar(Long.valueOf("1094935130"))).thenReturn(usuarioDTO);
		ServicioBuscarUsuario service = new ServicioBuscarUsuario(repositorioUsuario);

		UsuarioDTO resultado = service.buscar(Long.valueOf("1094935130"));

		assertEquals(resultado.getNombres(), usuarioDTO.getNombres());
	}
}
