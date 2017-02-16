package com.example.vinayg.genericinternetreciever;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = MyReceiver.class.getName();
    ActivityManager activityManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MyReceiver","onReceive");
        Myapp Reciever = (Myapp)context.getApplicationContext();
        if(isNetworkAvailable(context)) {
            Reciever.iDontKnow();
            Intent i = new Intent("FINISH");
            context.sendBroadcast(i);

        }
        boolean isActive = Reciever.isActivityVisible();
        try {
                    if(!isActive) {
                        if (!isNetworkAvailable(context)) {
                            Toast.makeText(context, context.getString(R.string.dialog_check_internet), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.d(TAG,"else "+Reciever.doIknow());
                        if(!isNetworkAvailable(context)&&!Reciever.doIknow()) {
                            Intent dialogIntent = new Intent(context, DialogActivity.class);
                            context.startActivity(dialogIntent);
                        }

                    }
        } catch (Exception e) {
            e.printStackTrace();
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
