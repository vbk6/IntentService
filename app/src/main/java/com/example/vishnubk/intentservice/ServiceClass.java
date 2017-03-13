package com.example.vishnubk.intentservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by vishnubk on 1/9/16.
 */

public class ServiceClass extends Service {
    int mStartMode;
    @Nullable
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(getApplicationContext(), "sada service", Toast.LENGTH_LONG).show();
        return mStartMode;
    }


}
