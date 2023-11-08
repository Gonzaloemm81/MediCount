package com.example.cards1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Patterns;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String MedsDB = "MedicamentosDB";
    private static final int DATABASE_VERSION = 3;
    private Medicamento medicamento;


    public DatabaseHelper(Context context) {
        super(context, MedsDB, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Medicamentos (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, dosis TEXT, hora TEXT, frecuencia INTEGER);");
        db.execSQL("CREATE TABLE Usuarios (_id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, password TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Medicamentos");
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
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
    public void registrarUsuario(String email, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        db.insert("Usuarios", null, values);
        db.close();
    }

    public boolean validarCredencialesUsuario(String email, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Usuarios WHERE email=? AND password=?", new String[]{email, password});
        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isValid;
    }
    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private boolean isValidPassword(String password) {
        // La contraseña debe tener al menos 8 caracteres, incluyendo al menos una letra mayúscula,
        // una letra minúscula, un número y un carácter especial.
        //String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.])[A-Za-z\\d@$!%*?&.]{8,}$";
        return password.matches(passwordPattern);
    }
    public boolean isValidInput(String email, String password, String confirmPassword) {
        return isValidEmail(email) && isValidPassword(password.trim()) && password.trim().equals(confirmPassword.trim());
    }

}