package com.example.galit.elective;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

    }

    //this method is activated when sign in is clicked. the method opens signIn activity
    public void SignInClicked(View v)
    {
        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(), SignIn.class));

    }

    //this method is activated when registration is clicked. the method opens registrarion activity
    public void RegisterClicked(View v)
    {
        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(), registration.class));

    }

    //this method is activated when logo is clicked.
   // public void HomeClicked(View v)
   // {

        //ImageButton button = (ImageButton) v;
        //startActivity(new Intent(getApplicationContext(), MainActivity.class));

   // }

    //this method is activated when search is clicked. the method opens Search activity
    public void SearchClicked(View v)
    {
        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(), Search.class));
    }


    public void recommandClicked(View v)
    {
        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(), reccomand.class));

    }

}
