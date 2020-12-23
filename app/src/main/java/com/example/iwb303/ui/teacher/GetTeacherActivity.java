package com.example.iwb303.ui.teacher;

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

import Controller.DBContext;
import Controller.InstructorsController;
import Controller.StudentsController;
import Models.Instructor;
import Models.LoginInfo;
import Models.Student;
import Models.ViewModels.StudentInfoVM;

public class GetTeacherActivity extends AppCompatActivity {

    EditText txtTeacherId ,txtEditTeacherFName,txtEditTeacherLName,txtEditTeacherPhone,txtEditTeacherAddress;
    Button btnGetTeacherById , btnApplyEditTeacher ,btnApplyDeleteTeacher;
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
        setContentView(R.layout.activity_get_teacher);
        txtTeacherId = findViewById(R.id.txtTeacherId);
        txtEditTeacherFName = findViewById(R.id.txtEditTeacherFName);
        txtEditTeacherLName = findViewById(R.id.txtEditTeacherLName);
        txtEditTeacherPhone = findViewById(R.id.txtEditTeacherPhone);
        txtEditTeacherAddress = findViewById(R.id.txtEditTeacherAddress);
        btnGetTeacherById = findViewById(R.id.btnGetTeacherById);
        btnApplyEditTeacher = findViewById(R.id.btnApplyEditTeacher);
        btnApplyDeleteTeacher = findViewById(R.id.btnApplyDeleteTeacher);
        EnableButton(false);
    }
    public void btnGetTeacherById_Click(View view){
        if (TextUtils.isEmpty(txtTeacherId.getText().toString())){
            Toast.makeText(this, "Please enter Teacher Id", Toast.LENGTH_LONG).show();
        }
        else{
            DBContext context = new DBContext(GetTeacherActivity.this);
            Integer teacherId =Integer.parseInt(txtTeacherId.getText().toString());
            Instructor teacher = InstructorsController.GetInstructor(context,teacherId);
            if (teacher == null){
                Toast.makeText(this, "Not Found", Toast.LENGTH_LONG).show();
                return;
            }
            EnableButton(true);
            txtEditTeacherFName.setText(teacher.getFirstname());
            txtEditTeacherLName.setText(teacher.getLastname());
            txtEditTeacherPhone.setText(teacher.getMobileNo());
            txtEditTeacherAddress.setText(teacher.getAddress());
        }
    }
    public void btnApplyEditTeacher_Click(View view){
        if (TextUtils.isEmpty(txtEditTeacherFName.getText().toString())||
                TextUtils.isEmpty(txtEditTeacherLName.getText().toString())||
                TextUtils.isEmpty(txtEditTeacherPhone.getText().toString())||
                TextUtils.isEmpty(txtEditTeacherAddress.getText().toString())||
                TextUtils.isEmpty(txtTeacherId.getText().toString())){
            Toast.makeText(this, "Please enter Dept Name", Toast.LENGTH_LONG).show();
            return;
        }
        DBContext context = new DBContext(GetTeacherActivity.this);
        Integer teacherId =Integer.parseInt(txtTeacherId.getText().toString());
        Instructor teahcer = new Instructor();
        teahcer.setId(teacherId);
        teahcer.setFirstname(txtEditTeacherFName.getText().toString());
        teahcer.setLastname(txtEditTeacherLName.getText().toString());
        teahcer.setMobileNo(txtEditTeacherPhone.getText().toString());
        teahcer.setAddress(txtEditTeacherAddress.getText().toString());
        InstructorsController.UpdateInstructor(context,teahcer);
        Toast.makeText(GetTeacherActivity.this, "Edit Successfully", Toast.LENGTH_LONG).show();
        finish();
    }
    public void btnApplyDeleteTeacher_Click(View view){
        if (TextUtils.isEmpty(txtTeacherId.getText().toString())){
            Toast.makeText(this, "Please enter Dept Id", Toast.LENGTH_LONG).show();
            return;
        }
        DBContext context = new DBContext(GetTeacherActivity.this);
        Integer teacherId =Integer.parseInt(txtTeacherId.getText().toString());
        InstructorsController.deleteInstructor(context,teacherId);
        Toast.makeText(GetTeacherActivity.this, "Deleted Successfully", Toast.LENGTH_LONG).show();
        finish();
    }
    void EnableButton(boolean IsEnable){
        txtEditTeacherFName.setEnabled(IsEnable);
        txtEditTeacherLName.setEnabled(IsEnable);
        txtEditTeacherPhone.setEnabled(IsEnable);
        txtEditTeacherAddress.setEnabled(IsEnable);
        btnApplyEditTeacher.setEnabled(IsEnable);
        btnApplyDeleteTeacher.setEnabled(IsEnable);
    }
}