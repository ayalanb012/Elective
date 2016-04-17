package com.example.galit.elective;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by User on 06/04/2016.
 */
public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {

        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
//        prefs.edit().putString("loggedIn","False");
    }

    public void setusename(String usename) {
        prefs.edit().putString("usename", usename).commit();
        prefs.edit().putString("loggedIn","True").commit();
        //prefs.Commit();
    }

    public void logout(){
        prefs.edit().putString("usename", "").commit();
        prefs.edit().putString("loggedIn","False").commit();
    }

    public String isLoggedIn() {
        return prefs.getString("loggedIn","");
    }

    public String getusename() {
        String usename = prefs.getString("usename","");
        return usename;
    }
}
