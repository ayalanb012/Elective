package com.example.galit.elective;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class reccomand extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reccomand);
        ListView listView = (ListView)findViewById(R.id.listView);
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


}
