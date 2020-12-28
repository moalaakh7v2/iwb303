package com.example.iwb303.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.iwb303.MainActivity;
import com.example.iwb303.R;
import com.example.iwb303.databinding.FragmentHomeBinding;
import com.example.iwb303.ui.dept.AddNewDeptActivity;
import com.example.iwb303.ui.dept.GetDeptActivity;
import com.example.iwb303.ui.subject.AddNewCourseInSectionActivity;

import Controller.CoursesController;
import Controller.CoursesinSectionsController;
import Controller.DBContext;
import Controller.InstructorsController;
import Controller.SectionsController;
import Models.Course;
import Models.Instructor;
import Models.Section;

public class SearchFragment extends Fragment implements View.OnClickListener {

    private FragmentHomeBinding binding;
    View root;
    Button btnGetStudents,btnGetTeacher,btnGetCourse,btnGetSection;
    DBContext context;
    Spinner spinnerSections,spinnerTeacher,spinnerCourse;
    Section section;
    Course course;
    Instructor instructor;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        btnGetStudents = (Button) root.findViewById(R.id.btnGetStudents);
        btnGetStudents.setOnClickListener(this);
        btnGetTeacher = (Button) root.findViewById(R.id.btnGetTeacher);
        btnGetTeacher.setOnClickListener(this);
        btnGetCourse = (Button) root.findViewById(R.id.btnGetCourse);
        btnGetCourse.setOnClickListener(this);
        btnGetSection = (Button) root.findViewById(R.id.btnGetSection);
        btnGetSection.setOnClickListener(this);
        spinnerSections = (Spinner) root.findViewById(R.id.spinnerSection);
        spinnerTeacher = (Spinner) root.findViewById(R.id.spinnerTeacher);
        spinnerCourse = (Spinner) root.findViewById(R.id.spinnerCourse);
        context = new DBContext(getActivity());
        ArrayAdapter<Section> sectionAdapter = new ArrayAdapter<Section>(getActivity(),
                android.R.layout.simple_spinner_item, SectionsController.GetSections(context));
        sectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSections.setAdapter(sectionAdapter);
        spinnerSections.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                section = (Section) spinnerSections.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
        ArrayAdapter<Course> courseAdapter = new ArrayAdapter<Course>(getActivity(),
                android.R.layout.simple_spinner_item, CoursesController.GetِAllCourses(context));
        sectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse.setAdapter(courseAdapter);
        spinnerCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                course = (Course) spinnerCourse.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
        ArrayAdapter<Instructor> instructorAdapter = new ArrayAdapter<Instructor>(getActivity(),
                android.R.layout.simple_spinner_item, InstructorsController.GetInstructors(context));
        instructorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTeacher.setAdapter(instructorAdapter);
        spinnerTeacher.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                instructor= (Instructor) spinnerTeacher.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
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
            case R.id.btnGetTeacher:

                if (instructor == null){
                    Toast.makeText(getActivity(), "Chose Teacher From Combo", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent i1 = new Intent(getActivity(),GetResultActivity.class);
                i1.putExtra("Id", instructor.getId());
                i1.putExtra("type", "teacher");
                startActivity(i1);
                break;
            case R.id.btnGetCourse:
                if (course == null){
                    Toast.makeText(getActivity(), "Chose Course From Combo", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent i2 = new Intent(getActivity(),GetResultActivity.class);
                i2.putExtra("Id", course.getId());
                i2.putExtra("type", "course");
                startActivity(i2);
                break;
            case R.id.btnGetSection:
                if (section == null){
                    Toast.makeText(getActivity(), "Chose Section From Combo", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent i3 = new Intent(getActivity(),GetResultActivity.class);
                i3.putExtra("Id", section.getSectionNo());
                i3.putExtra("type", "section");
                startActivity(i3);
                break;
        }
    }
}