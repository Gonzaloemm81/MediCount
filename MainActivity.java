package com.example.medicalacount;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    Button signup;
    ImageButton btnfacebook,btnwhatsapp,btninstagram;
    EditText edtemail;
    EditText edtpassword;
    EditText edtconfpassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}