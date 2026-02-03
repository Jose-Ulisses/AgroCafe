package com.example.safracafe.Colheita;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.safracafe.Colheita.ColheitaDB.ColheitaDB;
import com.example.safracafe.Lavouras.LavourasDB.LavouraDB;
import com.example.safracafe.Lavouras.LavourasDB.LavourasDbSchema;
import com.example.safracafe.Panhadores.PanhadorDB.PanhadorDB;
import com.example.safracafe.Panhadores.PanhadorDB.PanhadorDbSchema;
import com.example.safracafe.R;
import com.example.safracafe.Talhao.TalhaoDB.TalhaoDB;
import com.example.safracafe.Talhao.TalhaoDB.TalhaoDbSchema;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AdicionarColheitaActivity extends AppCompatActivity {
    ColheitaDB mColheitaDb;
    AutoCompleteTextView AutoCompTextViewLavoura, autoCompTextViewTalhao, autCompTextViewPanhador;
    LavouraDB mLavouraDb;
    TalhaoDB mTalhaoDb;
    PanhadorDB mPanhadorDb;
    ArrayAdapter<String> adapterItems;
    String nomeLavoura, nomeTalhao, nomePanhador;
    int idLavoura, idTalhao, idPanhador;
    double precoTalhao;
    Button mBotaoAddColheita;
    LocalDateTime dataHoraAtual;
    DateTimeFormatter formatador;
    EditText inputQntd;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_colheita);

        //autocomplete lavouras
        AutoCompTextViewLavoura = findViewById(R.id.ac_textView_lavoura);
        List<String> nomesLavouras = new ArrayList<>();
        mLavouraDb = new LavouraDB(getBaseContext());

        //get list of lavouras
        Cursor cursorL = mLavouraDb.queryNomesLavouras();
        if(cursorL != null){
            try{
                cursorL.moveToFirst();
                while(!cursorL.isAfterLast()){
                    nomesLavouras.add(cursorL.getString(cursorL.getColumnIndexOrThrow(LavourasDbSchema.LavourasTbl.Cols.NOME_LAVOURA)));
                    cursorL.moveToNext();
                }
            } finally {
                cursorL.close();
            }
        }

        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, nomesLavouras);
        AutoCompTextViewLavoura.setAdapter(adapterItems);
        AutoCompTextViewLavoura.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                nomeLavoura = adapterView.getItemAtPosition(i).toString();

                //get id lavoura by name
                Cursor cursorId = mLavouraDb.queryIdLavouraByNome(nomeLavoura);
                if(cursorId != null){
                    cursorId.moveToFirst();
                    idLavoura = cursorId.getInt(cursorId.getColumnIndexOrThrow(LavourasDbSchema.LavourasTbl.Cols.ID_LAVOURA));
                    cursorId.close();
                }

                //autoComplete talhao
                autoCompTextViewTalhao = findViewById(R.id.ac_textView_talhao);
                List<String> nomesTalhao = new ArrayList<>();
                mTalhaoDb = new TalhaoDB(getBaseContext());

                //get list talhao by id of lavoura
                Cursor cursorT = mTalhaoDb.queryTalhaoByIdLavoura(idLavoura);
                if(cursorT != null){
                    cursorT.moveToFirst();
                    while(!cursorT.isAfterLast()){
                        nomesTalhao.add(cursorT.getString(cursorT.getColumnIndexOrThrow(TalhaoDbSchema.TalhoesTbl.Cols.NOME_TALHAO)));
                        cursorT.moveToNext();
                    }
                    cursorT.close();
                }

                adapterItems = new ArrayAdapter<>(AdicionarColheitaActivity.this, R.layout.list_item, nomesTalhao);
                autoCompTextViewTalhao.setAdapter(adapterItems);
                autoCompTextViewTalhao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        nomeTalhao = adapterView.getItemAtPosition(i).toString();

                        //get id and preco talhao by name
                        Cursor cursorId = mTalhaoDb.queryIdAndPrecoByNome(nomeTalhao, idLavoura);
                        if(cursorId != null){
                            cursorId.moveToFirst();
                            idTalhao = cursorId.getInt(cursorId.getColumnIndexOrThrow(TalhaoDbSchema.TalhoesTbl.Cols.ID_TALHAO));
                            precoTalhao = cursorId.getInt(cursorId.getColumnIndexOrThrow(TalhaoDbSchema.TalhoesTbl.Cols.PRECO_TALHAO));
                            cursorId.close();
                        }
                    }
                });
            }
        });

        //autocomplete panhador
        autCompTextViewPanhador = findViewById(R.id.ac_textView_panhador);
        List<String> nomesPanhadores = new ArrayList<>();
        mPanhadorDb = new PanhadorDB(getBaseContext());

        //get list of Panhadores
        Cursor cursorP = mPanhadorDb.queryNomePanhadores();
        if (cursorP != null) {
            cursorP.moveToFirst();
            while (!cursorP.isAfterLast()) {
                nomesPanhadores.add(cursorP.getString(cursorP.getColumnIndexOrThrow(PanhadorDbSchema.PanhadorTbl.Cols.NOME_PANHADOR)));
                cursorP.moveToNext();
            }
            cursorP.close();
        }

        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, nomesPanhadores);
        autCompTextViewPanhador.setAdapter(adapterItems);
        autCompTextViewPanhador.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                nomePanhador = adapterView.getItemAtPosition(i).toString();

                //get ID_PANHADOR by name
                Cursor cursorId = mPanhadorDb.queryIdPanhadorByNome(nomePanhador);
                if(cursorId != null){
                    cursorId.moveToFirst();
                    idPanhador = cursorId.getInt(cursorId.getColumnIndexOrThrow(PanhadorDbSchema.PanhadorTbl.Cols.ID_PANHADOR));
                    cursorId.close();
                }
            }
        });

        mBotaoAddColheita = (Button) findViewById(R.id.botao_add_colheita);
        mBotaoAddColheita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataHoraAtual = LocalDateTime.now();
                formatador = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                String data = dataHoraAtual.format(formatador);

                inputQntd = (EditText) findViewById(R.id.input_qtd);
                float qntd = Float.parseFloat(inputQntd.getText().toString());

                mColheitaDb = new ColheitaDB(AdicionarColheitaActivity.this);
                mColheitaDb.addColheita(idLavoura, idTalhao, idPanhador, qntd, data);

                mLavouraDb.atualizarTotalLavoura(idLavoura, qntd);
                mTalhaoDb.atualizarTotalTalhao(idTalhao, qntd);
                mPanhadorDb.atualizarTotalPanhador(idPanhador, qntd, precoTalhao);
                finish();
            }
        });
    }
}