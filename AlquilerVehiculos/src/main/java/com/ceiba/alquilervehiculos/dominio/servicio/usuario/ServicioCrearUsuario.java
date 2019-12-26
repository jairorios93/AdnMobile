package com.ceiba.alquilervehiculos.dominio.servicio.usuario;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.ceiba.alquilervehiculos.dominio.modelo.Usuario;
import com.ceiba.alquilervehiculos.dominio.puerto.repositorio.RepositorioUsuario;

public class ServicioCrearUsuario {

	private RepositorioUsuario repositorioUsuario;

	public ServicioCrearUsuario(RepositorioUsuario repositorioUsuario) {
		this.repositorioUsuario = repositorioUsuario;
	}

	public void registrar(Usuario usuario) {
		GregorianCalendar fecha = new GregorianCalendar();
		fecha.setTime(usuario.getFechaNacimiento());
		fecha.add(Calendar.DATE, 1);

		usuario.setFechaNacimiento(fecha.getTime());
		repositorioUsuario.registrar(usuario);
	}
}
