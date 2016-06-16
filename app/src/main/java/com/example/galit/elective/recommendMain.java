package com.example.galit.elective;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class recommendMain extends Activity {
    TextView grade;
    TextView load;
    TextView diff;
    TextView lecturer;
    TextView interest;
    TextView comments;
    TextView title;

    private static String[] dataObjects_interest;
    private static String[] getDataObjects_interest_grade;
    private static String[] getDataObjects_interest_interest ;
    private static String[] getDataObjects_interest_diff ;
    private static String[] getDataObjects_interest_load ;
    private static String[] getDataObjects_interest_lecturer;
    private static String[] getDataObjects_interest_comments;

    private static String[] dataObjects_sports ;
    private static String[] getDataObjects_sports_grade;
    private static String[] getDataObjects_sports_interest;
    private static String[] getDataObjects_sports_diff;
    private static String[] getDataObjects_sports_load;
    private static String[] getDataObjects_sports_lecturer;
    private static String[] getDataObjects_sports_comments;

    private static String[] dataObjects_easy;
    private static String[] getDataObjects_easy_grade;
    private static String[] getDataObjects_easy_interest;
    private static String[] getDataObjects_easy_diff;
    private static String[] getDataObjects_easy_load ;
    private static String[] getDataObjects_easy_lecturer;
    private static String[] getDataObjects_easy_comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recommend_main);

        final RecommandSearchAdapter listview = (RecommandSearchAdapter) findViewById(R.id.listview);
        final RecommandSearchAdapter listview1 = (RecommandSearchAdapter) findViewById(R.id.listview1);
        final RecommandSearchAdapter listview2 = (RecommandSearchAdapter) findViewById(R.id.listview2);

        String popular = courses_controller.getPopularcall(getApplicationContext());

     //   Toast.makeText(getApplicationContext(), "from server: "+ popular, Toast.LENGTH_LONG).show();
        try {
            JSONObject J0bject = new JSONObject(popular);

            JSONObject easy = J0bject.getJSONObject("easy");
            JSONObject sport = J0bject.getJSONObject("sport");
            JSONObject interesting = J0bject.getJSONObject("intresting");

            JSONArray e_names = easy.optJSONArray("names");
            String[] s_e_names = new String[e_names.length()];
            for(int i = 0; i < e_names.length(); i++){
                s_e_names[i]=e_names.getString(i);
            }

            JSONArray e_grades = easy.optJSONArray("grades");
            String[] s_e_grades = new String[e_names.length()];
            for(int i = 0; i < e_names.length(); i++){
                s_e_grades[i]=e_grades.getString(i)+"/10";
            }
            JSONArray e_diifs = easy.optJSONArray("diffs");
            String[] s_e_diifs = new String[e_names.length()];
            for(int i = 0; i < e_names.length(); i++){
                s_e_diifs[i]=e_diifs.getString(i)+"/10";
            }
            JSONArray e_interes = easy.optJSONArray("interes");
            String[] s_e_interes = new String[e_names.length()];
            for(int i = 0; i < e_names.length(); i++){
                s_e_interes[i]=e_interes.getString(i)+"/10";
            }
            JSONArray e_lectures = easy.optJSONArray("lectures");
            String[] s_e_lectures = new String[e_names.length()];
            for(int i = 0; i < e_names.length(); i++){
                s_e_lectures[i]=e_lectures.getString(i)+"/10";
            }
            JSONArray e_load = easy.optJSONArray("load");
            String[] s_e_load = new String[e_names.length()];
            for(int i = 0; i < e_load.length(); i++){
                s_e_load[i]=e_load.getString(i)+"/10";
            }
            JSONArray e_comments = easy.optJSONArray("comments");
            String[] s_e_comments = new String[e_names.length()];
            for(int i = 0; i < e_names.length(); i++){
                s_e_comments[i]=e_comments.getString(i);
            }


            dataObjects_easy = s_e_names;
            getDataObjects_easy_grade=s_e_grades;
            getDataObjects_easy_diff= s_e_diifs;
            getDataObjects_easy_interest=s_e_interes;
            getDataObjects_easy_load=s_e_load;
            getDataObjects_easy_comments=s_e_comments;
            getDataObjects_easy_lecturer=s_e_lectures;
//------------------------------------------------------------------------------
            JSONArray s_names = sport.optJSONArray("names");
            String[] s_s_names = new String[s_names.length()];
            for(int i = 0; i < s_names.length(); i++){
                s_s_names[i]=s_names.getString(i);
            }

            JSONArray s_grades = sport.optJSONArray("grades");
            String[] s_s_grades = new String[s_names.length()];
            for(int i = 0; i < s_names.length(); i++){
                s_s_grades[i]=s_grades.getString(i)+"/10";
            }
            JSONArray s_diifs = sport.optJSONArray("diffs");
            String[] s_s_diifs = new String[s_names.length()];
            for(int i = 0; i < s_names.length(); i++){
                s_s_diifs[i]=s_diifs.getString(i)+"/10";
            }
            JSONArray s_interes = sport.optJSONArray("interes");
            String[] s_s_interes = new String[s_names.length()];
            for(int i = 0; i < s_names.length(); i++){
                s_s_interes[i]=s_interes.getString(i)+"/10";
            }
            JSONArray s_lectures = sport.optJSONArray("lectures");
            String[] s_s_lectures = new String[s_names.length()];
            for(int i = 0; i < s_names.length(); i++){
                s_s_lectures[i]=s_lectures.getString(i)+"/10";
            }
            JSONArray s_load = sport.optJSONArray("load");
            String[] s_s_load = new String[s_names.length()];
            for(int i = 0; i < s_names.length(); i++){
                s_s_load[i]=s_load.getString(i)+"/10";
            }
            JSONArray s_comments = sport.optJSONArray("comments");
            String[] s_s_comments = new String[s_names.length()];
            for(int i = 0; i < s_names.length(); i++){
                s_s_comments[i]=s_comments.getString(i);
            }

            dataObjects_sports = s_s_names;
            getDataObjects_sports_grade=s_s_grades;
            getDataObjects_sports_diff= s_s_diifs;
            getDataObjects_sports_interest=s_s_interes;
            getDataObjects_sports_load=s_s_load;
            getDataObjects_sports_comments=s_s_comments;
            getDataObjects_sports_lecturer=s_s_lectures;
//----------------------------------------------------------------------------------------------
            JSONArray i_names = interesting.optJSONArray("names");
            String[] s_i_names = new String[i_names.length()];
            for(int i = 0; i < e_names.length(); i++){
                s_i_names[i]=i_names.getString(i);
            }
            JSONArray i_grades = interesting.optJSONArray("grades");
            String[] s_i_grades = new String[i_names.length()];
            for(int i = 0; i < i_names.length(); i++){
                s_i_grades[i]=i_grades.getString(i)+"/10";
            }
            JSONArray i_diifs = interesting.optJSONArray("diffs");
            String[] s_i_diifs = new String[i_names.length()];
            for(int i = 0; i < i_names.length(); i++){
                s_i_diifs[i]=i_diifs.getString(i)+"/10";
            }
            JSONArray i_interes = interesting.optJSONArray("interes");
            String[] s_i_interes = new String[i_names.length()];
            for(int i = 0; i < i_names.length(); i++){
                s_i_interes[i]=i_interes.getString(i)+"/10";
            }
            JSONArray i_lectures = interesting.optJSONArray("lectures");
            String[] s_i_lectures = new String[i_names.length()];
            for(int i = 0; i < i_names.length(); i++){
                s_i_lectures[i]=i_lectures.getString(i)+"/10";
            }

            JSONArray i_load = interesting.optJSONArray("load");
            String[] s_i_load = new String[i_names.length()];
            for(int i = 0; i < i_names.length(); i++){
                s_i_load[i]=i_load.getString(i)+"/10";
            }
            JSONArray i_comments = interesting.optJSONArray("comments");
            String[] s_i_comments = new String[i_names.length()];
            for(int i = 0; i < i_names.length(); i++){
                s_i_comments[i]=i_comments.getString(i);
            }

            dataObjects_interest = s_i_names;
            getDataObjects_interest_grade=s_i_grades;
            getDataObjects_interest_diff= s_i_diifs;
            getDataObjects_interest_interest=s_i_interes;
            getDataObjects_interest_load=s_i_load;
            getDataObjects_interest_comments=s_i_comments;
            getDataObjects_interest_lecturer=s_i_lectures;


           // Toast.makeText(getApplicationContext(), e_names.toString(),  Toast.LENGTH_LONG).show();

           // String n = e_names.getString(0);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(),  Toast.LENGTH_LONG).show();
        }




        listview.setAdapter(interestAdapter);
        listview1.setAdapter(easyAdapter);
        listview2.setAdapter(sportsAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {

                ListAdapter la = listview.getAdapter();
                View selsected = la.getView(position, view, (ViewGroup) view.getParent());

                TextView title = (TextView) selsected.findViewById(R.id.title);
                TextView grade = (TextView) selsected.findViewById(R.id.tv_grade);
                TextView load = (TextView) selsected.findViewById(R.id.tv_load);
                TextView diff = (TextView) selsected.findViewById(R.id.tv_diff);
                TextView lecturer = (TextView) selsected.findViewById(R.id.tv_lecturer);
                TextView interest = (TextView) selsected.findViewById(R.id.tv_interest);
                TextView comments = (TextView) selsected.findViewById(R.id.tv_comments);

                //   Toast.makeText(getApplicationContext(), "selected Item Name is "+ title.getText() + " " + grade.getText(), Toast.LENGTH_LONG).show();

                Intent myintent = new Intent(getApplicationContext(), critiqueCourse.class);
                myintent.putExtra("title", title.getText());
                myintent.putExtra("grade", grade.getText());
                myintent.putExtra("load", load.getText());
                myintent.putExtra("diff", diff.getText());
                myintent.putExtra("lecturer", lecturer.getText());
                myintent.putExtra("interest", interest.getText());
                myintent.putExtra("comments", comments.getText());
                myintent.putExtra("caller_activity", "recommendMain");
                startActivity(myintent);
                //finish();
            }
        });


        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick (AdapterView < ? > adapter, View view,int position, long arg){

                ListAdapter la = listview1.getAdapter();
                View selsected =  la.getView(position,view, (ViewGroup)view.getParent());

                TextView title = (TextView)  selsected.findViewById(R.id.title);
                TextView grade = (TextView)  selsected.findViewById(R.id.tv_grade);
                TextView load = (TextView)  selsected.findViewById(R.id.tv_load);
                TextView diff = (TextView)  selsected.findViewById(R.id.tv_diff);
                TextView lecturer = (TextView)  selsected.findViewById(R.id.tv_lecturer);
                TextView interest = (TextView)  selsected.findViewById(R.id.tv_interest);
                TextView comments = (TextView)  selsected.findViewById(R.id.tv_comments);

                //   Toast.makeText(getApplicationContext(), "selected Item Name is "+ title.getText() + " " + grade.getText(), Toast.LENGTH_LONG).show();

                Intent myintent = new Intent(getApplicationContext(), critiqueCourse.class);
                myintent.putExtra("title",title.getText());
                myintent.putExtra("grade",grade.getText());
                myintent.putExtra("load",load.getText());
                myintent.putExtra("diff",diff.getText());
                myintent.putExtra("lecturer",lecturer.getText());
                myintent.putExtra("interest",interest.getText());
                myintent.putExtra("comments",comments.getText());
                myintent.putExtra("caller_activity", "recommendMain");
                startActivity(myintent);
                //finish();
            }
        });


        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick (AdapterView < ? > adapter, View view,int position, long arg){

                ListAdapter la = listview2.getAdapter();
                View selsected =  la.getView(position,view, (ViewGroup)view.getParent());

                TextView title = (TextView)  selsected.findViewById(R.id.title);
                TextView grade = (TextView)  selsected.findViewById(R.id.tv_grade);
                TextView load = (TextView)  selsected.findViewById(R.id.tv_load);
                TextView diff = (TextView)  selsected.findViewById(R.id.tv_diff);
                TextView lecturer = (TextView)  selsected.findViewById(R.id.tv_lecturer);
                TextView interest = (TextView)  selsected.findViewById(R.id.tv_interest);
                TextView comments = (TextView)  selsected.findViewById(R.id.tv_comments);

                //   Toast.makeText(getApplicationContext(), "selected Item Name is "+ title.getText() + " " + grade.getText(), Toast.LENGTH_LONG).show();

                Intent myintent = new Intent(getApplicationContext(), critiqueCourse.class);
                myintent.putExtra("title",title.getText());
                myintent.putExtra("grade",grade.getText());
                myintent.putExtra("load",load.getText());
                myintent.putExtra("diff",diff.getText());
                myintent.putExtra("lecturer",lecturer.getText());
                myintent.putExtra("interest",interest.getText());
                myintent.putExtra("comments",comments.getText());
                myintent.putExtra("caller_activity", "recommendMain");
                startActivity(myintent);
                //finish();
            }
        });
    }


  /*  private static String[] dataObjects_interest = new String[]{ "יצירות מופת מספרות העולם I","חברה ותרבות בסין המודרנית", "החוויה התיאטרונית","מבוא ללימודי אפריקה","אפריקה ואקטיביזם"};
    private static String[] getDataObjects_interest_grade = new String[]{"9.6/10","8.4/10","10/10","8.3/10","9.8/10" };
    private static String[] getDataObjects_interest_interest = new String[]{"9.6/10","9.7/10","9.4/10","9.3/10","9.8/10" };
    private static String[] getDataObjects_interest_diff = new String[]{"9.2/10","8.4/10","8.7/10","8.3/10","8.8/10" };
    private static String[] getDataObjects_interest_load = new String[]{"9.1/10","9.4/10","8.8/10","7.6/10","7.3/10" };
    private static String[] getDataObjects_interest_lecturer = new String[]{"7.6/10","8.4/10","8.8/10","7.3/10","6.8/10" };
    private static String[] getDataObjects_interest_comments = new String[]{"15","23","7","3","8" };

    private static String[] dataObjects_sports = new String[]{ "טניס מתחילים", "פילאטיס", "כדורעף מעורב","קט רגל","סייף","חדר כושר"};
    private static String[] getDataObjects_sports_grade = new String[]{"7.6/10","8.2/10","8.8/10","8.3/10","9.4/10","8.5/10" };
    private static String[] getDataObjects_sports_interest = new String[]{"9.4/10","8.0/10","8.1/10","8.3/10","9.3/10","8.7" };
    private static String[] getDataObjects_sports_diff = new String[]{"7.6/10","7.4/10","6.2/10","5.5/10","7.8/10","8.2" };
    private static String[] getDataObjects_sports_load = new String[]{"1.2/10","1.1/10","1.6/10","1.0/10","2.1/10","3.3" };
    private static String[] getDataObjects_sports_lecturer = new String[]{"9.6/10","9.4/10","10/10","8.3/10","9.8/10","8.7" };

    private static String[] dataObjects_easy = new String[] { "שמואל הנגיד: קצין וג'נטלמן", "ספרות של עולמות אפשריים", "המוח 120 שנות מחקר", "אפריקה בתקופה הקולוניאלית"};
    private static String[] getDataObjects_easy_grade = new String[]{"5.2/10","6.7/10","8.9/10","6.3/10" };
    private static String[] getDataObjects_easy_interest = new String[]{"3.6/10","2.4/10","3.3/10","4.3/10" };
    private static String[] getDataObjects_easy_diff = new String[]{"2.6/10","3.3/10","4.8/10","1.3/10" };
    private static String[] getDataObjects_easy_load = new String[]{"4.6/10","2.4/10","2.1/10","1.3/10" };
    private static String[] getDataObjects_easy_lecturer = new String[]{"7.6/10","6.4/10","5.3/10","8.3/10" }; */


    private BaseAdapter interestAdapter = new BaseAdapter() {


        @Override
        public int getCount() {
            return dataObjects_interest.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View retval = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommandsearchcustomelayout, null);

            TextView title = (TextView)  retval.findViewById(R.id.title);
            TextView grade = (TextView)  retval.findViewById(R.id.tv_grade);
            TextView load = (TextView)  retval.findViewById(R.id.tv_load);
            TextView diff = (TextView)  retval.findViewById(R.id.tv_diff);
            TextView lecturer = (TextView)  retval.findViewById(R.id.tv_lecturer);
            TextView interest = (TextView)  retval.findViewById(R.id.tv_interest);
            TextView comments = (TextView)  retval.findViewById(R.id.tv_comments);

           title.setPaintFlags(title.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            grade.setText(getDataObjects_interest_grade[position]);
            diff.setText(getDataObjects_interest_diff[position]);
            title.setText(dataObjects_interest[position]);
            load.setText(getDataObjects_interest_load[position]);
            lecturer.setText(getDataObjects_interest_lecturer[position]);
            interest.setText(getDataObjects_interest_interest[position]);
            comments.setText(getDataObjects_interest_comments[position]);
            return retval;
        }

    };

    private BaseAdapter sportsAdapter = new BaseAdapter() {


        @Override
        public int getCount() {
            return dataObjects_sports.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View retval = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommandsearchcustomelayout, null);
            TextView title = (TextView) retval.findViewById(R.id.title);
            title.setPaintFlags(title.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            TextView grade = (TextView) retval.findViewById(R.id.tv_grade);
            TextView load = (TextView) retval.findViewById(R.id.tv_load);
            TextView diff = (TextView) retval.findViewById(R.id.tv_diff);
            TextView lecturer = (TextView) retval.findViewById(R.id.tv_lecturer);
            TextView interest = (TextView) retval.findViewById(R.id.tv_interest);
            TextView comments = (TextView) retval.findViewById(R.id.tv_comments);
            grade.setText(getDataObjects_sports_grade[position]);
            diff.setText(getDataObjects_sports_diff[position]);
            load.setText(getDataObjects_sports_load[position]);
            lecturer.setText(getDataObjects_sports_lecturer[position]);
            interest.setText(getDataObjects_sports_interest[position]);
            title.setText(dataObjects_sports[position]);

            return retval;
        }

    };
    private BaseAdapter easyAdapter = new BaseAdapter() {


        @Override
        public int getCount() {
            return dataObjects_easy.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View retval = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommandsearchcustomelayout, null);
            TextView title = (TextView) retval.findViewById(R.id.title);
            title.setPaintFlags(title.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

            TextView grade = (TextView) retval.findViewById(R.id.tv_grade);
            TextView load = (TextView) retval.findViewById(R.id.tv_load);
            TextView diff = (TextView) retval.findViewById(R.id.tv_diff);
            TextView lecturer = (TextView) retval.findViewById(R.id.tv_lecturer);
            TextView interest = (TextView) retval.findViewById(R.id.tv_interest);
            TextView comments = (TextView) retval.findViewById(R.id.tv_comments);

            grade.setText(getDataObjects_easy_grade[position]);
            diff.setText(getDataObjects_easy_diff[position]);
            load.setText(getDataObjects_easy_load[position]);
            lecturer.setText(getDataObjects_easy_lecturer[position]);
            interest.setText(getDataObjects_easy_interest[position]);
            title.setText(dataObjects_easy[position]);
            return retval;
        }

    };

}
