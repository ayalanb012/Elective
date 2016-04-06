package com.example.galit.elective;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public static Session session = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        if(session==null)
            session = new Session(getApplicationContext()); //first time - need to create session

        else { //the user is logged in
            TextView hello = (TextView) findViewById(R.id.hello_txt);
            String name = ServerCalls.getNameCall(session.getusename());
            hello.setText( " שלום " + name);
        }

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

    public void userClicked(View v)
    {
        ImageButton button = (ImageButton) v;
        startActivity(new Intent(getApplicationContext(), User_Profile.class));
    }

}
