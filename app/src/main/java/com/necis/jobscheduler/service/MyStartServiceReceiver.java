package com.necis.jobscheduler.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.necis.jobscheduler.util.Util;

/**
 * Created by vim on 04/01/18.
 */

public class MyStartServiceReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Util.scheduleJob(context);
    }
}
