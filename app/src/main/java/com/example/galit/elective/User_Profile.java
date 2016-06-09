package com.example.galit.elective;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class User_Profile extends AppCompatActivity {

    TextView tv_update;
    EditText et_update;
    TextView tv_update_v;
    EditText et_update_v;
    Button bttn_update;
    Activity user_profile;
    Spinner faculty;
    Spinner department;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__profile);
        EditText name = (EditText)findViewById(R.id.txt_name);
        EditText mail = (EditText)findViewById(R.id.txt_mail);
        faculty = (Spinner)findViewById(R.id.spinner1);
        department = (Spinner)findViewById(R.id.spinner2);
        ListView wishList = (ListView)findViewById(R.id.wish_list);
        TableLayout schedule = (TableLayout)findViewById(R.id.schedule);

        et_update = (EditText)findViewById(R.id.et_update_pass);
        et_update_v = (EditText)findViewById(R.id.et_update_pass_validate);


        user_profile = this;

        Context context = getApplicationContext();
        //String[] popular_courses = {"a","b","c"};
        /* String[] popular_courses = {"היסטוריה","ספרות","מחשבים"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this ,R.layout.list_view_wish_list_layout,R.id.list_item,popular_courses);
        listView.setAdapter(adapter);
        setListViewHeightBasedOnItems(listView);*/

        String result =  ServerCalls.getUserDetailsCall(MainActivity.session.getusename());

        try {

            JSONArray array = new JSONArray(result);
            JSONObject details = array.getJSONObject(0);
            //String password = details.getString("password");
            // this.password.setText(password);
            mail.setText(MainActivity.session.getusename());
            String user_name = details.getString("name");
            name.setText(user_name);
            String user_faculty = details.getString("faculty");

            String user_department = details.getString("department");
            String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
            JSONObject user_schedule = array.getJSONObject(1);

            ServerCalls.getFacultiesCall(faculty, context, user_faculty);
            ServerCalls.setDepartments(department, context, user_faculty, user_department);


            for (int i = 0; i < days.length; i++) {
                JSONObject object = user_schedule.getJSONObject(days[i]);
                Iterator<String> iter = object.keys();
                while (iter.hasNext()) {
                    String key = iter.next();
                    String t = object.getString(key);
                    int hour= Integer.parseInt(key)-7;
                    TableRow tr = (TableRow) schedule.getChildAt(hour);
                    TableRow td = (TableRow)tr.getChildAt(days.length-1-i);
                    if (t.equals("True"))
                        td.setBackgroundColor(Color.rgb(100, 118, 182)); //turn to blue
                    else //the cell is blue
                        td.setBackgroundColor(Color.TRANSPARENT); //turn to white
                }
            }

            JSONObject JSON_wishList = array.getJSONObject(2);
            Iterator<String> iterator = JSON_wishList.keys();
            ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
            while( iterator.hasNext()){

                String course_num = iterator.next();
                try{
                    String course_name = JSON_wishList.getString(course_num);
                    HashMap<String,String> temp=new HashMap<String, String>();
                    temp.put("Second", course_num);
                    temp.put("First", course_name);
                    list.add(temp);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            ListViewAdapters adapter;
            adapter = new ListViewAdapters(user_profile, list);
            wishList.setAdapter(adapter);

        } catch (Exception e) {
            Toast ts = Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG);
            ts.show();
        }

        //when the user changes faculty, the departments will change accordingly
        faculty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                ServerCalls.setDepartments(department,getApplicationContext(),item,null);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        et_update.setOnFocusChangeListener(new View.OnFocusChangeListener() {

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
        });

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
            tr.setBackgroundColor(Color.TRANSPARENT); //turn to white
    }


    public void SubmitUpdatePassword(View v)
    {
        EditText et = (EditText) findViewById(R.id.et_update_pass);

            //server call to change the password in the DB
            ServerCalls.changeUserPassword(et.getText().toString(), MainActivity.session.getusename(), getApplicationContext());

            Toast toast = Toast.makeText(getApplicationContext(), "הסיסמא עודכנה", Toast.LENGTH_LONG);
            toast.show();
        }

  /*  public void SubmitClicked(View v)
    {
        EditText et = (EditText) findViewById(R.id.et_update_pass);

        //server call to update the user details in the DB
        ServerCalls.changeUserPassword(et.getText().toString(), MainActivity.session.getusename(), getApplicationContext());

        Toast toast = Toast.makeText(getApplicationContext(), "הסיסמא עודכנה", Toast.LENGTH_LONG);
        toast.show();
    } */

    public  void updatelicked(View v)
    {
       JSONObject Sunday = new JSONObject();
        for(int i=8; i <= 20 ; i++) { //for each hour in schedule

            String hour = "";
            String trID1 = "tr_sun_" + i;
            //String trID1 = "tr_sun_13";
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

      //   Toast toast1 = Toast.makeText(getApplicationContext(), "json: "+ Friday_Arr.toString() , Toast.LENGTH_LONG);
      //  toast1.show();

        JSONArray Schedule_Arr = new JSONArray(); //the whole schedule ---> we will add all the days

        Schedule_Arr.put(Sunday);
        Schedule_Arr.put(Monday_Arr);
        Schedule_Arr.put(Tuesday_Arr);
        Schedule_Arr.put(Wednesday_Arr);
        Schedule_Arr.put(Thursday_Arr);
        Schedule_Arr.put(Friday_Arr);

        String Schedule_Json_str = Schedule_Arr.toString(); // a JSON string that contains the schedule

        System.out.println(Schedule_Json_str);



           String Faculty;
            if(faculty.getSelectedItem()!= null)
                Faculty = faculty.getSelectedItem().toString();
            else
                Faculty = "";



            String Department;
            if(department.getSelectedItem() != null)
                Department = department.getSelectedItem().toString();
            else
                Department="";

            EditText name_ET =(EditText) findViewById(R.id.txt_name);
            String name = name_ET.getText().toString();

            String res = ServerCalls.updateDetails(getApplicationContext(), name, MainActivity.session.getusename(), Faculty, Department, Schedule_Json_str); //server call to register the user
            Toast toast = Toast.makeText(getApplicationContext(),res, Toast.LENGTH_LONG);
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
