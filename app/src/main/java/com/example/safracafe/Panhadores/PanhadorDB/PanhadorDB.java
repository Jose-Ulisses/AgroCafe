package com.example.safracafe.Panhadores.PanhadorDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PanhadorDB {
    private final SQLiteDatabase mDatabase;

    public PanhadorDB(Context contexto){
        Context mContext = contexto.getApplicationContext();
        mDatabase = new PanhadorDbHelper(mContext).getWritableDatabase();
    }

    public void addPanhador(String nome, String cpf, String numero, String chavePix){
        ContentValues valores_panhador = new ContentValues();
        valores_panhador.put(PanhadorDbSchema.PanhadorTbl.Cols.NOME_PANHADOR, nome);
        valores_panhador.put(PanhadorDbSchema.PanhadorTbl.Cols.CPF_PANHADOR, cpf);
        valores_panhador.put(PanhadorDbSchema.PanhadorTbl.Cols.NUMERO_PANHADOR, numero);
        valores_panhador.put(PanhadorDbSchema.PanhadorTbl.Cols.CHAVE_PIX, chavePix);
        valores_panhador.put(PanhadorDbSchema.PanhadorTbl.Cols.TOTAL_COLHIDO, 0);
        valores_panhador.put(PanhadorDbSchema.PanhadorTbl.Cols.ACERTO, 0);

        mDatabase.insert(PanhadorDbSchema.PanhadorTbl.NOME_TBL, null, valores_panhador);
    }

    public Cursor queryPanhadores(){
        String sql = "SELECT " + "* " + "FROM " + PanhadorDbSchema.PanhadorTbl.NOME_TBL;

        return mDatabase.rawQuery(sql, null);
    }

    public Cursor queryNomePanhadores(){
        String sql = "SELECT " + PanhadorDbSchema.PanhadorTbl.Cols.NOME_PANHADOR +
                " FROM " + PanhadorDbSchema.PanhadorTbl.NOME_TBL;

        return mDatabase.rawQuery(sql, null);
    }

    public Cursor queryPanhadorByNome(String nomePanhador){
        String sql = "SELECT " + "* " + "FROM " + PanhadorDbSchema.PanhadorTbl.NOME_TBL +
                " WHERE " + PanhadorDbSchema.PanhadorTbl.Cols.NOME_PANHADOR + " = ?";

        return mDatabase.rawQuery(sql, new String[]{nomePanhador});
    }

    public Cursor queryIdPanhadorByNome(String nomePanhador){
        String sql = "SELECT " +
                PanhadorDbSchema.PanhadorTbl.Cols.ID_PANHADOR +
                " FROM " + PanhadorDbSchema.PanhadorTbl.NOME_TBL +
                " WHERE " + PanhadorDbSchema.PanhadorTbl.Cols.NOME_PANHADOR + " = ?";

        return mDatabase.rawQuery(sql, new String[]{nomePanhador});
    }

    public Cursor queryNomePanhadorByid(int idPanhador){
        String sql = "SELECT " + PanhadorDbSchema.PanhadorTbl.Cols.NOME_PANHADOR +
                " FROM " + PanhadorDbSchema.PanhadorTbl.NOME_TBL +
                " WHERE " + PanhadorDbSchema.PanhadorTbl.Cols.ID_PANHADOR + " = ? ";

        return mDatabase.rawQuery(sql, new String[]{String.valueOf(idPanhador)});
    }

    public void atualizarTotalPanhador(int idPanhador, double qntd, double precoTalhao) {
        ContentValues valuesColhido = new ContentValues();
        ContentValues valuesAcerto = new ContentValues();

        String sql = "SELECT " +
                PanhadorDbSchema.PanhadorTbl.Cols.TOTAL_COLHIDO + ", " +
                PanhadorDbSchema.PanhadorTbl.Cols.ACERTO +
                " FROM " + PanhadorDbSchema.PanhadorTbl.NOME_TBL +
                " WHERE " + PanhadorDbSchema.PanhadorTbl.Cols.ID_PANHADOR + " = ?";

        Cursor c = mDatabase.rawQuery(sql, new String[]{String.valueOf(idPanhador)});
        c.moveToFirst();
        double totalPanhador = c.getDouble(c.getColumnIndexOrThrow(PanhadorDbSchema.PanhadorTbl.Cols.TOTAL_COLHIDO));
        double totalAcerto = c.getDouble(c.getColumnIndexOrThrow(PanhadorDbSchema.PanhadorTbl.Cols.ACERTO));
        c.close();

        totalPanhador += qntd;
        totalAcerto += qntd * precoTalhao;
        valuesColhido.put(PanhadorDbSchema.PanhadorTbl.Cols.TOTAL_COLHIDO, totalPanhador);
        mDatabase.update(PanhadorDbSchema.PanhadorTbl.NOME_TBL, valuesColhido, PanhadorDbSchema.PanhadorTbl.Cols.ID_PANHADOR + " = ?", new String[]{String.valueOf((idPanhador))});

        valuesAcerto.put(PanhadorDbSchema.PanhadorTbl.Cols.ACERTO, totalAcerto);
        mDatabase.update(PanhadorDbSchema.PanhadorTbl.NOME_TBL, valuesAcerto, PanhadorDbSchema.PanhadorTbl.Cols.ID_PANHADOR + " = ?", new String[]{String.valueOf(idPanhador)});
    }
}