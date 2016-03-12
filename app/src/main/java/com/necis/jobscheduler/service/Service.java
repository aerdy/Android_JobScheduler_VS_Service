package com.necis.jobscheduler.service;

import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Jarcode on 2016-03-12.
 */
public class Service extends android.app.Service {
    String LogName = "Service";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(LogName,"Membuat Service");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(LogName,"Mematikan Service");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
}
