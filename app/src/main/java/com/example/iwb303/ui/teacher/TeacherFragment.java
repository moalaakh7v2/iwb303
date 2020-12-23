package com.example.iwb303.ui.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.iwb303.R;
import com.example.iwb303.databinding.FragmentTeacherBinding;

import java.util.List;

import Controller.DBContext;
import Controller.InstructorsController;
import Models.Instructor;

public class TeacherFragment extends Fragment implements View.OnClickListener {

    private FragmentTeacherBinding binding;
    View root;
    Button btnEditTeacher;
    Button btnAddNewTeacher;
    TextView lblAllTeachers;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTeacherBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        btnEditTeacher = (Button) root.findViewById(R.id.btnEditStudent);
        btnEditTeacher.setOnClickListener(this);
        btnAddNewTeacher = (Button) root.findViewById(R.id.btnAddNewStudent);
        btnAddNewTeacher.setOnClickListener(this);
        lblAllTeachers = root.findViewById(R.id.lblAllTeachers);
        StringBuffer buffer=new StringBuffer();
        DBContext context = new DBContext(getActivity());
        List<Instructor> instructors = InstructorsController.GetInstructors(context);
        for (Instructor u: instructors)
        {
            buffer.append("Id: "+ u.getId() +"\n");
            buffer.append("Firstname: "+ u.getFirstname() +"\n");
            buffer.append("Lastname: "+ u.getLastname() +"\n");
            buffer.append("Gender: "+ u.getGender() +"\n");
            buffer.append("Address: "+ u.getAddress() +"\n");
            buffer.append("Mobile: "+ u.getMobileNo() +"\n\n");
            buffer.append("---------------------------\n");
        }
        lblAllTeachers.setText(buffer.toString());
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
            case R.id.btnEditStudent:
                startActivity(new Intent(getActivity(), GetTeacherActivity.class));
                break;
            case R.id.btnAddNewStudent:
                startActivity(new Intent(getActivity(), AddNewTeacherActivity.class));
                break;
        }
    }
}