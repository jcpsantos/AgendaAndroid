package com.example.juancaio.agenda_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JuanCaio on 01/05/2018.
 */

public class ContatoDAO {
    private final String TABLE_CONTATO = "Contatos";
    private DbGateway gw;

    public ContatoDAO(Context ctx){
        gw = DbGateway.getInstance(ctx);
    }

    public List<Contato> retornaTodos(){
        List<Contato> contatos = new ArrayList<>();
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Contatos" , null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("ID"));
            String n_agenda = cursor.getString(cursor.getColumnIndex("Nome_agenda"));
            String nome = cursor.getString(cursor.getColumnIndex("Nome"));
            String telefone = cursor.getString(cursor.getColumnIndex("Telefone"));
            String celular = cursor.getString(cursor.getColumnIndex("Celular"));
            String email = cursor.getString(cursor.getColumnIndex("Email"));
            contatos.add(new Contato(id, n_agenda, nome, telefone, celular, email));
        }
        cursor.close();
        return contatos;
    }

    public Contato retornarUltimo(){
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Contatos ORDER BY ID DESC", null);
        if(cursor.moveToFirst()){
            int id = cursor.getInt(cursor.getColumnIndex("ID"));
            String n_agenda = cursor.getString(cursor.getColumnIndex("Nome_agenda"));
            String nome = cursor.getString(cursor.getColumnIndex("Nome"));
            String telefone = cursor.getString(cursor.getColumnIndex("Telefone"));
            String celular = cursor.getString(cursor.getColumnIndex("Celular"));
            String email = cursor.getString(cursor.getColumnIndex("Email"));
            cursor.close();
            return new Contato(id, n_agenda, nome, telefone, celular, email);
        }

        return null;
    }

    public boolean salvar( String n_agenda, String nome, String telefone, String celular, String email){
        return salvar(0, n_agenda, nome, telefone, celular, email);
    }

    public boolean salvar(int id, String n_agenda, String nome, String telefone, String celular, String email){
        ContentValues cv = new ContentValues();
        cv.put("Nome_agenda", n_agenda);
        cv.put("Nome", nome);
        cv.put("Telefone", telefone);
        cv.put("Celular", celular);
        cv.put("Email", email);
        if(id > 0)
            return gw.getDatabase().update(TABLE_CONTATO, cv, "ID=?", new String[]{ id + "" }) > 0;
        else
            return gw.getDatabase().insert(TABLE_CONTATO, null, cv) > 0;
    }





    public boolean excluir(int id){
        return gw.getDatabase().delete(TABLE_CONTATO, "ID=?", new String[]{ id + "" }) > 0;
    }
}
