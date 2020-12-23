package com.example.iwb303.ui.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iwb303.R;

import Controller.DBContext;
import Controller.StudentsController;
import Models.LoginInfo;
import Models.Student;
import Models.ViewModels.StudentInfoVM;

public class GetStudentActivity extends AppCompatActivity {

    EditText txtStudentId ,txtEditFName,txtEditLName,txtEditPhone,txtEditUName,txtEditPass,txtEditAddress;
    Button btnApplyEditStudent , btnApplyDeleteStudent;
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
        btnApplyEditStudent = findViewById(R.id.btnApplyEditStudent);
        btnApplyDeleteStudent = findViewById(R.id.btnApplyDeleteStudent);
        EnableButton(false);

    }

    public void btnGetStudentById_Click(View view){
        if (TextUtils.isEmpty(txtStudentId.getText().toString())){
            //write Error
        }
        else{
            DBContext context = new DBContext(GetStudentActivity.this);
            Integer studetId =Integer.parseInt(txtStudentId.getText().toString());
            StudentInfoVM studentInfoVM = StudentsController.GetStudentInfo(context,studetId);
            EnableButton(true);
            txtEditFName.setText(studentInfoVM.getFirstname());
            txtEditPhone.setText(studentInfoVM.getMobileNo());
            txtEditUName.setText(studentInfoVM.getUsername());
            txtEditPass.setText(studentInfoVM.getPassword());
            txtEditLName.setText(studentInfoVM.getLastname());
            txtEditAddress.setText(studentInfoVM.getAddress());
            txtEditFName.setText(studentInfoVM.getFirstname());
        }

    }

    public void btnApplyEditStudent_Click(View view){
        DBContext context = new DBContext(GetStudentActivity.this);
        Integer studetId =Integer.parseInt(txtStudentId.getText().toString());
        Student student = new Student();
        student.setId(studetId);
        student.setFirstname(txtEditFName.getText().toString());
        student.setLastname(txtEditLName.getText().toString());
        student.setMobileNo(txtEditPhone.getText().toString());
        student.setAddress(txtEditAddress.getText().toString());
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUsername(txtEditUName.getText().toString());
        loginInfo.setPassword(txtEditPass.getText().toString());
        loginInfo.setStudentId(studetId);
        StudentsController.UpdateStudent(context,student,loginInfo);
        Toast.makeText(GetStudentActivity.this, "Edit Successfully", Toast.LENGTH_LONG).show();
        finish();
    }

    public void btnApplyDeleteStudent_Click(View view){
        DBContext context = new DBContext(GetStudentActivity.this);
        Integer studetId =Integer.parseInt(txtStudentId.getText().toString());
        StudentsController.deleteStudent(context,studetId);
        Toast.makeText(GetStudentActivity.this, "Deleted Successfully", Toast.LENGTH_LONG).show();
        finish();
    }

    void EnableButton(boolean IsEnable){
        txtEditFName.setEnabled(IsEnable);
        txtEditPhone.setEnabled(IsEnable);
        txtEditUName.setEnabled(IsEnable);
        txtEditPass.setEnabled(IsEnable);
        txtEditLName.setEnabled(IsEnable);
        txtEditAddress.setEnabled(IsEnable);
        btnApplyEditStudent.setEnabled(IsEnable);
        btnApplyDeleteStudent.setEnabled(IsEnable);
    }
}