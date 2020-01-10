package com.example.alquilervehiculosfront.servicios;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.example.alquilervehiculosfront.builder.RespuestaServicioDataBuilder;
import com.example.alquilervehiculosfront.builder.RespuestaServicioGetDataBuilder;
import com.example.alquilervehiculosfront.builder.RespuestaServicioPostDataBuilder;
import com.example.alquilervehiculosfront.builder.UsuarioDataBuilder;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioGet;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioPost;
import com.example.alquilervehiculosfront.dominio.modelo.Usuario;
import com.example.alquilervehiculosfront.dominio.repositorio.RepositorioUsuario;
import com.example.alquilervehiculosfront.dominio.servicios.ServicioUsuarioDominio;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ServicioUsuarioDominioTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    private RepositorioUsuario repositorioUsuario;

    @InjectMocks
    private ServicioUsuarioDominio servicioUsuarioDominio;

    @Test
    public void registrar() {
        Usuario usuario = new UsuarioDataBuilder().build();
        MutableLiveData<RespuestaServicioPost> respuestaRepositorioUsuario = new MutableLiveData<>();
        RespuestaServicioPost respuestaPost = new RespuestaServicioPostDataBuilder().build();
        respuestaRepositorioUsuario.setValue(respuestaPost);

        when(repositorioUsuario.registrar(usuario)).thenReturn(respuestaRepositorioUsuario);

        MutableLiveData<RespuestaServicioPost> respuestaServicioUsuarioDominio = servicioUsuarioDominio.registrar(usuario);

        assertEquals(respuestaRepositorioUsuario.getValue().getMensaje(), respuestaServicioUsuarioDominio.getValue().getMensaje());
    }

    @Test
    public void buscar() {
        Usuario usuario = new UsuarioDataBuilder().build();
        MutableLiveData<RespuestaServicioGet> respuestaRepositorioUsuario = new MutableLiveData<>();
        RespuestaServicioGet respuestaPost = new RespuestaServicioGetDataBuilder().build(usuario);
        respuestaRepositorioUsuario.setValue(respuestaPost);

        when(repositorioUsuario.buscar(usuario.getCedula())).thenReturn(respuestaRepositorioUsuario);

        MutableLiveData<RespuestaServicioGet> respuestaServicioUsuarioDominio = servicioUsuarioDominio.buscar(usuario.getCedula());

        assertEquals(((Usuario) respuestaRepositorioUsuario.getValue().getObjeto()).getCedula(),
                ((Usuario) respuestaServicioUsuarioDominio.getValue().getObjeto()).getCedula());
    }
}