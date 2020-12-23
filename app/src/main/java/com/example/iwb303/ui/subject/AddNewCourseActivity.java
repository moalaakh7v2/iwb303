package com.example.iwb303.ui.subject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iwb303.R;
import com.example.iwb303.ui.dept.AddNewDeptActivity;

import Controller.CoursesController;
import Controller.DBContext;
import Controller.SectionsController;
import Models.Course;
import Models.Section;

public class AddNewCourseActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_add_new_course);
    }
    public void btnAddCourse(View view){
        EditText txtAddCourseName = findViewById(R.id.txtAddCourseName);
        EditText txtAddCourseHours = findViewById(R.id.txtAddCourseHours);
        if (TextUtils.isEmpty(txtAddCourseName.getText().toString()) ||
                TextUtils.isEmpty(txtAddCourseHours.getText().toString())){
            Toast.makeText(this, "Please enter all data", Toast.LENGTH_LONG).show();
            return;
        }
        Course course = new Course();
        course.setTitle(txtAddCourseName.getText().toString());
        course.setHours(Integer.parseInt(txtAddCourseHours.getText().toString()));
        CoursesController.AddCourse(new DBContext(this),course);
        Toast.makeText(this, "Added Successfully", Toast.LENGTH_LONG).show();
        finish();
    }
}