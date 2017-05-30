package com.example.pedroclericuzi.exercicioandroid.sync;
import com.example.pedroclericuzi.exercicioandroid.adapter.adapter_parse;
import com.example.pedroclericuzi.exercicioandroid.helpers.*;
import com.example.pedroclericuzi.exercicioandroid.model.modelJSON;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by pedroclericuzi on 26/05/2017.
 */

public class LivrosSync extends AsyncTask<String, Void, String> {
    String conteudo = "";
    BaixarFilme baixarLivro = new BaixarFilme();
    ClassParser classParser = new ClassParser();

    private ListView listView;
    private final Context context;
    public LivrosSync(ListView listView, Context context){
        super();
        this.listView = listView;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            conteudo = baixarLivro.ListaFilmes(params[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conteudo;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            ArrayList<modelJSON> arrayList = classParser.Parser(s);
            ListAdapter a = new adapter_parse(context, arrayList);
            listView.setAdapter(a);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
