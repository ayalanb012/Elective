package com.example.galit.elective;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class User_Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__profile);
        ListView listView = (ListView)findViewById(R.id.wish_list);
        //String[] popular_courses = {"a","b","c"};
        String[] popular_courses = {"היסטוריה","ספרות","מחשבים"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this ,R.layout.list_view_wish_list_layout,R.id.list_item,popular_courses);

        listView.setAdapter(adapter);
    }
}
