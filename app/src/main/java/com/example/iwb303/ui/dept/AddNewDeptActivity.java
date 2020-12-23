package com.example.iwb303.ui.dept;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iwb303.R;
import com.example.iwb303.ui.student.AddNewStudentActivity;

import java.util.Calendar;

import Controller.DBContext;
import Controller.SectionsController;
import Controller.StudentsController;
import Models.LoginInfo;
import Models.Section;
import Models.Student;

public class AddNewDeptActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_add_new_dept);
    }
    public void btnAddDept(View view){
        EditText txtAddDeptName = findViewById(R.id.txtAddDeptName);
        Section section = new Section();
        section.setSectionName(txtAddDeptName.getText().toString());
        SectionsController.AddSection(new DBContext(AddNewDeptActivity.this),section);
        Toast.makeText(AddNewDeptActivity.this, "Added Successfully", Toast.LENGTH_LONG).show();
        finish();

    }
}