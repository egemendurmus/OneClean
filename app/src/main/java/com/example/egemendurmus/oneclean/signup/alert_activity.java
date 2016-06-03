package com.example.egemendurmus.oneclean.signup;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.egemendurmus.oneclean.R;

public class alert_activity extends AppCompatActivity {
   public static String error_cause;
    Context context = this;
    String already_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_activity);













    }

    public static void alertDialogShow(Context context, String message)
    {
        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setMessage(message);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                // Handle your on click action
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }


}
