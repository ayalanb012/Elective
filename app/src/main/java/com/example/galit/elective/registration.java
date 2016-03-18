package com.example.galit.elective;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registration extends AppCompatActivity  {

    EditText email_text;
    EditText password_text;
    EditText conf_password_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Context ctx = getApplicationContext();

        email_text = (EditText) findViewById(R.id.txt_mail);
        password_text = (EditText) findViewById(R.id.txt_password);
        conf_password_text = (EditText) findViewById(R.id.txt_cnfrm_password);
        email_text.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    final String email = email_text.getText().toString();
                    if (!isValidEmail(email)) {
                        email_text.setError("Invalid Email");
                    }
                }


            }
        });
        password_text.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    final String pass = password_text.getText().toString();
                    if (!isValidPassword(pass)) {
                        password_text.setError("password must be at least 5 characters");
                    }
                }
            }
        });


        conf_password_text.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    final String pass1 = password_text.getText().toString();
                    final String pass2 = conf_password_text.getText().toString();
                    if (!isConfirmedPassword(pass1, pass2)) {
                        conf_password_text.setError("invalid confirmation of password");
                    }
                }
            }
        });

        Spinner FacultySpinner = (Spinner) findViewById(R.id.spinner1);


        ServerCalls.getFacultiesCall(FacultySpinner, ctx);

        FacultySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
               // Toast toast = Toast.makeText(getApplicationContext(), "selected faculty:" + item, Toast.LENGTH_LONG);
                //toast.show();

                Spinner DepartmentSpinner = (Spinner) findViewById(R.id.spinner2);
                ServerCalls.setDepartments(DepartmentSpinner,getApplicationContext(),item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }



    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 5) {
            return true;
        }
        return false;
    }
    //validating confirmed password
    private boolean isConfirmedPassword(String pass1, String pass2) {
        if (pass1.equals(pass2)) {
            return true;
        }
        return false;
    }

    //this method checks if all the form is valid
    public boolean validate_form()
    {
        if (email_text.getError()!=null || password_text.getError()!=null  || conf_password_text.getError()!=null)
            return  false;
        if (password_text.getText().toString().equals("") || email_text.getText().toString().equals("") || conf_password_text.getText().toString().equals(""))
            return false;
        return  true;
    }

    //this method is activated when logo is clicked. the method return to main activity
    public void HomeClicked(View v)
    {

        ImageButton button = (ImageButton) v;
        // startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    public  void SubmitClicked(View v)
    {
        if(validate_form()) {
            Button button = (Button) v;

            startActivity(new Intent(getApplicationContext(), SignIn.class));
            finish();

        }

    }

    //this method is activated when the user clickes a table row on the table
    //the method changes the background color of the current tablerow
    public void table_row_clicked(View v)
    {
        TableRow tr = (TableRow)v;
        int background = Color.parseColor("#6476B6");
        ColorDrawable test = (ColorDrawable)tr.getBackground();
        int current = (test).getColor();
        if (current!=background)
            tr.setBackgroundColor(Color.rgb(100, 118, 182));
        else
            tr.setBackgroundColor(Color.rgb(255, 255, 255));

    }



}
