package com.example.galit.elective;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class course_page extends Activity {
    ArrayList<String> titles;
    ArrayList<String> descs;
    ArrayList<String> diffs;
    ArrayList<String> interests;
    ArrayList<String> grades;
    ListView list;
    List<RowItem> rowItems;

    public static TextView interest;
    public static TextView diff;
    public static TextView lecture;
    public static TextView general;
    public static TextView description;
    public static TextView course_name;
    public static TextView credit_points;
    public  static  ListView list_view;
    public static   String course_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);
        Context c = getApplicationContext();


        interest =(TextView) findViewById(R.id.interest_grade);
        diff =(TextView) findViewById(R.id.diff_grade);
        lecture =(TextView) findViewById(R.id.lecture_grade);
        general =(TextView) findViewById(R.id.general_grade);
        description =(TextView) findViewById(R.id.txt_discription);
        course_name =(TextView) findViewById(R.id.course_name);
        credit_points =(TextView) findViewById(R.id.credit_points);
        list_view =(ListView) findViewById(R.id.listView);
        Intent caller = getIntent();
        String call = caller.getStringExtra("caller_activity");

        if(call.equals("search_result")){ //the activity that started this activity was search results page

             course_num  = caller.getStringExtra("Selected_course_num");
           // Toast toast2 = Toast.makeText(getApplicationContext(), "[" + course_num +"]", Toast.LENGTH_LONG);
            //toast2.show();
            ServerCalls.allcommentscall(course_num, c, list_view);
            ServerCalls.getCourseDetails(course_num,c);

        }
        else { //-------------->> check if we came from a different activity, comment, need to add code

            Toast toast1 = Toast.makeText(getApplicationContext(),"in the else: ["+ call+"]", Toast.LENGTH_LONG);
            toast1.show();
        }

        list_view.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });


    }

    //this method is activated when logo is clicked. the method return to main activity
    public void HomeClicked(View v)
    {

        ImageButton button = (ImageButton) v;
        // startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    public void userClicked(View v)
    {
        ImageButton button = (ImageButton) v;
        startActivity(new Intent(getApplicationContext(), User_Profile.class));
    }


    public void CritiquingClicked(View v)
    {
        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(), critiquing.class));

    }

    //this method is activted when add a comment button is clicked.
    public void CommentClicked(View v)
    {
        Intent myintent = new Intent(getApplicationContext(), Comment.class);
        myintent.putExtra("Selected_course_num",course_num);
        myintent.putExtra("caller_activity","course_page");
        startActivity(myintent);
        finish();

    }
}
