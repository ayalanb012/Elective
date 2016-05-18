package com.example.galit.elective;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

//The search class is for searching courses
// by various types of search: by name, constraint of schedule or by category. can be combined
public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Spinner Category_sp1 = (Spinner) findViewById(R.id.lst_category_1);
        Spinner Category_sp2 = (Spinner) findViewById(R.id.lst_category_2);
        Spinner Category_sp3 = (Spinner) findViewById(R.id.lst_category_3);
        Context ctx = getApplicationContext();
        courses_controller.getCategories(Category_sp1, Category_sp2, Category_sp3, ctx); //put the categories in the spinners
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

    public  void SearchClicked(View v)
    {
        JSONArray Sunday_Arr = new JSONArray();
        for(int i=8; i <= 20 ; i++){ //for each hour in schedule

            JSONObject hour = new JSONObject();
            String trID1="s_tr_sun_"+i;
            int trID2 = getResources().getIdentifier(trID1,"id","com.example.galit.elective");
            TableRow tr =(TableRow) findViewById(trID2);

            ColorDrawable test = (ColorDrawable)tr.getBackground();
            int background = Color.parseColor("#6476B6");
            int current = (test).getColor();

            if (current!=background) //if the cell is white
                try {
                    hour.put(String.valueOf(i),"false");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            else //the cell is blue
                try {
                    hour.put(String.valueOf(i), "true");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            Sunday_Arr.put(hour);
        }

        JSONArray Monday_Arr = new JSONArray();
        for(int i=8; i <= 20 ; i++){ //for each hour in schedule

            JSONObject hour = new JSONObject();
            String trID1="s_tr_mon_"+i;
            int trID2 = getResources().getIdentifier(trID1,"id","com.example.galit.elective");
            TableRow tr =(TableRow) findViewById(trID2);

            ColorDrawable test = (ColorDrawable)tr.getBackground();
            int background = Color.parseColor("#6476B6");
            int current = (test).getColor();

            if (current!=background) //if the cell is white
                try {
                    hour.put(String.valueOf(i),"false");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            else //the cell is blue
                try {
                    hour.put(String.valueOf(i), "true");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            Monday_Arr.put(hour);
        }

        JSONArray Tuesday_Arr = new JSONArray();
        for(int i=8; i <= 20 ; i++){ //for each hour in schedule

            JSONObject hour = new JSONObject();
            String trID1="s_tr_tue_"+i;
            int trID2 = getResources().getIdentifier(trID1,"id","com.example.galit.elective");
            TableRow tr =(TableRow) findViewById(trID2);

            ColorDrawable test = (ColorDrawable)tr.getBackground();
            int background = Color.parseColor("#6476B6");
            int current = (test).getColor();

            if (current!=background) //if the cell is white
                try {
                    hour.put(String.valueOf(i),"false");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            else //the cell is blue
                try {
                    hour.put(String.valueOf(i), "true");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            Tuesday_Arr.put(hour);
        }

        JSONArray Wednesday_Arr = new JSONArray();
        for(int i=8; i <= 20 ; i++){ //for each hour in schedule

            JSONObject hour = new JSONObject();
            String trID1="s_tr_wed_"+i;
            int trID2 = getResources().getIdentifier(trID1,"id","com.example.galit.elective");
            TableRow tr =(TableRow) findViewById(trID2);

            ColorDrawable test = (ColorDrawable)tr.getBackground();
            int background = Color.parseColor("#6476B6");
            int current = (test).getColor();

            if (current!=background) //if the cell is white
                try {
                    hour.put(String.valueOf(i),"false");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            else //the cell is blue
                try {
                    hour.put(String.valueOf(i), "true");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            Wednesday_Arr.put(hour);
        }

        JSONArray Thursday_Arr = new JSONArray();
        for(int i=8; i <= 20 ; i++){ //for each hour in schedule

            JSONObject hour = new JSONObject();
            String trID1="s_tr_thu_"+i;
            int trID2 = getResources().getIdentifier(trID1,"id","com.example.galit.elective");
            TableRow tr =(TableRow) findViewById(trID2);

            ColorDrawable test = (ColorDrawable)tr.getBackground();
            int background = Color.parseColor("#6476B6");
            int current = (test).getColor();

            if (current!=background) //if the cell is white
                try {
                    hour.put(String.valueOf(i),"false");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            else //the cell is blue
                try {
                    hour.put(String.valueOf(i), "true");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            Thursday_Arr.put(hour);
        }

        JSONArray Friday_Arr = new JSONArray();
        for(int i=8; i <= 20 ; i++){ //for each hour in schedule

            JSONObject hour = new JSONObject();
            String trID1="s_tr_fri_"+i;
            int trID2 = getResources().getIdentifier(trID1,"id","com.example.galit.elective");
            TableRow tr =(TableRow) findViewById(trID2);

            ColorDrawable test = (ColorDrawable)tr.getBackground();
            int background = Color.parseColor("#6476B6");
            int current = (test).getColor();

            if (current!=background) //if the cell is white
                try {
                    hour.put(String.valueOf(i),"false");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            else //the cell is blue
                try {
                    hour.put(String.valueOf(i), "true");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            Friday_Arr.put(hour);
        }


        JSONArray Schedule_Arr = new JSONArray(); //the whole schedule ---> we will add all the days

        Schedule_Arr.put(Sunday_Arr);
        Schedule_Arr.put(Monday_Arr);
        Schedule_Arr.put(Tuesday_Arr);
        Schedule_Arr.put(Wednesday_Arr);
        Schedule_Arr.put(Thursday_Arr);
        Schedule_Arr.put(Friday_Arr);

        String Schedule_Json_str = Schedule_Arr.toString(); // a JSON string that contains the schedule

        //Toast toast = Toast.makeText(getApplicationContext(), Schedule_Json_str, Toast.LENGTH_LONG);
        //toast.show();

        EditText name_ET =(EditText) findViewById(R.id.txt_course_name);
        String name = name_ET.getText().toString();

        EditText number_ET =(EditText) findViewById(R.id.txt_course_number);
        String number = number_ET.getText().toString();

        Spinner Category_sp1 = (Spinner) findViewById(R.id.lst_category_1);
        String Cat1;
        if(Category_sp1.getSelectedItem() != null)
            Cat1 = Category_sp1.getSelectedItem().toString();
        else
            Cat1="";


        Spinner Category_sp2 = (Spinner) findViewById(R.id.lst_category_2);
        String Cat2;
        if(Category_sp2.getSelectedItem() != null)
            Cat2 = Category_sp2.getSelectedItem().toString();
        else
            Cat2="";

        Spinner Category_sp3 = (Spinner) findViewById(R.id.lst_category_3);
        String Cat3;
        if(Category_sp3.getSelectedItem() != null)
            Cat3 = Category_sp3.getSelectedItem().toString();
        else
            Cat3="";

        CheckBox check = (CheckBox) findViewById(R.id.checkBox);
        Boolean isChecked = check.isChecked();

        String JSON_list = courses_controller.Search(getApplicationContext(), name, number, Cat1, Cat2, Cat3, Schedule_Json_str, isChecked);
        //JSON_list is a  json string with the search results from server in this format: {"number1":"value1", "number2":"value2", "number3":"value3" }

       // Toast toast1 = Toast.makeText(getApplicationContext(),"got server call of search "+JSON_list, Toast.LENGTH_LONG);
      // toast1.show();

       JSONObject J0bject = null;
        try {
            J0bject = new JSONObject(JSON_list);
        } catch (JSONException e) {
            Toast toast = Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG);
            toast.show();
        }

        Iterator<String> iterator = J0bject.keys();
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();

        while( iterator.hasNext()){

            String course_num = iterator.next();
            try{
                String course_name = J0bject.getString(course_num);
                HashMap<String,String> temp=new HashMap<String, String>();
                temp.put("Second", course_num);
                temp.put("First", course_name);

                list.add(temp);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Intent myintent = new Intent(this, reccomand.class);
        myintent.putExtra("course_list",list);
        startActivity(myintent);
        //finish();------------------------------->> in some point we will need to close this activity
        //------------------------------------------->> for now we leave it open so the user can go back to the search results

    }

    //this method is activated when the user clickes a table row on the table
    //the method changes the background color of the current tablerow
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
