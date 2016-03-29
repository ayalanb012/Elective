package com.example.galit.elective;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;

public class reccomand extends AppCompatActivity {

    private ArrayList<HashMap<String, String>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reccomand);
        ListView listView = (ListView)findViewById(R.id.search_results_listview);
        //String[] popular_courses = {"a","b","c"};
           String[] popular_courses = {"היסטוריה","ספרות","מחשבים"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this ,R.layout.list_view_costume_layout,R.id.list_item,popular_courses);

        listView.setAdapter(adapter);
    }

    //this method is activated when logo is clicked. the method return to main activity
    public void HomeClicked(View v)
    {

        ImageButton button = (ImageButton) v;
        // startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    public void CourseClicked(View v) //------------------->>>>need to change this to the relevant course page, by inserting the course data into the page
    {
        //Button button = (Button) v;
       TextView course = (TextView) v;
        startActivity(new Intent(getApplicationContext(), course_page.class));
    }


}
