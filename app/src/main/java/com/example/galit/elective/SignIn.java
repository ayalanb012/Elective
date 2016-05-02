package com.example.galit.elective;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignIn extends AppCompatActivity {

    TextView tvData1;
    EditText edata1;
    EditText edata2;
    Button button;
    String student;
    String passwd;
    TextView tv_forgot;
    EditText et_forgot;
    Button bttn_forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        button = (Button) findViewById(R.id.bttn_sign_in);
        et_forgot = (EditText) findViewById(R.id.et_forgot_mail);
        et_forgot.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    final String email = et_forgot.getText().toString();
                    if (!isValidEmail(email)) {
                        et_forgot.setError("נא הכנס מייל תקין");
                    }
                }
            }
        });

//when log in button is pressed
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                edata1 = (EditText) findViewById(R.id.txt_mail);
                student = edata1.getText().toString();

                edata2 = (EditText) findViewById(R.id.txt_password);
                passwd = edata2.getText().toString();

                String check =  ServerCalls.signInCall(student,passwd);

                if(check.equals("True")) {

                    MainActivity.session.setusename(student);
                   // Toast toast = Toast.makeText(getApplicationContext(), "setting userName to: "+ student, Toast.LENGTH_LONG);
                    //toast.show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "שם משתמש או סיסמה לא נכונים", Toast.LENGTH_LONG);
                    toast.show();
                }

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

    //this method is activated when logo is clicked. the method return to main activity
    public void HomeClicked(View v) {
        ImageButton button = (ImageButton) v;
        //startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();

    }

//method that shows relevant control after user forgot his password
    public void ForgotPasswordClicked(View v)
    {
        tv_forgot = (TextView) findViewById(R.id.txt_forgot_mail);
        et_forgot = (EditText) findViewById(R.id.et_forgot_mail);
        bttn_forgot = (Button) findViewById(R.id.bttn_forgot_mail);
        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                if (tv_forgot.getVisibility() == View.VISIBLE) {
                    tv_forgot.setVisibility(View.INVISIBLE);
                    et_forgot.setVisibility(View.INVISIBLE);
                    bttn_forgot.setVisibility(View.INVISIBLE);
                } else {
                    tv_forgot.setVisibility(View.VISIBLE);
                    et_forgot.setVisibility(View.VISIBLE);
                    bttn_forgot.setVisibility(View.VISIBLE);
                }

            }
        });
    }

//sends mail to the user with new password
    public void SubmitForgotPassword(View v)
    {
        EditText et = (EditText) findViewById(R.id.et_forgot_mail);
        if(et.getError()==null && isValidEmail(et.getText().toString())) {
            Set<PasswordGenerator.PasswordCharacterSet> values = new HashSet<PasswordGenerator.PasswordCharacterSet>(EnumSet.allOf(SummerCharacterSets.class));
            PasswordGenerator pwGenerator = new PasswordGenerator(values, 6, 10);
            String s = pwGenerator.generatePassword().toString();
            String newPwd = s.substring(1);
            myTaskSendMail t = new myTaskSendMail(et,newPwd);
            t.execute();

            //server call to change the password in the DB
            ServerCalls.changeUserPassword(newPwd, et.getText().toString(), getApplicationContext());

            Toast toast = Toast.makeText(getApplicationContext(), "נשלחה סיסמה חדשה לאימייל שהקלדת", Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "הכנס מייל תקין", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    //this class sends mail in background thread via async task
    public static class myTaskSendMail extends AsyncTask<Void, Void, String> {
        EditText Mail;
        String s;
        String password;
        public  myTaskSendMail(EditText Mail,String password){
            this.Mail = Mail;
            s = Mail.getText().toString();
            this.password = password;
        }
        @Override
        protected String doInBackground(Void... params) {
            try {

                GMailSender sender = new GMailSender("elective.app@gmail.com", "password");
                sender.sendMail("אפליקציית קורסים כלליים- שחזור סיסמה",
                        "שלום, סיסמתך החדשה היא: "+"\u202C"+"\n"+password+"\u202B",
                        "elective.app@gmail.com",
                        s);

            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
            }
            return "";
        }
    }


    private enum SummerCharacterSets implements PasswordGenerator.PasswordCharacterSet {
        ALPHA_UPPER(ALPHA_UPPER_CHARACTERS, 1),
        ALPHA_LOWER(ALPHA_LOWER_CHARACTERS, 1),
        NUMERIC(NUMERIC_CHARACTERS, 1),
        SPECIAL(SPECIAL_CHARACTERS, 1);

        private final char[] chars;
        private final int minUsage;

        private SummerCharacterSets(char[] chars, int minUsage) {
            this.chars = chars;
            this.minUsage = minUsage;
        }

        @Override
        public char[] getCharacters() {
            return chars;
        }

        @Override
        public int getMinCharacters() {
            return minUsage;
        }
    }

    private static final char[] ALPHA_UPPER_CHARACTERS = { 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    private static final char[] ALPHA_LOWER_CHARACTERS = { 'a', 'b', 'c', 'd',
            'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
    private static final char[] NUMERIC_CHARACTERS = { '0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9' };
    private static final char[] SPECIAL_CHARACTERS = { '~', '`', '!', '@', '#',
            '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+', '[', '{',
            ']', '}', '\\', '|', ';', ':', '\'', '"', ',', '<', '.', '>', '/',
            '?' };

}