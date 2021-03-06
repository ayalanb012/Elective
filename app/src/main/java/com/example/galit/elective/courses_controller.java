package com.example.galit.elective;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * Created by User on 18/05/2016.
 */
public class courses_controller {

    private static final String SOAP_ACTION = "http://tempuri.org/isRegistered";
    private static final String OPERATION_NAME = "isRegistered";// your webservice web method name
    private static final String WSDL_TARGET_NAMESPACE = "http://tempuri.org";
    private static final String SOAP_ADDRESS = "--";

    public static void addCourseToWishList(String Email, String courseNum, Context context) {
        myTaskAddToWishList T = new myTaskAddToWishList(Email, courseNum, context);
        T.execute();
    }//

    public static void commentCall(String mail, String course, String feedback, String interest, String diff, String rating,String lecture,String load, Context t) {

        myTaskComments T = new myTaskComments(mail, course, feedback, interest, diff, rating,lecture,load, t);
        T.execute();
    }

    public static String allcommentscall(String courseNum, Context context, ListView ls) {
        myTaskAllComments T = new myTaskAllComments(courseNum, context, ls);
        T.execute();
        try {
            return T.get().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    } //

    public static String critiquecall() {
        myTaskcritique T = new myTaskcritique();
        T.execute();
        try {
            return T.get().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    } //

    public static String getPopularcall(Context ctx) {
        myTaskGetPopular T = new myTaskGetPopular(ctx);
        T.execute();
        try {
            return T.get().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    } //

    public static void getCourseDetails(String courseNum, Context context) {
        myTaskGetCourseDetails T = new myTaskGetCourseDetails(courseNum, context);
        T.execute();
    }//

    public static String Search(Context ctx, String name, String number, String cat1, String cat2, String cat3, String schedule_JSON, Boolean check) {
        myTaskSearch T = new myTaskSearch(ctx, name, number, cat1, cat2, cat3, schedule_JSON, check);
        T.execute();
        try {
            return T.get().toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "error in search";
    }

    public static void getCategories(Spinner sp1, Spinner sp2, Spinner sp3, Context ctx) {
        myTaskGetCourseCategories T = new myTaskGetCourseCategories(sp1, sp2, sp3, ctx);
        T.execute();
    } //

    //-------------------------------------------------------------------------------------------------------------------------

    private static class myTaskGetCourseCategories extends AsyncTask<Void, Void, String> {

        Spinner Category1;
        Spinner Category2;
        Spinner Category3;
        Context Appcontext;

        public myTaskGetCourseCategories(Spinner sp1, Spinner sp2, Spinner sp3, Context ctx) {
            Category1 = sp1;
            Category2 = sp2;
            Category3 = sp3;
            Appcontext = ctx;
        }

        @Override
        protected void onProgressUpdate(Void... progress) {
            // setProgressPercent(progress[0]);
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {

            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, "GetCategories");

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;

            // envelope.setOutputSoapObject(request);
            envelope.bodyOut = request;

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS, 60000);

            String res;

            try {
                httpTransport.call(WSDL_TARGET_NAMESPACE + "/GetCategories", envelope);

                Object response = envelope.getResponse();
                res = response.toString();
                httpTransport.getConnection().disconnect();

            } catch (Exception exception) {
                res = exception.toString();
            }

            return res;
        }


        @Override
        protected void onPostExecute(String result) {
            //showResult.setText(result);

            try {
                JSONObject J0bject = new JSONObject(result);
                JSONArray JList = J0bject.optJSONArray("Table");
                List<String> list = new ArrayList<String>();

                //Toast toast = Toast.makeText(Appcontext,JList.toString(), Toast.LENGTH_LONG);
                //toast.show();
                list.add("");
                for (int i = 0; i < JList.length(); i++) {
                    JSONObject JFaculty = JList.getJSONObject(i);
                    String faculty = JFaculty.getString("category_name");
                    list.add(faculty);
                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Appcontext, R.layout.my_spinner_item, list);

                dataAdapter.setDropDownViewResource(R.layout.my_spinner_dropdown_item);

                Category1.setAdapter(dataAdapter);
                Category2.setAdapter(dataAdapter);
                Category3.setAdapter(dataAdapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Toast toast = Toast.makeText(Appcontext,result, Toast.LENGTH_LONG);
            // toast.show();
        }
    }

    private static class myTaskcritique extends AsyncTask<Void, Void, String> {


        public myTaskcritique() {
        }

        @Override
        protected void onProgressUpdate(Void... progress) {
            // setProgressPercent(progress[0]);
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {

          //   Toast.makeText(Appcontext, MainActivity.recSession.getCritiques(), Toast.LENGTH_LONG).show();

            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, "recommend");

            PropertyInfo propertyInfo1 = new PropertyInfo();
            propertyInfo1.type = PropertyInfo.STRING_CLASS;
            propertyInfo1.name = "usermail";
            propertyInfo1.setValue(MainActivity.session.getusename());

            request.addProperty(propertyInfo1);

            PropertyInfo propertyInfo2 = new PropertyInfo();
            propertyInfo2.type = PropertyInfo.STRING_CLASS;
            propertyInfo2.name = "critiques";
            propertyInfo2.setValue(MainActivity.recSession.getCritiques());

            request.addProperty(propertyInfo2);


            PropertyInfo propertyInfo3 = new PropertyInfo();
            propertyInfo3.type = PropertyInfo.STRING_CLASS;
            propertyInfo3.name = "courses";
            propertyInfo3.setValue(MainActivity.recSession.getCourses());

            request.addProperty(propertyInfo3);

           // Toast toast = Toast.makeText(Appcontext,usermail +" " +ctitiques , Toast.LENGTH_LONG);
             //toast.show();

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.bodyOut = request;

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS, 60000);

            String res;

            try {
                httpTransport.call(WSDL_TARGET_NAMESPACE + "/recommend", envelope);

                Object response = envelope.getResponse();
                res = response.toString();
                httpTransport.getConnection().disconnect();

            } catch (Exception exception) {
                res = exception.toString();
            }
            return res;
        }

        @Override
        protected void onPostExecute(String result) {

         /*   try {
                JSONObject J0bject = new JSONObject(result);
                JSONArray JList = J0bject.optJSONArray("Table");
                JSONObject d = JList.getJSONObject(0);
                course_page.course_name.setText(d.getString("COURSE_NAME"));

                course_page.description.setText(d.getString("Course_Description"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            System.out.println(result);*/
            // Toast toast = Toast.makeText(Appcontext,result, Toast.LENGTH_LONG);
            // toast.show();
        }
    }

    private static class myTaskGetPopular extends AsyncTask<Void, Void, String> {

        String usermail;
        Context context;

        public myTaskGetPopular(Context ctx) {
            usermail = MainActivity.session.getusename();
            context=ctx;
        }

        @Override
        protected void onProgressUpdate(Void... progress) {
            // setProgressPercent(progress[0]);
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {

           // Toast.makeText(context, "from server: "+ popular + " " + grade.getText(), Toast.LENGTH_LONG).show();

            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, "getPopulaCourses");

            PropertyInfo propertyInfo1 = new PropertyInfo();
            propertyInfo1.type = PropertyInfo.STRING_CLASS;
            propertyInfo1.name = "username";
            propertyInfo1.setValue(usermail);

            request.addProperty(propertyInfo1);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;

            // envelope.setOutputSoapObject(request);
            envelope.bodyOut = request;

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS, 60000);

            String res;

            try {
                httpTransport.call(WSDL_TARGET_NAMESPACE + "/getPopulaCourses", envelope);

                Object response = envelope.getResponse();
                res = response.toString();
                httpTransport.getConnection().disconnect();

            } catch (Exception exception) {
                res = exception.toString();
            }
            return res;
        }

        @Override
        protected void onPostExecute(String result) {

         /*   try {
                JSONObject J0bject = new JSONObject(result);
                JSONArray JList = J0bject.optJSONArray("Table");
                JSONObject d = JList.getJSONObject(0);
                course_page.course_name.setText(d.getString("COURSE_NAME"));

                course_page.description.setText(d.getString("Course_Description"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            System.out.println(result);*/
            // Toast toast = Toast.makeText(Appcontext,result, Toast.LENGTH_LONG);
            // toast.show();
        }
    }

    private static class myTaskGetCourseDetails extends AsyncTask<Void, Void, String> {

        Context Appcontext;
        String course_number;

        public myTaskGetCourseDetails(String courseNum, Context ctx) {
            course_number = courseNum;
            Appcontext = ctx;

        }

        @Override
        protected void onProgressUpdate(Void... progress) {
            // setProgressPercent(progress[0]);
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {

            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, "GetCourseDetails");

            PropertyInfo propertyInfo1 = new PropertyInfo();
            propertyInfo1.type = PropertyInfo.STRING_CLASS;
            propertyInfo1.name = "course_num";
            propertyInfo1.setValue(course_number);

            request.addProperty(propertyInfo1);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;

            // envelope.setOutputSoapObject(request);
            envelope.bodyOut = request;

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS, 60000);

            String res;

            try {
                httpTransport.call(WSDL_TARGET_NAMESPACE + "/GetCourseDetails", envelope);

                Object response = envelope.getResponse();
                res = response.toString();
                httpTransport.getConnection().disconnect();

            } catch (Exception exception) {
                res = exception.toString();
            }
            return res;
        }

        @Override
        protected void onPostExecute(String result) {

            try {
                JSONObject J0bject = new JSONObject(result);
                JSONArray JList = J0bject.optJSONArray("Table");
                JSONObject d = JList.getJSONObject(0);
                course_page.course_name.setText(d.getString("COURSE_NAME"));

                course_page.description.setText(d.getString("Course_Description"));

                if(!d.getString("DIFFICULTY_AVG").equals("null")) {
                    String t1 = d.getString("DIFFICULTY_AVG");
                    if(t1.length()>4)
                        course_page.diff.setText(t1.substring(0,3) + "/10");
                    else course_page.diff.setText(t1 + "/10");
                }
                else
                    course_page.diff.setText("לא ידוע");

                if(!d.getString("AVG_RATING").equals("null")) {
                    String t2 = d.getString("AVG_RATING");
                    if (t2.length() > 4)
                        course_page.general.setText(t2.substring(0, 3) + "/10");
                    else
                        course_page.general.setText(t2 + "/10");
                }
                else
                    course_page.general.setText("לא ידוע");

                if(!d.getString("INTEREST_AVG").equals("null")) {
                    String t3 = d.getString("INTEREST_AVG");
                    if(t3.length() >4)
                        course_page.interest.setText( t3.substring(0,3)+ "/10");
                    else  course_page.interest.setText( t3 + "/10");
                }
                else
                    course_page.interest.setText("לא ידוע");

                if(!d.getString("Credit_Points").equals("null"))
                    course_page.credit_points.setText(d.getString("Credit_Points"));
                else
                    course_page.credit_points.setText("לא ידוע");

                String s = d.getString("Location");
                if(!d.getString("Location").equals("null"))
                    course_page.location.setText(d.getString("Location"));
                else
                    course_page.location.setText("             לא ידוע");

                if(!d.getString("lecture_name").equals("null"))
                    course_page.lecture_name.setText(d.getString("lecture_name"));
                else
                    course_page.lecture_name.setText("לא ידוע");

                if(!d.getString("load_AVG").equals("null")) {
                    String t4 = d.getString("load_AVG");
                    if(t4.length()>4)
                    course_page.load_avg.setText(t4.substring(0,3) + "/10");
                    else course_page.load_avg.setText(t4 + "/10");
                }
                else
                    course_page.load_avg.setText("לא ידוע");

                if(!d.getString("lecture_AVG").equals("null")) {
                    String t5 = d.getString("lecture_AVG");
                    if(t5.length()>4)
                    course_page.lecture.setText(t5.substring(0,3) + "/10");
                    else  course_page.lecture.setText(t5 + "/10");
                }
                else
                    course_page.lecture.setText("לא ידוע");


            } catch (JSONException e) {
                e.printStackTrace();
            }
            System.out.println(result);
            // Toast toast = Toast.makeText(Appcontext,result, Toast.LENGTH_LONG);
            // toast.show();
        }
    }

    private static class myTaskSearch extends AsyncTask<Void, Void, String> {

        String course_name;
        String course_number;
        String Category1;
        String Category2;
        String Category3;
        String schedule;
        Boolean mycheck;
        Context Appcontext;


        public myTaskSearch(Context ctx, String name, String number, String cat1, String cat2, String cat3, String schedule_JSON, Boolean check) {
            course_name = name;
            course_number = number;
            Category1 = cat1;
            Category2 = cat2;
            Category3 = cat3;
            mycheck = check;
            schedule = schedule_JSON;
            Appcontext = ctx;
        }

        @Override
        protected void onProgressUpdate(Void... progress) {
            // setProgressPercent(progress[0]);
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {

            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, "Search");

            PropertyInfo propertyInfo1 = new PropertyInfo();
            propertyInfo1.type = PropertyInfo.STRING_CLASS;
            propertyInfo1.name = "name";
            propertyInfo1.setValue(course_name);
            request.addProperty(propertyInfo1);

            PropertyInfo propertyInfo2 = new PropertyInfo();
            propertyInfo2.type = PropertyInfo.STRING_CLASS;
            propertyInfo2.name = "number";
            propertyInfo2.setValue(course_number);
            request.addProperty(propertyInfo2);

            PropertyInfo propertyInfo3 = new PropertyInfo();
            propertyInfo3.type = PropertyInfo.STRING_CLASS;
            propertyInfo3.name = "cat1";
            propertyInfo3.setValue(Category1);
            request.addProperty(propertyInfo3);

            PropertyInfo propertyInfo4 = new PropertyInfo();
            propertyInfo4.type = PropertyInfo.STRING_CLASS;
            propertyInfo4.name = "cat2";
            propertyInfo4.setValue(Category2);
            request.addProperty(propertyInfo4);

            PropertyInfo propertyInfo5 = new PropertyInfo();
            propertyInfo5.type = PropertyInfo.STRING_CLASS;
            propertyInfo5.name = "cat3";
            propertyInfo5.setValue(Category3);
            request.addProperty(propertyInfo5);

            PropertyInfo propertyInfo6 = new PropertyInfo();
            propertyInfo6.type = PropertyInfo.STRING_CLASS;
            propertyInfo6.name = "schedule_JSON";
            propertyInfo6.setValue(schedule);
            request.addProperty(propertyInfo6);

            PropertyInfo propertyInfo7 = new PropertyInfo();
            propertyInfo7.type = PropertyInfo.STRING_CLASS;
            propertyInfo7.name = "check";
            propertyInfo7.setValue(mycheck);
            request.addProperty(propertyInfo7);

            PropertyInfo propertyInfo8 = new PropertyInfo();
            propertyInfo8.type = PropertyInfo.STRING_CLASS;
            propertyInfo8.name = "username";
            if(MainActivity.session.isLoggedIn().equals("True"))
                propertyInfo8.setValue(MainActivity.session.getusename());
            else
                propertyInfo8.setValue("");
            request.addProperty(propertyInfo8);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;

            // envelope.setOutputSoapObject(request);
            envelope.bodyOut = request;
            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS, 60000);

            String res = "=[";

            try {
                httpTransport.call(WSDL_TARGET_NAMESPACE + "/Search", envelope);

                Object response = envelope.getResponse();
                res = response.toString();
                httpTransport.getConnection().disconnect();

            } catch (Exception exception) {
                res = exception.toString();
            }


            return res;
        }


        @Override
        protected void onPostExecute(String result) {

            //  System.out.println(result);
        }
    }

    public static class myTaskComments extends AsyncTask<Void, Void, String> {
        String mail;
        String course;
        String feedback;
        String interest;
        String diff;
        String rating;
        String lecture;
        String load;
        Context t;


        public myTaskComments(String mail, String course, String feedback, String interest, String diff, String rating,String lecture,String load, Context t) {
            this.mail = mail;
            this.course = course;
            this.feedback = feedback;
            this.interest = interest;
            this.diff = diff;
            this.rating = rating;
            this.t = t;
            this.lecture = lecture;
            this.load = load;
        }

        @Override
        protected void onProgressUpdate(Void... progress) {
            // setProgressPercent(progress[0]);
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, "addComment");
            PropertyInfo propertyInfo1 = new PropertyInfo();
            propertyInfo1.type = PropertyInfo.STRING_CLASS;
            propertyInfo1.name = "mail";
            propertyInfo1.setValue(mail);

            PropertyInfo propertyInfo2 = new PropertyInfo();
            propertyInfo2.type = PropertyInfo.STRING_CLASS;
            propertyInfo2.name = "courseNum";
            propertyInfo2.setValue(course);
            //System.out.print("course: "+course);
            PropertyInfo propertyInfo3 = new PropertyInfo();
            propertyInfo3.type = PropertyInfo.STRING_CLASS;
            propertyInfo3.name = "feedback";
            propertyInfo3.setValue(feedback);

            PropertyInfo propertyInfo4 = new PropertyInfo();
            propertyInfo4.type = PropertyInfo.INTEGER_CLASS;
            propertyInfo4.name = "interest";
            propertyInfo4.setValue(Integer.parseInt(interest));

            PropertyInfo propertyInfo5 = new PropertyInfo();
            propertyInfo5.type = PropertyInfo.INTEGER_CLASS;
            propertyInfo5.name = "diff";
            int d = Integer.parseInt(diff);
            propertyInfo5.setValue(Integer.parseInt(diff));

            PropertyInfo propertyInfo6 = new PropertyInfo();
            propertyInfo6.type = PropertyInfo.INTEGER_CLASS;
            propertyInfo6.name = "rating";
            propertyInfo6.setValue(Integer.parseInt(rating));

            PropertyInfo propertyInfo7 = new PropertyInfo();
            propertyInfo7.type = PropertyInfo.INTEGER_CLASS;
            propertyInfo7.name = "load";
            propertyInfo7.setValue(Integer.parseInt(load));

            PropertyInfo propertyInfo8 = new PropertyInfo();
            propertyInfo8.type = PropertyInfo.INTEGER_CLASS;
            propertyInfo8.name = "lecture_grade";
            propertyInfo8.setValue(Integer.parseInt(lecture));

            request.addProperty(propertyInfo1);
            request.addProperty(propertyInfo2);
            request.addProperty(propertyInfo3);
            request.addProperty(propertyInfo4);
            request.addProperty(propertyInfo5);
            request.addProperty(propertyInfo6);
            request.addProperty(propertyInfo7);
            request.addProperty(propertyInfo8);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;


            // envelope.setOutputSoapObject(request);
            envelope.bodyOut = request;

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS, 60000);

            String res = "";

            try {
                httpTransport.call(WSDL_TARGET_NAMESPACE + "/addComment", envelope);

                Object response = envelope.getResponse();
                res = response.toString();

                httpTransport.getConnection().disconnect();

            } catch (Exception exception) {
                res = exception.toString();

            }
            return res;

        }


