package com.ceiba.alquilervehiculos.aplicacion.fabrica;

import org.springframework.stereotype.Component;

import com.ceiba.alquilervehiculos.aplicacion.comando.ComandoUsuario;
import com.ceiba.alquilervehiculos.dominio.modelo.Usuario;

@Component
public class FabricaUsuario {

	public Usuario crearUsuario(ComandoUsuario comandoUsuario) {
		return new Usuario(comandoUsuario.getCedula(), comandoUsuario.getNombres(), comandoUsuario.getApellidos(),
				comandoUsuario.getFechaNacimiento());
	}

}
