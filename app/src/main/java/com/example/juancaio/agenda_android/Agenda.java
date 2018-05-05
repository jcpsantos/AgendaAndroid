package com.example.juancaio.agenda_android;

import java.io.Serializable;

/**
 * Created by JuanCaio on 05/05/2018.
 */

public class Agenda implements Serializable {
    private int id_agenda;
    private String nome_agenda;

    public Agenda (int id_agenda, String nome_agenda){
        this.id_agenda = id_agenda;
        this.nome_agenda = nome_agenda;
    }

    public int getId (){return this.id_agenda;}

    public String getNome_agenda() {
        return nome_agenda;
    }
}
