package com.example.galit.elective;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class recommendMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recommend_main);

        final RecommandSearchAdapter listview = (RecommandSearchAdapter) findViewById(R.id.listview);
        RecommandSearchAdapter listview1 = (RecommandSearchAdapter) findViewById(R.id.listview1);
        RecommandSearchAdapter listview2 = (RecommandSearchAdapter) findViewById(R.id.listview2);

        listview.setAdapter(interestAdapter);
        listview1.setAdapter(easyAdapter);
        listview2.setAdapter(sportsAdapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView < ? > adapter, View view,int position, long arg){
                // TODO Auto-generated method stub
                //TextView v = (TextView) view.findViewById(R.id.txtLstItem);
               // View selectedCourse = adapter.getSelectedView();
              //  Object selectedCourse = listview.getItemAtPosition(position);
              //  View coursename = listview.getChildAt(position);

                Toast.makeText(getApplicationContext(), "selected Item Name is "+ position , Toast.LENGTH_LONG).show();
            }
        });




    }
    private static String[] dataObjects_interest = new String[]{ "יצירות מופת מספרות העולם I","חברה ותרבות בסין המודרנית", "החוויה התיאטרונית","מבוא ללימודי אפריקה","אפריקה ואקטיביזם"};
    private static String[] getDataObjects_interest_grade = new String[]{"9.6/10","8.4/10","10/10","8.3/10","9.8/10" };
    private static String[] getDataObjects_interest_interest = new String[]{"9.6/10","9.7/10","9.4/10","9.3/10","9.8/10" };
    private static String[] getDataObjects_interest_diff = new String[]{"9.2/10","8.4/10","8.7/10","8.3/10","8.8/10" };
    private static String[] getDataObjects_interest_load = new String[]{"9.1/10","9.4/10","8.8/10","7.6/10","7.3/10" };
    private static String[] getDataObjects_interest_lecturer = new String[]{"7.6/10","8.4/10","8.8/10","7.3/10","6.8/10" };

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
    private static String[] getDataObjects_easy_lecturer = new String[]{"7.6/10","6.4/10","5.3/10","8.3/10" };

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
            TextView title = (TextView) retval.findViewById(R.id.title);
            title.setPaintFlags(title.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            TextView grade = (TextView) retval.findViewById(R.id.grade);
            TextView load = (TextView) retval.findViewById(R.id.load);
            TextView diff = (TextView) retval.findViewById(R.id.diff);
            TextView lecturer = (TextView) retval.findViewById(R.id.lecturer);
            TextView interest = (TextView) retval.findViewById(R.id.interest);
            grade.setText(getDataObjects_interest_grade[position]);
            diff.setText(getDataObjects_interest_diff[position]);
            title.setText(dataObjects_interest[position]);
            load.setText(getDataObjects_interest_load[position]);
            lecturer.setText(getDataObjects_interest_lecturer[position]);
            interest.setText(getDataObjects_interest_interest[position]);
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

            TextView grade = (TextView) retval.findViewById(R.id.grade);
            TextView load = (TextView) retval.findViewById(R.id.load);
            TextView diff = (TextView) retval.findViewById(R.id.diff);
            TextView lecturer = (TextView) retval.findViewById(R.id.lecturer);
            TextView interest = (TextView) retval.findViewById(R.id.interest);
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

            TextView grade = (TextView) retval.findViewById(R.id.grade);
            TextView load = (TextView) retval.findViewById(R.id.load);
            TextView diff = (TextView) retval.findViewById(R.id.diff);
            TextView lecturer = (TextView) retval.findViewById(R.id.lecturer);
            TextView interest = (TextView) retval.findViewById(R.id.interest);
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
