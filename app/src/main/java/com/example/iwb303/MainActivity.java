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
import Controller.btnSounds;
import Models.LoginInfo;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    EditText txtUserName;
    MediaPlayer md;
    Boolean isMute;
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
        if (settings.getString("Status", "notUser").equals("Student"))
        {
            startActivity(new Intent(MainActivity.this, ManageStudent.class));
        }
        if (settings.getString("Status", "notUser").equals("Admin"))
        {
            startActivity(new Intent(MainActivity.this, ManageAdminActivity.class));
        }
        btnLogin = findViewById(R.id.btnLogin);
        txtUserName = findViewById(R.id.txtUserName);
        SharedPreferences Sounds = getSharedPreferences("Sounds", 0);
        isMute= Sounds.getBoolean("Status", false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void btnLogIn_Click(View view){
        btnSounds.SetSounds(MainActivity.this,isMute, R.raw.btn_sound);
        DBContext context = new DBContext(MainActivity.this);
        TextView txtUsername = findViewById(R.id.txtUserName);
        TextView txtPassword = findViewById(R.id.txtPassword);
        if(!txtUsername.getText().toString().isEmpty() && !txtPassword.getText().toString().isEmpty()) {
           LoginInfo loginInfo = StudentsController.Login(context,txtUsername.getText().toString(), txtPassword.getText().toString());
            if (loginInfo != null) {
                SharedPreferences settings = getSharedPreferences("UserInfo", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("Id",loginInfo.getId());
                if (loginInfo.getStudentId() != null)
                    editor.putInt("StudentId",loginInfo.getStudentId());
                editor.putString("Username",loginInfo.getUsername());

                if(loginInfo.getStudentId() != 0)
                {
                    editor.putString("Status","Student");
                    editor.commit();
                    finish();
                    startActivity(new Intent(MainActivity.this, ManageStudent.class));
                }
                else
                {
                    editor.putString("Status","Admin");
                    editor.commit();
                    finish();
                    startActivity(new Intent(MainActivity.this, ManageAdminActivity.class));
                }
                finish();
                moveTaskToBack(false);
            } else {
                Toast.makeText(MainActivity.this, "UserName Or password Worng !", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(MainActivity.this, "UserName Or password is Empty !", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
        System.exit(0);
    }
}
