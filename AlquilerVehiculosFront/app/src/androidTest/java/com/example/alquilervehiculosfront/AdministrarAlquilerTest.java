package com.example.alquilervehiculosfront;

import androidx.test.rule.ActivityTestRule;

import com.example.alquilervehiculosfront.presentacion.actividades.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;

public class AdministrarAlquilerTest {

    private PageObject pageObject;

    @Rule
    public ActivityTestRule<MainActivity> menuActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, true);

    @Before
    public void iniciarPageObject() {
        pageObject = new PageObject();
    }

  //  @Test
    public void alquilar() throws InterruptedException {
        pageObject.abrirMenu();
        pageObject.sleep(1);

        pageObject.navegarMenu(R.id.nav_view, R.id.nav_administrar_alquileres);
        pageObject.sleep(1);

        pageObject.escribirEdit(R.id.cedula, "1094");
        pageObject.clickBoton(R.id.buscarUsuario);

        pageObject.sleep(3);

        pageObject.escribirEdit(R.id.placa, "ASD123");
        pageObject.clickBoton(R.id.buscarVehiculo);
        pageObject.sleep(3);

        pageObject.escribirEdit(R.id.fechaInicio, "2019-12-27");
        pageObject.escribirEdit(R.id.fechaFin, "2019-12-27");
        pageObject.sleep(1);

        pageObject.clickBoton(R.id.alquilar);

        pageObject.matchToast(R.string.fragment_administrar_alquiler_alquilado);
    }

  //  @Test
    public void devolver() throws InterruptedException {
        pageObject.abrirMenu();
        pageObject.sleep(1);

        pageObject.navegarMenu(R.id.nav_view, R.id.nav_administrar_alquileres);
        pageObject.sleep(1);

        pageObject.escribirEdit(R.id.cedula, "1094");
        pageObject.clickBoton(R.id.buscarUsuario);
        pageObject.sleep(3);

        pageObject.escribirEdit(R.id.placa, "ASD124");
        pageObject.clickBoton(R.id.buscarVehiculo);
        pageObject.sleep(3);

        pageObject.escribirEdit(R.id.fechaInicio, "2019-12-27");
        pageObject.escribirEdit(R.id.fechaFin, "2019-12-27");
        pageObject.sleep(1);

        pageObject.clickBoton(R.id.alquilar);
        pageObject.sleep(8);

        pageObject.escribirEdit(R.id.placa, "ASD124");
        pageObject.sleep(1);

        pageObject.clickBoton(R.id.devolver);

        pageObject.matchToast(R.string.fragment_administrar_alquiler_devuelto);
    }
}