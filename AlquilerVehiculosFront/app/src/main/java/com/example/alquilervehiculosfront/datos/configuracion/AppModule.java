package com.example.alquilervehiculosfront.datos.configuracion;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import dagger.android.DaggerApplication;

@Module
public class AppModule {

    DaggerApplication application;

    public AppModule(DaggerApplication application) {
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
