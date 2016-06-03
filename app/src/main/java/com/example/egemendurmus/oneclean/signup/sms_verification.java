package com.example.egemendurmus.oneclean.signup;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.egemendurmus.oneclean.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class sms_verification extends AppCompatActivity {
    static String responseServer,ticket,data_object,UserID,VerificationCode,PhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_varification);
        final Typeface tf = Typeface.createFromAsset(getApplicationContext().getAssets(), "opensans.ttf");//yazı fontu




        Button submit= (Button) findViewById(R.id.button);
        submit.setTypeface(tf);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new verification().execute((Void) null);

            }
        });


    }

    private class verification extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {





            JSONObject jsonObj = new JSONObject();
            try {
                jsonObj.put("Ticket", ticket);

                jsonObj.put("UserID", UserID);
                jsonObj.put("VerificationCode", VerificationCode);
                jsonObj.put("PhoneNumber", PhoneNumber);


            } catch (JSONException e) {
                e.printStackTrace();
            }


            HttpClient httpClient = new DefaultHttpClient(); //Deprecated


            StringEntity params1 = null;
            try {
                HttpPost request = new HttpPost("http://46.197.42.230:85/Membership.svc/VerifyUserPhoneNumber");
                params1 = new StringEntity(jsonObj.toString());
                request.addHeader("content-type", "application/x-www-form-urlencoded");
                request.setEntity(params1);
                HttpResponse response = httpClient.execute(request);
                InputStream inputStream = response.getEntity().getContent();
                InputStreamToStringExample str = new InputStreamToStringExample();
                responseServer = str.getStringFromInputStream(inputStream);
                Log.e("response", "responsesverify -----" + responseServer);





                // handle response here...
            } catch (Exception ex) {
                // handle exception here
            } finally {
                httpClient.getConnectionManager().shutdown(); //Deprecated
            }








            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);






        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(Void result) {
            super.onCancelled(result);
        }
    }
    public static class InputStreamToStringExample {

        public static void main(String[] args) throws IOException {

            // intilize an InputStream
            InputStream is =
                    new ByteArrayInputStream("file content..blah blah".getBytes());

            String result = getStringFromInputStream(is);

            System.out.println(result);
            System.out.println("Done");


        }

        // convert InputStream to String
        private static String getStringFromInputStream(InputStream is) {

            BufferedReader br = null;
            StringBuilder sb = new StringBuilder();

            String line;
            try {

                br = new BufferedReader(new InputStreamReader(is));
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return sb.toString();
        }

    }
}
