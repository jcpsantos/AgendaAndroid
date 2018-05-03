package com.example.juancaio.agenda_android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JuanCaio on 01/05/2018.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Crud.db";
    private static final int DATABASE_VERSION = 1;
    private final String CREATE_TABLE = "CREATE TABLE Contatos (ID INTEGER PRIMARY KEY AUTOINCREMENT, Nome TEXT NOT NULL, Telefone TEXT NOT NULL, Celular TEXT NOT NULL, Email TEXT NOT NULL);";

    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
