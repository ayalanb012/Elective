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
    private static final String SOAP_ADDRESS = "http://132.72.65.103/WebService.asmx";

    public static void signInCall(String student,String passwd,TextView res) {

        myTaskSignIn T = new myTaskSignIn(student,passwd, res);
        T.execute();
    }

    public static void getFacultiesCall(Spinner list,Context ctx) {

        myTaskGetFaculties T = new myTaskGetFaculties(list,ctx);
        T.execute();
    }

    public static void setDepartments(Spinner sp, Context ctx, String item) {
        myTaskSetDepartments T = new myTaskSetDepartments(sp,ctx,item);
        T.execute();
    }

    public static void Register(String name,String mail, String pwd, String Fac, String Dep, String schedule_JSON) {
        myTaskRegister T = new myTaskRegister(name,mail,pwd,Fac,Dep,schedule_JSON);
        T.execute();
    }

    public static void Search(String name,String number, String cat1, String cat2, String cat3, String schedule_JSON,Boolean check) {
        myTaskSearch T = new myTaskSearch(name,number,cat1,cat2,cat3,schedule_JSON, check);
        T.execute();
    }


    private static class myTaskSearch extends AsyncTask<Void, Void, String> {

        String course_name;
        String course_number;
        String Category1;
        String Category2;
        String Category3;
        String schedule;
        Boolean mycheck;

        public myTaskSearch(String name,String number, String cat1, String cat2, String cat3, String schedule_JSON,Boolean check ){
            course_name = name;
            course_number = number;
            Category1 = cat1;
            Category2=cat2;
            Category3=cat3;
            mycheck=check;
            schedule =schedule_JSON;
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

           /* PropertyInfo propertyInfo1 = new PropertyInfo();
            propertyInfo1.type = PropertyInfo.STRING_CLASS;
            propertyInfo1.name = "faculty";
            propertyInfo1.setValue(Faculty);
            request.addProperty(propertyInfo1); */

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;

            // envelope.setOutputSoapObject(request);
            envelope.bodyOut = request;
            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS,60000);

            String res="=[";

            try {
                httpTransport.call(WSDL_TARGET_NAMESPACE+"/Search", envelope);

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
            // Toast toast = Toast.makeText(Appcontext,result, Toast.LENGTH_LONG);
            //toast.show();
        }
    }

    private static class myTaskRegister extends AsyncTask<Void, Void, String> {

        String user_name;
        String user_mail;
        String user_pwd;
        String user_Faculty;
        String user_Department;
        String schedule;

        public myTaskRegister(String name,String mail, String pwd, String Fac, String Dep, String schedule_JSON ){
            user_name = name;
            user_mail = mail;
            user_pwd =pwd;
            user_Faculty = Fac;
            user_Department =Dep;
            schedule =schedule_JSON;
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

           /* PropertyInfo propertyInfo1 = new PropertyInfo();
            propertyInfo1.type = PropertyInfo.STRING_CLASS;
            propertyInfo1.name = "faculty";
            propertyInfo1.setValue(Faculty);
            request.addProperty(propertyInfo1); */

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;

            // envelope.setOutputSoapObject(request);
            envelope.bodyOut = request;
            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS,60000);

            String res="=[";

            try {
                httpTransport.call(WSDL_TARGET_NAMESPACE+"/Register", envelope);

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
            // Toast toast = Toast.makeText(Appcontext,result, Toast.LENGTH_LONG);
            //toast.show();
        }
    }

    private static class myTaskSetDepartments extends AsyncTask<Void, Void, String> {

        Spinner DepartmentList;
        Context Appcontext;
        String Faculty;

        public myTaskSetDepartments(Spinner sp, Context ctx, String item ){
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
            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS,60000);

           String res="=[";

            try {
                httpTransport.call(WSDL_TARGET_NAMESPACE+"/GetDepartmentsByFaculty", envelope);

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

           // Toast toast = Toast.makeText(Appcontext,result, Toast.LENGTH_LONG);
             //toast.show();
        }
    }

    private static class myTaskGetFaculties extends AsyncTask<Void, Void, String> {

       Spinner FacultiesList;
        Context Appcontext;

        public myTaskGetFaculties(Spinner sp, Context ctx ){
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


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;

            // envelope.setOutputSoapObject(request);
            envelope.bodyOut = request;

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS,60000);

            String res;

            try {
                httpTransport.call(WSDL_TARGET_NAMESPACE+"/GetFaculties", envelope);

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

                for(int i=0; i< JList.length(); i++){
                    JSONObject JFaculty = JList.getJSONObject(i);
                    String faculty = JFaculty.getString("Faculty_Name");
                    list.add(faculty);
                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Appcontext,android.R.layout.simple_spinner_item,list);

                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

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

        public myTaskSignIn(String s, String p, TextView tv){
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


            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS,60000);

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


}
