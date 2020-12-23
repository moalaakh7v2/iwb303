package com.example.iwb303.ui.search;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.iwb303.R;

import java.util.List;

import Controller.DBContext;
import Controller.EnrollmentsController;
import Controller.SectionsController;
import Models.Section;
import Models.ViewModels.StudentsEnrollmentInfoVM;

public class RaviewAllStudentsActivity extends AppCompatActivity {

    TextView lblReviewAllStudents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raview_all_students);
        lblReviewAllStudents = findViewById(R.id.lblReviewAllStudents);
        StringBuffer buffer=new StringBuffer();
        DBContext context = new DBContext(this);
        List<StudentsEnrollmentInfoVM> studentsEnrollmentInfoVMS = EnrollmentsController.getEnrollmentsStudentsInfo(context);
        for (StudentsEnrollmentInfoVM u: studentsEnrollmentInfoVMS)
        {
            buffer.append("StudentId: "+ u.getStudentId() +"\n");
            buffer.append("StudentName: "+ u.getStudentName() +"\n");
            buffer.append("SectionName: "+ u.getSectionName() +"\n");
            buffer.append("CourseTitle: "+ u.getCourseTitle() +"\n");
            buffer.append("InstructorName: "+ u.getSectionName() +"\n\n");
            buffer.append("---------------------------\n");
        }
        lblReviewAllStudents.setText(buffer.toString());
    }
}