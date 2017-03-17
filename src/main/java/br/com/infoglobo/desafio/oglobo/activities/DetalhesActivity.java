package br.com.infoglobo.desafio.oglobo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.infoglobo.desafio.oglobo.R;
import br.com.infoglobo.desafio.oglobo.models.Conteudo;

public class DetalhesActivity extends AppCompatActivity {
    Conteudo conteudo;
    TextView tvTitulo, tvSubTitulo, tvPublicadoEm,
            tvLegendaImagem, tvTexto, tvActionBar;
    ImageView iv_detalhes;
    RelativeLayout rlImagemDetalhes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        conteudo = (Conteudo) getIntent().getSerializableExtra("conteudo");
        setContentView(R.layout.activity_detalhes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detalhes);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_center_detalhes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setaViews();
        preencheCampos();
    }

    //Seta as Views da Activity
    private void setaViews() {
        tvTitulo = (TextView) findViewById(R.id.tv_titulo_detalhes);
        tvSubTitulo = (TextView) findViewById(R.id.tv_subtitulo_detalhes);
        tvPublicadoEm = (TextView) findViewById(R.id.tv_publicadoem_detalhes);
        tvLegendaImagem = (TextView) findViewById(R.id.tv_legenda_imagem);
        tvTexto = (TextView) findViewById(R.id.tv_texto_detalhes);
        tvActionBar = (TextView) findViewById(R.id.tv_actionbar_detalhes);
        rlImagemDetalhes = (RelativeLayout) findViewById(R.id.rl_imagem_detalhes);
        iv_detalhes = (ImageView) findViewById(R.id.iv_detalhes);
    }

    //Preenche as Views com informações obtidas do POJO
    private void preencheCampos() {
        tvActionBar.setText(conteudo.getSecao().getNome());
        tvTitulo.setText(conteudo.getTitulo());
        tvSubTitulo.setText(conteudo.getSubTitulo());
        tvPublicadoEm.setText(FormataData(conteudo.getPublicadoEm()));

        //Caso conteúdo tenha imagens, exibir, caso não tenha, esconder layout
        if(!conteudo.getImagens().isEmpty()) {
            tvLegendaImagem.setText(conteudo.getImagens().get(0).getLegenda());
            Glide.with(this).load(conteudo.getImagens().get(0).getUrl()).into(iv_detalhes);
        }else{
            rlImagemDetalhes.setVisibility(View.GONE);
        }
        tvTexto.setText(conteudo.getTexto());

    }

    /**
     *Formata a data recebida
     * @param data
     * @return data formatada
     */
    public String FormataData(String data){
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss-SSS");
        SimpleDateFormat destFormat = new SimpleDateFormat("dd/MM/yy hh:mm");

        Date date = null;
        try {
            date = sourceFormat.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dataFormatada = destFormat.format(date);

       return dataFormatada;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detalhes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            //Compartilhar a matéria
            case R.id.action_share_detalhes:
                String send = conteudo.getTitulo() + "\n\n Veja mais em: \n" + conteudo.getUrl();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, send);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
