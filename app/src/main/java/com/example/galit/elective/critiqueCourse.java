package com.example.galit.elective;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class critiqueCourse extends AppCompatActivity {

     String  title;
     String  grade;
     String  load;
     String  diff;
     String  lecturer;
     String  interest;
     String  comments;

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
        String critiques = MainActivity.recSession.getCritiques();

       // System.out.print(critiques);
       // Toast toast = Toast.makeText(getApplicationContext(), critiques, Toast.LENGTH_LONG);
       // toast.show();


      /*  Intent myintent = new Intent(getApplicationContext(), recommend_results.class);
        ArrayList<String[]> list = new ArrayList<String[]>() {{
            add(new String[] { "המוח 120 שנות מחקר", "יצירות מופת מעולם הספות","כדורעף מעורב" });
            add(new String[] { "7.3/10", "5.5/10","6.8/10" });
            add(new String[] { "7.3/10", "5.5/10","6.8/10" });
            add(new String[] { "7.3/10", "5.5/10","6.8/10" });
            add(new String[] { "7.3/10", "5.5/10","6.8/10" });
            add(new String[] { "7.3/10", "5.5/10","6.8/10" });
            add(new String[] { "7.3/10", "5.5/10","6.8/10" });

        }};


        myintent.putExtra("recommend results",list);
        startActivity(myintent); */
    }

    public void loadClicked(View V){
        MainActivity.recSession.addCritique("l: "+ load);
        String critiques = MainActivity.recSession.getCritiques();
        //System.out.print(critiques);

    }

    public void easyClicked(View V){

        MainActivity.recSession.addCritique("e: "+ diff );
        String critiques = MainActivity.recSession.getCritiques();
        System.out.print(critiques);
    }

    public void lectureClicked(View V){
        MainActivity.recSession.addCritique("t: "+ lecturer );
        String critiques = MainActivity.recSession.getCritiques();
       // System.out.print(critiques);
    }

    public void interestClicked(View V){
        MainActivity.recSession.addCritique("i: "+ interest );
        String critiques = MainActivity.recSession.getCritiques();
       // System.out.print(critiques);
    }

    public void surpriseClicked(View V){
        MainActivity.recSession.addCritique("s:");
        String critiques = MainActivity.recSession.getCritiques();
      //  System.out.print(critiques);
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
