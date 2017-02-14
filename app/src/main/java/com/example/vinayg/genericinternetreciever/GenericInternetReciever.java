package com.example.vinayg.genericinternetreciever;

import android.app.Activity;
import android.app.Application;

/**
 * Created by vinay.g.
 */

public class GenericInternetReciever extends Application{
    public static boolean activityVisible; // Variable that will check the
    // current activity state
    public static Activity mCurrentActivity;
    public static void setCurrentActivity(Activity CurrentActivity){
        mCurrentActivity = CurrentActivity;
    }
    public static Activity GetCurrentActivity(){
        return mCurrentActivity;
    }

    public static boolean isActivityVisible() {
        return activityVisible; // return true or false
    }

    public static void activityResumed() {
        activityVisible = true;// this will set true when activity resumed

    }

    public static void activityPaused() {
        activityVisible = false;// this will set false when activity paused
    }
}
