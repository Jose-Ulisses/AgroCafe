package com.example.safracafe.Talhao;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.safracafe.Lavouras.LavourasDB.LavouraDB;
import com.example.safracafe.Lavouras.LavourasDB.LavourasDbSchema;
import com.example.safracafe.R;
import com.example.safracafe.Talhao.TalhaoDB.TalhaoDB;
import java.util.ArrayList;
import java.util.List;

public class AddTalhaoActivity extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    LavouraDB mLavouraDb;
    TalhaoDB mTalhaoDb;
    EditText inputNomeTalhao;
    EditText inputPrecoTalhao;
    private Button mBotaoAddTalhao;
    int idLavoura;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_talhao);

        //autocomplete lavouras
        autoCompleteTextView = findViewById(R.id.auto_complete_textview);
        List<String> nomes = new ArrayList<>();
        mLavouraDb = new LavouraDB(getBaseContext());
        Cursor c = mLavouraDb.queryNomesLavouras();
        if(c != null){
            c.moveToFirst();
            while (!c.isAfterLast()) {
                nomes.add(c.getString(c.getColumnIndexOrThrow(LavourasDbSchema.LavourasTbl.Cols.NOME_LAVOURA)));
                c.moveToNext();
            }
            c.close();
        }

        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, nomes);
        autoCompleteTextView.setAdapter(adapterItems);


        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String nomeLavoura = adapterView.getItemAtPosition(i).toString();

                //query ID_LAVOURA by name
                Cursor c = mLavouraDb.queryIdLavouraByNome(nomeLavoura);
                if(c != null){
                    c.moveToFirst();
                    idLavoura = c.getInt(c.getColumnIndexOrThrow(LavourasDbSchema.LavourasTbl.Cols.ID_LAVOURA));
                    c.close();
                }
            }
        });

        mBotaoAddTalhao = (Button) findViewById(R.id.botao_add_talhao);
        mBotaoAddTalhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNomeTalhao = (EditText) findViewById(R.id.input_nome_talhao);
                String nomeTalhao = inputNomeTalhao.getText().toString();

                inputPrecoTalhao = (EditText) findViewById(R.id.input_preco_talhao);
                int precoTalhao = Integer.parseInt(inputPrecoTalhao.getText().toString());

                //add Talhao to DB
                mTalhaoDb = new TalhaoDB(AddTalhaoActivity.this);
                mTalhaoDb.addTalhao(nomeTalhao, precoTalhao, idLavoura);
                finish();
            }
        });
    }
}