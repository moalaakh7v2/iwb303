package com.example.iwb303.ui.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.iwb303.R;

import Controller.DBContext;
import Controller.StudentsController;
import Models.ViewModels.StudentInfoVM;

public class GetStudentActivity extends AppCompatActivity {

    EditText txtStudentId ,txtEditFName,txtEditLName,txtEditPhone,txtEditUName,txtEditPass,txtEditAddress;
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
        setContentView(R.layout.activity_get_student);
        txtStudentId = findViewById(R.id.txtStudentId);
        txtEditFName = findViewById(R.id.txtEditFName);
        txtEditPhone = findViewById(R.id.txtEditPhone);
        txtEditUName = findViewById(R.id.txtEditUName);
        txtEditPass = findViewById(R.id.txtEditPass);
        txtEditLName = findViewById(R.id.txtEditLName);
        txtEditAddress = findViewById(R.id.txtEditAddress);

    }

    public void btnGetStudentById_Click(View view){
        if (TextUtils.isEmpty(txtStudentId.getText().toString())){
            //write Error
        }
        DBContext context = new DBContext(GetStudentActivity.this);
        Integer x =Integer.parseInt(txtStudentId.getText().toString());
        StudentInfoVM studentInfoVM = StudentsController.GetStudentInfo(context,x);
        EnableButton(true);
    }

    void EnableButton(boolean IsEnable){
        txtStudentId.setEnabled(IsEnable);
        txtEditPhone.setEnabled(IsEnable);
        txtEditUName.setEnabled(IsEnable);
        txtEditPass.setEnabled(IsEnable);
        txtEditLName.setEnabled(IsEnable);
        txtEditAddress.setEnabled(IsEnable);

    }
}