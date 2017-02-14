package com.example.vinayg.genericinternetreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    MyReceiver mMyReceiver = new MyReceiver();
    private TextView mTextView;
    DialogFragment newFragment = null;
    private Button mNextButton;
    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG,"Recieved");
            changeTextStatus(isNetworkAvailable(context));
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate()");
        mTextView = (TextView) findViewById(R.id.tv);
        mNextButton = (Button)findViewById(R.id.bt1);
        mNextButton.setOnClickListener(new View.OnClickListener() {
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
        GenericInternetReciever.setCurrentActivity(this);
        Log.d(TAG,"onResume()");
        changeTextStatus(isNetworkAvailable(this));
        GenericInternetReciever.activityResumed();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(mMyReceiver,filter);
        registerReceiver(mReceiver,new IntentFilter(this.getClass().getName()));
    }
    public void changeTextStatus(boolean isConnected) {

        // Change status according to boolean value
        if (isConnected) {
            mTextView.setText("Internet Connected.");
            mTextView.setTextColor(Color.parseColor("#00ff00"));
            disabledialog();
        } else {
            mTextView.setText("Internet Disconnected.");
            mTextView.setTextColor(Color.parseColor("#ff0000"));
            showdialog();
        }
    }

    private void disabledialog() {
        if(newFragment!=null) {
            newFragment.dismiss();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause()");
        GenericInternetReciever.activityPaused();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy()");
        try {
            unregisterReceiver(mMyReceiver);
            unregisterReceiver(mReceiver);
        } catch (Exception e) {

        }

    }
    public void showdialog() {
        if (newFragment==null) {
            newFragment = new InternetConnectionDialogfragmnet();
            newFragment.show(getSupportFragmentManager(), "internet");
        } else if(!newFragment.isAdded()) {
            newFragment.show(getSupportFragmentManager(), "internet");
        }
    }

    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager
                .getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
