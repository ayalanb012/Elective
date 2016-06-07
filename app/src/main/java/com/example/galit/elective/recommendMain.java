package com.example.galit.elective;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class recommendMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recommend_main);

        RecommandSearchAdapter listview = (RecommandSearchAdapter) findViewById(R.id.listview);
        RecommandSearchAdapter listview1 = (RecommandSearchAdapter) findViewById(R.id.listview1);
        RecommandSearchAdapter listview2 = (RecommandSearchAdapter) findViewById(R.id.listview2);

        listview.setAdapter(interestAdapter);
        listview1.setAdapter(easyAdapter);
        listview2.setAdapter(sportsAdapter);
    }
    private static String[] dataObjects_interest = new String[]{ "יצירות מופת מספרות העולם I","חברה ותרבות בסין המודרנית",

            "החוויה התיאטרונית","מבוא ללימודי אפריקה","אפריקה ואקטיביזם"};
    private static String[] getDataObjects_interest_grade = new String[]{"9.6/10","8.4/10","10/10","8.3/10","9.8/10" };
    private static String[] dataObjects_sports = new String[]{ "טניס מתחילים",
            "פילאטיס",
            "כדורעף מעורב","קט רגל","סייף","חדר כושר"};
    private static String[] getDataObjects_sports_grade = new String[]{"7.6/10","8.2/10","8.8/10","8.3/10","9.4/10","8.5/10" };

    private static String[] dataObjects_easy = new String[]{ "זוגיות ופרישות בספרות היהודית והנוצרית הקדומה",
            "המוח 120 שנות מחקר",
            "הנגב:מספר ההתיישבות לארץ מושב","אפריקה בתקופה הקולוניאלית"};
    private static String[] getDataObjects_easy_grade = new String[]{"8.2/10","8.7/10","8.9/10","8.3/10" };

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
            TextView grade = (TextView) retval.findViewById(R.id.grade);
            TextView diff = (TextView) retval.findViewById(R.id.diff);
            grade.setText(getDataObjects_interest_grade[position]);
            diff.setText("4");
            title.setText(dataObjects_interest[position]);

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
            TextView grade = (TextView) retval.findViewById(R.id.grade);
            TextView diff = (TextView) retval.findViewById(R.id.diff);
            grade.setText(getDataObjects_sports_grade[position]);
            diff.setText("4");
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
            TextView grade = (TextView) retval.findViewById(R.id.grade);
            TextView diff = (TextView) retval.findViewById(R.id.diff);
            grade.setText(getDataObjects_easy_grade[position]);
            diff.setText("4");
            title.setText(dataObjects_easy[position]);

            return retval;
        }

    };

}
