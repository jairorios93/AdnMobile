package com.example.alquilervehiculosfront.servicios;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.example.alquilervehiculosfront.builder.RespuestaServicioGetDataBuilder;
import com.example.alquilervehiculosfront.builder.RespuestaServicioPostDataBuilder;
import com.example.alquilervehiculosfront.builder.VehiculoDataBuilder;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioGet;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioPost;
import com.example.alquilervehiculosfront.dominio.modelo.Vehiculo;
import com.example.alquilervehiculosfront.dominio.repositorio.RepositorioVehiculo;
import com.example.alquilervehiculosfront.dominio.servicios.ServicioVehiculoDominio;

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
public class ServicioVehiculoDominioTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    private RepositorioVehiculo repositorioVehiculo;

    @InjectMocks
    private ServicioVehiculoDominio servicioVehiculoDominio;

    @Test
    public void registrar() {
        Vehiculo vehiculo = new VehiculoDataBuilder().build();
        MutableLiveData<RespuestaServicioPost> respuestaRepositorioVehiculo = new MutableLiveData<>();
        RespuestaServicioPost respuestaPost = new RespuestaServicioPostDataBuilder().build();
        respuestaRepositorioVehiculo.setValue(respuestaPost);

        when(repositorioVehiculo.registrar(vehiculo)).thenReturn(respuestaRepositorioVehiculo);

        MutableLiveData<RespuestaServicioPost> respuestaServicioVehiculoDominio = servicioVehiculoDominio.registrar(vehiculo);

        assertEquals(respuestaRepositorioVehiculo.getValue().getMensaje(), respuestaServicioVehiculoDominio.getValue().getMensaje());
    }

    @Test
    public void buscar() {
        Vehiculo vehiculo = new VehiculoDataBuilder().build();
        MutableLiveData<RespuestaServicioGet> respuestaRepositorioVehiculo = new MutableLiveData<>();
        RespuestaServicioGet respuestaPost = new RespuestaServicioGetDataBuilder().build(vehiculo);
        respuestaRepositorioVehiculo.setValue(respuestaPost);

        when(repositorioVehiculo.buscar(vehiculo.getPlaca())).thenReturn(respuestaRepositorioVehiculo);

        MutableLiveData<RespuestaServicioGet> respuestaServicioVehiculoDominio = servicioVehiculoDominio.buscar(vehiculo.getPlaca());

        assertEquals(((Vehiculo) respuestaRepositorioVehiculo.getValue().getObjeto()).getPlaca(),
                ((Vehiculo) respuestaServicioVehiculoDominio.getValue().getObjeto()).getPlaca());
    }
}