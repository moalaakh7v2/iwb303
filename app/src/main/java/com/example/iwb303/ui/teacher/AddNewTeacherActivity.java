package com.example.iwb303.ui.teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.iwb303.R;
import com.example.iwb303.ui.student.AddNewStudentActivity;

import java.util.Calendar;

import Controller.DBContext;
import Controller.InstructorsController;
import Controller.StudentsController;
import Models.Enums.Gender;
import Models.Instructor;
import Models.LoginInfo;
import Models.Student;

public class AddNewTeacherActivity extends AppCompatActivity {

    Spinner drpTeacherGender;
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
        setContentView(R.layout.activity_add_new_teacher);
        drpTeacherGender = findViewById(R.id.drpTeacherGender);
        drpTeacherGender.setAdapter(new ArrayAdapter<Gender>(this,R.layout.support_simple_spinner_dropdown_item,Gender.values()));
    }

    public void btnAddStudent(View view){
        EditText txtAddTeacherFName = findViewById(R.id.txtAddTeacherFName);
        EditText txtAddTeacherLName = findViewById(R.id.txtAddTeacherLName);
        EditText txtAddTeacherPhone = findViewById(R.id.txtAddTeacherPhone);
        EditText txtAddTeacherAddress = findViewById(R.id.txtAddTeacherAddress);
        Instructor teacher = new Instructor();
        teacher.setFirstname(txtAddTeacherFName.getText().toString());
        teacher.setLastname(txtAddTeacherLName.getText().toString());
        teacher.setMobileNo(txtAddTeacherPhone.getText().toString());
        teacher.setAddress(txtAddTeacherAddress.getText().toString());
        teacher.setGender(drpTeacherGender.getSelectedItem().toString());
        InstructorsController.AddInstructor(new DBContext(AddNewTeacherActivity.this),teacher);
        Toast.makeText(AddNewTeacherActivity.this, "Added Successfully", Toast.LENGTH_LONG).show();
        finish();
    }
}