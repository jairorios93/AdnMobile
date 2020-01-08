package com.example.alquilervehiculosfront.dominio.inyeccion;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import dagger.android.DaggerApplication;

@Module
public class ModuloApp {

    public DaggerApplication application;

    public ModuloApp(DaggerApplication application) {
        this.application = application;
    }

    @Provides
    DaggerApplication provideDaggerApplication() {
        return application;
    }

    @Provides
    Context provideApplicationContext() {
        return application.getApplicationContext();
    }
}