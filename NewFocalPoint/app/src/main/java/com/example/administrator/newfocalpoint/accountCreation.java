package com.example.administrator.newfocalpoint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class accountCreation extends Fragment implements View.OnClickListener {
Button createAccount;
    View view;
    EditText name;
    EditText lastName;
    EditText email;
    EditText dob;
    EditText school;
    EditText textPassword;
    private Context globalContext = null;
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
        view = inflater.inflate(R.layout.fragment_account_creation, container, false);

        // Inflate the layout for this fragment
        createAccount = (Button) view.findViewById(R.id.b_create);
        createAccount.setOnClickListener(this);
        // Inflate the layout for this fragment        createAccount = (Button) view.findViewById(R.id.b_create);
        return view;
}


    @Override
    public void onClick(View v) {
        boolean status = true;
        //getting the id for all the edit text field
        name = (EditText) view.findViewById(R.id.first_name);
        lastName = (EditText) view.findViewById(R.id.last_name);
        email = (EditText) view.findViewById(R.id.email_address);
        dob = (EditText) view.findViewById(R.id.date_of_birth);
        school = (EditText) view.findViewById(R.id.school);
        textPassword = (EditText) view.findViewById(R.id.password);
        //convert them to strings
        String newName = name.getText().toString();
        String newLastName = lastName.getText().toString();
        String newEmail = email.getText().toString();
        String newDOB = dob.getText().toString();
        String newSchool = school.getText().toString();
        String newPassword = textPassword.getText().toString();
        globalContext = this.getContext();
        //validate them to see if they are emtpy
        if (newName.equals("")) {
            status = false;
        }
        if (lastName.getText().equals("")) {
            status = false;
        }
        if (email.getText().equals("")) {
            status = false;
        }
        if (dob.getText().equals("")) {
            status = false;
        }
        if (school.getText().equals("")) {
            status = false;
        }
        if (textPassword.getText().equals("")) {
            status = false;
        }
        //if everything is filled up continue
        if (status == true) {
            //getting the activity context
            AccountListDB db = new AccountListDB(globalContext);
            //create a new account
            Account account = new Account();
            //set all the parameter
            account.setNewName(newName);
            account.setNewLastName(newLastName);
            account.setNewEmail(newEmail);
            account.setNewDOB(newDOB);
            account.setNewSchool(newSchool);
            account.setNewTextPassword(newPassword);
            //do an insert
            long insertId = db.insertTask(account);
            Toast.makeText(getActivity(),"Account Created", Toast.LENGTH_LONG).show();
            //go back to the previous framgent
            this.getFragmentManager().popBackStack();
        }
        else
        {
            //error message
            Toast.makeText(getActivity(),"Missing Fields", Toast.LENGTH_LONG).show();
        }
    }

    }


