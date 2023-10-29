package com.example.medicount;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Modificar extends AppCompatActivity {

    EditText et_nombre;
    Button bt_modificar, bt_eliminar;
    int id;
    String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        Bundle b= getIntent().getExtras();

        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual
            }
        });
        if (b!=null){
            id = b.getInt("Id");
            nombre = b.getString("Nombre");
        }

        et_nombre = (EditText) findViewById(R.id.et_nombre);

        et_nombre.setText(nombre);

        bt_modificar = (Button) findViewById(R.id.bt_modificar);
        bt_eliminar = (Button) findViewById(R.id.bt_eliminar);

        bt_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar(id);
                onBackPressed();
            }
        });

        bt_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Modificar(id, et_nombre.getText().toString());
                onBackPressed();
            }
        });
    }

    private void Modificar(int Id, String Nombre){
        BaseHelper helper = new BaseHelper(this, "Demo",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "update Medicamentos set Nombre='" + Nombre + "' where Id="+Id;
        db.execSQL(sql);
        db.close();
    }

    private void Eliminar(int Id){
        BaseHelper helper = new BaseHelper(this, "Demo",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "delete from Medicamentos where Id="+Id;
        db.execSQL(sql);
        db.close();
    }
}