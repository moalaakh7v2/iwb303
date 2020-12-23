package com.example.iwb303.tab.Settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class SettingsFragment extends Fragment implements View.OnClickListener {

    Button btnDisplayTheme;
    Button btnSoundMode;
    Button btnLogOut;
    MediaPlayer md;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_settings, container, false);
        btnDisplayTheme = root.findViewById(R.id.btnDisplayAdminTheme);
        btnDisplayTheme.setOnClickListener(this);
        btnSoundMode = root.findViewById(R.id.btnSoundAdminMode);
        btnSoundMode.setOnClickListener(this);
        btnLogOut = root.findViewById(R.id.btnAdminLogOut);
        btnLogOut.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDisplayAdminTheme:
                md = MediaPlayer.create(getContext(), R.raw.tab_move);
                md.start();
                startActivity(new Intent(getActivity(), ChooseTheme.class));
                break;
            case R.id.btnSoundAdminMode:
                md = MediaPlayer.create(getContext(), R.raw.tab_move);
                md.start();
                break;
            case R.id.btnAdminLogOut:
                md = MediaPlayer.create(getActivity(), R.raw.tab_move);
                md.start();
                SharedPreferences settings = getContext().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
                settings.edit().clear().commit();
                getActivity().finish();
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
        }
    }
}