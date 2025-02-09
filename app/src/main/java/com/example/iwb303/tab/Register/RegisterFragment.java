package com.example.iwb303.tab.Register;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.iwb303.R;
import com.example.iwb303.ui.dept.AddNewDeptActivity;

import java.util.ArrayList;
import java.util.List;

import Controller.CoursesinSectionsController;
import Controller.DBContext;
import Controller.EnrollmentsController;
import Controller.SectionsController;
import Controller.FillSpinner;
import Controller.btnSounds;
import Models.Enrollment;
import Models.Section;
import Models.ViewModels.CourseInfoVM;

public class RegisterFragment extends Fragment implements View.OnClickListener {

    Button btnRegister;
    MediaPlayer md;
    private Spinner spinnerSections;
    private Spinner spinnerCourses;
    private Spinner spinnerInstructors;
    private Spinner spinnerRoomsNo;
    private DBContext context;
    Section section ;
    CourseInfoVM course;
    CourseInfoVM instructor;
    String roomNo;
    Integer studentId;
    Boolean isMute;


    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register, container, false);
        context = new DBContext(getActivity());
        SharedPreferences settings = getContext().getSharedPreferences("UserInfo", 0);
        studentId =settings.getInt("StudentId", 0) ;
        btnRegister = root.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        spinnerSections = root.findViewById(R.id.spinnerSections);
        spinnerCourses = root.findViewById(R.id.spinnerCourses);
        spinnerInstructors = root.findViewById(R.id.spinnerInstructor);
        spinnerRoomsNo = root.findViewById(R.id.spinnerRoomNo);
        SharedPreferences Sounds = getContext().getSharedPreferences("Sounds", 0);
        isMute= Sounds.getBoolean("Status", false);
        FillSpinner<Section> fillSpinnerSections =new FillSpinner<>(getActivity(),spinnerSections,SectionsController.GetSections(context));
        spinnerSections.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                section = (Section) parent.getSelectedItem();
                List<CourseInfoVM> CSVM =CoursesinSectionsController.GetCoursesinSections(context,studentId, section.getSectionNo());
                if(CSVM.isEmpty())
                {
                    FillSpinner<CourseInfoVM> fillSpinnerInstructors = new FillSpinner<>(getActivity(), spinnerInstructors, new ArrayList<CourseInfoVM>());
                    course = null;
                    instructor=null;
                }
                FillSpinner<CourseInfoVM> fillSpinnerCourses = new FillSpinner<>(getActivity(), spinnerCourses,CSVM);
                spinnerCourses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                         course = (CourseInfoVM) parent.getSelectedItem();
                        List<CourseInfoVM> CSVM =CoursesinSectionsController.GetInstructorsinCoursesinSections(context,course.getSectionNo(), course.getCourseId());
                       if(CSVM.isEmpty()) {
                           FillSpinner<String> fillSpinnerRoomsNo = new FillSpinner<>(getActivity(), spinnerRoomsNo, new ArrayList<String>());
                           instructor = null;
                           roomNo =null;
                       }
                       FillSpinner<CourseInfoVM> fillSpinnerInstructors = new FillSpinner<>(getActivity(),spinnerInstructors,CSVM);
                        spinnerInstructors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                                instructor = (CourseInfoVM) parent.getSelectedItem();
                                List<String> CSVM = CoursesinSectionsController.GetRoomsNo(context,course.getSectionNo(), course.getCourseId(),instructor.getInstructorId());
                                if(CSVM.isEmpty())
                                    roomNo =null;
                                FillSpinner<String> fillSpinnerInstructors = new FillSpinner<>(getActivity(),spinnerRoomsNo,CSVM);
                                spinnerRoomsNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                                        roomNo = (String) parent.getSelectedItem();
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
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return root;
    }
    @Override
    public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btnRegister:
                    btnSounds.SetSounds(getActivity(),isMute, R.raw.tab_move);
                    if(section != null && course != null && instructor!= null && roomNo != null)
                    {
                        Enrollment enrollment = new Enrollment(studentId, section.getSectionNo(), instructor.getInstructorId(), course.getCourseId(),roomNo, 0);
                        if(EnrollmentsController.IsEnrollmentExist(context,enrollment))
                            Toast.makeText(getActivity(), " You are already registered in this  ", Toast.LENGTH_LONG).show();
                        else {
                            EnrollmentsController.AddEnrollment(context, enrollment);
                            Toast.makeText(getActivity(), " Done  ", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(getActivity(), "Error , Please Fill All ComboBox ", Toast.LENGTH_LONG).show();
                    }
                    break;
        }

    }
}