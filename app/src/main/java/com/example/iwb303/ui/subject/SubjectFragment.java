package com.example.iwb303.ui.subject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.iwb303.R;
import com.example.iwb303.databinding.FragmentSubjectBinding;
import com.example.iwb303.ui.dept.AddNewDeptActivity;
import com.example.iwb303.ui.dept.GetDeptActivity;

import java.util.List;

import Controller.CoursesController;
import Controller.DBContext;
import Controller.btnSounds;
import Models.Course;

public class SubjectFragment extends Fragment implements View.OnClickListener {

    private FragmentSubjectBinding binding;
    Button btnAddNewCourses;
    Button btnEditCourses;
    Button btnAddNewCoursesInSection;
    TextView lblAllCourses;
    Boolean isMute;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSubjectBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        btnEditCourses = (Button) root.findViewById(R.id.btnEditCourses);
        btnEditCourses.setOnClickListener(this);
        btnAddNewCourses = (Button)root.findViewById(R.id.btnAddNewCourses);
        btnAddNewCourses.setOnClickListener(this);
        btnAddNewCoursesInSection = (Button)root.findViewById(R.id.btnAddNewCoursesInSection);
        btnAddNewCoursesInSection.setOnClickListener(this);
        lblAllCourses = root.findViewById(R.id.lblAllCourses);
        StringBuffer buffer=new StringBuffer();
        DBContext context = new DBContext(getActivity());
        List<Course> courses = CoursesController.GetِAllCourses(context);
        for (Course u: courses)
        {
            buffer.append("Id: "+ u.getId() +"\n");
            buffer.append("Title: "+ u.getTitle() +"\n");
            buffer.append("Hours: "+ u.getHours() +"\n\n");
            buffer.append("---------------------------\n");
        }
        lblAllCourses.setText(buffer.toString());
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
            case R.id.btnEditCourses:
                btnSounds.SetSounds(getActivity(),isMute, R.raw.tab_move);
                startActivity(new Intent(getActivity(), GetCourseActivity.class));
                break;
            case R.id.btnAddNewCourses:
                btnSounds.SetSounds(getActivity(),isMute, R.raw.tab_move);
                startActivity(new Intent(getActivity(), AddNewCourseActivity.class));
                break;
            case R.id.btnAddNewCoursesInSection:
                btnSounds.SetSounds(getActivity(),isMute, R.raw.tab_move);
                startActivity(new Intent(getActivity(), AddNewCourseInSectionActivity.class));
                break;
        }
    }
}