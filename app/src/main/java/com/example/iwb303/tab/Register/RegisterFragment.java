package com.example.iwb303.tab.Register;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.iwb303.MainActivity;
import com.example.iwb303.ManageStudent;
import com.example.iwb303.R;

public class RegisterFragment extends Fragment implements View.OnClickListener {

    private RegisterViewModel registerViewModel;
    Button btnRegister;
    MediaPlayer md;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        View root = inflater.inflate(R.layout.fragment_register, container, false);
        btnRegister = root.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        return root;
    }
    @Override
    public void onClick(View v) {
        Log.d("MyError",v.getId() + " hi " + R.id.btnRegister);
        switch (v.getId()) {
            case R.id.btnRegister:
                md = MediaPlayer.create(getContext(), R.raw.tab_move);
                md.start();
                break;
        }
    }

}