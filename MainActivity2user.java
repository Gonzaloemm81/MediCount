package com.example.medicalacount;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity2user extends AppCompatActivity {


    EditText edtemaulusuario, edtnombreusuario;
    ImageButton imgbtnsetting,imgbtnsalir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2user);
    }
}