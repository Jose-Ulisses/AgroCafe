package com.example.safracafe.Colheita;

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

public class ColheitasAnterioresActivity extends AppCompatActivity {
    TextView mTextViewColheitas;
    ColheitaDB mColheitaDb;
    LavouraDB mLavouraDb;
    TalhaoDB mTalhaoDb;
    PanhadorDB mPanhadorDb;
    String nomeLavoura, nomeTalhao, nomePanhador, dataColheita;
    Float qntdColheita;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prev_colheitas);

        mLavouraDb = new LavouraDB(getBaseContext());
        mTalhaoDb = new TalhaoDB(getBaseContext());
        mPanhadorDb = new PanhadorDB(getBaseContext());
        mColheitaDb = new ColheitaDB(getBaseContext());

        //get list of colheitas
        Cursor c = mColheitaDb.queryColheita();
        Cursor cQuery;
        if(c != null){
            try{
                c.moveToFirst();
                while(!c.isAfterLast()){

                    //get nome lavoura by id
                    int idLavoura = c.getInt(c.getColumnIndexOrThrow(ColheitaDbSchema.ColheitasTbl.Cols.ID_LAVOURA_COLHEITA));
                    cQuery = mLavouraDb.queryNomeLavouraById(idLavoura);
                    cQuery.moveToFirst();
                    nomeLavoura = cQuery.getString(cQuery.getColumnIndexOrThrow(LavourasDbSchema.LavourasTbl.Cols.NOME_LAVOURA));
                    cQuery.close();

                    //get nome talhao by id
                    int idTalhao = c.getInt(c.getColumnIndexOrThrow(ColheitaDbSchema.ColheitasTbl.Cols.ID_TAlHAO_COLHEITA));
                    cQuery = mTalhaoDb.queryNomeTalhaoById(idTalhao);
                    cQuery.moveToFirst();
                    nomeTalhao = cQuery.getString(cQuery.getColumnIndexOrThrow(TalhaoDbSchema.TalhoesTbl.Cols.NOME_TALHAO));
                    cQuery.close();

                    //get nome panhador by id
                    int idPanhador = c.getInt(c.getColumnIndexOrThrow(ColheitaDbSchema.ColheitasTbl.Cols.ID_PANHADOR_COLHEITA));
                    cQuery = mPanhadorDb.queryNomePanhadorByid(idPanhador);
                    cQuery.moveToFirst();
                    nomePanhador = cQuery.getString(cQuery.getColumnIndexOrThrow(PanhadorDbSchema.PanhadorTbl.Cols.NOME_PANHADOR));
                    cQuery.close();

                    dataColheita = c.getString(c.getColumnIndexOrThrow(ColheitaDbSchema.ColheitasTbl.Cols.DATA));
                    qntdColheita = c.getFloat(c.getColumnIndexOrThrow(ColheitaDbSchema.ColheitasTbl.Cols.QUANTIDADE));

                    mTextViewColheitas = new TextView(this);
                    String text = nomeLavoura + "\n" + nomeTalhao + "\n" + nomePanhador + "\n" + qntdColheita + "\n" + dataColheita;
                    mTextViewColheitas.setText(text);

                    mTextViewColheitas.setTextSize(20);
                    mTextViewColheitas.setPadding(0, 70, 0, 70);

                    LinearLayout.LayoutParams layoutColheitas = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );

                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
                    linearLayout.addView(mTextViewColheitas);

                    c.moveToNext();
                }
            } finally {
                c.close();
            }
        }
    }
}