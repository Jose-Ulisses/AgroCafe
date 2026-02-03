package com.example.safracafe.Talhao.TalhaoDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.safracafe.Lavouras.LavourasDB.LavourasDbSchema;

public class TalhaoDbHelper extends SQLiteOpenHelper {
    private static final int VERSAO = 4;
    private static final String NOME_DATABASE = "talhoesDB";
    public TalhaoDbHelper(Context context){
        super(context, NOME_DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TalhaoDbSchema.TalhoesTbl.NOME_TBL + "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TalhaoDbSchema.TalhoesTbl.Cols.ID_LAVOURA + " INTEGER, " +
                TalhaoDbSchema.TalhoesTbl.Cols.NOME_TALHAO + " TEXT, " +
                TalhaoDbSchema.TalhoesTbl.Cols.PRECO_TALHAO + " REAL, " +
                TalhaoDbSchema.TalhoesTbl.Cols.TOTAL_TALHAO + " REAL, " +
                "FOREIGN KEY(" + TalhaoDbSchema.TalhoesTbl.Cols.ID_LAVOURA + ") " +
                "REFERENCES " + LavourasDbSchema.LavourasTbl.NOME_TBL + "(_id))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
        db.execSQL("DROP TABLE IF EXISTS " + TalhaoDbSchema.TalhoesTbl.NOME_TBL);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TalhaoDbSchema.TalhoesTbl.NOME_TBL);
        onCreate(db);
    }
}
