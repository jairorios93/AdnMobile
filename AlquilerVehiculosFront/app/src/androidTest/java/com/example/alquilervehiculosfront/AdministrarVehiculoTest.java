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

public class AdministrarVehiculoTest {

    @Rule
    public ActivityTestRule<MainActivity> menuActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, true);

    @Test
    public void registrar() throws InterruptedException {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        Thread.sleep(1000);

        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_administrar_vehiculos));
        Thread.sleep(1000);

        escribirEdit(R.id.placa, "ASD123");
        escribirEdit(R.id.modelo, "A7");
        escribirEdit(R.id.marca, "Audi");
        escribirEdit(R.id.color, "Rojo");
        escribirEdit(R.id.precio, "10000");
        Thread.sleep(1000);

        onView(withId(R.id.guardar)).perform(click());

        onView(withText(R.string.fragment_administrar_vehiculo_registrado)).inRoot(new ToastMatcher())
                .check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void buscar() throws InterruptedException {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        Thread.sleep(1000);

        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_administrar_vehiculos));
        Thread.sleep(1000);

        escribirEdit(R.id.placa, "ASD124");
        escribirEdit(R.id.modelo, "A7");
        escribirEdit(R.id.marca, "Audi");
        escribirEdit(R.id.color, "Rojo");
        escribirEdit(R.id.precio, "10000");
        Thread.sleep(1000);

        onView(withId(R.id.guardar)).perform(click());
        Thread.sleep(8000);

        escribirEdit(R.id.placa, "ASD124");
        Thread.sleep(1000);

        onView(withId(R.id.buscar)).perform(click());
        Thread.sleep(3000);

        onView(withId(R.id.marca)).check(ViewAssertions.matches(withText("Audi")));
    }

    private void escribirEdit(int id, String texto) {
        ViewInteraction placa = onView(withId(id));
        placa.perform(replaceText(texto));
    }
}
