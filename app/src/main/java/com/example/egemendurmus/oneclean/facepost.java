package com.example.egemendurmus.oneclean;

import android.os.AsyncTask;
import android.util.Log;

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

/**
 * Created by egemen.durmus on 02/06/16.
 */
public class facepost extends AsyncTask<Void, Void, Void> {

    String responseServer,username,password;
    private static final String TAG = "LoginActivity";
    String url = "http://46.197.42.230:85/Membership.svc/login";
    String veri_string;




    protected Void doInBackground(Void... unused) {



        username=MainActivity.fb_user_id;
        password=MainActivity.fb_accessToken;
        Log.e("response", "username&pass" + username+password);


        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("FacebookID", username);
            jsonObj.put("Token", password);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        HttpClient httpClient = new DefaultHttpClient(); //Deprecated


        StringEntity params1 = null;
        try {
            HttpPost request = new HttpPost("http://46.197.42.230:85/Membership.svc/login");
            params1 = new StringEntity(jsonObj.toString());
            request.addHeader("content-type", "application/x-www-form-urlencoded");
            request.setEntity(params1);
            HttpResponse response = httpClient.execute(request);
            InputStream inputStream = response.getEntity().getContent();
            InputStreamToStringExample str = new InputStreamToStringExample();
            responseServer = str.getStringFromInputStream(inputStream);
            Log.e("response", "responses -----" + responseServer);




            try {

              JSONObject  jb= new JSONObject(responseServer);
                Log.e("mesaj", jb.toString());

               /* String ticket = jb.getString("Ticket");
                Log.e("mesaj", ticket);

                String messageData = jb.getString("Data");
                Log.e("mesaj",  messageData);
                JSONObject InData = new JSONObject(messageData);
                isim=InData.getString("FirstName");
                Log.e("mesaj", isim);


                if(isim!=null){

                }else{
                    isim="egemen";
                }*/

            } catch (JSONException e) {
                e.printStackTrace();
            }









            // handle response here...
        } catch (Exception ex) {
            // handle exception here
        } finally {
            httpClient.getConnectionManager().shutdown(); //Deprecated
        }


     //   veri_string = post.httpPost(url, "POST", params1, 20000); //PostClass daki httpPost metodunu �a��rd�k.Gelen string de�erini ald�k

       // Log.d("HTTP POST CEVAP:", "" + veri_string);// gelen veriyi log tuttuk
      //  Log.d("para:", "" + params);// gelen veriyi log tuttuk

        return null;
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

    public class execute {
    }
}


