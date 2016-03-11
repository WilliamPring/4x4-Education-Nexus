package com.example.administrator.newfocalpoint;

import android.content.Intent;
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

public class main_drawer_activity extends AppCompatActivity {

    private String[] menu;
    private DrawerLayout dLayout;
    private ListView dList;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer_activity);



        menu = new String[]{"Login", "Create Account", "Question Demo"};
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

                } else if (menu[position].equals("Create Account")) {


                } else if (menu[position].equals("Question Demo")) {


                }

            }
        });

        Fragment newFragment = new CourseListFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, newFragment).commit();
    }



}
