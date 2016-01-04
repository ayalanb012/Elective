package com.example.galit.elective;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableRow;

public class registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }


    public void HomeClicked(View v)
    {

        ImageButton button = (ImageButton) v;
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void table_row_clicked(View v)
    {


         TableRow tr = (TableRow)v;
        int color = Color.TRANSPARENT;
        Drawable background = v.getBackground();
        ColorDrawable cd = new ColorDrawable(0xFF8EB0B9);
        if (background==cd)
            tr.setBackgroundColor(Color.WHITE);
        tr.setBackgroundColor(0xFF5F6EA9);

    }
}
