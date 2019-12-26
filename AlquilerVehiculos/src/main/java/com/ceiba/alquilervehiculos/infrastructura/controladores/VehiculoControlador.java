package com.ceiba.alquilervehiculos.infrastructura.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.alquilervehiculos.aplicacion.comando.ComandoVehiculo;
import com.ceiba.alquilervehiculos.aplicacion.manejadores.vehiculo.ManejadorBuscarVehiculo;
import com.ceiba.alquilervehiculos.aplicacion.manejadores.vehiculo.ManejadorCrearVehiculo;
import com.ceiba.alquilervehiculos.dominio.modelo.dto.VehiculoDTO;

@RestController
@RequestMapping(value = "/vehiculo")
public class VehiculoControlador {

	private final ManejadorCrearVehiculo manejadorCrearVehiculo;
	private final ManejadorBuscarVehiculo manejadorBuscarVehiculo;

	public VehiculoControlador(ManejadorCrearVehiculo manejadorCrearVehiculo,
			ManejadorBuscarVehiculo manejadorBuscarVehiculo) {
		this.manejadorCrearVehiculo = manejadorCrearVehiculo;
		this.manejadorBuscarVehiculo = manejadorBuscarVehiculo;
	}

	@PostMapping
	public void registrar(@RequestBody ComandoVehiculo vehiculo) {
		manejadorCrearVehiculo.registrar(vehiculo);
	}

	@GetMapping("/{PLACA}")
	public VehiculoDTO buscar(@PathVariable(name = "PLACA") String placa) {
		return this.manejadorBuscarVehiculo.buscar(placa);
	}

}