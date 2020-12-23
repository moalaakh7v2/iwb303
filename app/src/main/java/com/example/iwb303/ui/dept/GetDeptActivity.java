package com.example.iwb303.ui.dept;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iwb303.R;
import com.example.iwb303.ui.student.GetStudentActivity;

import Controller.CoursesController;
import Controller.DBContext;
import Controller.SectionsController;
import Controller.StudentsController;
import Models.Course;
import Models.LoginInfo;
import Models.Section;
import Models.Student;
import Models.ViewModels.StudentInfoVM;

public class GetDeptActivity extends AppCompatActivity {

    EditText txtDeptId ,txtEditSectionName;
    Button btnApplyEditSection , btnApplyDeleteSection;
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
        setContentView(R.layout.activity_get_dept);
        setContentView(R.layout.activity_get_student);
        txtDeptId = findViewById(R.id.txtDeptId);
        txtEditSectionName = findViewById(R.id.txtEditSectionName);
        btnApplyEditSection = findViewById(R.id.btnApplyEditSection);
        btnApplyDeleteSection = findViewById(R.id.btnApplyDeleteSection);
        EnableButton(false);
    }
    public void btnGetDeptById_Click(View view){
        if (TextUtils.isEmpty(txtDeptId.getText().toString())){
            //write Error
        }
        else{
            DBContext context = new DBContext(GetDeptActivity.this);
            Integer sectionId =Integer.parseInt(txtDeptId.getText().toString());
            Section section = SectionsController.GetSection(context,sectionId);
            EnableButton(true);
            txtEditSectionName.setText(section.getSectionName());
        }

    }
    public void btnApplyEditSection_Click(View view){
        DBContext context = new DBContext(GetDeptActivity.this);
        Integer sectionId =Integer.parseInt(txtDeptId.getText().toString());
        Section section = new Section();
        section.setSectionNo(sectionId);
        section.setSectionName(txtEditSectionName.getText().toString());
        SectionsController.UpdateSection(context,section);
        Toast.makeText(GetDeptActivity.this, "Edit Successfully", Toast.LENGTH_LONG).show();
        finish();
    }
    public void btnApplyDeleteSection_Click(View view){
        DBContext context = new DBContext(GetDeptActivity.this);
        Integer sectionId =Integer.parseInt(txtDeptId.getText().toString());
        CoursesController.deleteCourse(context,sectionId);
        Toast.makeText(GetDeptActivity.this, "Deleted Successfully", Toast.LENGTH_LONG).show();
        finish();
    }
    void EnableButton(boolean IsEnable){
        txtDeptId.setEnabled(IsEnable);
        txtEditSectionName.setEnabled(IsEnable);
        btnApplyDeleteSection.setEnabled(IsEnable);
        btnApplyDeleteSection.setEnabled(IsEnable);
    }
}