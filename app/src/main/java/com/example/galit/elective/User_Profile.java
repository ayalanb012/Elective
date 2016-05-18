package com.example.galit.elective;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class User_Profile extends AppCompatActivity {

    TextView tv_update;
    EditText et_update;
    TextView tv_update_v;
    EditText et_update_v;
    Button bttn_update;
    Activity user_profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__profile);
        EditText name = (EditText)findViewById(R.id.txt_name);
        EditText mail = (EditText)findViewById(R.id.txt_mail);
        EditText pass = (EditText)findViewById(R.id.txt_password);
        Spinner faculty = (Spinner)findViewById(R.id.spinner1);
        Spinner department = (Spinner)findViewById(R.id.spinner2);
        ListView listView = (ListView)findViewById(R.id.wish_list);
        TableLayout schedule = (TableLayout)findViewById(R.id.schedule);

        user_profile = this;

        Context context = getApplicationContext();
        //String[] popular_courses = {"a","b","c"};
        String[] popular_courses = {"היסטוריה","ספרות","מחשבים"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this ,R.layout.list_view_wish_list_layout,R.id.list_item,popular_courses);
        listView.setAdapter(adapter);
        setListViewHeightBasedOnItems(listView);

       // Toast toast = Toast.makeText(getApplicationContext(), MainActivity.session.getusename(), Toast.LENGTH_LONG);
        //toast.show();
        ServerCalls.getUserDetailsCall(MainActivity.session.getusename(),mail, name, schedule, faculty, department,listView, context,user_profile);




     /*   et_update.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    final String pass = et_update.getText().toString();
                    if (!isValidPassword(pass)) {
                        et_update.setError("password must be at least 5 characters");
                    }
                }
            }
        });

        et_update_v.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    final String pass1 = et_update.getText().toString();
                    final String pass2 = et_update_v.getText().toString();
                    if (!isConfirmedPassword(pass1, pass2)) {
                        et_update_v.setError("invalid confirmation of password");
                    }
                }
            }
        }); */

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

//this method sets view to the size of its children in order to avoid scrolling
    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ArrayAdapter listAdapter = (ArrayAdapter)listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }

    }
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


    public void SubmitUpdatePassword(View v)
    {
        EditText et = (EditText) findViewById(R.id.et_update_pass);

            //server call to change the password in the DB
            ServerCalls.changeUserPassword(et.getText().toString(), MainActivity.session.getusename(), getApplicationContext());

            Toast toast = Toast.makeText(getApplicationContext(), "הסיסמא עודכנה", Toast.LENGTH_LONG);
            toast.show();
        }


    //method that shows relevant control after user forgot his password
    public void UpdatePasswordClicked(View v)
    {
        tv_update = (TextView) findViewById(R.id.txt_update_pass);
        tv_update_v = (TextView) findViewById(R.id.txt_update_pass_validate);
        et_update = (EditText) findViewById(R.id.et_update_pass);
        et_update_v = (EditText) findViewById(R.id.et_update_pass_validate);
        bttn_update = (Button) findViewById(R.id.bttn_update_pass);
        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                if (tv_update.getVisibility() == View.VISIBLE) {
                    tv_update.setVisibility(View.GONE);
                    tv_update_v.setVisibility(View.GONE);
                    et_update.setVisibility(View.GONE);
                    et_update_v.setVisibility(View.GONE);
                    bttn_update.setVisibility(View.GONE);

                } else {
                    tv_update.setVisibility(View.VISIBLE);
                    tv_update_v.setVisibility(View.VISIBLE);
                    et_update.setVisibility(View.VISIBLE);
                    et_update_v.setVisibility(View.VISIBLE);
                    bttn_update.setVisibility(View.VISIBLE);
                }

            }
        });
    }

}
