package com.example.iwb303.ui.search;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.iwb303.R;

import Controller.CoursesController;
import Controller.DBContext;
import Controller.InstructorsController;
import Controller.SectionsController;
import Models.Course;
import Models.Instructor;
import Models.Section;

public class GetResultActivity extends AppCompatActivity {

    TextView lblresult;
    String result;
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
        setContentView(R.layout.activity_get_result);
        DBContext context = new DBContext(this);
        Intent intent = getIntent();
        Integer Id = intent.getExtras().getInt("Id");
        String type = intent.getExtras().getString("type");
        lblresult = findViewById(R.id.lblresult);
        switch (type){
            case "teacher":
                Instructor teacher = InstructorsController.GetInstructor(context,Id);
                if (teacher == null){
                    lblresult.setText("Not Found");
                    return;
                }
                result = teacher.getFirstname() + "\n";
                result += teacher.getLastname() + "\n";
                result += teacher.getGender() + "\n";
                result += teacher.getMobileNo() + "\n";
                result += teacher.getAddress() + "\n";
                lblresult.setText(result);
                break;
            case "course":
                Course course = CoursesController.GetCourse(context,Id);
                if (course == null){
                    lblresult.setText("Not Found");
                    return;
                }
                result = course.getTitle() + "\n";
                result += String.valueOf(course.getHours()) + "\n";
                lblresult.setText(result);
                break;
            case "section":
                Section section = SectionsController.GetSection(context,Id);
                if (section == null){
                    lblresult.setText("Not Found");
                    return;
                }
                result = section.getSectionName();
                lblresult.setText(result);
                break;
        }
    }
}