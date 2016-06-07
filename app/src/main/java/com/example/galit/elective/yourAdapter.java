package com.example.galit.elective;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

class yourAdapter extends BaseAdapter {

 Context context;
 String[] names;
 String[] grades;
 String[] diffs;
 String[] interests;
 String[] lectures;
 String[] comments;
 String[] loads;

 private static LayoutInflater inflater = null;

 public yourAdapter(Context context, String[]name, String[]grades,String[]diffs,String[]interest,String[]lecture,String[]comments,String[]loads) {
 // TODO Auto-generated constructor stub
 this.context = context;
 this.names = name;
  this.grades = grades;
  this.diffs = diffs;
  this.interests = interest;
  this.lectures = lecture;
  this.comments = comments;
  this.loads = loads;

 inflater = (LayoutInflater) context
 .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 }

 @Override
 public int getCount() {
 // TODO Auto-generated method stub
 return names.length;
 }

 @Override
 public Object getItem(int position) {
 // TODO Auto-generated method stub
 return names[position];
 }

 @Override
 public long getItemId(int position) {
 // TODO Auto-generated method stub
 return position;
 }

 @Override
 public View getView(int position, View convertView, ViewGroup parent) {
 // TODO Auto-generated method stub
 View vi = convertView;
 if (vi == null)
 vi = inflater.inflate(R.layout.recommend_results_custom, null);

  TextView title = (TextView) vi.findViewById(R.id.title);
  TextView lecture = (TextView) vi.findViewById(R.id.txt_lecture);
  TextView diff = (TextView) vi.findViewById(R.id.txt_diff);
  TextView commentNum = (TextView) vi.findViewById(R.id.txt_commentNum);
  TextView interest = (TextView) vi.findViewById(R.id.txt_interest);
  TextView load = (TextView) vi.findViewById(R.id.txt_load);
  TextView grade = (TextView) vi.findViewById(R.id.txt_grade);

  title.setText(names[position]);
  lecture.setText(lectures[position]);
  diff.setText(diffs[position]);
  commentNum.setText(comments[position]);
  interest.setText(interests[position]);
  load.setText(loads[position]);
  grade.setText(grades[position]);

 return vi;
 }
 }