/*
* FILE : main_drawer_activity.java
* PROJECT : Mobile Application Development
* PROGRAMMER : Matt Warren, William Pring, Steven Johnston, Denys Politiuk
* FIRST VERSION : 2016-04-15
* DESCRIPTION :
* This file contains the supporting functions and behaviour for the main activity, with a drawer, that will hold the fragments.
*/

package com.example.administrator.newfocalpoint;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class main_drawer_activity extends AppCompatActivity {

    private String[] menu;
    private DrawerLayout dLayout;

    private QuestionReceiver qr;
    private BroadcastReceiver br;

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter("com.example.administrator.newfocalpoint.CHANGE_QUESTION");
        intentFilter.setPriority(1);
        getApplicationContext().registerReceiver(br, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("PAUSING", "br: " + br);

        if(br!=null) {
            getApplicationContext().unregisterReceiver(br);
        }
    }


    @Override
    protected void onCreate(Bundle info) {
        super.onCreate(info);
        setContentView(R.layout.activity_main_drawer_activity);


        //titles for the drawer layout
        menu = new String[]{"Login", "Create Account", "Question Demo"};
        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ListView dList = (ListView) findViewById(R.id.left_drawer);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menu);

        dList.setAdapter(adapter);
        dList.setSelector(android.R.color.holo_orange_light);
        dList.setOnItemClickListener(new AdapterView.OnItemClickListener() { //listener for drawer
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Clicked: " + menu[position], Toast.LENGTH_SHORT).show();
                dLayout.closeDrawers(); //close drawers when one is clicked
                switch (menu[position]) {
                    case "Login": {
                        //go to login page
                        Intent intent = new Intent(view.getContext(), LoginActivity.class);
                        Bundle b = new Bundle();
                        b.putInt("lORc", 0);
                        intent.putExtras(b);
                        startActivity(intent);

                        break;
                    }
                    case "Create Account": {
                        //go to create account page
                        Intent intent = new Intent(view.getContext(), LoginActivity.class);
                        Bundle b = new Bundle();
                        b.putInt("lORc", 1);
                        intent.putExtras(b);
                        startActivity(intent);

                        break;
                    }
                    case "Question Demo":
                        //go to waiting for questions
                        Fragment newFragment = new QuestionWaitFragment();
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.container, newFragment).commit();
                        break;
                }

            }
        });


        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment newFragment;
                Log.d("BROADCAST R", "Received: " + intent.getExtras());
                if(intent.hasExtra("questionType")) {

                    switch (intent.getStringExtra("questionType")) {
                        case "multiplechoice":
                            newFragment = MultipleChoice.newInstance(
                                    intent.getIntExtra("questionNumber", 0),
                                    intent.getStringExtra("question"),
                                    intent.getStringExtra("answerA"),
                                    intent.getStringExtra("answerB"),
                                    intent.getStringExtra("answerC"),
                                    intent.getStringExtra("answerD")
                            );
                            ft.replace(R.id.container, newFragment);
                            break;
                        case "truefalse":
                            newFragment = TrueFalse.newInstance(
                                    intent.getIntExtra("questionNumber", 0),
                                    intent.getStringExtra("question")
                            );
                            ft.replace(R.id.container, newFragment);
                            break;
                        case "fillblank":
                            newFragment = FillBlank.newInstance(
                                    intent.getIntExtra("questionNumber", 0),
                                    intent.getStringExtra("question")
                            );

                            ft.replace(R.id.container, newFragment);
                            break;
                        default:
                            break;
                    }
                    ft.commit();
                }
                abortBroadcast();
            }
        };

        Fragment newFragment = new CourseListFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, newFragment).commit();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment newFragment;

        Log.d("NEWINTENT", "extras: " + intent.getExtras());
        Log.d("NEWINTENT", "questionType: " + intent.getStringExtra("questionType"));
        if (intent.hasExtra("questionType")) {
            switch (intent.getStringExtra("questionType")) {
                case "multiplechoice":

                    newFragment = MultipleChoice.newInstance(
                            intent.getIntExtra("questionNumber", 0),
                            intent.getStringExtra("question"),
                            intent.getStringExtra("answerA"),
                            intent.getStringExtra("answerB"),
                            intent.getStringExtra("answerC"),
                            intent.getStringExtra("answerD")
                    );
                    ft.replace(R.id.container, newFragment);
                    break;
                case "truefalse":
                    newFragment = TrueFalse.newInstance(
                            intent.getIntExtra("questionNumber", 0),
                            intent.getStringExtra("question")
                    );
                    ft.replace(R.id.container, newFragment);
                    break;
                case "fillblank":
                    newFragment = FillBlank.newInstance(
                            intent.getIntExtra("questionNumber", 0),
                            intent.getStringExtra("question")
                    );
                    ft.replace(R.id.container, newFragment);
                    break;
                default:
                    break;
            }
            ft.commit();
        }
    }
}
