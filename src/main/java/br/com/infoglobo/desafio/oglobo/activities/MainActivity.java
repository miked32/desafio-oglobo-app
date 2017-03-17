package br.com.infoglobo.desafio.oglobo.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.infoglobo.desafio.oglobo.R;
import br.com.infoglobo.desafio.oglobo.models.Capa;
import br.com.infoglobo.desafio.oglobo.models.Conteudo;
import br.com.infoglobo.desafio.oglobo.network.WebApiService;
import br.com.infoglobo.desafio.oglobo.views.adapters.ConteudoAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    List<Conteudo> conteudos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_center_main);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setaRecyclerView();

        setaRetroFit();

    }

    //Seta o Retrofit e ajusta conexão com o end-point (capa.json)
    private void setaRetroFit() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        WebApiService client = retrofit.create(WebApiService.class);
        Call<List<Capa>> call = client.conteudosForArquivo("capa.json");

        call.enqueue(new Callback<List<Capa>>() {
            @Override
            public void onResponse(Call<List<Capa>> call, Response<List<Capa>> response) {

                List<Capa> capas = response.body();
                conteudos = new ArrayList<>();

                for(int i=0; capas.get(0).getConteudos().size() > i; i++){
                    conteudos.add(capas.get(0).getConteudos().get(i));
                }

                ConteudoAdapter adapter = new ConteudoAdapter(MainActivity.this, conteudos);

                adapter.setOnItemClickListener(new ConteudoAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (conteudos.get(position).getTipo().equals("linkExterno")){
                            criaDialogExterno(position);
                        }else {
                            Intent i = new Intent(MainActivity.this, DetalhesActivity.class);
                            i.putExtra("conteudo", conteudos.get(position));
                            startActivity(i);
                        }
                    }
                });

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Capa>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erro ao obter matérias", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void criaDialogExterno(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialog_externo);
        builder.setPositiveButton(R.string.ac_continue, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(conteudos.get(position).getUrl()));
                startActivity(i);
            }
        });

        // Usuário cancelou o dialog, não realiza nenhuma ação
        builder.setNegativeButton(R.string.ac_stay, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //Seta o RecyclerView e o Layout do mesmo
    private void setaRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewMain);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layout);
    }

    //Fecha o Drawer antes de sair da Activity
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //Exemplos padrão para popular o Drawer
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
