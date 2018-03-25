package services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;


import metier.Carnet;

/**
 * Created by alicevillafranca on 25/03/2018.
 */

public class CheckEtatVoyageService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Carnet c = (Carnet) intent.getSerializableExtra("carnet");
        if(c != null){
            c.testEtatVoyages();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
