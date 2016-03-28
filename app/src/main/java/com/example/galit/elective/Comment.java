package com.example.galit.elective;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
    }

    public void onClick(View v)
    {
        try {
            name = (TextView) findViewById(R.id.name);
            String name_string = name.getText().toString();
            grade = (TextView) findViewById(R.id.grade);
            String grade_string = grade.getText().toString();
            interest = (TextView) findViewById(R.id.interest);
            String interest_string = interest.getText().toString();
            //lecture = (TextView) findViewById(R.id.lecture);
            //String lecture_string = lecture.getText().toString();
            diff = (TextView) findViewById(R.id.diff);
            String diff_string = diff.getText().toString();
            comment = (TextView) findViewById(R.id.comment);
            String comment_string = comment.getText().toString();


            Intent myintent = new Intent(this, course_page.class);
            myintent.putExtra("name", name_string);
            myintent.putExtra("grade", grade_string);
            myintent.putExtra("interest", interest_string);
            //myintent.putExtra("lecture", lecture_string);
            myintent.putExtra("diff", diff_string);
            myintent.putExtra("comment", comment_string);

            startActivity(myintent);
            finish();
        }
        catch (Exception e)
        {
            Toast t =Toast.makeText(getApplicationContext(),e.getStackTrace().toString(),Toast.LENGTH_LONG);
            t.show();

        }

    }
}


