/*
* FILE : Account.java
* PROJECT : Mobile Application Development
* PROGRAMMER : Matt Warren, William Pring, Steven Johnston, Denys Politiuk
* FIRST VERSION : 2016-03-11
* DESCRIPTION :
* This file contains the supporting functions and behaviour for an Account.
*/

package com.example.administrator.newfocalpoint;


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
