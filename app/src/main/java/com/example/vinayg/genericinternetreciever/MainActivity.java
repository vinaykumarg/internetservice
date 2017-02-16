package com.example.vinayg.genericinternetreciever;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private final MyReceiver mMyReceiver = new MyReceiver();
    private Myapp mMyapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMyapp = (Myapp) getApplication();
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate()");
        Button nextButton = (Button) findViewById(R.id.bt1);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,A_activity.class);
                startActivity(i);
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume()");
        mMyapp.setCurrentActivity(this);
        mMyapp.activityResumed();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        //registerReceiver(mMyReceiver,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause()");
        mMyapp.activityPaused();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy()");
        try {
            //unregisterReceiver(mMyReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
