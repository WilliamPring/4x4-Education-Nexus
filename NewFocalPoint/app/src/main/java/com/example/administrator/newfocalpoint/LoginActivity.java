package com.example.administrator.newfocalpoint;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(getIntent().hasExtra("lORc")){
            Bundle b = getIntent().getExtras();
            if(b.getInt("lORc") == 0){
                Fragment newFragment = new LoginFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.main_menu, newFragment).commit();
            }
            else if(b.getInt("lORc") == 1){
                Fragment newFragment = new accountCreation();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.main_menu, newFragment).commit();
            }

        }
        else{
            Fragment newFragment = new LoginFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.main_menu, newFragment).commit();
        }



    }

}
