package com.example.safracafe.Colheita.ColheitaDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.safracafe.Lavouras.LavourasDB.LavourasDbSchema;
import com.example.safracafe.Panhadores.PanhadorDB.PanhadorDbSchema;
import com.example.safracafe.Talhao.TalhaoDB.TalhaoDbSchema;

public class ColheitaDbHelper extends SQLiteOpenHelper {
    private static final int VERSAO = 1;
    private static final String NOME_DATABASE = "ColheitasDB";

    public ColheitaDbHelper(Context context){
        super(context, NOME_DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + ColheitaDbSchema.ColheitasTbl.NOME_TBL + "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                ColheitaDbSchema.ColheitasTbl.Cols.ID_LAVOURA_COLHEITA + " INT, " +
                ColheitaDbSchema.ColheitasTbl.Cols.ID_TAlHAO_COLHEITA + " INT, " +
                ColheitaDbSchema.ColheitasTbl.Cols.ID_PANHADOR_COLHEITA + " INT, " +
                ColheitaDbSchema.ColheitasTbl.Cols.QUANTIDADE + " REAL, " +
                ColheitaDbSchema.ColheitasTbl.Cols.DATA + " TEXT, " +
                "FOREIGN KEY(" + ColheitaDbSchema.ColheitasTbl.Cols.ID_LAVOURA_COLHEITA + ") " +
                "REFERENCES " + LavourasDbSchema.LavourasTbl.NOME_TBL + "(_id), " +
                "FOREIGN KEY(" + ColheitaDbSchema.ColheitasTbl.Cols.ID_TAlHAO_COLHEITA + ") " +
                "REFERENCES " + TalhaoDbSchema.TalhoesTbl.NOME_TBL + "(_id), " +
                "FOREIGN KEY(" + ColheitaDbSchema.ColheitasTbl.Cols.ID_PANHADOR_COLHEITA + ") " +
                "REFERENCES " + PanhadorDbSchema.PanhadorTbl.NOME_TBL + "(_id)" + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
        db.execSQL("DROP TABLE IF EXISTS " + ColheitaDbSchema.ColheitasTbl.NOME_TBL);
        onCreate(db);
    }
}
