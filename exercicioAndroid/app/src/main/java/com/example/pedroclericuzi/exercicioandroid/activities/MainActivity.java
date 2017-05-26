package com.example.pedroclericuzi.exercicioandroid.activities;
import com.example.pedroclericuzi.exercicioandroid.adapter.*;
import com.example.pedroclericuzi.exercicioandroid.model.*;
import com.example.pedroclericuzi.exercicioandroid.helpers.*;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.pedroclericuzi.exercicioandroid.R;
import com.example.pedroclericuzi.exercicioandroid.sync.LivrosSync;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BaixarLivro baixarLivro = new BaixarLivro();
    private final String urlJson = "http://androidjsonteste.esy.es/filmes.json";

    String[] titulo = new String[]{"Titulo 1", "Titulo 2", "Titulo 3"};
    String[] data = new String[]{"2015", "2016", "2017"};
    ListView listView;
    //ArrayAdapter<modelJSON> parseFilms;
    adapter_parse adapter;
    ArrayList lista = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lista_filmes);

        for (int i = 0; i<titulo.length;i++){
            lista.add(new modelJSON(titulo[i], data[i], "http://google.com"));
        }
        listView.setAdapter(new adapter_parse(this, lista));
    }

    @Override
    protected void onStart() {
        super.onStart();
        new LivrosSync(listView, this).execute(urlJson);
    }
}
