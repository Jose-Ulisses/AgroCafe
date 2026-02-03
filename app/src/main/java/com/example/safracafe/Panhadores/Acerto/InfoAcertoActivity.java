package com.example.safracafe.Panhadores.Acerto;

import android.database.Cursor;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.safracafe.Colheita.ColheitaDB.ColheitaDB;
import com.example.safracafe.Colheita.ColheitaDB.ColheitaDbSchema;
import com.example.safracafe.Lavouras.LavourasDB.LavouraDB;
import com.example.safracafe.Lavouras.LavourasDB.LavourasDbSchema;
import com.example.safracafe.Panhadores.PanhadorDB.PanhadorDB;
import com.example.safracafe.Panhadores.PanhadorDB.PanhadorDbSchema;
import com.example.safracafe.R;
import com.example.safracafe.Talhao.TalhaoDB.TalhaoDB;
import com.example.safracafe.Talhao.TalhaoDB.TalhaoDbSchema;

public class InfoAcertoActivity extends AppCompatActivity {
    private PanhadorDB mPanhadorDb;
    private ColheitaDB mColheitaDb;
    private TalhaoDB mTalhaoDb;
    private LavouraDB mLavouraDb;
    private TextView mMainTextView, tv;
    private LinearLayout layoutInfo;
    private int idPanhador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        String nomePanhador = getIntent().getStringExtra("nomePanhador");

        mMainTextView = findViewById(R.id.MainTextView);
        layoutInfo = findViewById(R.id.layout_info);

        mMainTextView.setText(nomePanhador);
        mPanhadorDb = new PanhadorDB(this);
        mColheitaDb = new ColheitaDB(this);
        mTalhaoDb = new TalhaoDB(this);
        mLavouraDb = new LavouraDB(this);

        Cursor c = mPanhadorDb.queryPanhadorByNome(nomePanhador);
        if(c != null){
            c.moveToFirst();
            idPanhador = c.getInt(c.getColumnIndexOrThrow(PanhadorDbSchema.PanhadorTbl.Cols.ID_PANHADOR));
            c.close();
        }

        Cursor cursor = mColheitaDb.QueryAcerto(idPanhador);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    int idLavoura = cursor.getInt(cursor.getColumnIndexOrThrow(ColheitaDbSchema.ColheitasTbl.Cols.ID_LAVOURA_COLHEITA));
                    System.out.println(idLavoura);
                    int idTalhao = cursor.getInt(cursor.getColumnIndexOrThrow(ColheitaDbSchema.ColheitasTbl.Cols.ID_TAlHAO_COLHEITA));
                    double total = cursor.getDouble(cursor.getColumnIndexOrThrow("total"));

                    c = mTalhaoDb.queryNomeTalhaoById(idTalhao);
                    c.moveToFirst();
                    String nomeTalhao = c.getString(c.getColumnIndexOrThrow(TalhaoDbSchema.TalhoesTbl.Cols.NOME_TALHAO));
                    c.close();

                    c = mLavouraDb.queryNomeLavouraById(idLavoura);
                    c.moveToFirst();
                    String nomeLavoura = c.getString(c.getColumnIndexOrThrow(LavourasDbSchema.LavourasTbl.Cols.NOME_LAVOURA));
                    c.close();

                    tv = new TextView(this);
                    String text =
                            "LAVOURA: " + nomeLavoura + "\n" +
                            "TALHÃO: " + nomeTalhao + "\n" +
                            "COLHIDO: " + total;

                    tv.setText(text);
                    tv.setTextSize(24);
                    tv.setPadding(0, 40, 0, 40);

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );

                    tv.setLayoutParams(params);
                    layoutInfo.addView(tv);

                    cursor.moveToNext();
                }
                cursor.close();
            }
        }
    }
}