/*
* FILE : Results.java
* PROJECT : Mobile Application Development
* PROGRAMMER : Matt Warren, William Pring, Steven Johnston, Denys Politiuk
* FIRST VERSION : 2016-03-11
* DESCRIPTION :
* Contains the User Choice, Question Number, Qustion Answer
*/



package com.example.administrator.newfocalpoint;

import javax.xml.transform.Result;

/**
 * Created by William Pring on 4/13/2016.
 */
public class Results {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public String getUserChoice() {
        return userChoice;
    }

    public void setUserChoice(String userChoice) {
        this.userChoice = userChoice;
    }

    int id;
    int questionNumber;
    String questionAnswer;
    String userChoice;

    public Results(int curID, int questionNumber, String questionAnswer, String userChoice)
    {
        id = curID;
        this.questionNumber= questionNumber;
        this.questionAnswer = questionAnswer;
        this.userChoice = userChoice;
    }
    public Results()
    {}

}
