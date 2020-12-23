package com.example.iwb303.ui.subject;

import android.content.Intent;
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

public class SubjectFragment extends Fragment implements View.OnClickListener {

    private FragmentSubjectBinding binding;
    Button btnMngCourse;
    Button btnMngCourseInSection;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSubjectBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        btnMngCourse = (Button) root.findViewById(R.id.btnMngCourse);
        btnMngCourse.setOnClickListener(this);
        btnMngCourseInSection = (Button) root.findViewById(R.id.btnMngCourseInSection);
        btnMngCourseInSection.setOnClickListener(this);
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
            case R.id.btnMngCourse:
                startActivity(new Intent(getActivity(), GetDeptActivity.class));
                break;
            case R.id.btnMngCourseInSection:
                startActivity(new Intent(getActivity(), AddNewDeptActivity.class));
                break;
        }
    }
}