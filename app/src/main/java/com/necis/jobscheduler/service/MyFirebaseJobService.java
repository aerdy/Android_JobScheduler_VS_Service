package com.necis.jobscheduler.service;

import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Created by vim on 04/01/18.
 */

public class MyFirebaseJobService extends JobService {
    @Override
    public boolean onStartJob(final JobParameters job) {

        Log.e("data","start jobs");
        new Thread(new Runnable() {
            @Override
            public void run() {
                codeYouWantToRun(job);
            }
        }).start();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false;
    }


    public void codeYouWantToRun(final JobParameters parameters) {
        try {

            Log.e("data", "completeJob: " + "jobStarted");
            //This task takes 2 seconds to complete.
            Thread.sleep(2000);

            Log.e("data", "completeJob: " + "jobFinished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //Tell the framework that the job has completed and doesnot needs to be reschedule
            jobFinished(parameters, true);
        }
    }
}
