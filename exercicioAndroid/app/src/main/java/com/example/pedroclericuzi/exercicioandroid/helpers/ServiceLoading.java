package com.example.pedroclericuzi.exercicioandroid.helpers;
import com.example.pedroclericuzi.exercicioandroid.data.DBFilmes;
import com.example.pedroclericuzi.exercicioandroid.data.DBHelper;
import com.example.pedroclericuzi.exercicioandroid.model.modelJSON;

import android.app.Service;
import android.content.Intent;
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
                        BaixarFilme baixarLivro = new BaixarFilme();
                        ClassParser classParser = new ClassParser();
                        String conteudo = baixarLivro.ListaFilmes(urlJson);
                        DBFilmes dbFilmes = new DBFilmes(getApplicationContext());
                        ArrayList<modelJSON> arrayList = classParser.Parser(conteudo);
                        modelJSON model = new modelJSON();
                        dbFilmes.clearAll();
                        for (int i=0;i<arrayList.size();i++){
                            Log.d("Script log", "COUTN "+arrayList.get(i).getTitulo());
                            model.setTitulo(arrayList.get(i).getTitulo());
                            model.setData(arrayList.get(i).getData());
                            model.setLink(arrayList.get(i).getLink());
                            model.setAtualizado("false");
                            dbFilmes.insert(model);
                        }
                        sleep(5000);
                    }
                    stopSelf();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
