/*
* FILE : main_drawer_activity.java
* PROJECT : Mobile Application Development
* PROGRAMMER : Matt Warren, William Pring, Steven Johnston, Denys Politiuk
* FIRST VERSION : 2016-03-11
* DESCRIPTION :
* This file contains the supporting functions and behaviour for the main activity, with a drawer, that will hold the fragments.
*/

package com.example.administrator.newfocalpoint;

import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcel;
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

import java.io.File;

public class main_drawer_activity extends AppCompatActivity {

    private String[] menu;
    private DrawerLayout dLayout;
    private ListView dList;
    private ArrayAdapter<String> adapter;
    private boolean creatingQuestion = false;

    private QuestionReceiver qr;
    private BroadcastReceiver br;

    //used to download file from a given url and save it to a directory we create
    public void downloadFile(String uRl) {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media

            Toast.makeText(getApplicationContext(), "Read Writeable = True", Toast.LENGTH_SHORT).show();
            File direct = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/FocalPoint");
            boolean success;

            success = direct.exists() || direct.mkdirs();

            DownloadManager mgr = (DownloadManager) getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);

            Uri downloadUri = Uri.parse(uRl);
            DownloadManager.Request request = new DownloadManager.Request(downloadUri);

            //if the directory was created or exists
            if (success) {
                try {
                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
                            .setAllowedOverRoaming(false)
                            .setTitle("Logo")
                            .setDescription("FocalPoint Logo")
                            .setDestinationInExternalPublicDir("/FocalPoint", "logo.gif");
                    mgr.enqueue(request);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Failed to make directory.", Toast.LENGTH_SHORT).show();
            }

        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            Toast.makeText(getApplicationContext(), "Read Only.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Errors accessing directory.", Toast.LENGTH_SHORT).show();
        }
    }

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
        menu = new String[]{"Login", "Create Account", "Question Demo", "Download Logo"};
        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        dList = (ListView) findViewById(R.id.left_drawer);

        //adapter for the drawer
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menu);

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
                    case "Download Logo":
                        //download file
                        downloadFile("http://williampring.com/res/logoApp.gif");
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
