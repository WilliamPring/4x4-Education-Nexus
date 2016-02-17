package com.example.administrator.focalpoint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class account_creation extends Activity {

    private String[] menu;
    private DrawerLayout dLayout;
    private ListView dList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation);

        menu = new String[]{"Login", "Courses", "Multiple Choice", "True or False", "Fill in the Blank"};
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
                if (menu[position].equals("Courses")){
                    Intent intent = new Intent(getApplicationContext(), trueOrFalse.class);
                    startActivity(intent);
                }
                else if(menu[position].equals("Multiple Choice")){
                    Intent intent = new Intent(getApplicationContext(), HoldingMultipleChoice.class);
                    startActivity(intent);
                }
                else if (menu[position].equals("Courses")){
                    Intent intent = new Intent(getApplicationContext(), AddCourse.class);
                    startActivity(intent);
                }
                else if (menu[position].equals("True or False")){
                    Intent intent = new Intent(getApplicationContext(), trueOrFalse.class);
                    startActivity(intent);
                }
                else if (menu[position].equals("Fill in the Blank")){
                    Intent intent = new Intent(getApplicationContext(), fillInTheBlank.class);
                    startActivity(intent);
                }

            }
        });

    }
}
