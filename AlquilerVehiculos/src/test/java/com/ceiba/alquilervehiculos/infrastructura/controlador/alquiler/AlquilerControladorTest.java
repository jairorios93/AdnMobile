package com.ceiba.alquilervehiculos.infrastructura.controlador.alquiler;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ceiba.alquilervehiculos.AlquilerVehiculosApplication;
import com.ceiba.alquilervehiculos.aplicacion.comando.ComandoAlquilarVehiculo;
import com.ceiba.alquilervehiculos.aplicacion.comando.ComandoUsuario;
import com.ceiba.alquilervehiculos.aplicacion.comando.ComandoVehiculo;
import com.ceiba.alquilervehiculos.databuilder.ComandoAlquilarVehiculoDataBuilder;
import com.ceiba.alquilervehiculos.databuilder.ComandoUsuarioDataBuilder;
import com.ceiba.alquilervehiculos.databuilder.ComandoVehiculoDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AlquilerVehiculosApplication.class)
@AutoConfigureMockMvc
public class AlquilerControladorTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mvc;

	@Before
	public void setup() throws Exception {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	void devolverVehiculoFechaFinMayor() throws Exception {
		ComandoVehiculo comandoVehiculo = new ComandoVehiculoDataBuilder().build();
		mvc.perform(post("/vehiculo").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(comandoVehiculo))).andDo(print()).andExpect(status().isOk());

		mvc.perform(get("/vehiculo/{PLACA}", comandoVehiculo.getPlaca()).contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(jsonPath("$.placa").value(comandoVehiculo.getPlaca()));

		ComandoUsuario comandoUsuario = new ComandoUsuarioDataBuilder().build();
		mvc.perform(post("/usuario").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(comandoUsuario))).andDo(print()).andExpect(status().isOk());

		mvc.perform(get("/usuario/{CEDULA}", comandoUsuario.getCedula()).contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(jsonPath("$.cedula").value(comandoUsuario.getCedula()));

		ComandoAlquilarVehiculo comandoAlquilarVehiculo = new ComandoAlquilarVehiculoDataBuilder()
				.conVehiculoYFecha(comandoVehiculo, 5);
		mvc.perform(post("/alquiler").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(comandoAlquilarVehiculo))).andDo(print())
				.andExpect(status().isOk());

		mvc.perform(get("/alquiler/{PLACA}", comandoAlquilarVehiculo.getVehiculo().getPlaca())
				.contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void devolverVehiculoFechaFinMenor() throws Exception {
		ComandoVehiculo comandoVehiculo = new ComandoVehiculoDataBuilder().conPlaca("QWE123");
		mvc.perform(post("/vehiculo").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(comandoVehiculo))).andDo(print()).andExpect(status().isOk());

		mvc.perform(get("/vehiculo/{PLACA}", comandoVehiculo.getPlaca()).contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(jsonPath("$.placa").value(comandoVehiculo.getPlaca()));

		ComandoUsuario comandoUsuario = new ComandoUsuarioDataBuilder().build();
		mvc.perform(post("/usuario").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(comandoUsuario))).andDo(print()).andExpect(status().isOk());

		mvc.perform(get("/usuario/{CEDULA}", comandoUsuario.getCedula()).contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(jsonPath("$.cedula").value(comandoUsuario.getCedula()));

		ComandoAlquilarVehiculo comandoAlquilarVehiculo = new ComandoAlquilarVehiculoDataBuilder()
				.conVehiculoYFecha(comandoVehiculo, -5);
		mvc.perform(post("/alquiler").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(comandoAlquilarVehiculo))).andDo(print())
				.andExpect(status().isOk());

		mvc.perform(get("/alquiler/{PLACA}", comandoAlquilarVehiculo.getVehiculo().getPlaca())
				.contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void alquilarVehiculoSinDevolver() throws Exception {
		ComandoVehiculo comandoVehiculo = new ComandoVehiculoDataBuilder().conPlaca("QWE12345");
		mvc.perform(post("/vehiculo").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(comandoVehiculo))).andDo(print()).andExpect(status().isOk());

		mvc.perform(get("/vehiculo/{PLACA}", comandoVehiculo.getPlaca()).contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(jsonPath("$.placa").value(comandoVehiculo.getPlaca()));

		ComandoUsuario comandoUsuario = new ComandoUsuarioDataBuilder().build();
		mvc.perform(post("/usuario").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(comandoUsuario))).andDo(print()).andExpect(status().isOk());

		mvc.perform(get("/usuario/{CEDULA}", comandoUsuario.getCedula()).contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(jsonPath("$.cedula").value(comandoUsuario.getCedula()));

		ComandoAlquilarVehiculo comandoAlquilarVehiculo = new ComandoAlquilarVehiculoDataBuilder()
				.conVehiculoYFecha(comandoVehiculo, -5);
		mvc.perform(post("/alquiler").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(comandoAlquilarVehiculo))).andDo(print())
				.andExpect(status().isOk());

		try {
			mvc.perform(post("/alquiler").contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(comandoAlquilarVehiculo))).andDo(print())
					.andExpect(status().isInternalServerError());
		} catch (Exception e) {
			System.err.println(e.getCause().getMessage());
		}
	}

	@Test
	void devolverVehiculoNoEncontrado() throws Exception {
		try {
			mvc.perform(get("/alquiler/{PLACA}", 1L).contentType(MediaType.APPLICATION_JSON)).andDo(print())
					.andExpect(status().isInternalServerError());
		} catch (Exception e) {
			System.err.println(e.getCause().getMessage());
		}
	}

}