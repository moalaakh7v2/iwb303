package com.example.iwb303.ui.subject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iwb303.R;

import Controller.CoursesController;
import Controller.CoursesinSectionsController;
import Controller.DBContext;
import Models.Course;
import Models.CourseinSection;

public class AddNewCourseInSectionActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_add_new_course_in_section);
    }

    public void btnAddCourseInSoction(View view){
        EditText txtAddSectionId = findViewById(R.id.txtAddSectionId);
        EditText txtAddCourseId = findViewById(R.id.txtAddCourseId);
        EditText txtAddTeacherId = findViewById(R.id.txtAddTeacherId);
        EditText txtAddRoomId = findViewById(R.id.txtAddRoomId);
        CourseinSection courseinSection = new CourseinSection();
        courseinSection.setCourseId(Integer.parseInt(txtAddCourseId.getText().toString()));
        courseinSection.setInstructorId(Integer.parseInt(txtAddTeacherId.getText().toString()));
        courseinSection.setRoomNo(txtAddRoomId.getText().toString());
        courseinSection.setSectionNo(Integer.parseInt(txtAddSectionId.getText().toString()));
        CoursesinSectionsController.AddCourseinSection(new DBContext(this),courseinSection);
        Toast.makeText(this, "Added Successfully", Toast.LENGTH_LONG).show();
        finish();
    }
}