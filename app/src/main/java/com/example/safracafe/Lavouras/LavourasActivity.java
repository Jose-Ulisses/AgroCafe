package com.example.safracafe.Lavouras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.safracafe.R;
import com.example.safracafe.Talhao.AddTalhaoActivity;

public class LavourasActivity extends AppCompatActivity {
    public Button mButtonAddLavoura;
    public Button mButtonViewLavouras;
    public Button mButtonAddTalhao;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lavouras);

        mButtonAddLavoura = (Button) findViewById(R.id.botao_adicionar_lavoura);
        mButtonAddLavoura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LavourasActivity.this, AddLavouraActivity.class);
                startActivity(intent);
            }
        });

        mButtonViewLavouras = (Button) findViewById(R.id.botao_todas_lavouras);
        mButtonViewLavouras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LavourasActivity.this, TodasLavourasActivity.class);
                startActivity(intent);
            }
        });

        mButtonAddTalhao = (Button) findViewById(R.id.botao_add_talhao);
        mButtonAddTalhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LavourasActivity.this, AddTalhaoActivity.class);
                startActivity(intent);
            }
        });
    }
}
