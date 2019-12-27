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

public class AdministrarUsuarioTest {

    @Rule
    public ActivityTestRule<MainActivity> menuActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, true);

    @Test
    public void registrar() throws InterruptedException {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        Thread.sleep(1000);

        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_administrar_usuarios));
        Thread.sleep(1000);

        escribirEdit(R.id.cedula, "1094");
        escribirEdit(R.id.nombres, "Jairo");
        escribirEdit(R.id.apellidos, "Rios");
        escribirEdit(R.id.fechaNacimiento, "2019-12-27");
        Thread.sleep(1000);

        onView(withId(R.id.guardar)).perform(click());

        onView(withText(R.string.fragment_administrar_usuario_registrado)).inRoot(new ToastMatcher())
                .check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void buscar() throws InterruptedException {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        Thread.sleep(1000);

        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_administrar_usuarios));
        Thread.sleep(1000);

        escribirEdit(R.id.cedula, "10949");
        escribirEdit(R.id.nombres, "Jairo");
        escribirEdit(R.id.apellidos, "Rios");
        escribirEdit(R.id.fechaNacimiento, "2019-12-27");
        Thread.sleep(1000);

        onView(withId(R.id.guardar)).perform(click());
        Thread.sleep(8000);

        escribirEdit(R.id.cedula, "10949");
        Thread.sleep(1000);

        onView(withId(R.id.buscar)).perform(click());
        Thread.sleep(3000);

        onView(withId(R.id.nombres)).check(ViewAssertions.matches(withText("Jairo")));
    }

    private void escribirEdit(int id, String texto) {
        ViewInteraction placa = onView(withId(id));
        placa.perform(replaceText(texto));
    }

}
