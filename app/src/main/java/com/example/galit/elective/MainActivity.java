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
    Button signInBttn;

    TextView hello;
    public static Session session = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        signInBttn = (Button) findViewById(R.id.bttn_sign_in);

        setContentView(R.layout.activity_main);
        if(session==null) {
            session = new Session(getApplicationContext()); //first time - need to create session

          //  signInBttn.setText("התחברות");

        }
        else if (session.isLoggedIn().equals("True")){ //the user is logged in
            hello = (TextView) findViewById(R.id.hello_txt);
            String name = ServerCalls.getNameCall(session.getusename());
            hello.setText( " שלום " + name);

            //signInBttn.setText("התנתק");

        }
        else {
          //  signInBttn.setText("התחברות");
        }
    }

    //this method is activated when sign in is clicked. the method opens signIn activity
    public void SignInClicked(View v)
    {

        if(session.isLoggedIn().equals("False"))   //user wants to sign in
            startActivity(new Intent(getApplicationContext(), SignIn.class));
        else    //sign out user
        {
            session.logout();
            hello.setText("שלום");
        }
    }

    //this method is activated when registration is clicked. the method opens registrarion activity
    public void RegisterClicked(View v)
    {
        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(), registration.class));

    }



    //this method is activated when search is clicked. the method opens Search activity
    public void SearchClicked(View v)
    {
      if (session.isLoggedIn().equals("True"))
        startActivity(new Intent(getApplicationContext(), Search.class));
        else {
          Toast toast = Toast.makeText(getApplicationContext(), "צריך להיות מחובר כדי לבצע חיפוש", Toast.LENGTH_LONG);
          toast.show();
      }
    }


    public void recommandClicked(View v)
    {
        if (session.isLoggedIn().equals("True"))
        startActivity(new Intent(getApplicationContext(), reccomand.class));
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "צריך להיות מחובר כדי לקבל המלצה", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void userClicked(View v)
    {
        if(session.isLoggedIn().equals("True"))
            startActivity(new Intent(getApplicationContext(), User_Profile.class));
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "אנא התחבר/הרשם על מנת לצפות בפרופיל", Toast.LENGTH_LONG);
            toast.show();
        }
    }

}
