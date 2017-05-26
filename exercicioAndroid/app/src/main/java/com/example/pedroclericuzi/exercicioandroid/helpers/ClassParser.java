package com.example.pedroclericuzi.exercicioandroid.helpers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by pedroclericuzi on 26/05/2017.
 */

public class ClassParser {

    public ArrayList<String> Parser(String s) throws JSONException {
         ArrayList<String> parser = new ArrayList<>();
        JSONObject jo = new JSONObject(s);
        JSONArray filmes = jo.getJSONArray("filmes");
        //https://www.tutorialspoint.com/android/android_json_parser.htm
        for (int i = 0; i<filmes.length();i++){
            JSONObject nome = filmes.getString("nome");
            parser.add(String.valueOf(nome));
        }
        return parser;
    }

}