        @Override
        protected void onPostExecute(String result) {

            // t.setText(result);
            Toast toast = Toast.makeText(t, result, Toast.LENGTH_LONG);
            toast.show();
        }

    }

    public static class myTaskAllComments extends AsyncTask<Void, Void, String> {
        String courseNum;
        Context context;
        ListView comments;

        public myTaskAllComments(String courseNum, Context context, ListView comments) {
            this.courseNum = courseNum;
            this.context = context;
            this.comments = comments;
        }

        @Override
        protected String doInBackground(Void... params) {
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, "getAllCommentsForCourse");
            PropertyInfo propertyInfo1 = new PropertyInfo();
            propertyInfo1.type = PropertyInfo.STRING_CLASS;
            propertyInfo1.name = "coursenumber";
            propertyInfo1.setValue(courseNum);
            request.addProperty(propertyInfo1);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;


            // envelope.setOutputSoapObject(request);
            envelope.bodyOut = request;

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS, 60000);

            String res = "";
            try {
                httpTransport.call(WSDL_TARGET_NAMESPACE + "/getAllCommentsForCourse", envelope);

                Object response = envelope.getResponse();
                res = response.toString();

                httpTransport.getConnection().disconnect();

            } catch (Exception exception) {
                res = exception.toString();

            }
            return res;

        }

        protected void onPostExecute(String result) {
            //  Toast toast = Toast.makeText(context, result, Toast.LENGTH_LONG);
            // toast.show();
            try {

            } catch (Exception e) {
                Toast toast = Toast.makeText(context, e.getMessage().toString(), Toast.LENGTH_LONG);
                toast.show();

            }

            // t.setText(result);

        }


    }

    public static class myTaskAddToWishList extends AsyncTask<Void, Void, String> {
        String mail;
        String courseNum;
        Context t;


        public myTaskAddToWishList(String mail, String courseNum, Context t) {
            this.mail = mail;
            this.courseNum=courseNum;
            this.t = t;
        }

        @Override
        protected void onProgressUpdate(Void... progress) {
            // setProgressPercent(progress[0]);
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, "addToWishList");
            PropertyInfo propertyInfo1 = new PropertyInfo();
            propertyInfo1.type = PropertyInfo.STRING_CLASS;
            propertyInfo1.name = "username";
            propertyInfo1.setValue(mail);

            PropertyInfo propertyInfo2 = new PropertyInfo();
            propertyInfo2.type = PropertyInfo.STRING_CLASS;
            propertyInfo2.name = "course_num";
            propertyInfo2.setValue(courseNum);
            //System.out.print("course: "+course);

            request.addProperty(propertyInfo1);
            request.addProperty(propertyInfo2);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;


            // envelope.setOutputSoapObject(request);
            envelope.bodyOut = request;

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS, 60000);

            String res = "";

            try {
                httpTransport.call(WSDL_TARGET_NAMESPACE + "/addToWishList", envelope);

                Object response = envelope.getResponse();
                res = response.toString();

                httpTransport.getConnection().disconnect();

            } catch (Exception exception) {
                res = exception.toString();

            }
            return res;

        }



        @Override
        protected void onPostExecute(String result) {
            Toast toast;
            if(result.equals("Course Added to Wish List"))
                // t.setText(result);
                toast = Toast.makeText(t, "הקורס נוסף לרשימת הקורסים האהובים", Toast.LENGTH_LONG);
            else if(result.equals("duplicate"))
                toast = Toast.makeText(t, "הקורס כבר נמצא בקורסים אהובים", Toast.LENGTH_LONG);
            else
                toast = Toast.makeText(t, "error", Toast.LENGTH_LONG);
            toast.show();
        }

    }
}
