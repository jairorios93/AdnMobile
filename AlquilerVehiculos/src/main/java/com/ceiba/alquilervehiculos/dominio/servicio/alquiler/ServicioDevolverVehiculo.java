package com.ceiba.alquilervehiculos.dominio.servicio.alquiler;

import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import com.ceiba.alquilervehiculos.dominio.excepciones.ExcepcionNegocio;
import com.ceiba.alquilervehiculos.dominio.modelo.dto.AlquilarVehiculoDTO;
import com.ceiba.alquilervehiculos.dominio.puerto.repositorio.RepositorioAlquilarVehiculo;

public class ServicioDevolverVehiculo {

	private RepositorioAlquilarVehiculo repositorioAlquilarVehiculo;
	private static final String VEHICULO_NO_ENCONTRADO = "No se encuentra el vehiculo con placa ";
	private static final double DEVOLUCION_ATRASADA = 0.2;
	private static final double DEVOLUCION_ANTICIPADA = 0.05;

	public ServicioDevolverVehiculo(RepositorioAlquilarVehiculo repositorioAlquilarVehiculo) {
		this.repositorioAlquilarVehiculo = repositorioAlquilarVehiculo;
	}

	public void devolver(String placa) {
		AlquilarVehiculoDTO alquilarVehiculoDTO = repositorioAlquilarVehiculo.buscar(placa);
		if (alquilarVehiculoDTO == null) {
			throw new ExcepcionNegocio(VEHICULO_NO_ENCONTRADO + placa);
		} else {

			GregorianCalendar fin = new GregorianCalendar();
			fin.setTime(alquilarVehiculoDTO.getFechaFin());

			GregorianCalendar hoy = new GregorianCalendar();

			long dias = fin.getTime().getTime() - hoy.getTime().getTime();
			long diasDiferencia = TimeUnit.DAYS.convert(dias, TimeUnit.MILLISECONDS);

			double valorPagar = alquilarVehiculoDTO.getValor();
			if (diasDiferencia > 0) {
				valorPagar = valorPagar
						- alquilarVehiculoDTO.getValor() * (DEVOLUCION_ANTICIPADA * (diasDiferencia + 1));
			} else if (diasDiferencia < 0) {
				valorPagar = valorPagar
						+ alquilarVehiculoDTO.getValor() * ((DEVOLUCION_ATRASADA) * (diasDiferencia * -1));
			}

			repositorioAlquilarVehiculo.devolver(alquilarVehiculoDTO.getId(), false, hoy.getTime(),
					Double.valueOf(String.format("%.0f", valorPagar)));
		}
	}
}
