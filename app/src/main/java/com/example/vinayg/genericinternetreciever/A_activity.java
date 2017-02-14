package com.example.vinayg.genericinternetreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class A_activity extends AppCompatActivity {

    private TextView mTextView;
    private InternetConnectionDialogfragmnet newFragment;
    private static final String TAG = A_activity.class.getName();
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
        setContentView(R.layout.activity_a);
        mTextView = (TextView) findViewById(R.id.tv_a);
        GenericInternetReciever.setCurrentActivity(this);
    }
    private void disabledialog() {
        if(newFragment!=null) {
            newFragment.dismiss();
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
    @Override
    protected void onResume() {
        super.onResume();
        GenericInternetReciever.setCurrentActivity(this);
        GenericInternetReciever.activityResumed();
        changeTextStatus(isNetworkAvailable(this));
        registerReceiver(mReceiver,new IntentFilter(this.getClass().getName()));
    }

    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager
                .getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
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

    @Override
    protected void onPause() {
        super.onPause();
        GenericInternetReciever.activityPaused();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
