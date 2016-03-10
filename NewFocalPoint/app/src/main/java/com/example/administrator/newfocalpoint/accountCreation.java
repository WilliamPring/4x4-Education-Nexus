package com.example.administrator.newfocalpoint;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class accountCreation extends Fragment implements View.OnClickListener {
Button createAccount;

    public accountCreation() {
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
        createAccount = (Button) view.findViewById(R.id.create_account);
        createAccount.setOnClickListener(this);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_creation, container, false);
    }


    @Override
    public void onClick(View v) {

    }
}
