package com.ceiba.alquilervehiculos.infrastructura.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ceiba.alquilervehiculos.dominio.puerto.repositorio.RepositorioAlquilarVehiculo;
import com.ceiba.alquilervehiculos.dominio.puerto.repositorio.RepositorioUsuario;
import com.ceiba.alquilervehiculos.dominio.puerto.repositorio.RepositorioVehiculo;
import com.ceiba.alquilervehiculos.dominio.servicio.alquiler.ServicioAlquilarVehiculo;
import com.ceiba.alquilervehiculos.dominio.servicio.alquiler.ServicioDevolverVehiculo;
import com.ceiba.alquilervehiculos.dominio.servicio.usuario.ServicioBuscarUsuario;
import com.ceiba.alquilervehiculos.dominio.servicio.usuario.ServicioCrearUsuario;
import com.ceiba.alquilervehiculos.dominio.servicio.vehiculo.ServicioBuscarVehiculo;
import com.ceiba.alquilervehiculos.dominio.servicio.vehiculo.ServicioCrearVehiculo;

@Configuration
public class ServicioBean {

	@Bean
	public ServicioCrearVehiculo crearServicioCrearVehiculo(RepositorioVehiculo repositorioVehiculo) {
		return new ServicioCrearVehiculo(repositorioVehiculo);
	}

	@Bean
	public ServicioBuscarVehiculo crearServicioBuscarVehiculo(RepositorioVehiculo repositorioVehiculo) {
		return new ServicioBuscarVehiculo(repositorioVehiculo);
	}

	@Bean
	public ServicioCrearUsuario crearServicioCrearUsuario(RepositorioUsuario repositorioUsuario) {
		return new ServicioCrearUsuario(repositorioUsuario);
	}

	@Bean
	public ServicioBuscarUsuario crearServicioBuscarUsuario(RepositorioUsuario repositorioUsuario) {
		return new ServicioBuscarUsuario(repositorioUsuario);
	}

	@Bean
	public ServicioAlquilarVehiculo crearServicioAlquilarVehiculo(
			RepositorioAlquilarVehiculo repositorioAlquilarVehiculo, RepositorioVehiculo repositorioVehiculo) {
		return new ServicioAlquilarVehiculo(repositorioAlquilarVehiculo, repositorioVehiculo);
	}

	@Bean
	public ServicioDevolverVehiculo crearServicioDevolverVehiculo(
			RepositorioAlquilarVehiculo repositorioAlquilarVehiculo) {
		return new ServicioDevolverVehiculo(repositorioAlquilarVehiculo);
	}
}