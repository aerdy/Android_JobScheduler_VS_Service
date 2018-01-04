package com.necis.jobscheduler.service;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.necis.jobscheduler.MainActivity;
import com.necis.jobscheduler.util.Util;

/**
 * Created by Jarcode on 2016-03-12.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends android.app.job.JobService {
    String LogName = "JobSced";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(LogName, "Membuat Job Service");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(LogName, "Mematikan/ Menghancurkan MyJobService");
    }


    @Override
    public boolean onStartJob(JobParameters params) {
        Log.e(LogName, "Menjalan Job Scheduler");
        Intent service = new Intent(getApplicationContext(), MainActivity.class);
        getApplicationContext().startService(service);
        Util.scheduleJob(getApplicationContext()); // reschedule the job
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.e(LogName, "Mematikan Job Scheduler");
        return true;
    }

    MainActivity myMainActivity;

    public void setUICallback(MainActivity activity) {
        myMainActivity = activity;
    }

    @Override
    public int onStartCommand(Intent intent, int flags,
                              int startId) {
        Messenger callback = intent.getParcelableExtra("messenger");
        Message m = Message.obtain();
        m.what = 2;
        m.obj = this;
        try {
            callback.send(m);
        } catch (RemoteException e) {
            Log.e("MyService", "Error Pada Saat Mengembalikan Service Ke MainActivity Anda");
        }
        return START_NOT_STICKY;
    }

    // Method that schedules the job
    public void scheduleJob(JobInfo build) {
        Log.e(LogName, "Scheduling job");
        JobScheduler jobScheduler = (JobScheduler) getSystemService
                (Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(build);
    }
}
