package com.example.juancaio.agenda_android;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by JuanCaio on 01/05/2018.
 */

public class ContatoHolder extends RecyclerView.ViewHolder {
    public TextView nomeContato;
    public ImageButton btnEditar;
    public ImageButton btnExcluir;

    public ContatoHolder(View itemView){
        super(itemView);
        nomeContato = itemView.findViewById(R.id.nomeContato);
        btnEditar = itemView.findViewById(R.id.btnEdit);
        btnExcluir = itemView.findViewById(R.id.btnDelete);
    }

}
