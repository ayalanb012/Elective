package com.example.galit.elective;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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
                JSONObject Jlist = new JSONObject(result);
                //JSONArray JList2 = Jlist.optJSONArray("Faculty_Name");
                //ArrayList<String> list = new ArrayList<String>();

               // Toast toast = Toast.makeText(Appcontext,Jlist.toString(), Toast.LENGTH_LONG);
               // toast.show();
                //for(int i = 0; i< JList2.length(); i++){
                    //View T = new TextView(Appcontext);
                   // list.add(JList2.get(i).toString());
               // }
               /* ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Appcontext,android.R.layout.simple_list_item_1,list);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                FacultiesList.setAdapter(dataAdapter);
*/
               // addListenerOnSpinnerItemSelection();

            } catch (JSONException e) {
                e.printStackTrace();
            }

            Toast toast = Toast.makeText(Appcontext,result, Toast.LENGTH_LONG);
            toast.show();
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
