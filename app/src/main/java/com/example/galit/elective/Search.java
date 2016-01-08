package com.example.galit.elective;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableRow;
//The search class is for searching courses
// by various types of search: by name, constraint of schedule or by category. can be combined
public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    //this method is activated when logo is clicked. the method return to main activity
    public void HomeClicked(View v)
    {
        ImageButton button = (ImageButton) v;
        // startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    //this method is activated when the user clickes a table row on the table
    //the method changes the background color of the current tablerow
    public void table_row_clicked(View v)
    {
        TableRow tr = (TableRow)v;
        tr.setBackgroundColor(0xFF5F6EA9);
    }
}
