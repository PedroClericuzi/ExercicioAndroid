package com.example.pedroclericuzi.exercicioandroid.helpers;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.pedroclericuzi.exercicioandroid.R;
import com.example.pedroclericuzi.exercicioandroid.activities.MainActivity;
import com.example.pedroclericuzi.exercicioandroid.sync.LivrosSync;

/**
 * Created by pedroclericuzi on 29/05/2017.
 */

public class ServiceLoading extends Service{
    private final String urlJson = "http://androidjsonteste.esy.es/filmes.json";
    public Boolean running = true;
    ListView listView;
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Script log", "onBind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Script log", "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Script log", "onDestroy");
        running = false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Script log", "onStartCommand");
        this.getThread();
        return super.onStartCommand(intent, flags, startId);
    }

    public void getThread(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    while (running){
                        //Log.d("Script log", "COUTN "+count);
                        sleep(10000);
                    }
                    stopSelf();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
