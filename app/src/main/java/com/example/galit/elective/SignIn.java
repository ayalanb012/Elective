package com.example.galit.elective;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    //this method is activated when logo is clicked. the method return to main activity
    public void HomeClicked(View v)
    {
        ImageButton button = (ImageButton) v;
        //startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();

    }
}
