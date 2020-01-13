package com.example.alquilervehiculosfront;

import androidx.test.rule.ActivityTestRule;

import com.example.alquilervehiculosfront.presentacion.actividades.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class AdministrarVehiculoTest {

    private PageObject pageObject;

    @Rule
    public ActivityTestRule<MainActivity> menuActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, true);

    @Before
    public void iniciarPageObject() {
        pageObject = new PageObject();
    }

    //@Test
    public void registrar() throws InterruptedException {
        pageObject.abrirMenu();
        pageObject.sleep(1);

        pageObject.navegarMenu(R.id.nav_view, R.id.nav_administrar_vehiculos);
        pageObject.sleep(1);

        pageObject.escribirEdit(R.id.placa, "ASD123");
        pageObject.escribirEdit(R.id.modelo, "A7");
        pageObject.escribirEdit(R.id.marca, "Audi");
        pageObject.escribirEdit(R.id.color, "Rojo");
        pageObject.escribirEdit(R.id.precio, "10000");
        pageObject.sleep(1);

        pageObject.clickBoton(R.id.guardar);

        pageObject.matchToast(R.string.fragment_administrar_vehiculo_registrado);
    }

    //@Test
    public void buscar() throws InterruptedException {
        pageObject.abrirMenu();
        pageObject.sleep(1);

        pageObject.navegarMenu(R.id.nav_view, R.id.nav_administrar_vehiculos);
        pageObject.sleep(1);

        pageObject.escribirEdit(R.id.placa, "ASD124");
        pageObject.escribirEdit(R.id.modelo, "A7");
        pageObject.escribirEdit(R.id.marca, "Audi");
        pageObject.escribirEdit(R.id.color, "Rojo");
        pageObject.escribirEdit(R.id.precio, "10000");
        pageObject.sleep(1);

        pageObject.clickBoton(R.id.guardar);
        pageObject.sleep(8);

        pageObject.escribirEdit(R.id.placa, "ASD124");
        pageObject.sleep(1);

        pageObject.clickBoton(R.id.buscar);
        pageObject.sleep(3);

        pageObject.matchEdit(R.id.marca, "Audi");
    }
}