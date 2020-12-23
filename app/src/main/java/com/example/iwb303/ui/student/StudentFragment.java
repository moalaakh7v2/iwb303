package com.example.iwb303.ui.student;
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
    TextView lblAllStudents;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentStudentBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        btnEditStudent = (Button) root.findViewById(R.id.btnEditStudent);
        btnEditStudent.setOnClickListener(this);
        btnAddNewStudent = (Button) root.findViewById(R.id.btnAddNewStudent);
        btnAddNewStudent.setOnClickListener(this);
        lblAllStudents = root.findViewById(R.id.lblAllTeachers);
        StringBuffer buffer=new StringBuffer();
        DBContext context = new DBContext(getActivity());
        List<StudentInfoVM> studentInfoVMS = StudentsController.getStudents(context);
        for (StudentInfoVM u: studentInfoVMS)
        {
            buffer.append("Id: "+ u.getId() +"\n");
            buffer.append("UserName: "+ u.getUsername() +"\n");
            buffer.append("RegYear: "+ u.getMobileNo() +"\n");
            buffer.append("Mobile: "+ u.getMobileNo() +"\n\n");
            buffer.append("---------------------------\n");
        }
        lblAllStudents.setText(buffer.toString());

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