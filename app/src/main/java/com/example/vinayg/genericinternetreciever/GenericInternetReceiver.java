package com.example.vinayg.genericinternetreciever;

import android.app.Activity;
import android.app.Application;

/**
 * Created by vinay.g.
 */

public class GenericInternetReceiver extends Application{
    public boolean activityVisible; // Variable that will check the
    // current activity state
    public  Activity mCurrentActivity;

    public boolean miKnow = false;

    public void setCurrentActivity(Activity CurrentActivity){
        mCurrentActivity = CurrentActivity;
    }

    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    public boolean isActivityVisible() {
        return activityVisible; // return true or false
    }

    public void activityResumed() {
        activityVisible = true;// this will set true when activity resumed

    }


    public void iKnow(){
        miKnow = true;
    }
    public void iDontKnow(){
        miKnow =false;
    }

    public boolean doIknow() {
        return miKnow;
    }

    public void activityPaused() {
        activityVisible = false;// this will set false when activity paused
    }
}
