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
        listView.setAdapter(new yourAdapter(getApplicationContext(), new String[] { "המוח 120 שנות מחקר", "יצירות מופת מעולם הספות","כדורעף מעורב" },new String[] { "7.3/10", "5.5/10","6.8/10" },new String[] { "7.3/10", "5.5/10","6.8/10" },new String[] { "7.3/10", "5.5/10","6.8/10" },new String[] { "7.3/10", "5.5/10","6.8/10" } ,new String[] { "7.3/10", "5.5/10","6.8/10" },new String[] { "7.3/10", "5.5/10","6.8/10" }));


    }
}
