package com.example.medicount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CalendarActivity extends AppCompatActivity {
    Button btnAtras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        btnAtras = findViewById(R.id.btn_atras_ca);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalendarActivity.this,ClockActivity.class);
                startActivity(intent);
            }
        });
    }
}