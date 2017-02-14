package com.example.vinayg.genericinternetreciever;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = MyReceiver.class.getName();
    ActivityManager activityManager;

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MyReceiver","onReceive");
        Toast.makeText(context,"recieved",Toast.LENGTH_SHORT).show();
        ActivityManager.AppTask task = null;
        boolean isActive = GenericInternetReciever.isActivityVisible();
        try {
                    if(!isActive) {
                        if (!isNetworkAvailable(context)) {
                            activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                                task = activityManager.getAppTasks().get(0);
                                Intent i = task.getTaskInfo().baseIntent;
                                i.addFlags(FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(i);
                                Log.d("MyReceiver", "started");
                                Toast.makeText(context, "started", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "else", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Log.d(TAG,"else");
                        Activity activity = GenericInternetReciever.GetCurrentActivity();
                        Intent refresh = new Intent(activity.getClass().getName());
                        context.sendBroadcast(refresh);

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
