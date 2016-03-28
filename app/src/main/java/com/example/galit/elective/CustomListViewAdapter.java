package com.example.galit.elective;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Galit on 27/03/2016.
 */
public class CustomListViewAdapter extends ArrayAdapter<RowItem> {
    Context context;

    public CustomListViewAdapter(Context context, int resourceId,
                                 List<RowItem> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /*private view holder class*/
    private class ViewHolder {

        TextView txtMail;
        TextView txtFeedback;
        TextView txtInterest;
        TextView txtDiff;
        TextView txtGrade;


    }

    public View getView(int position, View convertView, ViewGroup parent) {
       try {
           ViewHolder holder = null;
           RowItem rowItem = getItem(position);

           LayoutInflater mInflater = (LayoutInflater) context
                   .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
           if (convertView == null) {
               convertView = mInflater.inflate(R.layout.list_view_comment, null);
               holder = new ViewHolder();
               holder.txtFeedback = (TextView) convertView.findViewById(R.id.txt_feedback);
               holder.txtMail = (TextView) convertView.findViewById(R.id.txt_email);
               holder.txtDiff = (TextView) convertView.findViewById(R.id.txt_diff);
               holder.txtGrade = (TextView) convertView.findViewById(R.id.txt_grade);
               holder.txtInterest = (TextView) convertView.findViewById(R.id.txt_interest);
               convertView.setTag(holder);
           } else
               holder = (ViewHolder) convertView.getTag();

           holder.txtFeedback.setText(rowItem.getDesc());
           holder.txtMail.setText(rowItem.getTitle());
           holder.txtInterest.setText(rowItem.getInterest());
           holder.txtGrade.setText(rowItem.getGrade());
           holder.txtDiff.setText(rowItem.getDiff());
       }
       catch (Exception e)
       {
           System.out.println("---------------------"+e.getMessage().toString());
       }
        return convertView;
    }
}
