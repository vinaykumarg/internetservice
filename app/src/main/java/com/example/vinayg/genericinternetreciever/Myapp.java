package com.example.vinayg.genericinternetreciever;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Created by vinay.g.
 */

public class Myapp extends Application implements Application.ActivityLifecycleCallbacks
{
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

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
