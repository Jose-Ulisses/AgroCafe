package com.example.safracafe.Panhadores.Acerto;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.safracafe.Panhadores.PanhadorDB.PanhadorDB;
import com.example.safracafe.R;

public class AcertoActivity extends AppCompatActivity {
    PanhadorDB mPanhadorDb;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_panhadores);

        mPanhadorDb = new PanhadorDB(getBaseContext());
        Cursor c = mPanhadorDb.queryPanhadores();
        if (c != null) {
            try {
                c.moveToFirst();
                while (!c.isAfterLast()) {
                    String nomePanhador = c.getString(c.getColumnIndexOrThrow("nomePanhador"));

                    mTextView = new TextView(this);
                    mTextView.setText(nomePanhador);
                    mTextView.setTextSize(35);
                    mTextView.setPadding(0, 70, 0, 70);

                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_todos_panhadores);
                    linearLayout.addView(mTextView);

                    mTextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(AcertoActivity.this, InfoAcertoActivity.class);
                            intent.putExtra("nomePanhador", nomePanhador);
                            startActivity(intent);
                        }
                    });
                    
                    c.moveToNext();
                }
            } finally {
                c.close();
            }
        }
    }
}
