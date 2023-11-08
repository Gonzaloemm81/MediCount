package com.example.cards1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

  private  Button btnSignUp;
    private DatabaseHelper databaseHelper;
    ImageButton btnfacebook,btnwhatsapp,btninstagram;
    private EditText edtemail;
    private EditText edtpassword;
    private EditText edtconfpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        databaseHelper = new DatabaseHelper(this);
        btnSignUp= findViewById(R.id.btnsignup);
        edtemail = findViewById(R.id.edtemail);
        edtpassword = findViewById(R.id.edtpassword);
        edtconfpassword = findViewById(R.id.edtconfpassword);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtemail.getText().toString().trim();
                String password = edtpassword.getText().toString().trim();
                String confpassword = edtconfpassword.getText().toString().trim();
                // validar los campos de entrada, si son válidos, registra el usuario
                if (databaseHelper.isValidInput(email, password, confpassword)){
                    // registra al nuevo usuario en la base de datos
                    databaseHelper.registrarUsuario(email, password);
                    // muestra un mensaje o realiza la acción adecuada después del registro
                    Toast.makeText(SignUp.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    // navega a la pantalla de inicio de sesión después del registro
                    Intent intent = new Intent(SignUp.this, Login.class);
                    startActivity(intent);
                    finish(); // Cierra la actividad actual para evitar volver atrás al presionar el botón de retroceso
                } else {
                    // muestra un mensaje de error si los campos de entrada no son válidos
                    Toast.makeText(SignUp.this, "Campos de entrada no válidos o las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    }
