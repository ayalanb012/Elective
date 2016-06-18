package com.example.galit.elective;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class course_page extends Activity {


    public static TextView interest;
    public static TextView diff;
    public static TextView lecture;
    public static TextView general;
    public static TextView description;
    public static TextView course_name;
    public static TextView location;
    public static TextView lecture_name;
    public static TextView credit_points;
    public  static  ListView list_view;
    public static  String course_num;
    public TextView CommentCount;
    public static TextView load_avg;
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
        location = (TextView) findViewById(R.id.location_txt);
        lecture_name = (TextView) findViewById(R.id.lecture_name);
        CommentCount = (TextView) findViewById(R.id.tv_commentCount);
        load_avg = (TextView) findViewById(R.id.load_avg);
        Intent caller = getIntent();
        String call = caller.getStringExtra("caller_activity");

        if(call.equals("search_result") || call.equals("Commment")){ //the activity that started this activity was search results page

             course_num  = caller.getStringExtra("Selected_course_num");
           // Toast toast2 = Toast.makeText(getApplicationContext(), "[" + course_num +"]", Toast.LENGTH_LONG);
            //toast2.show();
            String json = courses_controller.allcommentscall(course_num, c, list_view);
            list_view.setVisibility(View.INVISIBLE);
            int count=0;
            try {
                count = parseJson(json);
                CommentCount.setText("יש "+"\u202A"+count+"\u202C"+" תגובות");
                CommentCount.setVisibility(View.INVISIBLE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            courses_controller.getCourseDetails(course_num,c);

        }
        else { //-------------->> check if we came from a different activity, comment, need to add code

            //Toast toast1 = Toast.makeText(getApplicationContext(),"in the else: ["+ call+"]", Toast.LENGTH_LONG);
            //toast1.show();
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
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
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
        myintent.putExtra("caller_activity", "course_page");
        startActivity(myintent);
        finish();

    }

    public void AddToWishListClicked(View v)
    {
        String mail = MainActivity.session.getusename();
        Context c = getApplicationContext();
        courses_controller.addCourseToWishList(mail, course_num, c);
    }


    public void ShowCommentsClicked(View v)
    {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                if (list_view.getVisibility() == View.INVISIBLE) {
                    list_view.setVisibility(View.VISIBLE);
                    CommentCount.setVisibility(View.VISIBLE);

                }

            }
        });
    }
public int parseJson (String json) throws JSONException {
    JSONObject jObject = new JSONObject(json);

    List<RowItem> rowItems = new ArrayList<RowItem>();

    Iterator iter = jObject.keys();
    int count=0;
    while (iter.hasNext()) {
        count++;
        String key = (String) iter.next();
        String value = jObject.getString(key);
        String feedback = jObject.getJSONObject(key).getString("feedback");
        String diff = jObject.getJSONObject(key).getString("diff");
        String interest = jObject.getJSONObject(key).getString("interest");
        String rating = jObject.getJSONObject(key).getString("rating");
        String load = jObject.getJSONObject(key).getString("load");
        String lecture = jObject.getJSONObject(key).getString("lecture");
        RowItem item = new RowItem(key, feedback, rating, interest, diff,load,lecture);
        rowItems.add(item);

        //   Toast toast = Toast.makeText(context, "json: "+key+" "+value, Toast.LENGTH_LONG);

        // toast.show();

    }
    CustomListViewAdapter adapter = new CustomListViewAdapter(getApplicationContext(), R.layout.list_view_comment, rowItems);
    list_view.setAdapter(adapter);

    User_Profile.setListViewHeightBasedOnItems(list_view);
    return count;
}
}
