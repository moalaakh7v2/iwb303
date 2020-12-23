package com.example.iwb303.ui.subject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.iwb303.R;
import com.example.iwb303.ui.dept.AddNewDeptActivity;
import com.example.iwb303.ui.dept.GetDeptActivity;

import java.util.List;

import Controller.CoursesController;
import Controller.DBContext;
import Controller.SectionsController;
import Models.Course;
import Models.Section;

public class ManageCourseActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAddNewCourses;
    Button btnEditCourses;
    TextView lblAllCourses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_course);
        btnEditCourses = (Button) findViewById(R.id.btnEditCourses);
        btnEditCourses.setOnClickListener(this);
        btnAddNewCourses = (Button)findViewById(R.id.btnAddNewCourses);
        btnAddNewCourses.setOnClickListener(this);
        lblAllCourses = findViewById(R.id.lblAllCourses);
        StringBuffer buffer=new StringBuffer();
        DBContext context = new DBContext(this);
        List<Course> courses = CoursesController.GetِAllCourses(context);
        for (Course u: courses)
        {
            buffer.append("Id: "+ u.getId() +"\n");
            buffer.append("Title: "+ u.getTitle() +"\n");
            buffer.append("Hours: "+ u.getHours() +"\n\n");
            buffer.append("---------------------------\n");
        }
        lblAllCourses.setText(buffer.toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEditCourses:
                startActivity(new Intent(this, GetCourseActivity.class));
                break;
            case R.id.btnAddNewCourses:
                startActivity(new Intent(this, AddNewCourseActivity.class));
                break;
        }
    }

}