package com.example.project_akhir;

import androidx.appcompat.app.AppCompatActivity;
import  android.app.Instrumentation;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Button btnLogin, btnRegister;
    EditText ednama, edpassword;
    String nama, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegister = findViewById(R.id.bRegister);
        btnLogin = findViewById(R.id.bSignIn);
        ednama = findViewById(R.id.Nama);
        edpassword = findViewById(R.id.Password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama = ednama.getText().toString();
                password = edpassword.getText().toString();

                String nama = "Akbar";
                String password ="ModeSantuy";
                if (nama.equals(nama) && password.equals(password)) {
                    Toast toast = Toast.makeText(getApplicationContext(),"Login Sukses", Toast.LENGTH_LONG);
                    toast.show();

                    Bundle b =new Bundle();
                    b.putString("a", nama.trim());
                    b.putString("b", password.trim());

                    Intent i = new Intent(getApplicationContext(), Activity_Dua.class);
                    i.putExtras(b);
                    startActivity(i);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),"Login Gagal", Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });

    }
}