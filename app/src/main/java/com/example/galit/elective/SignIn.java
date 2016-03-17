package com.example.galit.elective;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class SignIn extends AppCompatActivity {
    private static final String SOAP_ACTION = "http://tempuri.org/isRegistered";

    private static final String OPERATION_NAME = "isRegistered";// your webservice web method name

    private static final String WSDL_TARGET_NAMESPACE = "http://tempuri.org";

    private static final String SOAP_ADDRESS = "http://WebService.asmx";

    TextView tvData1;
    EditText edata1;
    EditText edata2;
    Button button;
    String student;
    String passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        tvData1 = (TextView) findViewById(R.id.textView1);
        button = (Button) findViewById(R.id.bttn_sign_in);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                edata1 = (EditText) findViewById(R.id.txt_mail);
                student = edata1.getText().toString();


                edata2 = (EditText) findViewById(R.id.txt_password);
                passwd = edata2.getText().toString();

                myTask T = new myTask();
                T.execute();

                //tvData1 = (TextView) findViewById(R.id.textView1);

            }
        });
    }

    //this method is activated when logo is clicked. the method return to main activity
    public void HomeClicked(View v) {
        ImageButton button = (ImageButton) v;
        //startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();

    }


    //first parameter is for doInbackground, the second for progressupdate, the third for the results
    private class myTask extends AsyncTask<Void, Void, String> {

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
                System.out.println("--------------> got the object response !!!!!!<--------------");
                Object response = envelope.getResponse();

                // System.out.println("-------------->"+httpTransport.responseDump+"<--------------");
                //   System.out.println(httpTransport.responseDump);
                res = response.toString();
                // tvData1.setText(response.toString());
                httpTransport.getConnection().disconnect();

            } catch (Exception exception) {
                // tvData1.setText(exception.toString() + "  Or enter number is not Available!");
                res = exception.toString();
            }

            //tvData1 = (TextView) findViewById(R.id.textView1);--------------??????????
            return res;
        }


        @Override
        protected void onPostExecute(String result) {
            tvData1.setText(result);
            System.out.println("-------------->postExecute<--------------");
            Toast toast = Toast.makeText(getApplicationContext(), "succsess", Toast.LENGTH_LONG);
            toast.show();
        }


    }
}