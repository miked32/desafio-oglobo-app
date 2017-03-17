package br.com.infoglobo.desafio.oglobo.models;

import java.io.Serializable;

/**
 * Created by miked on 16/03/2017.
 */

public class Secao implements Serializable{
    private String nome;
    private String url;

    public Secao(){
        super();
    }

    public Secao(String nome, String url){
        this.nome = nome;
        this.url = url;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
