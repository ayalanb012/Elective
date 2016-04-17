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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        ServerCalls.getFacultiesCall(FacultySpinner, ctx,null);

        FacultySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
               // Toast toast = Toast.makeText(getApplicationContext(), "selected faculty:" + item, Toast.LENGTH_LONG);
                //toast.show();

                Spinner DepartmentSpinner = (Spinner) findViewById(R.id.spinner2);
                ServerCalls.setDepartments(DepartmentSpinner,getApplicationContext(),item,null);
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
        email_text = (EditText) findViewById(R.id.txt_mail);
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
       JSONObject Sunday = new JSONObject();
        for(int i=8; i <= 20 ; i++){ //for each hour in schedule

            String hour = "";
            String trID1="tr_sun_"+i;
            int trID2 = getResources().getIdentifier(trID1,"id","com.example.galit.elective");
            TableRow tr =(TableRow) findViewById(trID2);

            ColorDrawable test = (ColorDrawable)tr.getBackground();
            int background = Color.parseColor("#6476B6");
            int current = (test).getColor();

            if (current!=background) //if the cell is white
                hour="false";
            else //the cell is blue
                hour= "true";

            try {
                Sunday.put(String.valueOf(i),hour);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        JSONObject Monday_Arr = new JSONObject();
        for(int i=8; i <= 20 ; i++){ //for each hour in schedule

            String hour = "";
            String trID1="tr_mon_"+i;
            int trID2 = getResources().getIdentifier(trID1,"id","com.example.galit.elective");
            TableRow tr =(TableRow) findViewById(trID2);

            ColorDrawable test = (ColorDrawable)tr.getBackground();
            int background = Color.parseColor("#6476B6");
            int current = (test).getColor();

            if (current!=background) //if the cell is white
                hour="false";
            else //the cell is blue
                hour= "true";

            try {
                Monday_Arr.put(String.valueOf(i),hour);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        JSONObject Tuesday_Arr = new JSONObject();
        for(int i=8; i <= 20 ; i++){ //for each hour in schedule

            String hour = "";
            String trID1="tr_tue_"+i;
            int trID2 = getResources().getIdentifier(trID1,"id","com.example.galit.elective");
            TableRow tr =(TableRow) findViewById(trID2);

            ColorDrawable test = (ColorDrawable)tr.getBackground();
            int background = Color.parseColor("#6476B6");
            int current = (test).getColor();

            if (current!=background) //if the cell is white
                hour="false";
            else //the cell is blue
                hour= "true";

            try {
                Tuesday_Arr.put(String.valueOf(i),hour);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        JSONObject Wednesday_Arr = new JSONObject();
        for(int i=8; i <= 20 ; i++){ //for each hour in schedule

            String hour = "";
            String trID1="tr_wed_"+i;
            int trID2 = getResources().getIdentifier(trID1,"id","com.example.galit.elective");
            TableRow tr =(TableRow) findViewById(trID2);

            ColorDrawable test = (ColorDrawable)tr.getBackground();
            int background = Color.parseColor("#6476B6");
            int current = (test).getColor();

            if (current!=background) //if the cell is white
                hour="false";
            else //the cell is blue
                hour= "true";

            try {
                Wednesday_Arr.put(String.valueOf(i),hour);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        JSONObject Thursday_Arr = new JSONObject();
        for(int i=8; i <= 20 ; i++){ //for each hour in schedule

            String hour = "";
            String trID1="tr_thu_"+i;
            int trID2 = getResources().getIdentifier(trID1,"id","com.example.galit.elective");
            TableRow tr =(TableRow) findViewById(trID2);

            ColorDrawable test = (ColorDrawable)tr.getBackground();
            int background = Color.parseColor("#6476B6");
            int current = (test).getColor();

            if (current!=background) //if the cell is white
                hour="false";
            else //the cell is blue
                hour= "true";

            try {
                Thursday_Arr.put(String.valueOf(i),hour);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        JSONObject Friday_Arr = new JSONObject();
        for(int i=8; i <= 20 ; i++) { //for each hour in schedule

            String hour = "";
            String trID1 = "tr_fri_" + i;
            int trID2 = getResources().getIdentifier(trID1, "id", "com.example.galit.elective");
            TableRow tr = (TableRow) findViewById(trID2);

            ColorDrawable test = (ColorDrawable) tr.getBackground();
            int background = Color.parseColor("#6476B6");
            int current = (test).getColor();

            if (current != background) //if the cell is white
                hour = "false";
            else //the cell is blue
                hour = "true";

            try {
                Friday_Arr.put(String.valueOf(i), hour);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        JSONArray Schedule_Arr = new JSONArray(); //the whole schedule ---> we will add all the days

        Schedule_Arr.put(Sunday);
        Schedule_Arr.put(Monday_Arr);
        Schedule_Arr.put(Tuesday_Arr);
        Schedule_Arr.put(Wednesday_Arr);
        Schedule_Arr.put(Thursday_Arr);
        Schedule_Arr.put(Friday_Arr);

        String Schedule_Json_str = Schedule_Arr.toString(); // a JSON string that contains the schedule

        System.out.println(Schedule_Json_str);

       // Toast toast = Toast.makeText(getApplicationContext(), Schedule_Json_str, Toast.LENGTH_LONG);
       // toast.show();

        if(validate_form()) {
            Button button = (Button) v;

            Spinner Faculty_sp = (Spinner) findViewById(R.id.spinner1);
            String Faculty;
            if(Faculty_sp.getSelectedItem()!= null)
                 Faculty = Faculty_sp.getSelectedItem().toString();
            else
                Faculty = "";

            Spinner Department_sp = (Spinner) findViewById(R.id.spinner2);
            String Department;
            if(Department_sp.getSelectedItem() != null)
                 Department = Department_sp.getSelectedItem().toString();
            else
                Department="";

             EditText name_ET =(EditText) findViewById(R.id.txt_name);
            String name = name_ET.getText().toString();

            EditText mail_ET =(EditText) findViewById(R.id.txt_mail);
            String mail = mail_ET.getText().toString();

            EditText pwd_ET =(EditText) findViewById(R.id.txt_password);
            String pwd = pwd_ET.getText().toString();

            Context ctx = getApplicationContext();
            ServerCalls.Register(ctx,name, mail, pwd, Faculty, Department, Schedule_Json_str, email_text); //server call to register the user
            if(validate_form()) {
                startActivity(new Intent(getApplicationContext(), SignIn.class)); //move user to sign in page
                finish();
            }

        }

    }

    //this method is activated when the user clickes a table row on the table
    //the method changes the background color of the current tablerow (a cell in the table)
    public void table_row_clicked(View v)
    {
        TableRow tr = (TableRow)v;
        int background = Color.parseColor("#6476B6");
        ColorDrawable test = (ColorDrawable)tr.getBackground();
        int current = (test).getColor();
        if (current!=background) //if the cell is white
            tr.setBackgroundColor(Color.rgb(100, 118, 182)); //turn to blue
        else //the cell is blue
            tr.setBackgroundColor(Color.rgb(255, 255, 255)); //turn to white

    }



}
