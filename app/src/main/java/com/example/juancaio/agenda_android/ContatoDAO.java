package com.example.juancaio.agenda_android;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by JuanCaio on 01/05/2018.
 */

public class ContatoDAO {
    private final String TABLE_CONTATO = "Contatos";
    private DbGateway gw;

    public ContatoDAO(Context ctx){
        gw = DbGateway.getInstance(ctx);
    }

    public boolean salvar(String nome, String telefone, String celular, String email){
        ContentValues cv = new ContentValues();
        cv.put("Nome", nome);
        cv.put("Telefone", telefone);
        cv.put("Celular", celular);
        cv.put("Email", email);

        return gw.getDatabase().insert(TABLE_CONTATO, null, cv) > 0;
    }
}
