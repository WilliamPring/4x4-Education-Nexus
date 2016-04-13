/*
* FILE : main_drawer_activity.java
* PROJECT : Mobile Application Development
* PROGRAMMER : Matt Warren, William Pring, Steven Johnston, Denys Politiuk
* FIRST VERSION : 2016-03-11
* DESCRIPTION :
* This file contains the supporting functions and behaviour for the main activity, with a drawer, that will hold the fragments.
*/

package com.example.administrator.newfocalpoint;


import android.content.BroadcastReceiver;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(getIntent().hasExtra("lORc")){
            Bundle b = getIntent().getExtras();
            if(b.getInt("lORc") == 0){
                //login fragment
                Fragment newFragment = new LoginFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.main_menu, newFragment).commit();
            }
            else if(b.getInt("lORc") == 1){
                //create account fragment
                Fragment newFragment = new accountCreation();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.main_menu, newFragment).commit();
            }

        }
        else{
            //login fragment
            Fragment newFragment = new LoginFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.main_menu, newFragment).commit();
        }



    }

}
