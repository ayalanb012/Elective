package com.example.galit.elective;

import android.content.Intent;
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
       // listView.setAdapter(new yourAdapter(getApplicationContext(), new String[] { "המוח 120 שנות מחקר", "יצירות מופת מעולם הספות","כדורעף מעורב" },new String[] { "7.3/10", "5.5/10","6.8/10" },new String[] { "7.3/10", "5.5/10","6.8/10" },new String[] { "7.3/10", "5.5/10","6.8/10" },new String[] { "7.3/10", "5.5/10","6.8/10" } ,new String[] { "7.3/10", "5.5/10","6.8/10" },new String[] { "7.3/10", "5.5/10","6.8/10" }));
        Intent caller = getIntent(); //the activity that started this activity (search page)
        ArrayList<String[]> list =  (ArrayList<String[]>) caller.getSerializableExtra("recommend results");

       //public yourAdapter(Context context, String[]name, String[]grades,String[]diffs,String[]interest,String[]lecture,String[]comments,String[]loads) {
        listView.setAdapter(new yourAdapter(getApplicationContext(),list.get(0),list.get(1),list.get(2),list.get(3),list.get(4),list.get(5),list.get(6)));
    }
}
