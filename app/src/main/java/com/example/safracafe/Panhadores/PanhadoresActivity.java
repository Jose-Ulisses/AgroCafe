package com.example.safracafe.Panhadores;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.safracafe.R;

public class PanhadoresActivity extends AppCompatActivity {

    private Button mBotaoAdicionarPanhador;
    private Button mBotaoTodosPanhadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panhadores);

        mBotaoAdicionarPanhador = (Button) findViewById(R.id.botao_adicionar_panhador);
        mBotaoAdicionarPanhador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PanhadoresActivity.this, AddPanhadorActivity.class);
                startActivity(intent);
            }
        });

        mBotaoTodosPanhadores = (Button) findViewById(R.id.botao_todos_panhadores);
        mBotaoTodosPanhadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PanhadoresActivity.this, TodosPanhadoresActivity.class);
                startActivity(intent);
            }
        });
    }
}