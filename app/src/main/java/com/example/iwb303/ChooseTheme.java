package com.example.iwb303;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import com.example.iwb303.ui.dept.AddNewDeptActivity;

import Controller.btnSounds;

public class ChooseTheme extends AppCompatActivity {

    Spinner spinner;
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
        setContentView(R.layout.activity_choose_theme);
        spinner = (Spinner) findViewById(R.id.drpTeacherGender);
        SharedPreferences Sounds = getSharedPreferences("Sounds", 0);
        isMute= Sounds.getBoolean("Status", false);

    }

    public void btnApplay(View view) {
        btnSounds.SetSounds(ChooseTheme.this,isMute, R.raw.tab_move);
        String text = spinner.getSelectedItem().toString();
        SharedPreferences prefs = getSharedPreferences("themeFile", MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString("themeName", text);
        prefsEditor.commit();
        System.exit(0);
    }
}