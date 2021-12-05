package com.example.capstoneprojectpb;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    EditText TxUsername, TxPassword;
    Button BtnLogin;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TxUsername = (EditText)findViewById(R.id.txUsername);
        TxPassword = (EditText)findViewById(R.id.txPassword);
        BtnLogin = (Button)findViewById(R.id.btnLogin);

        dbHelper = new DBHelper(this);

        TextView tvCreateAccount = (TextView)findViewById(R.id.tvCreateAccount);

        tvCreateAccount.setText(fromHtml("Belum memiliki akun? "));

        TextView tvCreateAccount2 = (TextView)findViewById(R.id.tvCreateAccount2);

        tvCreateAccount2.setText(fromHtml("</font><font color='#3b5998'>Daftar sekarang!</font>"));
        tvCreateAccount2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = TxUsername.getText().toString().trim();
                String password = TxPassword.getText().toString().trim();

                Boolean res = dbHelper.checkUser(username,password);
                if(res == true){
                    Toast.makeText(Login.this, "Login Berhasil!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, MainActivity.class));
                }else {
                    Toast.makeText(Login.this, "Login Gagal!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static Spanned fromHtml(String html){
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        }else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}

