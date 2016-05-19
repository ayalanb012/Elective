package com.example.galit.elective;
import android.app.Activity;
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
 * Created by User on 17/03/2016.
 */
public class ServerCalls {

    private static final String SOAP_ACTION = "http://tempuri.org/isRegistered";
    private static final String OPERATION_NAME = "isRegistered";// your webservice web method name
    private static final String WSDL_TARGET_NAMESPACE = "http://tempuri.org";
    private static final String SOAP_ADDRESS = "http://proj.ise.bgu.ac.il/1155/webservice.asmx";

    static String signInCall(String student, String passwd) {

        myTaskSignIn T = new myTaskSignIn(student, passwd);
        T.execute();
        try {
            return T.get().toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "error in signIn";
    }

    public static String getNameCall(String student) {

        myTaskgetUserName T = new myTaskgetUserName(student);
        T.execute();
        try {
            return T.get().toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "error in getName";
    }

    public static void getFacultiesCall(Spinner list, Context ctx, String defaultValue) {

        myTaskGetFaculties T = new myTaskGetFaculties(list, ctx, defaultValue);
        T.execute();
    }

    public static void setDepartments(Spinner sp, Context ctx, String item,String defaultValue) {
        myTaskSetDepartments T = new myTaskSetDepartments(sp, ctx, item, defaultValue);
        T.execute();
    }

    public static String Register(Context ctx, String name, String mail, String pwd, String Fac, String Dep, String schedule_JSON) {
        myTaskRegister T = new myTaskRegister(ctx, name, mail, pwd, Fac, Dep, schedule_JSON);
        T.execute();
        try {
            return T.get().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error in register";
    }

    public static void changeUserPassword(String newPassword, String userMail,Context context) {
        myTaskchangeUserPassword T = new myTaskchangeUserPassword(newPassword, userMail,context);
        T.execute();
    }

    public static String getUserDetailsCall(String Email) {
        myTaskUserDetails T = new myTaskUserDetails(Email);
        T.execute();
        try {
            return T.get().toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "error in signIn";
    }

    public static String updateDetails(Context ctx, String name, String mail, String Fac, String Dep, String schedule_JSON) {
        myTaskUpdateDetails T = new myTaskUpdateDetails(ctx, name, mail, Fac, Dep, schedule_JSON);
        T.execute();
        try {
            return T.get().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error in updateDetails";
    }

//----------------------------------------------------------------------------------------------------------------------------------//

    private static class myTaskchangeUserPassword extends AsyncTask<Void, Void, String> {

        String user_mail;
        String user_pwd;
        Context myCtx;

        public myTaskchangeUserPassword(String newPassword, String userMail,Context context) {

            user_mail = userMail;
            user_pwd = newPassword;
            myCtx = context;
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

            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, "changeUserPassword");

            PropertyInfo propertyInfo2 = new PropertyInfo();
            propertyInfo2.type = PropertyInfo.STRING_CLASS;
            propertyInfo2.name = "email";
            propertyInfo2.setValue(user_mail);
            request.addProperty(propertyInfo2);

            PropertyInfo propertyInfo3 = new PropertyInfo();
            propertyInfo3.type = PropertyInfo.STRING_CLASS;
            propertyInfo3.name = "pwd";
            propertyInfo3.setValue(user_pwd);
            request.addProperty(propertyInfo3);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;

            // envelope.setOutputSoapObject(request);
            envelope.bodyOut = request;
            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS, 60000);

            String res = "=[";

            try {

                httpTransport.call(WSDL_TARGET_NAMESPACE + "/changeUserPassword", envelope);

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

            //Toast toast = Toast.makeText(myCtx, result, Toast.LENGTH_LONG);
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
            SoapObject request1 = new SoapObject(WSDL_TARGET_NAMESPACE, "isEmailValid");
            PropertyInfo propertyInfo_validation = new PropertyInfo();
            propertyInfo_validation.type = PropertyInfo.STRING_CLASS;
            propertyInfo_validation.name = "email";
            propertyInfo_validation.setValue(user_mail);
            request1.addProperty(propertyInfo_validation);

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

            SoapSerializationEnvelope envelope1 = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope1.dotNet = true;
            envelope1.bodyOut = request1;

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;

            // envelope.setOutputSoapObject(request);
            envelope.bodyOut = request;
            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS, 60000);

            String res = "=[";
            try {

                httpTransport.call(WSDL_TARGET_NAMESPACE + "/isEmailValid", envelope1);

                Object response = envelope1.getResponse();
                res = response.toString();
                httpTransport.getConnection().disconnect();

            } catch (Exception exception) {
                res = exception.toString();
            }

            if (res.equals("true")) {
                res="invalidMail";
                return res;
            }
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
        String defaultValue;

        public myTaskSetDepartments(Spinner sp, Context ctx, String item,String defaultValue) {
            DepartmentList = sp;
            Appcontext = ctx;
            Faculty = item;
            this.defaultValue = defaultValue;
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

                if (defaultValue!=null)
                    DepartmentList.setSelection((dataAdapter.getPosition(defaultValue)));

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
        String defaultValue;

        public myTaskGetFaculties(Spinner sp, Context ctx,String defaultValue) {
            FacultiesList = sp;
            Appcontext = ctx;
            this.defaultValue = defaultValue;
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

                if (defaultValue!=null)
                    FacultiesList.setSelection((dataAdapter.getPosition(defaultValue)));


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


        public myTaskSignIn(String s, String p) {
            student = s;
            passwd = p;
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

            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, "isRegistered");
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
               //httpTransport.call(SOAP_ACTION, envelope);
                httpTransport.call(WSDL_TARGET_NAMESPACE + "/isRegistered", envelope);

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

            //Toast toast = Toast.makeText(getApplicationContext(), "succsess", Toast.LENGTH_LONG);
            //toast.show();
        }


    }

    public static class myTaskUserDetails extends AsyncTask<Void, Void, String> {
        String Email;

         public myTaskUserDetails(String Email) {
            this.Email = Email;
        }

        @Override
        protected String doInBackground(Void... params) {
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, "getAllUserDetails");
            PropertyInfo propertyInfo1 = new PropertyInfo();
            propertyInfo1.type = PropertyInfo.STRING_CLASS;
            propertyInfo1.name = "email";
            propertyInfo1.setValue(Email);
            request.addProperty(propertyInfo1);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.bodyOut = request;

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS, 60000);

            String res = "";
            try {
                httpTransport.call(WSDL_TARGET_NAMESPACE + "/getAllUserDetails", envelope);

                Object response = envelope.getResponse();
                res = response.toString();

                httpTransport.getConnection().disconnect();

            } catch (Exception exception) {
                res = exception.toString();
            }

            return res;
        }

        protected void onPostExecute(String result) {
            //System.out.println(result);
        }
    }

    private static class myTaskgetUserName extends AsyncTask<Void, Void, String> {
            String student_mail;

            public myTaskgetUserName(String s) {
                student_mail = s;
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

                SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, "getName");
                PropertyInfo propertyInfo1 = new PropertyInfo();
                propertyInfo1.type = PropertyInfo.STRING_CLASS;
                propertyInfo1.name = "mail";
                propertyInfo1.setValue(student_mail);

                request.addProperty(propertyInfo1);


                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                        SoapEnvelope.VER11);
                envelope.dotNet = true;

                // envelope.setOutputSoapObject(request);
                envelope.bodyOut = request;


                HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS, 60000);

                String res;

                try {
                    httpTransport.call(WSDL_TARGET_NAMESPACE + "/getName", envelope);

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
                //Toast toast = Toast.makeText(getApplicationContext(), "succsess", Toast.LENGTH_LONG);
                //toast.show();
            }


        }


    private static class myTaskUpdateDetails extends AsyncTask<Void, Void, String> {

        String user_name;
        String user_mail;
        String user_Faculty;
        String user_Department;
        String schedule;
        Context myCtx;

        public myTaskUpdateDetails(Context ctx, String name, String mail, String Fac, String Dep, String schedule_JSON) {
            user_name = name;
            user_mail = mail;
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

            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, "updateUserDetails");

            PropertyInfo propertyInfo1 = new PropertyInfo();
            propertyInfo1.type = PropertyInfo.STRING_CLASS;
            propertyInfo1.name = "name";
            propertyInfo1.setValue(user_name);
            request.addProperty(propertyInfo1);

            PropertyInfo propertyInfo2 = new PropertyInfo();
            propertyInfo2.type = PropertyInfo.STRING_CLASS;
            propertyInfo2.name = "Email";
            propertyInfo2.setValue(user_mail);
            request.addProperty(propertyInfo2);

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

                httpTransport.call(WSDL_TARGET_NAMESPACE + "/updateUserDetails", envelope);

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




}










//GetCourseDetails