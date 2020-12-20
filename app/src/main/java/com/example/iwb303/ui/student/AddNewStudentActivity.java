package com.example.iwb303.ui.student;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.iwb303.R;

import java.time.LocalDate;

import Controller.DBContext;
import Controller.StudentsController;
import Models.Enums.Gender;
import Models.LoginInfo;
import Models.Student;

public class AddNewStudentActivity extends AppCompatActivity {

    Spinner drpStudentGender;
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
        setContentView(R.layout.activity_add_new_student);
        drpStudentGender = findViewById(R.id.drpStudentGender);
        drpStudentGender.setAdapter(new ArrayAdapter<Gender>(this,R.layout.support_simple_spinner_dropdown_item,Gender.values()));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void btnAddStudent(View view){
        EditText txtAddFName = findViewById(R.id.txtAddFName);
        EditText txtAddLName = findViewById(R.id.txtAddLName);
        EditText txtAddPhone = findViewById(R.id.txtAddPhone);
        EditText txtAddUName = findViewById(R.id.txtAddUName);
        EditText txtAddPassword = findViewById(R.id.txtAddPassword);
        EditText txtAddAddress = findViewById(R.id.txtAddAddress);


        Student student = new Student();
        student.setFirstname(txtAddFName.getText().toString());
        student.setLastname(txtAddLName.getText().toString());
        student.setMobileNo(txtAddPhone.getText().toString());
        student.setAddress(txtAddAddress.getText().toString());
        student.setGender(drpStudentGender.getSelectedItem().toString());
        student.setRegYeer(LocalDate.now().getYear());
        LoginInfo loginInfo =new LoginInfo();
        loginInfo.setUsername(txtAddUName.getText().toString());
        loginInfo.setPassword(txtAddPassword.getText().toString());
        StudentsController.AddStudent(new DBContext(AddNewStudentActivity.this),student,loginInfo);
    }
}