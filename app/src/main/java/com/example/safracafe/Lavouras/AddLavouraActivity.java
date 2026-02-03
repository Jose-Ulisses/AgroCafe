package com.example.safracafe.Lavouras;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.safracafe.Lavouras.LavourasDB.LavouraDB;
import com.example.safracafe.R;

public class AddLavouraActivity extends AppCompatActivity {

    public Button mButtonAddLavoura;
    EditText inputNomeLavoura;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lavoura);

        mButtonAddLavoura = (Button) findViewById(R.id.botao_add_lavoura);
        mButtonAddLavoura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNomeLavoura = (EditText) findViewById(R.id.input_nome_lavoura);
                LavouraDB lavouraDb = new LavouraDB(AddLavouraActivity.this);
                lavouraDb.addLavoura(inputNomeLavoura.getText().toString());
                finish();
            }
        });
    }
}