package com.example.cards1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String MedsDB = "MedicamentosDB";
    private static final int DATABASE_VERSION = 2;
    private Medicamento medicamento;


    public DatabaseHelper(Context context) {
        super(context, MedsDB, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Medicamentos (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, dosis TEXT, hora TEXT, frecuencia INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Medicamentos");
        onCreate(db);

    }

    public void agregarMedicamento(String nombre, String dosis, String hora, int frecuencia){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("dosis", dosis);
        values.put("hora", hora);
        values.put("frecuencia", frecuencia);
        db.insert("Medicamentos", null, values);
        db.close();
    }

    public List<Medicamento> obtenerMedicamentos(){
        List<Medicamento> medicamentos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Medicamentos", null);

        if (cursor.moveToFirst()){
            do{
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("_id"));
                @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                @SuppressLint("Range") String dosis = cursor.getString(cursor.getColumnIndex("dosis"));
                @SuppressLint("Range") String hora = cursor.getString(cursor.getColumnIndex("hora"));
                @SuppressLint("Range") int frecuencia = cursor.getInt(cursor.getColumnIndex("frecuencia"));
                // Cree un nuevo objeto Medicamento y asignar los valores del cursor
                Medicamento medicamento = new Medicamento();
                medicamento.setId(id);
                medicamento.setNombre(nombre);
                medicamento.setDosis(dosis);
                medicamento.setHora(hora);
                medicamento.setFreq(frecuencia);
                // Agregar el objeto Medicamento a la lista
                medicamentos.add(medicamento);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return medicamentos;
    }
    // Método para actualizar un medicamento existente
    public void actualizarMedicamento(int id, String nombre, String dosis, String hora, int frecuencia) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("dosis", dosis);
        values.put("hora", hora);
        values.put("frecuencia", frecuencia);
        db.update("Medicamentos", values, "_id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Método para eliminar un medicamento
    public void eliminarMedicamento(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("Medicamentos", "_id=?", new String[]{String.valueOf(id)});
        db.close();
    }
}