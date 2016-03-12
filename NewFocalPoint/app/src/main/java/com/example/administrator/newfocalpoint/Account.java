package com.example.administrator.newfocalpoint;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by WilliamPring on 3/11/2016.
 */
public class Account {
    //varibles
    String newName;
    String newLastName;
    String newEmail;
    String newDOB;
    String newSchool;
    String newTextPassword;
    //getter for name
    public Account()
    {

    }

    public String getNewName() {
        return newName;
    }
    //getter for name
    public void setNewName(String newName) {
        this.newName = newName;
    }

    //getter for lastname string
    public String getNewLastName() {
        return newLastName;
    }
    //setter for lastName
    public void setNewLastName(String newLastName) {
        this.newLastName = newLastName;
    }
    //get email
    public String getNewEmail() {
        return newEmail;
    }
    //set email
    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getNewDOB() {
        return newDOB;
    }

    public void setNewDOB(String newDOB) {
        this.newDOB = newDOB;
    }
    public String getNewSchool() {
        return newSchool;
    }

    public void setNewSchool(String newSchool) {
        this.newSchool = newSchool;
    }

    public String getNewTextPassword() {
        return newTextPassword;
    }

    public void setNewTextPassword(String newTextPassword) {
        this.newTextPassword = newTextPassword;
    }

}
