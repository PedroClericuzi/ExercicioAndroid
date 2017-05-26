package com.example.pedroclericuzi.exercicioandroid.adapter;
import com.example.pedroclericuzi.exercicioandroid.R;
import com.example.pedroclericuzi.exercicioandroid.model.parserJSON;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pedroclericuzi on 25/05/2017.
 */

public class adapter_parse extends ArrayAdapter<parserJSON>{
    private ArrayList<parserJSON> lista_livros;
    private Context context;

    public adapter_parse(Context context, ArrayList<parserJSON> lista_livros) {
        super(context, R.layout.inflater_filmes, lista_livros);
        this.lista_livros = lista_livros;
        this.context = context;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public int getPosition(@Nullable parserJSON item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    //No método getView eu preciso settar os valores para a classe parseJSON
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = LayoutInflater.from(context).inflate(R.layout.inflater_filmes, parent, false);
        TextView titulo = (TextView) view.findViewById(R.id.tv_titulo);
        TextView data = (TextView) view.findViewById(R.id.tv_data);

        parserJSON parse = (parserJSON) getItem(position);
        titulo.setText(parse.getTitulo());
        data.setText(parse.getData());

        return view;
    }
}
