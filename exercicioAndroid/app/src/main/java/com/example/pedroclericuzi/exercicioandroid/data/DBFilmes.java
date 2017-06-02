package com.example.pedroclericuzi.exercicioandroid.data;
import com.example.pedroclericuzi.exercicioandroid.model.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pedroclericuzi on 31/05/2017.
 */

public class DBFilmes {
    DBHelper dbHelper;
    //Pega a informação da coluna para chamar o getWritableDatabase que verifica se o dado pode ser inserido
    ContentValues contentValues;
    SQLiteDatabase sqLiteDatabase;

    public DBFilmes(Context context){
        dbHelper = new DBHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public void insert(modelJSON model){
//        sqLiteDatabase.execSQL(dbHelper.TABELA);
        contentValues = new ContentValues();
        contentValues.put(dbHelper.TITULO, model.getTitulo());
        contentValues.put(dbHelper.URL, model.getLink());
        contentValues.put(dbHelper.ANO, model.getData());
        contentValues.put(dbHelper.ATUALIZADO, model.getAtualizado());

        String sql = String.format("SELECT * FROM %s WHERE %s = ?", dbHelper.TABELA, dbHelper.URL);
        Cursor cursor2 = sqLiteDatabase.rawQuery(sql, new String[]{model.getLink()});
        //Ele só vai inserir, caso não tenha na tabela
        if(cursor2.getCount()==0){
            //Log.d("TEEESTE", model.getTitulo());
            sqLiteDatabase.insert(dbHelper.TABELA, null, contentValues);
        }
    }

    public void clearAll(){
        sqLiteDatabase.delete(dbHelper.TABELA, null, null);
    }

    public void update(modelJSON model){
        contentValues = new ContentValues();
        contentValues.put(dbHelper.TITULO, model.getTitulo());
        contentValues.put(dbHelper.URL, model.getLink());
        contentValues.put(dbHelper.ANO, model.getData());
        contentValues.put(dbHelper.ATUALIZADO, model.getAtualizado());

        sqLiteDatabase.update(dbHelper.TABELA, contentValues, dbHelper.URL+" = ?", new String[]{model.getLink()});
    }

    public void delete(modelJSON model){
        sqLiteDatabase.delete(dbHelper.TABELA, dbHelper._ID+" = ?", new String[]{""+model.getId()});
    }

    public ArrayList<modelJSON> search(modelJSON model){
        ArrayList<modelJSON> list = new ArrayList<modelJSON>();
        String[] columns = new String[]{dbHelper._ID, dbHelper.TITULO, dbHelper.ANO, dbHelper.URL, dbHelper.ATUALIZADO};
        Cursor cursor = sqLiteDatabase.query(dbHelper.TABELA, columns, null, null, null, null, null);

        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                model = new modelJSON();
                model.setId(cursor.getInt(0));
                model.setTitulo(cursor.getString(1));
                model.setData(cursor.getString(2));
                model.setLink(cursor.getString(3));
                model.setAtualizado(cursor.getString(4));
                list.add(model);
            } while (cursor.moveToNext());
        }
        return list;
    }
}
