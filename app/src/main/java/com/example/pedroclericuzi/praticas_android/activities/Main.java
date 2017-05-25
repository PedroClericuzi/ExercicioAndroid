package com.example.pedroclericuzi.praticas_android.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pedroclericuzi.praticas_android.R;
import com.example.pedroclericuzi.praticas_android.model.parserJSON;
import com.example.pedroclericuzi.praticas_android.adapter.*;

import java.util.ArrayList;

public class Main extends AppCompatActivity {

    String[] titulo = new String[]{"Titulo 1", "Titulo 2", "Titulo 3"};
    String[] data = new String[]{"2015", "2016", "2017"};
    ListView listView;
    //ArrayAdapter<parserJSON> parseFilms;
    adapter_parse adapter;
    ArrayList lista = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lista_filmes);

        for (int i = 0; i<titulo.length;i++){
            lista.add(new parserJSON(titulo[i], data[i], "http://google.com"));
        }
        listView.setAdapter(new adapter_parse(this, lista));
    }

    public String downloadJson (){
        String data = new String();

        return data;
    }

}
