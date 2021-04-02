package com.example.OrderMaps.utilis;

import android.content.Context;
import android.net.ConnectivityManager;

public class InternetConnection {
   private static ConnectivityManager manager;


    public InternetConnection(Context context) {


        manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

    }


    public static boolean checkInternetConnection()
    {
       return manager.getActiveNetworkInfo()!=null&& manager.getActiveNetworkInfo().isConnected();
    }

}