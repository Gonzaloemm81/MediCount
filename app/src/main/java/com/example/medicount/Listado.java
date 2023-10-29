package com.example.medicount;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Listado extends AppCompatActivity {

    ListView listView;
    ArrayList<String> listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        listView = (ListView) findViewById(R.id.listView);
        CargarListado();


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
