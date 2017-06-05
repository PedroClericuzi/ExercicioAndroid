package com.example.pedroclericuzi.exercicioandroid.helpers;
import com.example.pedroclericuzi.exercicioandroid.data.DBFilmes;
import com.example.pedroclericuzi.exercicioandroid.data.DBHelper;
import com.example.pedroclericuzi.exercicioandroid.model.modelJSON;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.ListView;

import com.example.pedroclericuzi.exercicioandroid.data.DBFilmes;
import com.example.pedroclericuzi.exercicioandroid.model.modelJSON;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by pedroclericuzi on 29/05/2017.
 */

public class ServiceLoading extends Service{
    private final String urlJson = "http://androidjsonteste.esy.es/filmes.json";
    public static final String BROADCAST_ACTION = "com.example.pedroclericuzi.exercicioandroid.helpers.displayevent";
    Intent intent;
    public Boolean running = true;
    ListView listView;
    private final Handler handler = new Handler();
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Script log", "onBind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Script log", "onCreate");
        intent = new Intent(BROADCAST_ACTION);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Script log", "onDestroy");
        running = false;
    }

    private Runnable sendUpdatesToUI = new Runnable() {
        public void run() {
            DisplayLoggingInfo();
            handler.postDelayed(this, 10000); // 10 seconds
        }
    };

    private void DisplayLoggingInfo() {
        ThreadLivros threadLivros = new ThreadLivros();
        threadLivros.getThread(running, getApplicationContext());
        sendBroadcast(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        Log.d("Script log", "onStartCommand");
//        //this.getThread();
//        ThreadLivros threadLivros = new ThreadLivros();
//        threadLivros.getThread(running, getApplicationContext());
        Log.d("Script log", "onStartCommand");
        handler.removeCallbacks(sendUpdatesToUI);
        handler.postDelayed(sendUpdatesToUI, 10000);
        Log.d("Script log", "onStartCommand 222");
        return super.onStartCommand(intent, flags, startId);
    }
}
