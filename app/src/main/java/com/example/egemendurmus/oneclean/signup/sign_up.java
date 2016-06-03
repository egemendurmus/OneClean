package com.example.egemendurmus.oneclean.signup;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

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

public class sign_up extends AppCompatActivity {

    String mail,name,surname,phone_number,password,re_password,ticket,data_object,UserID,VerificationCode,PhoneNumber;
    final Context context = this;
    CheckBox agreement;
public String agree;
    public static String status_ctrl;
    boolean agr;

    String responseServer,task_username,task_usersurname,task_password,task_phonenumber,task_mail,task_StatusCode,task_mesaj;
    private static final String TAG = "LoginActivity";
    String url = "http://46.197.42.230:85/Membership.svc/login";
    String veri_string;
    //sign_up s_up=new sign_up();
    alert_activity alerts=new alert_activity();


alert_activity alert=new alert_activity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);







agreement= (CheckBox) findViewById(R.id.checkBox);

        agreement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if ( isChecked )
                {
                    agr=true;
                    Toast.makeText(getApplicationContext(),
                            "Checked", Toast.LENGTH_LONG).show();

                    StringBuffer result = new StringBuffer();
                    result.append("IPhone check : ").append(agreement.isChecked());

                }else{
                    agr=false;
                    Toast.makeText(getApplicationContext(),
                            "unChecked", Toast.LENGTH_LONG).show();
                }
            }
        });






        Button login = (Button) findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //formu tanıt
                EditText et_mail= (EditText) findViewById(R.id.editText);
                EditText et_name= (EditText) findViewById(R.id.editText3);
                EditText et_phone_number= (EditText) findViewById(R.id.editText4);

                EditText et_password= (EditText) findViewById(R.id.editText2);
                EditText et__re_password= (EditText) findViewById(R.id.editText5);
                EditText et__surname= (EditText) findViewById(R.id.editText6);









                //stringleri al
                mail=et_mail.getText().toString();
                name=et_name.getText().toString();
                surname=et__surname.getText().toString();
                phone_number=et_phone_number.getText().toString();
                password=et_password.getText().toString();
                re_password=et__re_password.getText().toString();








                Log.e("bak",mail+" "+name+surname);



                //boşmu değilmi onların kontrolü aynı zmanda password ve repasswordün kontrölüde var

                if(name.isEmpty()){


                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            context);

                    // set title
                    alertDialogBuilder.setTitle("giriş");


                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Hoşgeldin"+" "+"boş alanı doldur (name)")
                            .setCancelable(false)
                            .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // if this button is clicked, close
                                    // current activity
                                    dialog.cancel();
                                }
                            });



                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();


                }else if(surname.isEmpty()){


                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            context);

                    // set title
                    alertDialogBuilder.setTitle("giriş");


                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Hoşgeldin"+" "+"boş alanı doldur (surname)")
                            .setCancelable(false)
                            .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // if this button is clicked, close
                                    // current activity
                                    dialog.cancel();
                                }
                            });



                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();


                }else if(mail.isEmpty()){


                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            context);

                    // set title
                    alertDialogBuilder.setTitle("giriş");


                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Hoşgeldin"+" "+"boş alanı doldur (mail)")
                            .setCancelable(false)
                            .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // if this button is clicked, close
                                    // current activity
                                    dialog.cancel();
                                }
                            });



                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();


                }else if(phone_number.isEmpty()){


                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            context);

                    // set title
                    alertDialogBuilder.setTitle("giriş");


                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Hoşgeldin"+" "+"boş alanı doldur (telefon)")
                            .setCancelable(false)
                            .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // if this button is clicked, close
                                    // current activity
                                    dialog.cancel();
                                }
                            });



                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();


                }else  if(password.isEmpty()){


                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            context);

                    // set title
                    alertDialogBuilder.setTitle("giriş");


                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Hoşgeldin"+" "+"boş alanı doldur (pass)")
                            .setCancelable(false)
                            .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // if this button is clicked, close
                                    // current activity
                                    dialog.cancel();
                                }
                            });



                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();


                }else if(re_password.isEmpty()){


                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            context);

                    // set title
                    alertDialogBuilder.setTitle("giriş");


                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Hoşgeldin"+" "+"boş alanı doldur (repass)")
                            .setCancelable(false)
                            .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // if this button is clicked, close
                                    // current activity
                                    dialog.cancel();
                                }
                            });



                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();


                }else  if(!password.equals(re_password)){


                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            context);

                    // set title
                    alertDialogBuilder.setTitle("giriş");


                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Hoşgeldin"+" "+"boş alanı doldur (repass ile pass aynı değil)"+password+" "+re_password)
                            .setCancelable(false)
                            .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // if this button is clicked, close
                                    // current activity
                                    dialog.cancel();
                                }
                            });



                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();


                }else if(password.equals(re_password)){



                    Log.e("agree", String.valueOf(agr));



                    if(agr==true){
                        Log.e("agree", String.valueOf(true));


                        task_username=name;
                        task_usersurname=surname;
                        task_mail=mail;
                        task_password=re_password;
                        task_phonenumber=phone_number;






                     /*



                      alert_activity.alertDialogShow(context,"bu zaten var");

                    s_post.hatakodlar();



                      Log.i("genel",status_ctrl);*/

                        new signtask().execute((Void) null);



                    }
                    if(agr==false){

                        Log.e("agree", String.valueOf(agr));

                    }






















                }




            }
        });

















    }

    private class signtask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {



            Log.e("response", "username&pass" + task_username + task_password);


            JSONObject jsonObj = new JSONObject();
            try {
                jsonObj.put("FirstName", task_username);

                jsonObj.put("LastName", task_usersurname);
                jsonObj.put("Email", task_mail);
                jsonObj.put("Password", task_password);
                jsonObj.put("PhoneNumber", task_phonenumber);


            } catch (JSONException e) {
                e.printStackTrace();
            }


            HttpClient httpClient = new DefaultHttpClient(); //Deprecated


            StringEntity params1 = null;
            try {
                HttpPost request = new HttpPost("http://46.197.42.230:85/Membership.svc/SignUp");
                params1 = new StringEntity(jsonObj.toString());
                request.addHeader("content-type", "application/x-www-form-urlencoded");
                request.setEntity(params1);
                HttpResponse response = httpClient.execute(request);
                InputStream inputStream = response.getEntity().getContent();
                InputStreamToStringExample str = new InputStreamToStringExample();
                responseServer = str.getStringFromInputStream(inputStream);
                Log.e("response", "responses -----" + responseServer);





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


            try {
                JSONObject jb= new JSONObject(responseServer);

                task_StatusCode = jb.getString("StatusCode");
                Log.e("status", task_StatusCode);


                ticket=jb.getString("Ticket");
                //ticket ta sms doğrulama







                Log.e("mesaj", jb.toString());


                if(task_StatusCode.equals("410")){
                    alert_activity ac= new alert_activity();
                    ac.alertDialogShow(context,"bu kullanıcı zaten var");
                }else{

                    data_object=jb.getString("Data");
                    //data objesinin içindekileri  sms doğrulamaya gönderiyoruz

                    JSONObject jb_user=new JSONObject(data_object);
                    UserID=jb_user.getString("MemberID");
                    VerificationCode=jb_user.getString("VerificationCode");
                    PhoneNumber=jb_user.getString("PhoneNumber");



                    Intent intent=new Intent(sign_up.this,sms_verification.class);
                    startActivity(intent);
                }




            } catch (JSONException e) {
                e.printStackTrace();
            }





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
