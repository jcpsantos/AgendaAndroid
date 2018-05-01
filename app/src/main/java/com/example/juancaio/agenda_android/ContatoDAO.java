package com.example.juancaio.agenda_android;

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
}
