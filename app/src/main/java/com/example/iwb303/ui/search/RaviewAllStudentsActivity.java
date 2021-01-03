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
import Controller.StudentsController;
import Models.Section;
import Models.ViewModels.StudentInfoVM;
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
        List<StudentInfoVM> studentsEnrollmentInfoVMS = StudentsController.GetStudentInfo(context);
        for (StudentInfoVM u: studentsEnrollmentInfoVMS)
        {
            List<StudentsEnrollmentInfoVM> SVM = EnrollmentsController.GetStudentEnrollments(context,u.getId());
            buffer.append("Id : "+ u.getId() +"\n");
            buffer.append("UserName : "+ u.getUsername() +"\n");
            buffer.append("Full Name : "+ u.getFirstname() +" "+u.getLastname() +"\n");
            for (StudentsEnrollmentInfoVM i: SVM)
            {
                String info = " - "+ i.getSectionName() +" -> " + i.getCourseTitle()+ " -> " +i.getInstructorName();
            buffer.append(info +"\n");
            }
            buffer.append("\n ---------------------------\n");
        }
        lblReviewAllStudents.setText(buffer.toString());
    }
}