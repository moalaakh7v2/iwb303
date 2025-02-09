package com.example.iwb303.tab.MySubjects;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.iwb303.R;

import java.util.List;

import Controller.DBContext;
import Controller.EnrollmentsController;
import Controller.StudentsController;
import Models.ViewModels.StudentInfoVM;
import Models.ViewModels.StudentsEnrollmentInfoVM;

public class MySubjectsFragment extends Fragment {

    TextView lblUname,lblSubjects;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my_subjects, container, false);
        lblUname = root.findViewById(R.id.lblUname);
        lblSubjects = root.findViewById(R.id.lblSubjects);
        SharedPreferences settings = getActivity().getSharedPreferences("UserInfo", 0);
        lblUname.setText("Welcome " + settings.getString("Username", ""));
        Integer studentId = settings.getInt("StudentId",0);
        StringBuffer buffer=new StringBuffer();
        DBContext context = new DBContext(getActivity());
        List<StudentsEnrollmentInfoVM> studentsEnrollmentInfoVM = EnrollmentsController.GetStudentEnrollments(context,studentId);
        for (StudentsEnrollmentInfoVM u: studentsEnrollmentInfoVM)
        {
            buffer.append("Section Name: "+ u.getSectionName() +"\n");
            buffer.append("Course Title: "+ u.getCourseTitle() +"\n");
            buffer.append("Teacher Name: "+ u.getInstructorName() +"\n");
            buffer.append("---------------------------\n");
        }
        lblSubjects.setText(buffer);
        return root;
    }

}