package com.example.iwb303.ui.subject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iwb303.R;
import com.example.iwb303.ui.dept.GetDeptActivity;

import Controller.CoursesController;
import Controller.DBContext;
import Controller.SectionsController;
import Models.Course;
import Models.Section;

public class GetCourseActivity extends AppCompatActivity {

    EditText txtCourseId ,txtEditCourseTitle , txtEditCourseHours;
    Button btnApplyEditCourse , btnApplyDeleteCourse;
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
        txtCourseId = findViewById(R.id.txtCourseId);
        txtEditCourseTitle = findViewById(R.id.txtEditCourseTitle);
        txtEditCourseHours = findViewById(R.id.txtEditCourseHours);
        btnApplyEditCourse = findViewById(R.id.btnApplyEditCourse);
        btnApplyDeleteCourse = findViewById(R.id.btnApplyDeleteCourse);
        EnableButton(false);
    }
    public void btnGetCourseById_Click(View view){
        if (TextUtils.isEmpty(txtCourseId.getText().toString())) {
            Toast.makeText(this, "Please enter Course Id", Toast.LENGTH_LONG).show();
        }
        else{
            DBContext context = new DBContext(this);
            Integer courseId =Integer.parseInt(txtCourseId.getText().toString());
            Course course = CoursesController.GetCourse(context,courseId);
            if (course == null){
                Toast.makeText(this, "Not Found", Toast.LENGTH_LONG).show();
                return;
            }
            EnableButton(true);
            txtEditCourseTitle.setText(course.getTitle());
            txtEditCourseHours.setText(String.valueOf(course.getHours()));
        }
    }
    public void btnApplyEditCourse_Click(View view){
        if (TextUtils.isEmpty(txtEditCourseTitle.getText().toString())||
                TextUtils.isEmpty(txtEditCourseHours.getText().toString())||
                TextUtils.isEmpty(txtCourseId.getText().toString())){
            Toast.makeText(this, "Please enter All Data", Toast.LENGTH_LONG).show();
            return;
        }
        DBContext context = new DBContext(this);
        Integer courseId =Integer.parseInt(txtCourseId.getText().toString());
        Course course = CoursesController.GetCourse(context,courseId);
        if (course == null){
            Toast.makeText(this, "Not Found", Toast.LENGTH_LONG).show();
            return;
        }
        course.setId(courseId);
        course.setTitle(txtEditCourseTitle.getText().toString());
        course.setHours(Integer.parseInt(txtEditCourseHours.getText().toString()));
        CoursesController.UpdateCourse(context,course);
        Toast.makeText(this, "Edit Successfully", Toast.LENGTH_LONG).show();
        finish();
    }
    public void btnApplyDeleteCourse_Click(View view){
        if (TextUtils.isEmpty(txtCourseId.getText().toString())){
            Toast.makeText(this, "Please enter Dept Id", Toast.LENGTH_LONG).show();
            return;
        }
        DBContext context = new DBContext(this);
        Integer courseId =Integer.parseInt(txtCourseId.getText().toString());
        CoursesController.deleteCourse(context,courseId);
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