package com.example.pedroclericuzi.exercicioandroid.activities;
import com.example.pedroclericuzi.exercicioandroid.adapter.*;
import com.example.pedroclericuzi.exercicioandroid.helpers.*;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.pedroclericuzi.exercicioandroid.R;
import com.example.pedroclericuzi.exercicioandroid.sync.LivrosSync;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BaixarFilme baixarLivro = new BaixarFilme();
    Intent it;
    private ListView listView;
    private final String urlJson = "http://androidjsonteste.esy.es/filmes.json";
    //ArrayAdapter<modelJSON> parseFilms;
    adapter_parse adapter;
    ArrayList lista = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lista_filmes);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.activity_main, null);
        listView = (ListView) v.findViewById(R.id.lista_filmes);*/
        new LivrosSync(listView, MainActivity.this).execute(urlJson);
        it = new Intent(this, ServiceLoading.class);
        startService(it);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(it);
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }
}
