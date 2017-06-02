package com.example.pedroclericuzi.exercicioandroid.helpers;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.ListView;

/**
 * Created by pedroclericuzi on 01/06/2017.
 */

public class IntentServiceLoading extends IntentService {
    private final String urlJson = "http://androidjsonteste.esy.es/filmes.json";
    public static final String BROADCAST_ACTION = "com.example.pedroclericuzi.exercicioandroid.helpers.displayevent";
    Intent intent;
    public Boolean running = true;
    ListView listView;
    private final Handler handler = new Handler();
    boolean ativo;
    int cont;

    public IntentServiceLoading() {
        super("ServiceIntentThread");
        ativo = true;
        cont = 0;
    }

    private Runnable sendUpdatesToUI = new Runnable() {
        public void run() {
            DisplayLoggingInfo();
            handler.postDelayed(this, 5000); // 10 seconds
        }
    };

    private void DisplayLoggingInfo() {
        ThreadLivros threadLivros = new ThreadLivros();
        threadLivros.getThread(running, getApplicationContext());
        sendBroadcast(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler.removeCallbacks(sendUpdatesToUI);
        handler.postDelayed(sendUpdatesToUI, 5000);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
