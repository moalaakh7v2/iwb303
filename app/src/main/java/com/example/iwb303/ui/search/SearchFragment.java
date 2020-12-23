package com.example.iwb303.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.iwb303.R;
import com.example.iwb303.databinding.FragmentHomeBinding;
import com.example.iwb303.ui.dept.AddNewDeptActivity;
import com.example.iwb303.ui.dept.GetDeptActivity;

public class SearchFragment extends Fragment implements View.OnClickListener {

    private FragmentHomeBinding binding;
    View root;
    Button btnGetStudents,btnCustomSearch,btnGetTeacher,btnGetCourse,btnGetSection;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        btnGetStudents = (Button) root.findViewById(R.id.btnGetStudents);
        btnGetStudents.setOnClickListener(this);
        btnCustomSearch = (Button) root.findViewById(R.id.btnCustomSearch);
        btnCustomSearch.setOnClickListener(this);
        btnGetTeacher = (Button) root.findViewById(R.id.btnGetTeacher);
        btnGetTeacher.setOnClickListener(this);
        btnGetCourse = (Button) root.findViewById(R.id.btnGetCourse);
        btnGetCourse.setOnClickListener(this);
        btnGetSection = (Button) root.findViewById(R.id.btnGetSection);
        btnGetSection.setOnClickListener(this);
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
            case R.id.btnGetStudents:
                startActivity(new Intent(getActivity(), RaviewAllStudentsActivity.class));
                break;
            case R.id.btnCustomSearch:
                //startActivity(new Intent(getActivity(), AddNewDeptActivity.class));
                break;
            case R.id.btnGetTeacher:
                EditText txtTeacheId = root.findViewById(R.id.txtTeacheId);
                int teacherId = Integer.parseInt(txtTeacheId.getText().toString());
                Intent i1 = new Intent(getActivity(),GetResultActivity.class);
                i1.putExtra("Id", teacherId);
                i1.putExtra("type", "teacher");
                startActivity(i1);
                break;
            case R.id.btnGetCourse:
                EditText txtGetCourseId = root.findViewById(R.id.txtGetCourseId);
                int CourseId = Integer.parseInt(txtGetCourseId.getText().toString());
                Intent i2 = new Intent(getActivity(),GetResultActivity.class);
                i2.putExtra("Id", CourseId);
                i2.putExtra("type", "course");
                startActivity(i2);
                break;
            case R.id.btnGetSection:
                EditText txtGetSectionId = root.findViewById(R.id.txtGetSectionId);
                int SectionId = Integer.parseInt(txtGetSectionId.getText().toString());
                Intent i3 = new Intent(getActivity(),GetResultActivity.class);
                i3.putExtra("Id", SectionId);
                i3.putExtra("type", "section");
                startActivity(i3);
                break;
        }
    }
}