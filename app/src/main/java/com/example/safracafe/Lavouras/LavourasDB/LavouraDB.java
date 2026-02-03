package com.example.safracafe.Lavouras.LavourasDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LavouraDB {
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public LavouraDB(Context contexto) {
        mContext = contexto.getApplicationContext();
        mDatabase = new LavourasDbHelper(mContext).getWritableDatabase();
    }

    public void addLavoura(String nome) {
        ContentValues valores_lavoura = new ContentValues();
        valores_lavoura.put(LavourasDbSchema.LavourasTbl.Cols.NOME_LAVOURA, nome);
        valores_lavoura.put(LavourasDbSchema.LavourasTbl.Cols.TOTAL_LAVOURA, 0);

        mDatabase.insert(LavourasDbSchema.LavourasTbl.NOME_TBL, null, valores_lavoura);
    }

    public Cursor queryLavouras() {
        String sql = "SELECT " + "* " + "FROM " + LavourasDbSchema.LavourasTbl.NOME_TBL;

        return mDatabase.rawQuery(sql, null);
    }

    public Cursor queryNomesLavouras() {
        String sql = "SELECT " +
                LavourasDbSchema.LavourasTbl.Cols.NOME_LAVOURA +
                " FROM " + LavourasDbSchema.LavourasTbl.NOME_TBL;

        return mDatabase.rawQuery(sql, null);
    }

    public Cursor queryIdLavouraByNome(String nomeLavoura) {
        String sql = "SELECT " + LavourasDbSchema.LavourasTbl.Cols.ID_LAVOURA +
                " FROM " + LavourasDbSchema.LavourasTbl.NOME_TBL +
                " WHERE " + LavourasDbSchema.LavourasTbl.Cols.NOME_LAVOURA + " = ? ";

        return mDatabase.rawQuery(sql, new String[]{nomeLavoura});
    }

    public Cursor queryNomeLavouraById(int idLavoura) {
        String sql = "SELECT " + LavourasDbSchema.LavourasTbl.Cols.NOME_LAVOURA +
                " FROM " + LavourasDbSchema.LavourasTbl.NOME_TBL +
                " WHERE " + LavourasDbSchema.LavourasTbl.Cols.ID_LAVOURA + " = ? ";

        return mDatabase.rawQuery(sql, new String[]{String.valueOf(idLavoura)});
    }

    public void atualizarTotalLavoura(int idLavoura, float qntd) {
        String sql = "SELECT " +
                LavourasDbSchema.LavourasTbl.Cols.TOTAL_LAVOURA +
                " FROM " + LavourasDbSchema.LavourasTbl.NOME_TBL +
                " WHERE " + LavourasDbSchema.LavourasTbl.Cols.ID_LAVOURA + " = ?";

        Cursor c = mDatabase.rawQuery(sql, new String[]{String.valueOf(idLavoura)});
        c.moveToFirst();
        float total = c.getFloat(c.getColumnIndexOrThrow(LavourasDbSchema.LavourasTbl.Cols.TOTAL_LAVOURA));
        c.close();

        ContentValues values = new ContentValues();
        total += qntd;
        values.put(LavourasDbSchema.LavourasTbl.Cols.TOTAL_LAVOURA, total);
        System.out.println(total);

        mDatabase.update(LavourasDbSchema.LavourasTbl.NOME_TBL, values, LavourasDbSchema.LavourasTbl.Cols.ID_LAVOURA + " = ?",
                new String[]{String.valueOf(idLavoura)});
    }

    void deleteTbl() {
        int delete;
        delete = mDatabase.delete(LavourasDbSchema.LavourasTbl.NOME_TBL, null, null);
    }
}