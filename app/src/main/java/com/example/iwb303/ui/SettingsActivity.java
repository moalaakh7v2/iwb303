package com.example.iwb303.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.iwb303.ChooseTheme;
import com.example.iwb303.MainActivity;
import com.example.iwb303.ManageAdminActivity;
import com.example.iwb303.R;

import Controller.btnSounds;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDisplayAdminTheme;
    Button btnSoundAdminMode;
    Button btnAdminLogOut;
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
        setContentView(R.layout.activity_settings);
        btnDisplayAdminTheme = findViewById(R.id.btnDisplayAdminTheme);
        btnDisplayAdminTheme.setOnClickListener(this);
        btnSoundAdminMode = findViewById(R.id.btnSoundAdminMode);
        btnSoundAdminMode.setOnClickListener(this);
        btnAdminLogOut = findViewById(R.id.btnAdminLogOut);
        btnAdminLogOut.setOnClickListener(this);
        SharedPreferences Sounds = getSharedPreferences("Sounds", 0);
        isMute= Sounds.getBoolean("Status", false);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDisplayAdminTheme:
                btnSounds.SetSounds(SettingsActivity.this,isMute, R.raw.tab_move);
                startActivity(new Intent(this, ChooseTheme.class));
                break;
            case R.id.btnSoundAdminMode:
                SharedPreferences Sounds = getSharedPreferences("Sounds", 0);
                SharedPreferences.Editor editor = Sounds.edit();
                isMute = !isMute;
                editor.putBoolean("Status",isMute);
                editor.commit();
                btnSounds.SetSounds(SettingsActivity.this,isMute, R.raw.tab_move);
                break;
            case R.id.btnAdminLogOut:
                btnSounds.SetSounds(SettingsActivity.this,isMute, R.raw.tab_move);
                SharedPreferences settings = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
                settings.edit().clear().commit();
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}