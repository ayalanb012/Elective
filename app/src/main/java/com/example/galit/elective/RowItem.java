package com.example.galit.elective;

/**
 * Created by Galit on 27/03/2016.
 */
public class RowItem {

    private String title;
    private String desc;
    private String grade;
    private String interest;
    private String diff;
    private String load;
    private String lecture;


    public RowItem(String title, String desc,String grade,String interest,String diff,String load,String lecture) {

        this.title = title;
        this.desc = desc;
        this.diff= diff;
        this.grade = grade;
        this.interest = interest;
        this.load = load;
        this.lecture = lecture;
    }


    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getGrade() {
        return grade;
    }

    public String getDiff() {
        return diff;
    }
    public String getInterest() {
        return interest;
    }
    public  String getLoad(){return load;}
    public  String getLecture(){return  lecture;}

    @Override
    public String toString() {
        return title + "\n" + desc;
    }
}
