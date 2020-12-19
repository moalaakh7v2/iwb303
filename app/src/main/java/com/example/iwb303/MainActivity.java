package com.example.iwb303;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.media.RatingCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iwb303.tab.MySubjects.MySubjectsFragment;

import Controller.DBContext;
import Controller.StudentsController;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    EditText txtUserName;
    MediaPlayer md;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("themeFile", MODE_PRIVATE);
        String themeName = prefs.getString("themeName", "Blue");
        switch (themeName) {
            case "Blue":
                setTheme(R.style.AppTheme1);
                break;
            case "Purple":
                setTheme(R.style.AppTheme2);
                break;
            case "Pink":
                setTheme(R.style.AppTheme3);
                break;
            case "Pastel":
                setTheme(R.style.AppTheme4);
                break;
            case "Green":
                setTheme(R.style.AppTheme5);
                break;
        }
        setContentView(R.layout.activity_main);
        SharedPreferences settings = getSharedPreferences("UserInfo", 0);
        if (settings.getString("Username", "") != "")
        {
            startActivity(new Intent(MainActivity.this, ManageAdminActivity.class));
        }
        btnLogin = findViewById(R.id.btnLogin);
        txtUserName = findViewById(R.id.txtUserName);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void btnLogIn_Click(View view){
        md = MediaPlayer.create(this, R.raw.btn_sound);
        md.start();
        DBContext context = new DBContext(MainActivity.this);
        TextView txtUsername = findViewById(R.id.txtUserName);
        TextView txtPassword = findViewById(R.id.txtPassword);
        if(!txtUsername.getText().toString().isEmpty() && !txtPassword.getText().toString().isEmpty()) {
            if (null != StudentsController.Login(context,txtUsername.getText().toString(), txtPassword.getText().toString())) {
                SharedPreferences settings = getSharedPreferences("UserInfo", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("Username",txtUsername.getText().toString());
                editor.commit();
                startActivity(new Intent(MainActivity.this, ManageAdminActivity.class));
            } else {
                Toast.makeText(MainActivity.this, "UserName Or password Worng !", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(MainActivity.this, "UserName Or password is Empty !", Toast.LENGTH_LONG).show();
        }
    }
}
