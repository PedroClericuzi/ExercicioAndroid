package com.example.pedroclericuzi.exercicioandroid.sync;
import com.example.pedroclericuzi.exercicioandroid.R;
import com.example.pedroclericuzi.exercicioandroid.adapter.adapter_parse;
import com.example.pedroclericuzi.exercicioandroid.helpers.*;
import com.example.pedroclericuzi.exercicioandroid.model.modelJSON;

import android.content.Context;
import android.os.AsyncTask;
import android.speech.tts.Voice;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by pedroclericuzi on 26/05/2017.
 */

public class LivrosSync extends AsyncTask<String, Void, String> {
    String conteudo = "";
    BaixarLivro baixarLivro = new BaixarLivro();
    ClassParser classParser = new ClassParser();

    private ListView listView;
    private Context context;
    public LivrosSync(ListView listView, Context context){
        super();
        this.listView = listView;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            conteudo = baixarLivro.ListaLivros(params[0]);
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
            listView.setAdapter(new adapter_parse(context, arrayList));

            for (int i = 0; i<arrayList.size();i++){
                Log.d("Item " + i, ""+arrayList.get(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
