package com.example.galit.elective;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class critiqueCourse extends AppCompatActivity {

     String  title;
     String  grade;
     String  load;
     String  diff;
     String  lecturer;
     String  interest;
     String  comments;

    private void criqitiqueHelper(){

        String u = MainActivity.session.getusename();
        String c = MainActivity.recSession.getCritiques();

        Toast toast = Toast.makeText(getApplicationContext(),"user-"+ u + " critique" +c, Toast.LENGTH_LONG);
        toast.show();

        String result = courses_controller.critiquecall(u,c);
         Toast toast1 = Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG);
        toast1.show();

        JSONObject easy = null;
        try {
            easy = new JSONObject(result);

            JSONArray e_names = easy.optJSONArray("names");
            final String[] s_e_names = new String[e_names.length()];
            for(int i = 0; i < e_names.length(); i++){
                s_e_names[i]=e_names.getString(i);
            }

            JSONArray e_grades = easy.optJSONArray("grades");
            final String[] s_e_grades = new String[e_names.length()];
            for(int i = 0; i < e_names.length(); i++){
                s_e_grades[i]=e_grades.getString(i)+"/10";
            }
            JSONArray e_diifs = easy.optJSONArray("diffs");
            final String[] s_e_diifs = new String[e_names.length()];
            for(int i = 0; i < e_names.length(); i++){
                s_e_diifs[i]=e_diifs.getString(i)+"/10";
            }
            JSONArray e_interes = easy.optJSONArray("interes");
            final String[] s_e_interes = new String[e_names.length()];
            for(int i = 0; i < e_names.length(); i++){
                s_e_interes[i]=e_interes.getString(i)+"/10";
            }
            JSONArray e_lectures = easy.optJSONArray("lectures");
            final String[] s_e_lectures = new String[e_names.length()];
            for(int i = 0; i < e_names.length(); i++){
                s_e_lectures[i]=e_lectures.getString(i)+"/10";
            }
            JSONArray e_load = easy.optJSONArray("load");
            final String[] s_e_load = new String[e_names.length()];
            for(int i = 0; i < e_load.length(); i++){
                s_e_load[i]=e_load.getString(i)+"/10";
            }
            JSONArray e_comments = easy.optJSONArray("comments");
            final String[] s_e_comments = new String[e_names.length()];
            for(int i = 0; i < e_names.length(); i++){
                s_e_comments[i]=e_comments.getString(i);
            }

            ArrayList<String[]> list = new ArrayList<String[]>() {{
                add(s_e_names);
                add(s_e_grades);
                add(s_e_diifs);
                add(s_e_interes);
                add(s_e_lectures);
                add(s_e_comments);
                add(s_e_load);

            }};

            Intent myintent = new Intent(getApplicationContext(), recommend_results.class);
            myintent.putExtra("recommend results",list);
            startActivity(myintent);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critique_course);

        Intent caller = getIntent();
          title  = caller.getStringExtra("title");
          grade  = caller.getStringExtra("grade");
          load  = caller.getStringExtra("load");
          diff  = caller.getStringExtra("diff");
         lecturer  = caller.getStringExtra("lecturer");
         interest  = caller.getStringExtra("interest");
         comments  = caller.getStringExtra("comments");

        TextView name = (TextView) findViewById(R.id.course_name);
        TextView t_diff = (TextView) findViewById(R.id.txt_diff);
        TextView t_grade = (TextView) findViewById(R.id.txt_grade);
        TextView t_lecture = (TextView) findViewById(R.id.txt_lecture);
        TextView t_load = (TextView) findViewById(R.id.txt_load);
        TextView t_comments = (TextView) findViewById(R.id.txt_commentNum);
        TextView t_interest = (TextView) findViewById(R.id.txt_interest);

        name.setText(title);
        t_diff.setText(diff);
        t_grade.setText(grade);
        t_lecture.setText(lecturer);
        t_load.setText(load);
        t_comments.setText(comments);
        t_interest.setText(interest);
    }


    public void popularClicked(View V){
        MainActivity.recSession.addCritique("p: "+ comments + ","+ grade);
        Toast toast = Toast.makeText(getApplicationContext(), MainActivity.recSession.getCritiques(), Toast.LENGTH_LONG);
        toast.show();
        criqitiqueHelper();

       // System.out.print(critiques);
       // Toast toast = Toast.makeText(getApplicationContext(), critiques, Toast.LENGTH_LONG);
       // toast.show();

    }

    public void loadClicked(View V){
        MainActivity.recSession.addCritique("l: "+ load);
        //System.out.print(critiques);
        criqitiqueHelper();

    }

    public void easyClicked(View V){

        MainActivity.recSession.addCritique("e: "+ diff );
        criqitiqueHelper();
    }

    public void lectureClicked(View V){
        MainActivity.recSession.addCritique("t: "+ lecturer );
        criqitiqueHelper();
    }

    public void interestClicked(View V){
        MainActivity.recSession.addCritique("i: "+ interest );
        criqitiqueHelper();
    }

    public void surpriseClicked(View V){
        MainActivity.recSession.addCritique("s:");
        criqitiqueHelper();
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
