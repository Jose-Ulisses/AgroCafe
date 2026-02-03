package com.example.safracafe.Lavouras.LavourasDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LavourasDbHelper extends SQLiteOpenHelper {
    private static final int VERSAO = 1;
    private static final String NOME_DATABASE = "lavourasDB";

    public LavourasDbHelper(Context context){
        super(context, NOME_DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + LavourasDbSchema.LavourasTbl.NOME_TBL + "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                LavourasDbSchema.LavourasTbl.Cols.NOME_LAVOURA + " TEXT, " +
                LavourasDbSchema.LavourasTbl.Cols.TOTAL_LAVOURA + " REAL" + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
        db.execSQL("DROP TABLE IF EXISTS " + LavourasDbSchema.LavourasTbl.NOME_TBL);
        onCreate(db);
    }
}
