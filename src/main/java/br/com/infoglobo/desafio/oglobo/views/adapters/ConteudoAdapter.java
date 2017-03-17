package br.com.infoglobo.desafio.oglobo.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.com.infoglobo.desafio.oglobo.R;
import br.com.infoglobo.desafio.oglobo.models.Conteudo;
import br.com.infoglobo.desafio.oglobo.models.Imagem;

/**
 * Created by miked on 16/03/2017.
 */

public class ConteudoAdapter extends RecyclerView.Adapter {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_EXTERNAL = 2;

    private Context context;
    private List<Conteudo> conteudos;
    static OnItemClickListener mItemClickListener;

    private static class HeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvHeaderTitulo, tvHeaderSecaoNome;
        private ImageView ivHeaderImage;

        private HeaderViewHolder(View view) {
            super(view);
            tvHeaderTitulo = (TextView)view.findViewById(R.id.tvHeaderTitulo);
            tvHeaderSecaoNome = (TextView) view.findViewById(R.id.tvHeaderSecaoNome);
            ivHeaderImage = (ImageView) view.findViewById(R.id.ivHeaderImage);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null)
            mItemClickListener.onItemClick(v, getPosition());
        }
    }

    private static class ConteudoExternoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvRowTitulo, tvRowSecaoNome;
        private ImageView ivRowImage;

        private ConteudoExternoViewHolder(View view){
            super(view);
            tvRowTitulo = (TextView) view.findViewById(R.id.tvRowTitulo);
            tvRowSecaoNome = (TextView) view.findViewById(R.id.tvRowSecaoNome);
            ivRowImage = (ImageView) view.findViewById(R.id.ivRowImage);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null)
                mItemClickListener.onItemClick(v, getPosition());
        }
    }

    private static class ConteudoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvRowTitulo, tvRowSecaoNome;
        private ImageView ivRowImage;

        private ConteudoViewHolder(View view){
            super(view);
            tvRowTitulo = (TextView) view.findViewById(R.id.tvRowTitulo);
            tvRowSecaoNome = (TextView) view.findViewById(R.id.tvRowSecaoNome);
            ivRowImage = (ImageView) view.findViewById(R.id.ivRowImage);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null)
            mItemClickListener.onItemClick(v, getPosition());
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View itemView;
        switch (viewType){
            case TYPE_HEADER:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_header_main, parent, false);
                return  new HeaderViewHolder(itemView);
            case TYPE_EXTERNAL:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_main, parent, false);
                return new ConteudoExternoViewHolder(itemView);
            case TYPE_ITEM:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_main, parent, false);
                return new ConteudoViewHolder(itemView);
        }throw new RuntimeException("recyclerView: There is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    public ConteudoAdapter(Context context, List<Conteudo> conteudos){
        this.context = context;
        this.conteudos = conteudos;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Conteudo conteudo = conteudos.get(position);


        if(viewHolder instanceof HeaderViewHolder) {
            HeaderViewHolder holder = (HeaderViewHolder) viewHolder;
            holder.tvHeaderTitulo.setText(conteudo.getTitulo());
            holder.tvHeaderSecaoNome.setText(conteudo.getSecao().getNome());
            for (Imagem i : conteudo.getImagens()){
                Glide.with(context).load(i.getUrl()).into(holder.ivHeaderImage);
            }
        } else if(viewHolder instanceof ConteudoViewHolder) {
            ConteudoViewHolder holder = (ConteudoViewHolder) viewHolder;
            holder.tvRowTitulo.setText(conteudo.getTitulo());
            holder.tvRowSecaoNome.setText(conteudo.getSecao().getNome());
            for (Imagem i : conteudo.getImagens()){
                Glide.with(context).load(i.getUrl()).into(holder.ivRowImage);
            }
        }else if(viewHolder instanceof ConteudoExternoViewHolder) {
            ConteudoExternoViewHolder holder = (ConteudoExternoViewHolder) viewHolder;
            holder.tvRowTitulo.setText(conteudo.getTitulo());
            holder.tvRowSecaoNome.setText(conteudo.getSecao().getNome());
            if (conteudo.getImagens().isEmpty()){
                Glide.with(context).load(R.drawable.oglobo).into(holder.ivRowImage);
            }else for (Imagem i : conteudo.getImagens()) {
                Glide.with(context).load(i.getUrl()).into(holder.ivRowImage);
            }
        }

    }

    public interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0)
            return TYPE_HEADER;
        if (conteudos.get(position).getTipo().equals("linkExterno"))
            return TYPE_EXTERNAL;
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return conteudos.size();
    }
}
