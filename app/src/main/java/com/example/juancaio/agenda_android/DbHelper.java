package com.example.juancaio.agenda_android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JuanCaio on 01/05/2018.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Crud.db";
    private static final int DATABASE_VERSION = 4;
    private final String CREATE_TABLE = "CREATE TABLE Contatos (ID INTEGER PRIMARY KEY AUTOINCREMENT, Nome_agenda TEXT, Nome TEXT NOT NULL, Telefone TEXT NOT NULL, Celular TEXT NOT NULL, Email TEXT NOT NULL);";
    private final String CREATE_TABLE2 = "CREATE TABLE Agenda (ID INTEGER PRIMARY KEY, Nome TEXT NOT NULL)";
    private final String INSERT_INTO = "INSERT INTO Agenda values (1, 'Agenda 1')";
    private final String INSERT_INTO2 = "INSERT INTO Agenda values (2, 'Agenda 2')";

    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //Criação das tabelas
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE2);

        //Criação das tuplas-Agenda
        db.execSQL(INSERT_INTO);
        db.execSQL(INSERT_INTO2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS Contatos");
        db.execSQL("DROP TABLE IF EXISTS Agenda");
    }
}
