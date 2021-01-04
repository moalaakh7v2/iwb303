package com.example.iwb303.ui.dept;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.iwb303.R;
import com.example.iwb303.databinding.FragmentDeptBinding;

import java.util.List;

import Controller.DBContext;
import Controller.SectionsController;
import Controller.btnSounds;
import Models.Section;

public class DeptFragment extends Fragment implements View.OnClickListener {

    private FragmentDeptBinding binding;
    View root;
    Button btnEditDept;
    Button btnAddDept;
    TextView lblAllDepts;
    Boolean isMute;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDeptBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        btnEditDept = (Button) root.findViewById(R.id.btnEditDept);
        btnEditDept.setOnClickListener(this);
        btnAddDept = (Button) root.findViewById(R.id.btnAddDept);
        btnAddDept.setOnClickListener(this);
        lblAllDepts = root.findViewById(R.id.lblAllDepts);
        StringBuffer buffer=new StringBuffer();
        DBContext context = new DBContext(getActivity());
        List<Section> sections = SectionsController.GetSections(context);
        for (Section u: sections)
        {
            buffer.append("Id: "+ u.getSectionNo() +"\n");
            buffer.append("Name: "+ u.getSectionName() +"\n\n");
            buffer.append("---------------------------\n");
        }
        lblAllDepts.setText(buffer.toString());
        SharedPreferences Sounds = getContext().getSharedPreferences("Sounds", 0);
        isMute = Sounds.getBoolean("Status", false);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEditDept:
                btnSounds.SetSounds(getActivity(),isMute, R.raw.tab_move);
                startActivity(new Intent(getActivity(), GetDeptActivity.class));
                break;
            case R.id.btnAddDept:
                btnSounds.SetSounds(getActivity(),isMute, R.raw.tab_move);
                startActivity(new Intent(getActivity(), AddNewDeptActivity.class));
                break;
        }
    }
}