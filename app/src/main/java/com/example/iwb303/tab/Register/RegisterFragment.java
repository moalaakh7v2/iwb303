package com.example.iwb303.tab.Register;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.iwb303.MainActivity;
import com.example.iwb303.R;

import Controller.CoursesinSectionsController;
import Controller.DBContext;
import Controller.EnrollmentsController;
import Controller.SectionsController;
import Controller.StudentsController;
import Models.Course;
import Models.Enrollment;
import Models.LoginInfo;
import Models.Section;
import Models.ViewModels.CourseInfoVM;
import Models.ViewModels.StudentInfoVM;

public class RegisterFragment extends Fragment implements View.OnClickListener {

    Button btnRegister;
    TextView lblSubjects;
    MediaPlayer md;
    private Spinner spinnerSections;
    private Spinner spinnerCources;
    private Spinner spinnerInstructors;
    private DBContext context;
    Section section ;
    CourseInfoVM course;
    CourseInfoVM instructor;
    Integer studentId;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register, container, false);
        context = new DBContext(getActivity());
        SharedPreferences settings = getContext().getSharedPreferences("UserInfo", 0);
        studentId =settings.getInt("StudentId", 0) ;
        btnRegister = root.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        spinnerSections = root.findViewById(R.id.spinnerSections);
        spinnerCources = root.findViewById(R.id.spinnerCourses);
        spinnerInstructors = root.findViewById(R.id.spinnerInstructor);
        ArrayAdapter<Section> adapter = new ArrayAdapter<Section>(getActivity(),
                android.R.layout.simple_spinner_item, SectionsController.GetSections(context));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSections.setAdapter(adapter);
        spinnerSections.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                section = (Section) parent.getSelectedItem();
                ArrayAdapter<CourseInfoVM> adapter = new ArrayAdapter<CourseInfoVM>(getActivity(),
                        android.R.layout.simple_spinner_item, CoursesinSectionsController.GetCoursesinSections(context,studentId, section.getSectionNo()));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCources.setAdapter(adapter);
                spinnerCources.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                         Course = (CourseInfoVM) parent.getSelectedItem();
                        ArrayAdapter<CourseInfoVM> adapter = new ArrayAdapter<CourseInfoVM>(getActivity(),
                                android.R.layout.simple_spinner_item, CoursesinSectionsController.GetInstructorsinCoursesinSections(context,Course.getSectionNo(), Course.getCourseId()));
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerInstructors.setAdapter(adapter);
                        spinnerInstructors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                                Instructors = (CourseInfoVM) parent.getSelectedItem();
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return root;
    }
    @Override
    public void onClick(View v) {
        if(section != null && course != null && instructor!= null) {
            Enrollment enrollment = new Enrollment(studentId, section.getSectionNo(), instructor.getInstructorId(), course.getCourseId(), 0);
            switch (v.getId()) {
                case R.id.btnRegister:
                    md = MediaPlayer.create(getContext(), R.raw.tab_move);
                    md.start();
                    EnrollmentsController.AddEnrollment(context, enrollment);
                    break;

            }
        }
        else {
            Toast.makeText(getActivity(), "Error , Please Fill All ComboBox ", Toast.LENGTH_LONG).show();
        }
    }

}