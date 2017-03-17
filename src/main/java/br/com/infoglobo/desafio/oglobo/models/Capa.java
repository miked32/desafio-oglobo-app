package br.com.infoglobo.desafio.oglobo.models;

import java.util.List;

/**
 * Created by miked on 17/03/2017.
 */

public class Capa {
    private List<Conteudo> conteudos;
    private String produto;

    public Capa(){
        super();
    }

    public List<Conteudo> getConteudos() {
        return conteudos;
    }

    public void setConteudos(List<Conteudo> conteudos) {
        this.conteudos = conteudos;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }
}
