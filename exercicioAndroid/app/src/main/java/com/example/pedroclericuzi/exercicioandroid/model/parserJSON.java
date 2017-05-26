package com.example.pedroclericuzi.exercicioandroid.model;

/**
 * Created by pedroclericuzi on 25/05/2017.
 */

public class parserJSON {

    private String titulo;
    private String data;
    private String link;

    public parserJSON(String titulo, String data, String link) {
        this.titulo = titulo;
        this.data = data;
        this.link = link;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
