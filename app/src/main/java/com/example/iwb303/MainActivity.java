package com.example.iwb303;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    EditText txtUserName;
    MediaPlayer md;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        String s = prefs.getString("name","");
        if (!TextUtils.isEmpty(s)){
            startActivity(new Intent(MainActivity.this, ManageStudent.class));
        }
        btnLogin = findViewById(R.id.btnLogin);
        txtUserName = findViewById(R.id.txtUserName);
    }

    public void btnLogIn_Click(View view){
        md = MediaPlayer.create(this, R.raw.btn_sound);
        md.start();
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString("name", txtUserName.getText().toString());
        prefsEditor.apply(); // or commit();
        startActivity(new Intent(MainActivity.this, ManageStudent.class));
    }
}