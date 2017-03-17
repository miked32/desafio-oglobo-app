package br.com.infoglobo.desafio.oglobo;

import junit.framework.Assert;

import org.junit.Test;

import br.com.infoglobo.desafio.oglobo.models.Conteudo;

/**
 * Created by miked on 16/03/2017.
 */

public class ConteudoTest {

    @Test
    public void testConteudoSemId(){
        Conteudo c = new Conteudo();
        c.setUrl("url");
        c.setUrlOriginal("urlOriginal");
        c.setTipo("tipo");
        c.setTitulo("titulo mateira");
        c.setAtualizadoEm("data");
        c.setPublicadoEm("data");

        Assert.assertNotNull(c.getId());
    }

    @Test
    public void testGetConteudoQuandoConteudoNaoNull(){
        Conteudo c = new Conteudo();
        c.setUrl("url");
        c.setUrlOriginal("urlOriginal");
        c.setTipo("tipo");
        c.setTitulo("titulo mateira");
        c.setAtualizadoEm("data");
        c.setPublicadoEm("data");

        Assert.assertNotNull(c);
    }
}
