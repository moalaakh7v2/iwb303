package com.example.iwb303.tab.Settings;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.iwb303.ChooseTheme;
import com.example.iwb303.MainActivity;
import com.example.iwb303.ManageStudent;
import com.example.iwb303.R;

public class SettingsFragment extends Fragment implements View.OnClickListener {

    private SettingsViewModel settingsViewModel;
    Button btnDisplayTheme;
    Button btnSoundMode;
    Button btnLogOut;
    MediaPlayer md;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        btnDisplayTheme = root.findViewById(R.id.btnDisplayTheme);
        btnDisplayTheme.setOnClickListener(this);
        btnSoundMode = root.findViewById(R.id.btnSoundMode);
        btnSoundMode.setOnClickListener(this);
        btnLogOut = root.findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDisplayTheme:
                md = MediaPlayer.create(getContext(), R.raw.tab_move);
                md.start();
                startActivity(new Intent(getActivity(), ChooseTheme.class));
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