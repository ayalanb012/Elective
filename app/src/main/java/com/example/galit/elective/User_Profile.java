package com.example.galit.elective;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

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
        setListViewHeightBasedOnItems(listView);

        Toast toast = Toast.makeText(getApplicationContext(), MainActivity.session.getusename(), Toast.LENGTH_LONG);
        toast.show();
        ServerCalls.getUserDetailsCall(MainActivity.session.getusename(), name, pass, schedule, faculty, department, context);

    }

//this method sets view to the size of its children in order to avoid scrolling
    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ArrayAdapter listAdapter = (ArrayAdapter)listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }

    }
}
