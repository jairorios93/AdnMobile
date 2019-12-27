package com.example.alquilervehiculosfront;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.rule.ActivityTestRule;

import com.example.alquilervehiculosfront.vistas.MainActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class AdministrarAlquilerTest {

    @Rule
    public ActivityTestRule<MainActivity> menuActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, true);

    @Test
    public void alquilar() throws InterruptedException {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        Thread.sleep(1000);

        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_administrar_alquileres));
        Thread.sleep(1000);

        escribirEdit(R.id.cedula, "1094");
        onView(withId(R.id.buscarUsuario)).perform(click());
        Thread.sleep(3000);

        escribirEdit(R.id.placa, "ASD123");
        onView(withId(R.id.buscarVehiculo)).perform(click());
        Thread.sleep(3000);

        escribirEdit(R.id.fechaInicio, "2019-12-27");
        escribirEdit(R.id.fechaFin, "2019-12-27");
        Thread.sleep(1000);

        onView(withId(R.id.alquilar)).perform(click());

        onView(withText(R.string.fragment_administrar_alquiler_alquilado)).inRoot(new ToastMatcher())
                .check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void devolver() throws InterruptedException {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        Thread.sleep(1000);

        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_administrar_alquileres));
        Thread.sleep(1000);

        escribirEdit(R.id.cedula, "1094");
        onView(withId(R.id.buscarUsuario)).perform(click());
        Thread.sleep(3000);

        escribirEdit(R.id.placa, "ASD124");
        onView(withId(R.id.buscarVehiculo)).perform(click());
        Thread.sleep(3000);

        escribirEdit(R.id.fechaInicio, "2019-12-27");
        escribirEdit(R.id.fechaFin, "2019-12-27");
        Thread.sleep(1000);

        onView(withId(R.id.alquilar)).perform(click());
        Thread.sleep(8000);

        escribirEdit(R.id.placa, "ASD124");
        Thread.sleep(1000);

        onView(withId(R.id.devolver)).perform(click());

        onView(withText(R.string.fragment_administrar_alquiler_devuelto)).inRoot(new ToastMatcher())
                .check(ViewAssertions.matches(isDisplayed()));
    }

    private void escribirEdit(int id, String texto) {
        ViewInteraction placa = onView(withId(id));
        placa.perform(replaceText(texto));
    }

}
