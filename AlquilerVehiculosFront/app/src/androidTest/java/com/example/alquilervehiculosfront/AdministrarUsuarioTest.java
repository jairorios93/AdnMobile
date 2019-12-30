package com.example.alquilervehiculosfront;

import androidx.test.rule.ActivityTestRule;

import com.example.alquilervehiculosfront.vistas.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class AdministrarUsuarioTest {

    private PageObject pageObject;

    @Rule
    public ActivityTestRule<MainActivity> menuActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, true);

    @Before
    public void iniciarPageObject() {
        pageObject = new PageObject();
    }

    @Test
    public void registrar() throws InterruptedException {
        pageObject.abrirMenu();
        pageObject.sleep(1);

        pageObject.navegarMenu(R.id.nav_view, R.id.nav_administrar_usuarios);
        pageObject.sleep(1);

        pageObject.escribirEdit(R.id.cedula, "1094");
        pageObject.escribirEdit(R.id.nombres, "Jairo");
        pageObject.escribirEdit(R.id.apellidos, "Rios");
        pageObject.escribirEdit(R.id.fechaNacimiento, "2019-12-27");
        pageObject.sleep(1);

        pageObject.clickBoton(R.id.guardar);

        pageObject.matchToast(R.string.fragment_administrar_usuario_registrado);
    }

    @Test
    public void buscar() throws InterruptedException {
        pageObject.abrirMenu();
        pageObject.sleep(1);

        pageObject.navegarMenu(R.id.nav_view, R.id.nav_administrar_usuarios);
        pageObject.sleep(1);

        pageObject.escribirEdit(R.id.cedula, "10949");
        pageObject.escribirEdit(R.id.nombres, "Jairo");
        pageObject.escribirEdit(R.id.apellidos, "Rios");
        pageObject.escribirEdit(R.id.fechaNacimiento, "2019-12-27");
        pageObject.sleep(1);

        pageObject.clickBoton(R.id.guardar);
        pageObject.sleep(8);

        pageObject.escribirEdit(R.id.cedula, "10949");
        pageObject.sleep(1);

        pageObject.clickBoton(R.id.buscar);
        pageObject.sleep(3);

        pageObject.matchEdit(R.id.nombres, "Jairo");
    }
}