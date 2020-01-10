package com.example.alquilervehiculosfront.servicios;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.example.alquilervehiculosfront.builder.AlquilarVehiculoDataBuilder;
import com.example.alquilervehiculosfront.builder.RespuestaServicioGetDataBuilder;
import com.example.alquilervehiculosfront.builder.RespuestaServicioPostDataBuilder;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioGet;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioPost;
import com.example.alquilervehiculosfront.dominio.modelo.AlquilarVehiculo;
import com.example.alquilervehiculosfront.dominio.repositorio.RepositorioAlquiler;
import com.example.alquilervehiculosfront.dominio.servicios.ServicioAlquilerDominio;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ServicioAlquilerDominioTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    private RepositorioAlquiler repositorioAlquiler;

    @InjectMocks
    private ServicioAlquilerDominio servicioAlquilerDominio;

    @Test
    public void alquilar() {
        AlquilarVehiculo alquiler = new AlquilarVehiculoDataBuilder().build();
        MutableLiveData<RespuestaServicioPost> respuestaRepositorioAlquiler = new MutableLiveData<>();
        RespuestaServicioPost respuestaPost = new RespuestaServicioPostDataBuilder().build();
        respuestaRepositorioAlquiler.setValue(respuestaPost);

        when(repositorioAlquiler.alquilar(alquiler)).thenReturn(respuestaRepositorioAlquiler);

        MutableLiveData<RespuestaServicioPost> respuestaServicioAlquilerDominio = servicioAlquilerDominio.alquilar(alquiler);

        assertEquals(respuestaRepositorioAlquiler.getValue().getMensaje(), respuestaServicioAlquilerDominio.getValue().getMensaje());
    }

    @Test
    public void devolver() {
        AlquilarVehiculo alquiler = new AlquilarVehiculoDataBuilder().build();
        MutableLiveData<RespuestaServicioGet> respuestaRepositorioAlquiler = new MutableLiveData<>();
        RespuestaServicioGet respuestaPost = new RespuestaServicioGetDataBuilder().build("Operacion exitosa");
        respuestaRepositorioAlquiler.setValue(respuestaPost);

        when(repositorioAlquiler.devolver(alquiler.getVehiculo().getPlaca())).thenReturn(respuestaRepositorioAlquiler);

        MutableLiveData<RespuestaServicioGet> respuestaServicioAlquilerDominio = servicioAlquilerDominio.devolver(alquiler.getVehiculo().getPlaca());

        assertEquals((respuestaRepositorioAlquiler.getValue().getObjeto()),
                (respuestaServicioAlquilerDominio.getValue().getObjeto()));
    }
}