package com.example.galit.elective;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class recommend_results extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_results);

        ListView listView = (ListView)findViewById(R.id.recommend_results_list);
        listView.setAdapter(new yourAdapter(this, new String[] { "data1", "data2","data3" }));


    }
}
