package com.example.safracafe.Talhao.TalhaoDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TalhaoDB {
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public TalhaoDB(Context contexto){
        mContext = contexto.getApplicationContext();
        mDatabase = new TalhaoDbHelper(mContext).getWritableDatabase();
    }

    public void addTalhao(String nomeTalhao, int preco, int idLavoura){
        ContentValues valores_talhao = new ContentValues();

        valores_talhao.put(TalhaoDbSchema.TalhoesTbl.Cols.ID_LAVOURA, idLavoura);
        valores_talhao.put(TalhaoDbSchema.TalhoesTbl.Cols.NOME_TALHAO, nomeTalhao);
        valores_talhao.put(TalhaoDbSchema.TalhoesTbl.Cols.PRECO_TALHAO, preco);
        valores_talhao.put(TalhaoDbSchema.TalhoesTbl.Cols.TOTAL_TALHAO, 0);

        mDatabase.insert(TalhaoDbSchema.TalhoesTbl.NOME_TBL, null, valores_talhao);
    }

    public Cursor queryTalhoes(){
        String sql = "SELECT " + "* "  + "FROM " + TalhaoDbSchema.TalhoesTbl.NOME_TBL;

        return mDatabase.rawQuery(sql, null);
    }

    public Cursor queryTalhaoByIdLavoura(int idLavoura){
        String sql = "SELECT " +
                TalhaoDbSchema.TalhoesTbl.Cols.NOME_TALHAO +
                " FROM " + TalhaoDbSchema.TalhoesTbl.NOME_TBL +
                " WHERE " + TalhaoDbSchema.TalhoesTbl.Cols.ID_LAVOURA + " = ?";

        return mDatabase.rawQuery(sql, new String[]{String.valueOf(idLavoura)});
    }

    public Cursor queryNomeAndTotalByIdLavoura(int idLavoura){
        String sql = "SELECT " +
                TalhaoDbSchema.TalhoesTbl.Cols.NOME_TALHAO + ", " +
                TalhaoDbSchema.TalhoesTbl.Cols.TOTAL_TALHAO +
                " FROM " + TalhaoDbSchema.TalhoesTbl.NOME_TBL +
                " WHERE " + TalhaoDbSchema.TalhoesTbl.Cols.ID_LAVOURA + " = ?";

        return mDatabase.rawQuery(sql, new String[]{String.valueOf(idLavoura)});
    }

    public Cursor queryIdAndPrecoByNome(String nomeTalhao, int idLavoura){
        String sql = "SELECT " +
                TalhaoDbSchema.TalhoesTbl.Cols.ID_TALHAO + ", " +
                TalhaoDbSchema.TalhoesTbl.Cols.PRECO_TALHAO +
                " FROM " + TalhaoDbSchema.TalhoesTbl.NOME_TBL +
                " WHERE " + TalhaoDbSchema.TalhoesTbl.Cols.NOME_TALHAO + " = ?" +
                " AND " + TalhaoDbSchema.TalhoesTbl.Cols.ID_LAVOURA + " = ?";

        return mDatabase.rawQuery(sql, new String[]{nomeTalhao, String.valueOf(idLavoura)});
    }

    public Cursor queryNomeTalhaoById(int idTalhao){
        String sql = "SELECT " + TalhaoDbSchema.TalhoesTbl.Cols.NOME_TALHAO +
                " FROM " + TalhaoDbSchema.TalhoesTbl.NOME_TBL +
                " WHERE " + TalhaoDbSchema.TalhoesTbl.Cols.ID_TALHAO + " = ?";

        return mDatabase.rawQuery(sql, new String[]{String.valueOf(idTalhao)});
    }

    public void atualizarTotalTalhao(int idTalhao, float qntd){
        String sql = "SELECT " +
                TalhaoDbSchema.TalhoesTbl.Cols.TOTAL_TALHAO +
                " FROM " + TalhaoDbSchema.TalhoesTbl.NOME_TBL +
                " WHERE " + TalhaoDbSchema.TalhoesTbl.Cols.ID_TALHAO + " = ?";

        Cursor c = mDatabase.rawQuery(sql, new String[]{String.valueOf(idTalhao)});
        c.moveToFirst();
        float total = c.getFloat(c.getColumnIndexOrThrow(TalhaoDbSchema.TalhoesTbl.Cols.TOTAL_TALHAO));
        c.close();

        total += qntd;
        
        ContentValues values = new ContentValues();
        values.put(TalhaoDbSchema.TalhoesTbl.Cols.TOTAL_TALHAO, total);

        mDatabase.update(TalhaoDbSchema.TalhoesTbl.NOME_TBL, values, TalhaoDbSchema.TalhoesTbl.Cols.ID_TALHAO + " = ?",
                new String[]{String.valueOf(idTalhao)});
    }

    public float getPreco(int idTalhao){
        String sql = "SELECT " + TalhaoDbSchema.TalhoesTbl.Cols.PRECO_TALHAO +
                " FROM " + TalhaoDbSchema.TalhoesTbl.NOME_TBL +
                " WHERE " + TalhaoDbSchema.TalhoesTbl.Cols.ID_TALHAO + " = ?";

        Cursor c = mDatabase.rawQuery(sql, new String[]{String.valueOf(idTalhao)});
        c.moveToFirst();
        float preco = c.getFloat(c.getColumnIndexOrThrow(TalhaoDbSchema.TalhoesTbl.Cols.PRECO_TALHAO));
        c.close();

        return preco;
    }

    public void deleteTbl(){
        int delete;
        delete = mDatabase.delete(TalhaoDbSchema.TalhoesTbl.NOME_TBL, null, null);
    }
}