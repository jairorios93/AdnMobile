package com.example.alquilervehiculosfront;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class PageObject {

    public void abrirMenu() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
    }

    public void sleep(int segundos) throws InterruptedException {
        Thread.sleep(segundos * 1000);
    }

    public void navegarMenu(int idActivity, int idFragment) {
        onView(withId(idActivity)).perform(NavigationViewActions.navigateTo(idFragment));
    }

    public void escribirEdit(int id, String texto) {
        ViewInteraction editText = onView(withId(id));
        editText.perform(replaceText(texto));
    }

    public void clickBoton(int idBoton) {
        onView(withId(idBoton)).perform(click());
    }

    public void matchToast(int idMensaje) {
        onView(withText(idMensaje)).inRoot(new ToastMatcher())
                .check(ViewAssertions.matches(isDisplayed()));
    }

    public void matchEdit(int idEdit, String mensaje) {
        onView(withId(idEdit)).check(ViewAssertions.matches(withText(mensaje)));
    }
}