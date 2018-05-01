package com.example.juancaio.agenda_android;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by JuanCaio on 01/05/2018.
 */

public class ContatoAdapter extends RecyclerView.Adapter<ContatoHolder> {
    private final List<Contato> contatos;

    public ContatoAdapter(List<Contato> contatos){
        this.contatos = contatos;
    }

    @Override
    public ContatoHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return new ContatoHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.contato_lista, parent, false));

    }

    @Override
    public void onBindViewHolder(ContatoHolder holder, int position){
        holder.nomeContato.setText(contatos.get(position).getNome());
    }

    @Override
    public int getItemCount(){
        return contatos != null ? contatos.size(): 0;
    }

    public void adicionarContato(Contato contato){
        contatos.add(contato);
        notifyItemInserted(getItemCount());
    }
}
