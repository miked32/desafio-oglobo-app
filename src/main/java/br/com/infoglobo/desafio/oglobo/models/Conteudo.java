package br.com.infoglobo.desafio.oglobo.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by miked on 16/03/2017.
 */

public class Conteudo implements Serializable{
    private List<String> autores;
    private boolean informePublicitario;
    private String subTitulo;
    private String texto;
    private List<String> videos ;
    private String atualizadoEm;
    private int id;
    private String publicadoEm;
    private Secao secao;
    private String tipo;
    private String titulo;
    private String url;
    private String urlOriginal;
    private List<Imagem> imagens;

    public Conteudo(){
        super();
    }

    public Conteudo(List<String> autores, boolean informePublicitario, String subTitulo,
                    String texto, List<String> videos, String atualizadoEm, int id,
                    String publicadoEm, Secao secao, String tipo, String titulo,
                    String url,String urlOriginal, List<Imagem> imagens){

        this.autores = autores;
        this.informePublicitario = informePublicitario;
        this.subTitulo = subTitulo;
        this.texto = texto;
        this.videos = videos;
        this.atualizadoEm = atualizadoEm;
        this.id = id;
        this.publicadoEm =  publicadoEm;
        this.secao =  secao;
        this.tipo = tipo;
        this.titulo = titulo;
        this.url = url;
        this.urlOriginal = urlOriginal;
        this.imagens = imagens;
    }

    public boolean isInformePublicitario() {
        return informePublicitario;
    }

    public void setInformePublicitario(boolean informePublicitario) {
        this.informePublicitario = informePublicitario;
    }

    public String getSubTitulo() {
        return subTitulo;
    }

    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(String atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPublicadoEm() {
        return publicadoEm;
    }

    public void setPublicadoEm(String publicadoEm) {
        this.publicadoEm = publicadoEm;
    }

    public Secao getSecao() {
        return secao;
    }

    public void setSecao(Secao secao) {
        this.secao = secao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlOriginal() {
        return urlOriginal;
    }

    public void setUrlOriginal(String urlOriginal) {
        this.urlOriginal = urlOriginal;
    }

    public List<Imagem> getImagens() {
        return imagens;
    }

    public int getImagensCount() {
        return imagens.size();
    }


    public void setImagens(List<Imagem> imagens) {
        this.imagens = imagens;
    }

    public List<String> getAutores() {
        return autores;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }
}
