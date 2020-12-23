package com.example.iwb303.ui.search;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
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
        SharedPreferences prefs = getSharedPreferences("themeFile", MODE_PRIVATE);
        String themeName = prefs.getString("themeName", "Blue");
        switch (themeName) {
            case "Blue":
                setTheme(R.style.AppTheme1);
                break;
            case "Purple":
                setTheme(R.style.AppTheme2);
                break;
            case "Pink":
                setTheme(R.style.AppTheme3);
                break;
            case "Pastel":
                setTheme(R.style.AppTheme4);
                break;
            case "Green":
                setTheme(R.style.AppTheme5);
                break;
        }
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