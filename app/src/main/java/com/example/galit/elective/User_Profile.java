package com.example.galit.elective;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;

public class User_Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__profile);
        EditText name = (EditText)findViewById(R.id.txt_name);
        EditText mail = (EditText)findViewById(R.id.txt_mail);
        EditText pass = (EditText)findViewById(R.id.txt_password);
        Spinner faculty = (Spinner)findViewById(R.id.spinner1);
        Spinner department = (Spinner)findViewById(R.id.spinner2);
        ListView listView = (ListView)findViewById(R.id.wish_list);
        TableLayout schedule = (TableLayout)findViewById(R.id.schedule);

        Context context = getApplicationContext();
        //String[] popular_courses = {"a","b","c"};
        String[] popular_courses = {"היסטוריה","ספרות","מחשבים"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this ,R.layout.list_view_wish_list_layout,R.id.list_item,popular_courses);

        listView.setAdapter(adapter);
        ServerCalls.getUserDetailsCall("galit.naim@gmail.com", name, pass, schedule, faculty, department, context);

    }
}
