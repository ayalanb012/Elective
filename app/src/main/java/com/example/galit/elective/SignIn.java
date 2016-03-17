package com.example.galit.elective;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class SignIn extends AppCompatActivity {

    TextView tvData1;
    EditText edata1;
    EditText edata2;
    Button button;
    String student;
    String passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        tvData1 = (TextView) findViewById(R.id.textView1);
        button = (Button) findViewById(R.id.bttn_sign_in);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                edata1 = (EditText) findViewById(R.id.txt_mail);
                student = edata1.getText().toString();


                edata2 = (EditText) findViewById(R.id.txt_password);
                passwd = edata2.getText().toString();

                ServerCalls.signInCall(student,passwd,tvData1);

            }
        });
    }

    //this method is activated when logo is clicked. the method return to main activity
    public void HomeClicked(View v) {
        ImageButton button = (ImageButton) v;
        //startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();

    }


}