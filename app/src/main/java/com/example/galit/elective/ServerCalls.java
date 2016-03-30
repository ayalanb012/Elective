package com.example.galit.elective;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
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
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
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
import java.util.List;

/**
 * Created by User on 17/03/2016.
 */
public class ServerCalls {

    private static final String SOAP_ACTION = "http://tempuri.org/isRegistered";
    private static final String OPERATION_NAME = "isRegistered";// your webservice web method name
    private static final String WSDL_TARGET_NAMESPACE = "http://tempuri.org";
    private static final String SOAP_ADDRESS = "http://---/WebService.asmx";

    public static void signInCall(String student, String passwd, TextView res) {

        myTaskSignIn T = new myTaskSignIn(student, passwd, res);
        T.execute();
    }

    public static void getFacultiesCall(Spinner list, Context ctx) {

        myTaskGetFaculties T = new myTaskGetFaculties(list, ctx);
        T.execute();
    }

    public static void setDepartments(Spinner sp, Context ctx, String item) {
        myTaskSetDepartments T = new myTaskSetDepartments(sp, ctx, item);
        T.execute();
    }

    public static void Register(Context ctx, String name, String mail, String pwd, String Fac, String Dep, String schedule_JSON) {
        myTaskRegister T = new myTaskRegister(ctx, name, mail, pwd, Fac, Dep, schedule_JSON);
        T.execute();
    }

    public static String Search(Context ctx, String name, String number, String cat1, String cat2, String cat3, String schedule_JSON, Boolean check) {
        myTaskSearch T = new myTaskSearch(ctx, name, number, cat1, cat2, cat3, schedule_JSON, check);
        T.execute();
        return T.search_result;
    }

    public static void getCategories(Spinner sp1, Spinner sp2, Spinner sp3, Context ctx) {
        myTaskGetCourseCategories T = new myTaskGetCourseCategories(sp1, sp2, sp3, ctx);
        T.execute();
    }

    public static void commentCall(String mail, String course, String feedback, String interest, String diff, String rating,Context t) {

        myTaskComments T = new myTaskComments(mail, course, feedback,interest,diff,rating,t);
        T.execute();
    }
//----------------------------------------------------------------------------------------------------------------------------------//

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

    private static class myTaskSearch extends AsyncTask<Void, Void, String> {

        String course_name;
        String course_number;
        String Category1;
        String Category2;
        String Category3;
        String schedule;
        Boolean mycheck;
        Context Appcontext;
        String search_result;

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
            //showResult.setText(result);

           /* try {
                JSONObject J0bject = new JSONObject(result);
                JSONArray JList = J0bject.optJSONArray("Table");
                List<String> list = new ArrayList<String>();


                for(int i=0; i< JList.length(); i++){
                    JSONObject JFaculty = JList.getJSONObject(i);
                    String faculty = JFaculty.getString("Department_Name");
                    list.add(faculty);
                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Appcontext,android.R.layout.simple_spinner_item,list);

                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                DepartmentList.setAdapter(dataAdapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }
                */


