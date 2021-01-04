package com.example.iwb303.tab.Settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.iwb303.ChooseTheme;
import com.example.iwb303.MainActivity;
import com.example.iwb303.R;

import Controller.btnSounds;

public class SettingsFragment extends Fragment implements View.OnClickListener {

    Button btnDisplayTheme;
    Button btnSoundMode;
    Button btnLogOut;
    View root;
    Boolean isMute;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_settings, container, false);
        btnDisplayTheme = root.findViewById(R.id.btnDisplayAdminTheme);
        btnDisplayTheme.setOnClickListener(this);
        btnSoundMode = root.findViewById(R.id.btnSoundAdminMode);
        btnSoundMode.setOnClickListener(this);
        btnLogOut = root.findViewById(R.id.btnAdminLogOut);
        btnLogOut.setOnClickListener(this);
        SharedPreferences Sounds = getContext().getSharedPreferences("Sounds", 0);
        isMute= Sounds.getBoolean("Status", false);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDisplayAdminTheme:
                btnSounds.SetSounds(getActivity(),isMute, R.raw.tab_move);
                startActivity(new Intent(getActivity(), ChooseTheme.class));
                break;
            case R.id.btnSoundAdminMode:
                SharedPreferences Sounds = getContext().getSharedPreferences("Sounds", 0);
                SharedPreferences.Editor editor = Sounds.edit();
                isMute = !isMute;
                editor.putBoolean("Status",isMute);
                editor.commit();
                btnSounds.SetSounds(getActivity(),isMute, R.raw.tab_move);
                break;
            case R.id.btnAdminLogOut:
                btnSounds.SetSounds(getActivity(),isMute, R.raw.tab_move);
                SharedPreferences settings = getContext().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
                settings.edit().clear().commit();
                getActivity().finish();
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
        }
    }
}