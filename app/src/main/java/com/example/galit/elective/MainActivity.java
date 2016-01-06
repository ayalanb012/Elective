package com.example.galit.elective;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    Intent sign_in;

    public MainActivity() {
        sign_in = new Intent(getApplicationContext(), SignIn.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void SignInClicked(View v)
    {

        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(), SignIn.class));

    }

    public void RegisterClicked(View v)
    {
        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(), registration.class));

    }
    public void HomeClicked(View v)
    {

        ImageButton button = (ImageButton) v;
        startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }

}