            // startActivity(new Intent(Appcontext, reccomand.class));
            //finish();
            System.out.println(result);
            Toast toast = Toast.makeText(Appcontext, result, Toast.LENGTH_LONG);
            toast.show();
            search_result = result;
            // afterExecute(result);
        }


    }

    private static class myTaskRegister extends AsyncTask<Void, Void, String> {

        String user_name;
        String user_mail;
        String user_pwd;
        String user_Faculty;
        String user_Department;
        String schedule;
        Context myCtx;

        public myTaskRegister(Context ctx, String name, String mail, String pwd, String Fac, String Dep, String schedule_JSON) {
            user_name = name;
            user_mail = mail;
            user_pwd = pwd;
            user_Faculty = Fac;
            user_Department = Dep;
            schedule = schedule_JSON;
            myCtx = ctx;
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

            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, "Register");

            PropertyInfo propertyInfo1 = new PropertyInfo();
            propertyInfo1.type = PropertyInfo.STRING_CLASS;
            propertyInfo1.name = "name";
            propertyInfo1.setValue(user_name);
            request.addProperty(propertyInfo1);

            PropertyInfo propertyInfo2 = new PropertyInfo();
            propertyInfo2.type = PropertyInfo.STRING_CLASS;
            propertyInfo2.name = "eid";
            propertyInfo2.setValue(user_mail);
            request.addProperty(propertyInfo2);

            PropertyInfo propertyInfo3 = new PropertyInfo();
            propertyInfo3.type = PropertyInfo.STRING_CLASS;
            propertyInfo3.name = "pwd";
            propertyInfo3.setValue(user_pwd);
            request.addProperty(propertyInfo3);

            PropertyInfo propertyInfo4 = new PropertyInfo();
            propertyInfo4.type = PropertyInfo.STRING_CLASS;
            propertyInfo4.name = "Faculty";
            propertyInfo4.setValue(user_Faculty);
            request.addProperty(propertyInfo4);

            PropertyInfo propertyInfo5 = new PropertyInfo();
            propertyInfo5.type = PropertyInfo.STRING_CLASS;
            propertyInfo5.name = "Department";
            propertyInfo5.setValue(user_Department);
            request.addProperty(propertyInfo5);

            PropertyInfo propertyInfo6 = new PropertyInfo();
            propertyInfo6.type = PropertyInfo.STRING_CLASS;
            propertyInfo6.name = "schedule_JSON";
            propertyInfo6.setValue(schedule);
            request.addProperty(propertyInfo6);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;

            // envelope.setOutputSoapObject(request);
            envelope.bodyOut = request;
            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS, 60000);

            String res = "=[";

            try {
                httpTransport.call(WSDL_TARGET_NAMESPACE + "/Register", envelope);

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

            Toast toast = Toast.makeText(myCtx, result, Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private static class myTaskSetDepartments extends AsyncTask<Void, Void, String> {

        Spinner DepartmentList;
        Context Appcontext;
        String Faculty;

        public myTaskSetDepartments(Spinner sp, Context ctx, String item) {
            DepartmentList = sp;
            Appcontext = ctx;
            Faculty = item;
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

            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, "GetDepartmentsByFaculty");

            PropertyInfo propertyInfo1 = new PropertyInfo();
            propertyInfo1.type = PropertyInfo.STRING_CLASS;
            propertyInfo1.name = "faculty";
            propertyInfo1.setValue(Faculty);
            request.addProperty(propertyInfo1);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;

            // envelope.setOutputSoapObject(request);
            envelope.bodyOut = request;
            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS, 60000);

            String res = "=[";

            try {
                httpTransport.call(WSDL_TARGET_NAMESPACE + "/GetDepartmentsByFaculty", envelope);

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


                for (int i = 0; i < JList.length(); i++) {
                    JSONObject JFaculty = JList.getJSONObject(i);
                    String faculty = JFaculty.getString("Department_Name");
                    list.add(faculty);
                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Appcontext, R.layout.my_spinner_item, list);

                dataAdapter.setDropDownViewResource(R.layout.my_spinner_dropdown_item);

                DepartmentList.setAdapter(dataAdapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Toast toast = Toast.makeText(Appcontext,result, Toast.LENGTH_LONG);
            //toast.show();
        }
    }

    private static class myTaskGetFaculties extends AsyncTask<Void, Void, String> {

        Spinner FacultiesList;
        Context Appcontext;

        public myTaskGetFaculties(Spinner sp, Context ctx) {
            FacultiesList = sp;
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

            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, "GetFaculties");

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;

            // envelope.setOutputSoapObject(request);
            envelope.bodyOut = request;

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS, 60000);

            String res;

            try {
                httpTransport.call(WSDL_TARGET_NAMESPACE + "/GetFaculties", envelope);

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

                for (int i = 0; i < JList.length(); i++) {
                    JSONObject JFaculty = JList.getJSONObject(i);
                    String faculty = JFaculty.getString("Faculty_Name");
                    list.add(faculty);
                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Appcontext, R.layout.my_spinner_item, list);

                dataAdapter.setDropDownViewResource(R.layout.my_spinner_dropdown_item);

                FacultiesList.setAdapter(dataAdapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Toast toast = Toast.makeText(Appcontext,result, Toast.LENGTH_LONG);
            // toast.show();
        }
    }

    private static class myTaskSignIn extends AsyncTask<Void, Void, String> {

        String student;
        String passwd;
        TextView showResult;

        public myTaskSignIn(String s, String p, TextView tv) {
            student = s;
            passwd = p;
            showResult = tv;
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

            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, OPERATION_NAME);
            PropertyInfo propertyInfo1 = new PropertyInfo();
            propertyInfo1.type = PropertyInfo.STRING_CLASS;
            propertyInfo1.name = "eid";
            propertyInfo1.setValue(student);

            PropertyInfo propertyInfo2 = new PropertyInfo();
            propertyInfo2.type = PropertyInfo.STRING_CLASS;
            propertyInfo2.name = "pwd";
            propertyInfo2.setValue(passwd);

            request.addProperty(propertyInfo1);
            request.addProperty(propertyInfo2);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;

            // envelope.setOutputSoapObject(request);
            envelope.bodyOut = request;


            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS, 60000);

            String res;

            try {
                httpTransport.call(SOAP_ACTION, envelope);

                Object response = envelope.getResponse();
                res = response.toString();
                // tvData1.setText(response.toString());
                httpTransport.getConnection().disconnect();

            } catch (Exception exception) {
                // tvData1.setText(exception.toString() + "  Or enter number is not Available!");
                res = exception.toString();
            }

            return res;
        }


        @Override
        protected void onPostExecute(String result) {
            showResult.setText(result);

            //Toast toast = Toast.makeText(getApplicationContext(), "succsess", Toast.LENGTH_LONG);
            //toast.show();
        }


    }


    public static class myTaskComments extends AsyncTask<Void, Void, String> {
        String mail;
        String course;
        String feedback;
        String interest;
        String diff;
        String rating;
        Context t;


        public myTaskComments(String mail, String course, String feedback, String interest, String diff, String rating,Context t) {
            this.mail = mail;
            this.course = course;
            this.feedback = feedback;
            this.interest = interest;
            this.diff = diff;
            this.rating = rating;
            this.t= t;
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
            propertyInfo1.type = PropertyInfo.STRING_CLASS;
            propertyInfo1.name = "courseNum";
            propertyInfo1.setValue(course);

            PropertyInfo propertyInfo3 = new PropertyInfo();
            propertyInfo1.type = PropertyInfo.STRING_CLASS;
            propertyInfo1.name = "feedback";
            propertyInfo1.setValue(feedback);

            PropertyInfo propertyInfo4 = new PropertyInfo();
            propertyInfo1.type = PropertyInfo.INTEGER_CLASS;
            propertyInfo1.name = "interest";
            propertyInfo1.setValue(Integer.parseInt(interest));

            PropertyInfo propertyInfo5 = new PropertyInfo();
            propertyInfo1.type = PropertyInfo.INTEGER_CLASS;
            propertyInfo1.name = "diff";
            propertyInfo1.setValue(Integer.parseInt(diff));

            PropertyInfo propertyInfo6 = new PropertyInfo();
            propertyInfo1.type = PropertyInfo.INTEGER_CLASS;
            propertyInfo1.name = "rating";
            propertyInfo1.setValue(Integer.parseInt(rating));

            request.addProperty(propertyInfo1);
            request.addProperty(propertyInfo2);
            request.addProperty(propertyInfo3);
            request.addProperty(propertyInfo4);
            request.addProperty(propertyInfo5);
            request.addProperty(propertyInfo6);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;

            // envelope.setOutputSoapObject(request);
            envelope.bodyOut = request;

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS, 60000);

            String res;

            try
            {
                httpTransport.call(WSDL_TARGET_NAMESPACE + "/addComment", envelope);

                Object response = envelope.getResponse();
                res = response.toString();
                httpTransport.getConnection().disconnect();

            }
            catch (Exception exception)
            {
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
}
