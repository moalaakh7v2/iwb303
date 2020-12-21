package com.example.iwb303.ui.student;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.iwb303.R;
import com.example.iwb303.databinding.FragmentStudentBinding;

import java.util.List;

import Controller.DBContext;
import Controller.StudentsController;
import Models.ViewModels.StudentInfoVM;

public class StudentFragment extends Fragment implements View.OnClickListener {

    private FragmentStudentBinding binding;
    View root;
    Button btnEditStudent;
    Button btnAddNewStudent;
    Spinner drpAllStudent;
    GridView grdDisplayStudents;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentStudentBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        btnEditStudent = (Button) root.findViewById(R.id.btnEditStudent);
        btnEditStudent.setOnClickListener(this);
        btnAddNewStudent = (Button) root.findViewById(R.id.btnAddNewStudent);
        btnAddNewStudent.setOnClickListener(this);
        grdDisplayStudents = root.findViewById(R.id.grdDisplayStudents);
        DBContext context =new DBContext(getActivity());
        List<StudentInfoVM> studentsInfo = StudentsController.getStudents(context);
        ArrayAdapter<StudentInfoVM> adapter = new ArrayAdapter<StudentInfoVM>(
                getActivity(), android.R.layout.simple_spinner_item, studentsInfo);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        grdDisplayStudents.setAdapter(adapter);
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
                startActivity(new Intent(getActivity(), GetStudentActivity.class));
                break;
            case R.id.btnAddNewStudent:
                startActivity(new Intent(getActivity(), AddNewStudentActivity.class));
                break;
        }
    }
}