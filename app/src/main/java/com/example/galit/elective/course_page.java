package com.example.galit.elective;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class course_page extends Activity {
    ArrayList<String> titles;
    ArrayList<String> descs;
    ArrayList<String> diffs;
    ArrayList<String> interests;
    ArrayList<String> grades;
    ListView list;
    List<RowItem> rowItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);

        try {
            rowItems = new ArrayList<RowItem>();

            titles = new ArrayList<String>();
            descs = new ArrayList<String>();
            diffs = new ArrayList<String>();
            interests = new ArrayList<String>();
            grades = new ArrayList<String>();


            Intent extras = getIntent();
            System.out.print("-------------" + extras.toString());
            if (extras != null) {
                Bundle bun = extras.getExtras();
                if (bun != null) {
                    String name = bun.getString("name");
                    String desc = bun.getString("comment");
                    String diff = bun.getString("diff");
                    String interest = bun.getString("interest");
                    String grade = bun.getString("grade");
                    titles.add(name);
                    descs.add(desc);
                    grades.add(grade);
                    interests.add(interest);
                    diffs.add(diff);
                    for (int i = 0; i < titles.size(); i++) {
                        RowItem item = new RowItem(titles.get(i), descs.get(i),grades.get(i),interests.get(i),diffs.get(i));
                        rowItems.add(item);
                    }


                }
            }
            list = (ListView) findViewById(R.id.listView);
            CustomListViewAdapter adapter = new CustomListViewAdapter(this, R.layout.list_view_comment, rowItems);
            list.setAdapter(adapter);

        }
        catch (Exception e)
        {
            Toast t =Toast.makeText(getApplicationContext(),e.getStackTrace().toString(),Toast.LENGTH_LONG);
            t.show();

        }
    }

    //this method is activated when logo is clicked. the method return to main activity
    public void HomeClicked(View v)
    {

        ImageButton button = (ImageButton) v;
        // startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }


    public void CritiquingClicked(View v)
    {
        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(), critiquing.class));

    }

    public void CommentClicked(View v)
    {

        Button button = (Button) v;

        startActivity(new Intent(getApplicationContext(), Comment.class));
        finish();

    }
}
