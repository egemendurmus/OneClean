package com.example.egemendurmus.oneclean.ordermenu;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.egemendurmus.oneclean.R;

import java.util.Calendar;

public class customer_drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public static String isim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Typeface tf = Typeface.createFromAsset(getApplicationContext().getAssets(), "opensans.ttf");//yazı fontu


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.text_isim);
        nav_user.setText(isim);


       //default text ayarları

        TextView tv_1= (TextView) findViewById(R.id.textView6);
        TextView tv_2= (TextView) findViewById(R.id.textView7);
        TextView tv_3= (TextView) findViewById(R.id.textView8);
        TextView tv_4= (TextView) findViewById(R.id.textView9);

        tv_1.setTypeface(tf);
        tv_2.setTypeface(tf);
        tv_3.setTypeface(tf);
        tv_4.setTypeface(tf);









          //müşteriden alınan tarihler

        final Button tarih = (Button) findViewById(R.id.button4);
        tarih.setTypeface(tf);
        tarih.setOnClickListener(new View.OnClickListener() {
          @Override
         public void onClick(View v) {



          Calendar mcurrentTime = Calendar.getInstance();
          int year = mcurrentTime.get(Calendar.YEAR);//Güncel Yılı alıyoruz
          int month = mcurrentTime.get(Calendar.MONTH);//Güncel Ayı alıyoruz
          int day = mcurrentTime.get(Calendar.DAY_OF_MONTH);//Güncel Günü alıyoruz


          DatePickerDialog datePicker ;//Datepicker objemiz


            //müşteriden alınan tarih ve saatler

          datePicker = new DatePickerDialog(customer_drawer.this, new DatePickerDialog.OnDateSetListener() {

              @Override
              public void onDateSet(DatePicker view, int year, int monthOfYear,
                                    int dayOfMonth) {
                  // TODO Auto-generated method stub
                  //tarihTextView.setText( dayOfMonth + "/" + monthOfYear+ "/"+year);//Ayarla butonu tıklandığında textview'a yazdırıyoruz

                  tarih.setText(dayOfMonth + "/" + monthOfYear+ "/"+year);

              }
          },year,month,day);//başlarken set edilcek değerlerimizi atıyoruz
          DatePicker dp = datePicker.getDatePicker();

          dp.setMinDate(mcurrentTime.getTimeInMillis());//set the current day as the max date




          datePicker.setTitle("Tarih Seçiniz");
          datePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ayarla", datePicker);


          datePicker.show();






      }
     });


      final  Button saat = (Button) findViewById(R.id.button5);
        saat.setTypeface(tf);
        saat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();//
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);//Güncel saati aldık
                int minute = mcurrentTime.get(Calendar.MINUTE);//Güncel dakikayı aldık
                TimePickerDialog timePicker; //Time Picker referansımızı oluşturduk

                //TimePicker objemizi oluşturuyor ve click listener ekliyoruz
                timePicker = new TimePickerDialog(customer_drawer.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        //saatTextView.setText( selectedHour + ":" + selectedMinute);//Ayarla butonu tıklandığında textview'a yazdırıyoruz
                        saat.setText(selectedHour + ":" + selectedMinute);

                    }
                }, hour, minute, true);//true 24 saatli sistem için
                timePicker.setTitle("Saat Seçiniz");
                timePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ayarla", timePicker);

                timePicker.show();
            }
        });





         //kuru temizlemiciden alınan tarihler

        final Button kuru_tarih= (Button) findViewById(R.id.button6);
        final Button kuru_saat= (Button) findViewById(R.id.button7);


        kuru_saat.setTypeface(tf);
        kuru_tarih.setTypeface(tf);

          kuru_tarih.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  Calendar mcurrentTime = Calendar.getInstance();
                  int year = mcurrentTime.get(Calendar.YEAR);//Güncel Yılı alıyoruz
                  int month = mcurrentTime.get(Calendar.MONTH);//Güncel Ayı alıyoruz
                  int day = mcurrentTime.get(Calendar.DAY_OF_MONTH);//Güncel Günü alıyoruz


                  DatePickerDialog datePicker ;//Datepicker objemiz


                  //müşteriden alınan tarih ve saatler

                  datePicker = new DatePickerDialog(customer_drawer.this, new DatePickerDialog.OnDateSetListener() {

                      @Override
                      public void onDateSet(DatePicker view, int year, int monthOfYear,
                                            int dayOfMonth) {
                          // TODO Auto-generated method stub
                          //tarihTextView.setText( dayOfMonth + "/" + monthOfYear+ "/"+year);//Ayarla butonu tıklandığında textview'a yazdırıyoruz

                          kuru_tarih.setText(dayOfMonth + "/" + monthOfYear+ "/"+year);

                      }
                  },year,month,day);//başlarken set edilcek değerlerimizi atıyoruz
                  DatePicker dp = datePicker.getDatePicker();

                  dp.setMinDate(mcurrentTime.getTimeInMillis());//set the current day as the max date




                  datePicker.setTitle("Tarih Seçiniz");
                  datePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ayarla", datePicker);


                  datePicker.show();



              }
          });





         kuru_saat.setOnClickListener(new View.OnClickListener() {







             @Override
             public void onClick(View v) {



                 // TODO Auto-generated method stub
                 Calendar mcurrentTime = Calendar.getInstance();//
                 int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);//Güncel saati aldık
                 int minute = mcurrentTime.get(Calendar.MINUTE);//Güncel dakikayı aldık
                 TimePickerDialog timePicker; //Time Picker referansımızı oluşturduk

                 //TimePicker objemizi oluşturuyor ve click listener ekliyoruz
                 timePicker = new TimePickerDialog(customer_drawer.this, new TimePickerDialog.OnTimeSetListener() {
                     @Override
                     public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                         //saatTextView.setText( selectedHour + ":" + selectedMinute);//Ayarla butonu tıklandığında textview'a yazdırıyoruz
                         kuru_saat.setText(selectedHour + ":" + selectedMinute);

                     }
                 }, hour, minute, true);//true 24 saatli sistem için
                 timePicker.setTitle("Saat Seçiniz");
                 timePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ayarla", timePicker);

                 timePicker.show();





             }
         });



             //talebi gönderme tuşu

           Button odeme = (Button) findViewById(R.id.button8);
            odeme.setText("ödeme yap");
           odeme.setTypeface(tf);
           odeme.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

               }
           });









    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.customer_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Toast.makeText(getApplicationContext(), "msg msg", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
