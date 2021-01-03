package com.example.iwb303.ui.subject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.iwb303.R;
import com.example.iwb303.ui.dept.GetDeptActivity;
import com.example.iwb303.ui.teacher.GetTeacherActivity;

import java.util.List;

import Controller.CoursesController;
import Controller.DBContext;
import Controller.FillSpinner;
import Controller.InstructorsController;
import Controller.SectionsController;
import Models.Course;
import Models.Instructor;
import Models.Section;

public class GetCourseActivity extends AppCompatActivity {

    EditText txtEditCourseTitle , txtEditCourseHours;
    Button btnApplyEditCourse , btnApplyDeleteCourse;
    Spinner spCourses;
    Course course;
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
        setContentView(R.layout.activity_get_course);
        spCourses = findViewById(R.id.spCourses);
        txtEditCourseTitle = findViewById(R.id.txtEditCourseTitle);
        txtEditCourseHours = findViewById(R.id.txtEditCourseHours);
        btnApplyEditCourse = findViewById(R.id.btnApplyEditCourse);
        btnApplyDeleteCourse = findViewById(R.id.btnApplyDeleteCourse);
        EnableButton(false);
        List<Course> Courses = CoursesController.GetِAllCourses(new DBContext(GetCourseActivity.this));
        FillSpinner<Course> fillSpinnerCourses = new FillSpinner<>(GetCourseActivity.this,spCourses,Courses);
        spCourses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                course = (Course) parent.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public void btnGetCourseById_Click(View view){

        if (course == null){
            Toast.makeText(this, "Not Found", Toast.LENGTH_LONG).show();
            return;
        }
        EnableButton(true);
        txtEditCourseTitle.setText(course.getTitle());
        txtEditCourseHours.setText(String.valueOf(course.getHours()));

    }
    public void btnApplyEditCourse_Click(View view){
        if (TextUtils.isEmpty(txtEditCourseTitle.getText().toString())||
                TextUtils.isEmpty(txtEditCourseHours.getText().toString())){
            Toast.makeText(this, "Please enter All Data", Toast.LENGTH_LONG).show();
            return;
        }
        Course subject = new Course();
        subject.setId(course.getId());
        subject.setTitle(txtEditCourseTitle.getText().toString());
        subject.setHours(Integer.parseInt(txtEditCourseHours.getText().toString()));
        CoursesController.UpdateCourse(new DBContext(GetCourseActivity.this),course);
        Toast.makeText(this, "Edit Successfully", Toast.LENGTH_LONG).show();
        finish();
    }
    public void btnApplyDeleteCourse_Click(View view){
        if (course == null){
            Toast.makeText(this, "Please Select Course !", Toast.LENGTH_LONG).show();
            return;
        }
        CoursesController.deleteCourse(new DBContext(GetCourseActivity.this),course.getId());
        Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_LONG).show();
        finish();
    }
    void EnableButton(boolean IsEnable){
        txtEditCourseTitle.setEnabled(IsEnable);
        txtEditCourseHours.setEnabled(IsEnable);
        btnApplyEditCourse.setEnabled(IsEnable);
        btnApplyDeleteCourse.setEnabled(IsEnable);
    }
}