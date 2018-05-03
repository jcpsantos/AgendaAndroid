package com.example.juancaio.agenda_android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
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

    public void atualizarContato(Contato contato){
        contatos.set(contatos.indexOf(contato), contato);
        notifyItemChanged(contatos.indexOf(contato));
    }

    public void adicionarContato(Contato contato){
        contatos.add(contato);
        notifyItemInserted(getItemCount());
    }

    public void removerContato(Contato contato){
        int position = contatos.indexOf(contato);
        contatos.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public ContatoHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return new ContatoHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.contato_lista, parent, false));

    }

    @Override
    public void onBindViewHolder(ContatoHolder holder, int position){
        holder.nomeContato.setText(contatos.get(position).getNome());
        holder.numeroTelefone.setText(contatos.get(position).getTelefone());
        holder.numeroCelular.setText(contatos.get(position).getCelular());
        holder.nomeEmail.setText(contatos.get(position).getEmail());
        final Contato contato = contatos.get(position);
        holder.btnExcluir.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = v;
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Confirmação")
                        .setMessage("Xiiiii!!! Tem certeza que deseja excluir este contato?")
                        .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ContatoDAO dao = new ContatoDAO(view.getContext());
                                boolean sucesso = dao.excluir(contato.getId());
                                if(sucesso) {
                                    removerContato(contato);
                                    Snackbar.make(view, "Você quem manda contato Excluido!", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }else{
                                    Snackbar.make(view, "Ops!!! =( Erro ao excluir o contato!", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .create()
                        .show();
            }
        });

        holder.btnEditar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity(v);
                Intent intent = activity.getIntent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("contato", contato);
                activity.finish();
                activity.startActivity(intent);
            }
        });


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

    @Override
    public int getItemCount(){
        return contatos != null ? contatos.size(): 0;
    }








}
