package com.example.safracafe.Panhadores.PanhadorDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PanhadorDbHelper extends SQLiteOpenHelper {
    private static final int VERSAO = 4;
    private static final String NOME_DATABASE = "PanhadorDB";

    public PanhadorDbHelper(Context context){
        super(context, NOME_DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + PanhadorDbSchema.PanhadorTbl.NOME_TBL + "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                PanhadorDbSchema.PanhadorTbl.Cols.NOME_PANHADOR + " TEXT, " +
                PanhadorDbSchema.PanhadorTbl.Cols.CPF_PANHADOR + " TEXT, " +
                PanhadorDbSchema.PanhadorTbl.Cols.NUMERO_PANHADOR + " TEXT, " +
                PanhadorDbSchema.PanhadorTbl.Cols.CHAVE_PIX + " TEXT, " +
                PanhadorDbSchema.PanhadorTbl.Cols.TOTAL_COLHIDO + " REAL, " +
                PanhadorDbSchema.PanhadorTbl.Cols.ACERTO + " REAL " + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
        db.execSQL("DROP TABLE IF EXISTS " + PanhadorDbSchema.PanhadorTbl.NOME_TBL);
        onCreate(db);
    }
}
