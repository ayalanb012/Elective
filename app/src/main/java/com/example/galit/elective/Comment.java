package com.example.galit.elective;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//this class represents adding a comment for a certain course.
// this class calls ServerCalls which connects to the server and adds the comment
public class Comment extends Activity {

    TextView name;
    TextView grade;
    TextView interest;
    TextView lecture;
    TextView diff;
    TextView comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Button button = (Button) findViewById(R.id.button3);
        name = (TextView) findViewById(R.id.name);
        grade = (TextView) findViewById(R.id.grade);
        interest = (TextView) findViewById(R.id.interest);
        diff = (TextView) findViewById(R.id.diff);
        comment = (TextView) findViewById(R.id.comment);
        Intent caller = getIntent();
        final String  course_num  = caller.getStringExtra("Selected_course_num");
        //validation of grade
        grade.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    final String gr = grade.getText().toString();
                    if (!isValidGrade(gr)) {
                        grade.setError("ציון צריך להיות מספר בין 1 ל 10");
                    }
                }
            }
        });

        //validation of interest
        interest.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    final String gr = interest.getText().toString();
                    if (!isValidGrade(gr)) {
                        interest.setError("עניין צריך להיות מספר מ1 עד 10");
                    }
                }
            }
        });


        //validation of difficulty
        diff.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    final String gr = diff.getText().toString();
                    if (!isValidGrade(gr)) {
                        diff.setError("קושי צריך להיות מספר בין 1 ל 10");
                    }
                }
            }
        });


//when the button for adding a comment is clicked,we pass all parameters of the comment to server via serverCall class
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String mail  =MainActivity.session.getusename();
                String name_string = name.getText().toString();
                String grade_string = grade.getText().toString();
                String interest_string = interest.getText().toString();
                //lecture = (TextView) findViewById(R.id.lecture);
                //String lecture_string = lecture.getText().toString();
                String diff_string = diff.getText().toString();
                String comment_string = comment.getText().toString();
                Context c = getApplicationContext();
               // TextView tvData1 = (TextView) findViewById(R.id.textView23);
                ServerCalls.commentCall(mail, course_num, comment_string, interest_string, diff_string, grade_string, c);
                Intent myintent = new Intent(getApplicationContext(), course_page.class);
                myintent.putExtra("caller_activity", "Commment");
                myintent.putExtra("Selected_course_num",course_num);
                startActivity(myintent);
                finish();


            }
        });
    }

//this method checks if a string is empty or number between 1 and 5
    private boolean isValidGrade(String grade) {
        if (grade==null) {
            return true;
        }
        else if (isNumeric(grade) )
            if (Integer.parseInt(grade)<11 && Integer.parseInt(grade)>0)
                return  true;
        return false;
    }

    //this method checks if a string is a number
    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

}


