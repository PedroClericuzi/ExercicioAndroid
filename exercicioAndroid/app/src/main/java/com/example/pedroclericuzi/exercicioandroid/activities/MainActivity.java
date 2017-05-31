package com.example.pedroclericuzi.exercicioandroid.activities;
import com.example.pedroclericuzi.exercicioandroid.adapter.*;
import com.example.pedroclericuzi.exercicioandroid.helpers.*;
import com.example.pedroclericuzi.exercicioandroid.data.*;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.pedroclericuzi.exercicioandroid.R;
import com.example.pedroclericuzi.exercicioandroid.model.modelJSON;
import com.example.pedroclericuzi.exercicioandroid.sync.ConteudoSync;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    modelJSON json_model = new modelJSON();
    BaixarFilme baixarLivro = new BaixarFilme();
    Intent it;
    ArrayAdapter adaper;
    private ListView listView;
    private final String urlJson = "http://androidjsonteste.esy.es/filmes.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lista_filmes);
        it = new Intent(this, ServiceLoading.class);
        startService(it);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //new ConteudoSync(MainActivity.this).execute(urlJson);

    }

    @Override
    protected void onResume() {
        super.onResume();
        DBFilmes dbFilmes = new DBFilmes(MainActivity.this);
        adaper = new adapter_parse(MainActivity.this, dbFilmes.search(json_model));
        listView.setAdapter(adaper);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(it);
    }
}
