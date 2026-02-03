package com.example.safracafe.Lavouras;

import android.database.Cursor;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.safracafe.Lavouras.LavourasDB.LavouraDB;
import com.example.safracafe.Lavouras.LavourasDB.LavourasDbSchema;
import com.example.safracafe.R;
import com.example.safracafe.Talhao.TalhaoDB.TalhaoDB;
import com.example.safracafe.Talhao.TalhaoDB.TalhaoDbSchema;

public class InfoLavouraActivity extends AppCompatActivity {
    LavouraDB mLavouraDb;
    TalhaoDB mTalhaoDb;
    private TextView mTextView, mTextViewLavoura;
    int idLavoura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        String nomeLavoura = getIntent().getStringExtra("nomeLavoura");

        mTextViewLavoura = (TextView) findViewById(R.id.MainTextView);
        mTextViewLavoura.setText(nomeLavoura);

        mLavouraDb = new LavouraDB(getBaseContext());

        //get ID lavoura by name
        Cursor c = mLavouraDb.queryIdLavouraByNome(nomeLavoura);
        if(c != null){
            c.moveToFirst();
            idLavoura = c.getInt(c.getColumnIndexOrThrow(LavourasDbSchema.LavourasTbl.Cols.ID_LAVOURA));
            c.close();
        }

        mTalhaoDb = new TalhaoDB(getBaseContext());
        c = mTalhaoDb.queryNomeAndTotalByIdLavoura(idLavoura);
        if(c != null){
            c.moveToFirst();
            while(!c.isAfterLast()){
                String nomeTalhao = c.getString(c.getColumnIndexOrThrow(TalhaoDbSchema.TalhoesTbl.Cols.NOME_TALHAO));
                double totalTalhao = c.getDouble(c.getColumnIndexOrThrow(TalhaoDbSchema.TalhoesTbl.Cols.TOTAL_TALHAO));

                mTextView = new TextView(this);
                String text = nomeTalhao + " = " + totalTalhao;
                mTextView.setText(text);
                mTextView.setTextSize(35);
                mTextView.setPadding(0, 25, 0, 25);

                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_info);
                linearLayout.addView(mTextView);
                c.moveToNext();
            }
            c.close();
        }
    }
}