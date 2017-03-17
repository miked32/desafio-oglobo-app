package br.com.infoglobo.desafio.oglobo.network;

import java.util.List;

import br.com.infoglobo.desafio.oglobo.models.Capa;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by miked on 17/03/2017.
 */

public interface WebApiService {

    @GET("/Infoglobo/desafio-apps/master/{arquivo}")
    Call<List<Capa>> conteudosForArquivo(@Path("arquivo") String arquivo);
}
