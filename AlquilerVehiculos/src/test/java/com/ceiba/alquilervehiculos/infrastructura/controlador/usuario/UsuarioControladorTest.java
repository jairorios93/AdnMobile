package com.ceiba.alquilervehiculos.infrastructura.controlador.usuario;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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
import com.fasterxml.jackson.databind.ObjectMapper;

import com.ceiba.alquilervehiculos.AlquilerVehiculosApplication;
import com.ceiba.alquilervehiculos.aplicacion.comando.ComandoUsuario;
import com.ceiba.alquilervehiculos.databuilder.ComandoUsuarioDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AlquilerVehiculosApplication.class)
@AutoConfigureMockMvc
public class UsuarioControladorTest {

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
	void registrarUsuario() throws Exception {
		ComandoUsuario comandoUsuario = new ComandoUsuarioDataBuilder().build();
		mvc.perform(post("/usuario").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(comandoUsuario))).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void buscarUsuario() throws Exception {
		ComandoUsuario comandoUsuario = new ComandoUsuarioDataBuilder().build();
		mvc.perform(post("/usuario").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(comandoUsuario))).andDo(print()).andExpect(status().isOk());

		mvc.perform(get("/usuario/{CEDULA}", comandoUsuario.getCedula()).contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(jsonPath("$.cedula").value(comandoUsuario.getCedula()));
	}

	@Test
	void usuarioNoEncontrado() throws Exception {
		mvc.perform(get("/usuario/{CEDULA}", 1L).contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(content().string(""));
	}
}