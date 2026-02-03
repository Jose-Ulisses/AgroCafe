package com.example.safracafe.Colheita;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.safracafe.R;

public class ColheitaActivity extends AppCompatActivity {
    public Button mButtonAdiconarColheita;
    public Button mButtonColheitasAnt;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colheita);

        mButtonAdiconarColheita = (Button) findViewById(R.id.botao_adicionar_colheita);
        mButtonAdiconarColheita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ColheitaActivity.this, AdicionarColheitaActivity.class);
                startActivity(intent);
            }
        });

        mButtonColheitasAnt = (Button) findViewById(R.id.botao_colheitas_anteriores);
        mButtonColheitasAnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ColheitaActivity.this, ColheitasAnterioresActivity.class);
                startActivity(intent);
            }
        });
    }
}
