package com.example.safracafe.Panhadores;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.safracafe.Panhadores.Acerto.InfoAcertoActivity;
import com.example.safracafe.Panhadores.PanhadorDB.PanhadorDB;
import com.example.safracafe.Panhadores.PanhadorDB.PanhadorDbSchema;
import com.example.safracafe.R;


public class InfoPanhadorActivity extends AppCompatActivity {
    private TextView mTextView;
    private Button mBotaoAcerto;
    PanhadorDB mPanhadorDb;
    String cpf, numero, chavePix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerto);

        String nomePanhador = getIntent().getStringExtra("nomePanhador");

        mTextView = (TextView) findViewById(R.id.MainTextView);
        mTextView.setText(nomePanhador);

        //get data of panhador
        mPanhadorDb = new PanhadorDB(getBaseContext());
        Cursor c = mPanhadorDb.queryPanhadorByNome(nomePanhador);
        if(c != null){
            c.moveToFirst();
            cpf = c.getString(c.getColumnIndexOrThrow(PanhadorDbSchema.PanhadorTbl.Cols.CPF_PANHADOR));
            numero = c.getString(c.getColumnIndexOrThrow(PanhadorDbSchema.PanhadorTbl.Cols.NUMERO_PANHADOR));
            chavePix = c.getString(c.getColumnIndexOrThrow(PanhadorDbSchema.PanhadorTbl.Cols.CHAVE_PIX));
            c.close();
        }

        mTextView = new TextView(this);
        String placeHolder =
                "CPF:\n" + cpf + "\n\n" +
                "Número:\n" + numero + "\n\n" +
                "Chave pix:\n" + chavePix;

        mTextView.setText(placeHolder);
        mTextView.setTextSize(30);
        mTextView.setPadding(0, 25, 0, 25);

        LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_info);
        linearLayout.addView(mTextView);

        mBotaoAcerto = (Button) findViewById(R.id.botao_acerto);
        mBotaoAcerto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoPanhadorActivity.this, InfoAcertoActivity.class);
                intent.putExtra("nomePanhador", nomePanhador);
                startActivity(intent);
            }
        });
    }
}