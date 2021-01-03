package com.example.iwb303.ui.student;

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

import java.util.List;

import Controller.DBContext;
import Controller.FillSpinner;
import Controller.StudentsController;
import Models.LoginInfo;
import Models.Student;
import Models.ViewModels.StudentInfoVM;

public class GetStudentActivity extends AppCompatActivity {

    EditText txtEditFName,txtEditLName,txtEditPhone,txtEditUName,txtEditPass,txtEditAddress;
    Spinner studentsSpinner;
    Button btnApplyEditStudent , btnApplyDeleteStudent;
    StudentInfoVM studentVM;
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
        studentsSpinner = findViewById(R.id.spinnerStudents);
        txtEditFName = findViewById(R.id.txtEditFName);
        txtEditPhone = findViewById(R.id.txtEditPhone);
        txtEditUName = findViewById(R.id.txtEditUName);
        txtEditPass = findViewById(R.id.txtEditPass);
        txtEditLName = findViewById(R.id.txtEditLName);
        txtEditAddress = findViewById(R.id.txtEditAddress);
        btnApplyEditStudent = findViewById(R.id.btnApplyEditStudent);
        btnApplyDeleteStudent = findViewById(R.id.btnApplyDeleteStudent);
        EnableButton(false);
        List<StudentInfoVM> students = StudentsController.getStudents(new DBContext(GetStudentActivity.this));
        FillSpinner<StudentInfoVM> fillSpinnerInstructors = new FillSpinner<>(GetStudentActivity.this,studentsSpinner,students);
        studentsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                studentVM = (StudentInfoVM) parent.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    public void btnGetStudentById_Click(View view){

        if (studentVM == null){
            Toast.makeText(this, "Not Found", Toast.LENGTH_LONG).show();
            return;
        }
        EnableButton(true);
        txtEditFName.setText(studentVM.getFirstname());
        txtEditPhone.setText(studentVM.getMobileNo());
        txtEditUName.setText(studentVM.getUsername());
        txtEditPass.setText(studentVM.getPassword());
        txtEditLName.setText(studentVM.getLastname());
        txtEditAddress.setText(studentVM.getAddress());
    }

    public void btnApplyEditStudent_Click(View view){
        if (TextUtils.isEmpty(txtEditFName.getText().toString()) ||
                TextUtils.isEmpty(txtEditPhone.getText().toString())||
                TextUtils.isEmpty(txtEditUName.getText().toString())||
                TextUtils.isEmpty(txtEditPass.getText().toString())||
                TextUtils.isEmpty(txtEditLName.getText().toString() )||
                TextUtils.isEmpty(txtEditAddress.getText().toString())) {
            Toast.makeText(this, "Please enter all data !", Toast.LENGTH_LONG).show();
            return;
        }
        DBContext context = new DBContext(GetStudentActivity.this);
        Student student = new Student();
        student.setId(studentVM.getId());
        student.setFirstname(txtEditFName.getText().toString());
        student.setLastname(txtEditLName.getText().toString());
        student.setMobileNo(txtEditPhone.getText().toString());
        student.setAddress(txtEditAddress.getText().toString());
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUsername(txtEditUName.getText().toString());
        loginInfo.setPassword(txtEditPass.getText().toString());
        loginInfo.setStudentId(studentVM.getId());
        StudentsController.UpdateStudent(context,student,loginInfo);
        Toast.makeText(GetStudentActivity.this, "Edit Successfully", Toast.LENGTH_LONG).show();
        finish();
    }

    public void btnApplyDeleteStudent_Click(View view){
        if (studentVM == null){
            Toast.makeText(this, "Please select Student !", Toast.LENGTH_LONG).show();
            return;
        }
        DBContext context = new DBContext(GetStudentActivity.this);
        StudentsController.deleteStudent(context,studentVM.getId());
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