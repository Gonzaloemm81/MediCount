package com.example.medicount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClockActivity extends AppCompatActivity {

    Button btnAtras;
    Button btnSiguiente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        btnAtras = findViewById(R.id.btn_atras_cl);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClockActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });
        btnSiguiente = findViewById(R.id.btn_siguiente_cl);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClockActivity.this,CalendarActivity.class);
                startActivity(intent);
            }
        });

        Button btnMasCl = findViewById(R.id.btn_mas_cl);
        btnMasCl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClockActivity.this, Agregar.class);
                startActivity(intent);
            }
});


    }

}