package com.example.cards1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class SignUp extends AppCompatActivity {

  private  Button btnSignUp;
    ImageButton btnfacebook,btnwhatsapp,btninstagram;
    EditText edtemail;
    EditText edtpassword;
    EditText edtconfpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        btnSignUp= findViewById(R.id.btnsignup);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });
    }
    }
