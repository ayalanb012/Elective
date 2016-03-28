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


    public RowItem(String title, String desc,String grade,String interest,String diff) {

        this.title = title;
        this.desc = desc;
        this.diff= diff;
        this.grade = grade;
        this.interest = interest;
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

    @Override
    public String toString() {
        return title + "\n" + desc;
    }
}
