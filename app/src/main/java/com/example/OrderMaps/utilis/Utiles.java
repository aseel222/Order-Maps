package com.example.OrderMaps.utilis;

import android.content.Context;




import com.example.OrderMaps.model.local.PreferencesManager;
import com.example.splashactivity.R;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;


import java.io.IOException;
import java.net.SocketException;
import java.util.Locale;

public class Utiles {

    public static Language getAppLanguage(Context context) {
        String language = new PreferencesManager(context).getLanguage();

        if ((language == null) ? Locale.getDefault().getLanguage().equals(Language.ar.toString())
                : language.equals(Language.ar.toString())) {
            return Language.ar;
        } else return Language.en;
    }

    public static String getTextForAppLanguage(Context context, String en, String ar) {
        return getAppLanguage(context) == Language.en ? en : ar;
    }
    public static int getMessageErrorRes(Throwable e) {
        if (e instanceof HttpException) {
            switch (((HttpException) e).code()) {
                case 400:
                    return R.string.bad_request;
                case 401:
                    return R.string.unauthorized_user;
                case 403:
                    return R.string.request_forbidden;
                case 404:
                    return R.string.request_not_found;
                case 500:
                    return R.string.server_error;
                default:
                    return R.string.unexpected_error;
            }
        } else if (e instanceof SocketException) {
            return R.string.not_connected_to_network;
        } else if (e instanceof IOException) {
            return R.string.problem_in_network;
        } else
            return R.string.unexpected_error;
    }

//    public static int getMessageErrorRes(Throwable e) {
//        if (e instanceof HttpException) {
//            switch (((HttpException) e).code()) {
//                case 400:
//                    return R.string.bad_request;
//                case 401:
//                    return R.string.unauthorized_user;
//                case 403:
//                    return R.string.request_forbidden;
//                case 404:
//                    return R.string.request_not_found;
//                case 500:
//                    return R.string.server_error;
//                default:
//                    return R.string.unexpected_error;
//            }
//        } else if (e instanceof SocketException) {
//            return R.string.not_connected_to_network;
//        } else if (e instanceof IOException) {
//            return R.string.problem_in_network;
//        } else
//            return R.string.unexpected_error;
//    }
//












}
