package com.example.administrator.focalpoint;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mwarren on 2/17/2016.
 */
public class UserChoice implements Parcelable {
    private int questionNumber;
    private int answerChoice;
    private int correctAnswer;

    public UserChoice(int qnum, int achoice){
        questionNumber = qnum;
        answerChoice = achoice;
    }


    public int getQuestionNumber() {
        return questionNumber;
    }
    public void setQuestionNumber(int qnum){
        questionNumber = qnum;
    }

    public int getAnswerChoice() {
        return answerChoice;
    }
    public void setAnswerChoice(int achoice){
        answerChoice = achoice;
    }

    protected UserChoice(Parcel in) {
        questionNumber = in.readInt();
        answerChoice = in.readInt();
        correctAnswer = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(questionNumber);
        dest.writeInt(answerChoice);
        dest.writeInt(correctAnswer);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<UserChoice> CREATOR = new Parcelable.Creator<UserChoice>() {
        @Override
        public UserChoice createFromParcel(Parcel in) {
            return new UserChoice(in);
        }

        @Override
        public UserChoice[] newArray(int size) {
            return new UserChoice[size];
        }
    };
}
