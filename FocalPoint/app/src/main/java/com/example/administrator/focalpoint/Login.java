package com.example.administrator.focalpoint;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;

public class Login extends Activity {
    final Context context = this;
    Button b_CreateAccount;
    Button b_ForgetPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b_CreateAccount = (Button) findViewById(R.id.create_account);
        b_CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), account_creation.class);
                startActivity(intent);
            }
        });
        b_ForgetPassword = (Button) findViewById(R.id.forgot_pass);

        b_ForgetPassword.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage("Send a message to *** *** 2203");


                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    builder1.show();
              //  SmsManager sms = SmsManager.getDefault();
                //sms.sendTextMessage("4169992203", null, "sup", null, null);
                }
        });


    }
}
