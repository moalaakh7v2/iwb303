package com.example.iwb303.ui.student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.iwb303.R;
import com.example.iwb303.databinding.FragmentStudentBinding;

import java.util.ArrayList;
import java.util.List;

public class StudentFragment extends Fragment {

    private FragmentStudentBinding binding;
    ListView lv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentStudentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        lv = (ListView)root.findViewById(R.id.lstDisplayStudents);
        List<String> your_array_list = new ArrayList<String>();
        your_array_list.add("Alaa");
        your_array_list.add("KH");
        your_array_list.add("7");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(root.getContext(),
                 android.R.layout.simple_list_item_1, your_array_list );
        lv.setAdapter(arrayAdapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}