package com.example.galit.elective;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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


        setContentView(R.layout.activity_main);
        signInBttn = (Button) findViewById(R.id.bttn_sign_in);
        if(session==null) {
            session = new Session(getApplicationContext()); //first time - need to create session

          //  signInBttn.setText("התחברות");

        }
        else if (session.isLoggedIn().equals("True")){ //the user is logged in
            hello = (TextView) findViewById(R.id.hello_txt);
            String name = ServerCalls.getNameCall(session.getusename());
            hello.setText(" שלום " + name);
            //SetButtonText();
            //myTaskChangetextInButtom task = new myTaskChangetextInButtom(signInBttn);
            //task.execute();
            //signInBttn.setText("התנתק");

        }
        else {

            //myTaskChangetextInButtom task = new myTaskChangetextInButtom(signInBttn);
            //task.execute();
         //   SetButtonText();
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
            hello = (TextView) findViewById(R.id.hello_txt);
            hello.setText(" שלום " );
           // startActivity(new Intent(getApplicationContext(), SignIn.class));
        }
    }

    //this method is activated when registration is clicked. the method opens registrarion activity
    public void RegisterClicked(View v)
    {
        if(session.isLoggedIn().equals("False"))
            startActivity(new Intent(getApplicationContext(), registration.class));
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(),"נא התנתק כדי להירשם", Toast.LENGTH_LONG);
            toast.show();
        }

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
        startActivity(new Intent(getApplicationContext(), recommendMain.class));
        //    startActivity(new Intent(getApplicationContext(), critiqueCourse.class));
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

    public void SetButtonText()
    {
        MainActivity.this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if(session!=null) {
                    signInBttn = (Button) findViewById(R.id.bttn_sign_in);
                    if (session.isLoggedIn().equals("True")) {
                        signInBttn.setText("התנתק");
                    }
                    else {
                        signInBttn.setText("התחברות");
                    }
                }
            }
        });

    }

    public static class myTaskChangetextInButtom extends AsyncTask<Void, Void, String> {
        Button signInBttn;
        public  myTaskChangetextInButtom(Button signInBttn){
            this.signInBttn  = signInBttn;
            if(session!=null) {
                if (session.isLoggedIn().equals("True")) {
                    signInBttn.setText("התנתק");
                }
                else {
                    signInBttn.setText("התחברות");
                }
            }
        }
        @Override
        protected String doInBackground(Void... params) {
        return "";
        }
    }

}
