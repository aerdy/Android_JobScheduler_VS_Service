package com.necis.jobscheduler;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.necis.jobscheduler.service.MyJobService;

public class MainActivity extends AppCompatActivity {
    ComponentName myServiceComponent;
    MyJobService myService;
    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            myService = (MyJobService) msg.obj;
            myService.setUICallback(MainActivity.this);
        }
    };

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnStart = (Button) findViewById(R.id.btnStart);
        Button btninfo = (Button) findViewById(R.id.btnInfo);

        btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JobInfo.Builder builder = new JobInfo.Builder(0, myServiceComponent);
                builder.setRequiresCharging(true);
                myService.scheduleJob(builder.build());
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myServiceComponent = new ComponentName(getApplicationContext(), MyJobService.class);
                Intent myServiceIntent = new Intent(getApplicationContext(), MyJobService.class);
                myServiceIntent.putExtra("messenger", new Messenger(myHandler));
                startService(myServiceIntent);

            }
        });
    }

}
