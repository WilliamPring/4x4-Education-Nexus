/*
* FILE : LoginFragment.java
* PROJECT : Mobile Application Development
* PROGRAMMER : Matt Warren, William Pring, Steven Johnston, Denys Politiuk
* FIRST VERSION : 2016-03-11
* DESCRIPTION :
* This file contains the supporting functions and behaviour for the main activity, with a drawer, that will hold the fragments.
*/

package com.example.administrator.newfocalpoint;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginFragment extends Fragment implements View.OnClickListener {
    Button loginBttn;
    Button createAcc;
    Button forgotPass;
    static int ID;
    EditText login;
    EditText pass;

    Context globalContext;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        // Inflate the layout for this fragment

        loginBttn = (Button) view.findViewById(R.id.log_in);
        loginBttn.setOnClickListener(this);

        createAcc = (Button) view.findViewById(R.id.create_account);
        createAcc.setOnClickListener(this);

        login = (EditText) view.findViewById(R.id.username);
        pass = (EditText) view.findViewById(R.id.password);


        forgotPass = (Button) view.findViewById(R.id.forgot_pass);
        forgotPass.setOnClickListener(this);
        globalContext = this.getContext();

        return view;
    }


    @Override
    public void onClick(View v) {
        FragmentTransaction ft;
        switch (v.getId()) {
            case R.id.log_in:
                AccountListDB db = new AccountListDB(globalContext);
                boolean status = db.matchPasswordAndUser(login.getText().toString(), pass.getText().toString());
                if (status)
                {
                    ID = db.getID(login.getText().toString(), pass.getText().toString());
                    Toast.makeText(getActivity(),"Logging In", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getActivity(), main_drawer_activity.class);
                    startActivity(intent);
                }
                else if((login.getText().toString().equals("")) && (pass.getText().toString().equals("")))
                {
                    Toast.makeText(getActivity(), "Email and Password Field Are Empty", Toast.LENGTH_LONG).show();
                }
                else if (login.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(), "Email Field is Empty", Toast.LENGTH_LONG).show();
                }
                else if (pass.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(), "Password Field is Empty", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "Invalid Login", Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.forgot_pass:
                Uri uriUrl = Uri.parse("http://williampring.com/forgotpass/");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                break;

            case R.id.create_account:
                Fragment newFragment = new accountCreation();
                ft = getFragmentManager().beginTransaction();
                ft.addToBackStack(String.valueOf(newFragment));
                ft.replace(R.id.main_menu, newFragment).commit();
                break;

            default:
                break;
        }
    }
}
