package com.example.iwb303.ui.student;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.iwb303.R;
import com.example.iwb303.databinding.FragmentStudentBinding;

import java.util.ArrayList;
import java.util.List;

public class StudentFragment extends Fragment implements View.OnClickListener {

    private FragmentStudentBinding binding;
    View root;
    Button btnEditStudent;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentStudentBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        btnEditStudent = (Button) root.findViewById(R.id.btnEditStudent);
        btnEditStudent.setOnClickListener(this);
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
        }
    }
}