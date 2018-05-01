package com.example.juancaio.agenda_android;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.Serializable;
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

        holder.btnEditar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity(v);
                Intent intent = activity.getIntent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("contato", (Serializable) contatos);
                activity.finish();
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){
        return contatos != null ? contatos.size(): 0;
    }

    public void adicionarContato(Contato contato){
        contatos.add(contato);
        notifyItemInserted(getItemCount());
    }

    private Activity getActivity(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }

    public void atualizarContato(Contato cliente){
        contatos.set(contatos.indexOf(cliente), cliente);
        notifyItemChanged(contatos.indexOf(cliente));
    }
}
