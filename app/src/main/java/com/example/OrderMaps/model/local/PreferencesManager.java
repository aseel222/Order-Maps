package com.example.OrderMaps.model.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.splashactivity.R;

import org.intellij.lang.annotations.Language;


public class PreferencesManager {
    String USER_TOKEN  = "Some token From Server";
    String USER_ID  = "USER_ID";
    String LANGUAGE_KEY = "LANGUAGE_KEY";
    String FCM_TOKEN="fcm";
    String USER_KEY = "USER_KEY";
    String STATUS="status";
    public SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    boolean tgpref;

    public PreferencesManager(Context _context) {
        this._context = _context;
        pref = _context.getSharedPreferences(_context.getString(R.string.app_name), Context.MODE_PRIVATE);



    }

    public void savetoken( String token) {
       editor=pref.edit();
       editor.putString(USER_TOKEN , token);
        editor.apply();
    }
    public void savestatus( boolean status) {
        editor=pref.edit();
        editor.putBoolean(STATUS ,status);
        editor.apply();
    }
    public boolean  getstatus(){
        return pref.getBoolean(STATUS,true);
    }


    public void savefcmtoken( String token) {
        editor=pref.edit();
        editor.putString(FCM_TOKEN , token);
        editor.apply();
    }
    public String fetchtoken(){
        return pref.getString(USER_TOKEN,null);
    }
    public String fetchfcmtoken(){
        return pref.getString(FCM_TOKEN,null);
    }

    public boolean islogin(){
        if(pref.contains(USER_TOKEN)){
            return true;
        }
        return false;
    }


    public void signout(){
        pref.edit().remove(USER_TOKEN).apply();
    }
    public void signout2(){
        pref.edit().remove(FCM_TOKEN).apply();
    }



    public  void saveUserID( String id) {
        editor=pref.edit();
        editor.putString(USER_ID , id);
        editor.apply();
    }
    public String getUserID(){
        return pref.getString(USER_ID,null);
    }

    public  void saveLanguage(Language language) {
        editor=pref.edit();
        editor.putString(LANGUAGE_KEY , language.toString());
        editor.apply();
    }
    public String getLanguage(){
        return pref.getString(LANGUAGE_KEY,null);
    }

    public  void setUser(String user) {
        editor=pref.edit();
        editor.putString(USER_KEY , user);
        editor.apply();
    }
    public String getUser(){
        return pref.getString(USER_KEY,null);
    }

}
