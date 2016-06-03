package com.example.egemendurmus.oneclean;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.egemendurmus.oneclean.ordermenu.customer_drawer;
import com.example.egemendurmus.oneclean.signup.sign_up;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private LoginButton loginButton;
    private CallbackManager callbackManager;

    // Google client to interact with Google API
    private static final String TAG = "LoginActivity";
    private static final int SIGN_IN_CODE = 9001;
    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;
    private GoogleSignInAccount account;

    private static final int RC_SIGN_IN = 9001;





    ProgressDialog pDialog;
    String url = "http://46.197.42.230:85/Membership.svc/login";
    String veri_string;
    Button buton;
   static String responseServer;
    public static String username;
    static String password;
    PostClass post = new PostClass();  //Post Class dan post ad�nda nesne olusturduk.Post class�n i�indeki methodu kullanabilmek i�in
    public String isim;
    JSONObject jb;
    final Context context = this;

    // facebook post ayarla
  public static String fb_accessToken;
    public static String fb_user_id;







    facepost fp=new facepost();




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }





    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
           // tv_username.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));

        } else {
            // Signed out, show unauthenticated UI.
            // updateUI(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the SDK before executing any other operations,
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_main);



        AccessToken token = AccessToken.getCurrentAccessToken();

        Log.i("token", String.valueOf(token));




        final Typeface tf = Typeface.createFromAsset(getApplicationContext().getAssets(), "opensans.ttf");//yazı fontu

        TextView login_as_guest = (TextView) findViewById(R.id.textView);
        TextView ask_account = (TextView) findViewById(R.id.textView2);
        TextView ask_account_sign = (TextView) findViewById(R.id.textView3);

        login_as_guest.setTypeface(tf);
        ask_account.setTypeface(tf);
        ask_account_sign.setTypeface(tf);






        //yeni kayıt olduğu zaman
       ask_account_sign.setMovementMethod(LinkMovementMethod.getInstance());

        ask_account_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sign_up= new Intent(MainActivity.this, sign_up.class);
                startActivity(sign_up);
            }
        });









        //sistem logini mail girişli olan (post işlemi)

        buton = (Button)findViewById(R.id.button);

        buton.setTypeface(tf);
        buton.setOnClickListener(new View.OnClickListener() { //buton a click listener ekledik

            public void onClick(View v) {
                EditText u_name = (EditText) findViewById(R.id.editText);
                EditText psw = (EditText) findViewById(R.id.editText2);

                u_name.setTypeface(tf);
                psw.setTypeface(tf);

                username=u_name.getText().toString();
                password=psw.getText().toString();

                new Post().execute(); //Asynctask Classı Çağırıyoruz




            }
        });










        //facebook logini yapılıyor
        loginButton = (LoginButton)findViewById(R.id.login_button);
       loginButton.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);

        loginButton.setBackgroundResource(R.drawable.fb_login);
        loginButton.setText(" ");

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                // Application code
                                Log.d(TAG, object.optString("id"));
                                Log.d(TAG, object.optString("email"));




                                fb_accessToken= loginResult.getAccessToken().getToken();
                                Log.d(TAG, fb_accessToken);


                                fb_user_id=object.optString("id");

                                //facebook post ayarla

                                fp.username=fb_user_id;
                                fp.password=fb_accessToken;
                                fp.execute();



                            }
                        });







                Bundle parameters = new Bundle();
                parameters.putString("fields", "id");

                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }



        });


// Initializing google plus api client

        //953282697165-tp08p3m3vuvra87q2obsasblhgmt4osv.apps.googleusercontent.com
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

// Build a GoogleApiClient with access to the Google Sign-In API and the
// options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        SignInButton signInButton = (SignInButton) findViewById(R.id.btn_sign_in);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setScopes(gso.getScopeArray());
signInButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        signIn();

    }
});

    }

    private void signIn() {

        //google api bağlantısı başlatılıyor

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }











    class Post extends AsyncTask<Void, Void, Void> {





        protected void onPreExecute() { // Post tan �nce yap�lacak i�lemler. Y�kleniyor yaz�s�n�(ProgressDialog) g�sterdik.
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Yükleniyor...");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(false); // ProgressDialog u iptal edilemez hale getirdik.
            pDialog.show();






        }

        protected Void doInBackground(Void... unused) { // Arka Planda yap�lacaklar. Yani Post i�lemi

            List<NameValuePair> params = new ArrayList<NameValuePair>(2); //Post edilecek de�i�kenleri ayarliyoruz.
            //Bu de�i�kenler bu uygulamada hi�bir i�e yaram�yor.Sadece g�stermek ama�l�
            //  params.add(new BasicNameValuePair("UserID", "admin"));
            //params.add(new BasicNameValuePair("Password", "4321"));


            String user = "admin";

            JSONObject jsonObj = new JSONObject();
            try {
                jsonObj.put("UserID", username);
                jsonObj.put("Password", password);


            } catch (JSONException e) {
                e.printStackTrace();
            }

            params.add(new BasicNameValuePair("", jsonObj.toString()));

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


                // handle response here...
            } catch (Exception ex) {
                // handle exception here
            } finally {
                httpClient.getConnectionManager().shutdown(); //Deprecated
            }


            veri_string = post.httpPost(url, "POST", params1, 20000); //PostClass daki httpPost metodunu �a��rd�k.Gelen string de�erini ald�k

            Log.d("HTTP POST CEVAP:", "" + veri_string);// gelen veriyi log tuttuk
            Log.d("para:", "" + params);// gelen veriyi log tuttuk

            return null;
        }

        protected void onPostExecute(Void unused) { //Posttan sonra
            pDialog.dismiss();  //ProgresDialog u kapat�yoruz.





            try {

                jb= new JSONObject(responseServer);

                String ticket = jb.getString("Ticket");
                Log.e("mesaj", ticket);

                String messageData = jb.getString("Data");
                Log.e("mesaj",  messageData);
                JSONObject InData = new JSONObject(messageData);
                isim=InData.getString("FirstName");
                Log.e("mesaj", isim);




                if(isim!=null){

                }else{
                    isim="egemen";
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }






            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);

            // set title
            alertDialogBuilder.setTitle("giriş");


            // set dialog message
            alertDialogBuilder
                    .setMessage("Hoşgeldin"+isim)
                    .setCancelable(false)
                    .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, close
                            // current activity

                          Intent i = new Intent(MainActivity.this,customer_drawer.class);
                                 i.putExtra(isim,customer_drawer.isim);
                           startActivity(i);

                        }
                    });



            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();






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
