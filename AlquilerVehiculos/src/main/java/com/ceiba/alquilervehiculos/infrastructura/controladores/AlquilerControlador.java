package com.ceiba.alquilervehiculos.infrastructura.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.alquilervehiculos.aplicacion.comando.ComandoAlquilarVehiculo;
import com.ceiba.alquilervehiculos.aplicacion.manejadores.alquiler.ManejadorAlquilarVehiculo;
import com.ceiba.alquilervehiculos.aplicacion.manejadores.alquiler.ManejadorDevolverVehiculo;

@RestController
@RequestMapping("/alquiler")
public class AlquilerControlador {

	private final ManejadorAlquilarVehiculo manejadorAlquilarVehiculo;
	private final ManejadorDevolverVehiculo manejadorDevolverVehiculo;

	public AlquilerControlador(ManejadorAlquilarVehiculo manejadorAlquilarVehiculo,
			ManejadorDevolverVehiculo manejadorDevolverVehiculo) {
		this.manejadorAlquilarVehiculo = manejadorAlquilarVehiculo;
		this.manejadorDevolverVehiculo = manejadorDevolverVehiculo;
	}

	@PostMapping
	public void alquilar(@RequestBody ComandoAlquilarVehiculo alquilarVehiculo) {
		manejadorAlquilarVehiculo.alquilar(alquilarVehiculo);
	}

	@GetMapping("/{PLACA}")
	public void devolver(@PathVariable(name = "PLACA") String placa) {
		manejadorDevolverVehiculo.devolver(placa);
	}
}