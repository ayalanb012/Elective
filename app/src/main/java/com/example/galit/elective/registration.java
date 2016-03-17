package com.example.galit.elective;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableRow;

public class registration extends AppCompatActivity {

    EditText et1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

         et1 = (EditText) findViewById(R.id.txt_name);


        et1.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    et1.setTextColor(Color.BLACK);
                }
                else
                    et1.setTextColor(Color.GRAY);
            }
        });
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


    public void whole_row_clicked(View v)
    {
        switch(v.getId()) {
            case R.id.tr_sunday:

                break;
            case R.id.tr_monday:

                break;
            case R.id.tr_tuesday:
            case R.id.tr_wednesday:
            case R.id.tr_thursday:
            case R.id.tr_friday:
        }
    }
}
