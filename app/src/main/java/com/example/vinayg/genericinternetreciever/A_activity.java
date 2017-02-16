package com.example.vinayg.genericinternetreciever;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class A_activity extends AppCompatActivity {
    private Myapp mMyapp;
    private static final String TAG = A_activity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        mMyapp =((Myapp)getApplicationContext());
        mMyapp.setCurrentActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume()");
        mMyapp.setCurrentActivity(this);
        mMyapp.activityResumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMyapp.activityPaused();
    }

}
