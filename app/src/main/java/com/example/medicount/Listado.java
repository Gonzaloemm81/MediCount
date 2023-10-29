package com.example.medicount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Listado extends AppCompatActivity {

    ListView listView;
    ArrayList<String> listado;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        CargarListado();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual
            }
        });

        listView = findViewById(R.id.listView);
        CargarListado();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(Listado.this,listado.get(position),Toast.LENGTH_SHORT).show();
                int clave = Integer.parseInt(listado.get(position).split(" ")[0]);
                String nombre = listado.get(position).split(" ")[1];
                Intent intent = new Intent(Listado.this, Modificar.class);
                intent.putExtra("Id", clave);
                intent.putExtra("Nombre", nombre);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void CargarListado(){
        listado = ListaMedicamentos();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listado);
        listView.setAdapter(adapter);
    }

    private ArrayList<String> ListaMedicamentos() {
        ArrayList<String> datos = new ArrayList<String>();
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT ID, NOMBRE FROM MEDICAMENTOS";
        Cursor c = db.rawQuery(sql, null);
        if (c.moveToFirst()) {
            do {
                String linea = c.getInt(0) + " " + c.getString(1); // Solo dos columnas: ID y NOMBRE
                datos.add(linea);
            } while (c.moveToNext());
        }
        c.close(); // Cierra el cursor
        db.close();
        return datos;
    }
}
