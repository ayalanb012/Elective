package com.example.galit.elective;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class critiqueCourse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critique_course);

        Intent caller = getIntent();
        final String  title  = caller.getStringExtra("title");
        final String  grade  = caller.getStringExtra("grade");
        final String  load  = caller.getStringExtra("load");
        final String  diff  = caller.getStringExtra("diff");
        final String  lecturer  = caller.getStringExtra("lecturer");
        final String  interest  = caller.getStringExtra("interest");
        final String  comments  = caller.getStringExtra("comments");
      
    }







    public void userClicked(View v)
    {
        if(MainActivity.session.isLoggedIn().equals("True"))
            startActivity(new Intent(getApplicationContext(), User_Profile.class));
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "אנא התחבר/הרשם על מנת לצפות בפרופיל", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
