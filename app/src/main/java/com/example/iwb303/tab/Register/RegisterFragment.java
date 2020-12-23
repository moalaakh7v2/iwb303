package com.example.iwb303.tab.Register;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.iwb303.MainActivity;
import com.example.iwb303.R;

import Controller.DBContext;
import Controller.SectionsController;
import Controller.StudentsController;
import Models.LoginInfo;
import Models.Section;
import Models.ViewModels.StudentInfoVM;

public class RegisterFragment extends Fragment implements View.OnClickListener {

    Button btnRegister;
    TextView lblSubjects;
    MediaPlayer md;
    private Spinner spinner;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register, container, false);
        btnRegister = root.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        spinner = root.findViewById(R.id.spinner);
        ArrayAdapter<Section> adapter = new ArrayAdapter<Section>(getActivity(),
                android.R.layout.simple_spinner_item, SectionsController.GetSections(new DBContext(getActivity())));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Section section = (Section) parent.getSelectedItem();
                Toast.makeText(getActivity() ,section.getSectionNo() + " - " + section.getSectionName() , Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return root;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                md = MediaPlayer.create(getContext(), R.raw.tab_move);
                md.start();
                break;
        }
    }

}