package com.example.galit.elective;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by User on 08/06/2016.
 */
public class critiqueSession {

    private SharedPreferences critiques;
    private SharedPreferences courses;
    private int index;

    public critiqueSession(Context cntx) {
        index=0;
        critiques = PreferenceManager.getDefaultSharedPreferences(cntx);
        critiques.edit().clear().commit();
        courses = PreferenceManager.getDefaultSharedPreferences(cntx);
        courses.edit().clear().commit();
    }

    public void clearCritiques() {
        critiques.edit().clear().commit();
        courses.edit().clear().commit();
    }

    public void addCritique(String critique,String courseName) {
        index++;
        critiques.edit().putString("" + index, critique).commit();
        critiques.edit().putString("" + index, courseName).commit();
        //prefs.Commit();
    }


    public String getCritiques() {

        Map<String,?> keys = critiques.getAll();
        String[] res = new String[keys.size()];
        //System.out.print("critique number: " + keys.size());
        int i=0;
        JSONObject json_res = new JSONObject();
        for(Map.Entry<String,?> entry : keys.entrySet()){
            res[i] =entry.getKey() + "-" + entry.getValue();
            i++;
            try {
                json_res.put(entry.getKey(), entry.getValue().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        System.out.println(json_res.toString());

        return json_res.toString();
    }


    public String getCourses() {

        Map<String,?> keys = courses.getAll();
        String[] res = new String[keys.size()];
        //System.out.print("critique number: " + keys.size());
        int i=0;
        JSONObject json_res = new JSONObject();
        for(Map.Entry<String,?> entry : keys.entrySet()){
            res[i] =entry.getKey() + "-" + entry.getValue();
            i++;
            try {
                json_res.put(entry.getKey(), entry.getValue().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        System.out.println(json_res.toString());

        return json_res.toString();
    }





}
