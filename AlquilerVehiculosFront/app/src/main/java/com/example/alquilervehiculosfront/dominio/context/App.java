package com.example.alquilervehiculosfront.dominio.context;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    public static void setmContext(Context mContext) {
        App.mContext = mContext;
    }
}