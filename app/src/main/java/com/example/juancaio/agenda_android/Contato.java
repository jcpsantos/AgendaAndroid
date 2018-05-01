package com.example.juancaio.agenda_android;

import java.io.Serializable;

/**
 * Created by JuanCaio on 01/05/2018.
 */

public class Contato implements Serializable {
    private int id;
    private String nome;
    private String telefone;
    private String celular;
    private String email;

    public Contato(int id, String nome, String telefone, String celular, String email){
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.celular = celular;
        this.email= email;
    }

    public int getId(){return this.id;}
    public String getNome(){return this.nome;}
    public String getTelefone(){return this.telefone;}
    public String getCelular(){return this.celular;}
    public String getEmail(){return this.email;}

    @Override
    public boolean equals(Object o){
        return this.id == ((Contato)o).id;
    }

    @Override
    public int hashCode(){
        return this.id;
    }
}
