package com.example.bingmp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class broaddingScreen extends AppCompatActivity {

    ImageView imgBack;
    EditText edtAccount, edtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadding_screen);
        mapping();

        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // trước khi người dùng nhập

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // người dùng đang nhập

            }

            @Override
            public void afterTextChanged(Editable s) {
                // sau khi người dùng nhập xong
                String inputPassword = edtPassword.getText().toString().trim();

                if (inputPassword.length() < 6){
                    edtPassword.setError("Xin mời nhập mật khẩu trên 6 kí tự");
                }
                else{
                    edtPassword.setError(null);
                }

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = "0989701185";
                String pass  = "hoangduyen";
                String inputAccount= edtAccount.getText().toString().trim();
                String inputPassword = edtPassword.getText().toString().trim();
                if (inputPassword.toString().equals(pass) && inputAccount.toString().equals(account)){
                    Intent intent = new Intent(broaddingScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(broaddingScreen.this, "Xin mời nhập lại", Toast.LENGTH_SHORT).show();
                }

            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(broaddingScreen.this, flashScreen.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void mapping() {
        imgBack = findViewById(R.id.imBack);
        edtAccount = findViewById(R.id.edtAccount);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }
}