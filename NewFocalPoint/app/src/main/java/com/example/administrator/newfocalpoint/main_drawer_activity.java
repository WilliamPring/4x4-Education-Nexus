package com.example.administrator.newfocalpoint;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;

public class main_drawer_activity extends AppCompatActivity {
    Context mContext;
    private String[] menu;
    private DrawerLayout dLayout;
    private ListView dList;
    private ArrayAdapter<String> adapter;

    public void downloadFile(String uRl) {
        File direct = new File(Environment.getExternalStorageDirectory()
                + "/RESFILE");

        if (!direct.exists()) {
            direct.mkdirs();
        }
        mContext = this;
        DownloadManager mgr = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);

        Uri downloadUri = Uri.parse(uRl);
        DownloadManager.Request request = new DownloadManager.Request(
                downloadUri);

        request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI
                        | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false).setTitle("LOGO")
                .setDescription("Our Logo")
                .setDestinationInExternalPublicDir("/RESFILE", "LOGO.jpg");

        mgr.enqueue(request);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer_activity);



        menu = new String[]{"Login", "Create Account", "Question Demo", "Download Image"};
        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        dList = (ListView) findViewById(R.id.left_drawer);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menu);

        dList.setAdapter(adapter);
        dList.setSelector(android.R.color.holo_orange_light);
        dList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Clicked: " + menu[position], Toast.LENGTH_SHORT).show();
                dLayout.closeDrawers();
                if (menu[position].equals("Login")) {
                    Intent intent = new Intent(view.getContext(), LoginActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("lORc", 0);
                    intent.putExtras(b);
                    startActivity(intent);

                } else if (menu[position].equals("Create Account")) {
                    Intent intent = new Intent(view.getContext(), LoginActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("lORc", 1);
                    intent.putExtras(b);
                    startActivity(intent);

                } else if (menu[position].equals("Question Demo")) {
                    Fragment newFragment = new QuestionWaitFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.container, newFragment).commit();
                }
                else if (menu[position].equals("Download Logo")) {
                    downloadFile("http://williampring.com/res/logoApp.gif");
                }

            }
        });

        Fragment newFragment = new CourseListFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, newFragment).commit();
    }



}
