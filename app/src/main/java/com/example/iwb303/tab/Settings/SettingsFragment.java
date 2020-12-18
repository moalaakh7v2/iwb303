package com.example.iwb303.tab.Settings;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.iwb303.R;

public class SettingsFragment extends Fragment implements View.OnClickListener {

    private SettingsViewModel settingsViewModel;
    Button btnDisplayColor;
    Button btnSoundMode;
    Button btnLogOut;
    MediaPlayer md;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        btnDisplayColor = root.findViewById(R.id.btnDisplayColor);
        btnDisplayColor.setOnClickListener(this);
        btnSoundMode = root.findViewById(R.id.btnSoundMode);
        btnSoundMode.setOnClickListener(this);
        btnLogOut = root.findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDisplayColor:
                md = MediaPlayer.create(getContext(), R.raw.tab_move);
                md.start();
                break;
            case R.id.btnSoundMode:
                md = MediaPlayer.create(getContext(), R.raw.tab_move);
                md.start();
                break;
            case R.id.btnLogOut:
                md = MediaPlayer.create(getContext(), R.raw.tab_move);
                md.start();
                break;
        }
    }
}