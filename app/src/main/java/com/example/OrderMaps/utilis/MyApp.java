package com.example.OrderMaps.utilis;

import android.app.Application;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new InternetConnection(getApplicationContext());
    }
}
