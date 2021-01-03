package com.example.iwb303.ui.teacher;

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
import com.example.iwb303.ui.student.GetStudentActivity;

import java.util.List;

import Controller.DBContext;
import Controller.FillSpinner;
import Controller.InstructorsController;
import Controller.StudentsController;
import Models.Instructor;
import Models.LoginInfo;
import Models.Student;
import Models.ViewModels.StudentInfoVM;

public class GetTeacherActivity extends AppCompatActivity {

    EditText txtEditTeacherFName,txtEditTeacherLName,txtEditTeacherPhone,txtEditTeacherAddress;
    Spinner teacherSpinner;
    Button btnGetTeacherById , btnApplyEditTeacher ,btnApplyDeleteTeacher;
    Instructor instructor;
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
        teacherSpinner = findViewById(R.id.spinnerTeachers);
        txtEditTeacherFName = findViewById(R.id.txtEditTeacherFName);
        txtEditTeacherLName = findViewById(R.id.txtEditTeacherLName);
        txtEditTeacherPhone = findViewById(R.id.txtEditTeacherPhone);
        txtEditTeacherAddress = findViewById(R.id.txtEditTeacherAddress);
        btnGetTeacherById = findViewById(R.id.btnGetTeacherById);
        btnApplyEditTeacher = findViewById(R.id.btnApplyEditTeacher);
        btnApplyDeleteTeacher = findViewById(R.id.btnApplyDeleteTeacher);
        EnableButton(false);
        List<Instructor> teachers = InstructorsController.GetInstructors(new DBContext(GetTeacherActivity.this));
        FillSpinner<Instructor> fillSpinnerInstructors = new FillSpinner<>(GetTeacherActivity.this,teacherSpinner,teachers);
        teacherSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                instructor = (Instructor) parent.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
    public void btnGetTeacherById_Click(View view){
        if (instructor == null){
            Toast.makeText(this, "Not Found", Toast.LENGTH_LONG).show();
            return;
        }
        EnableButton(true);
        txtEditTeacherFName.setText(instructor.getFirstname());
        txtEditTeacherLName.setText(instructor.getLastname());
        txtEditTeacherPhone.setText(instructor.getMobileNo());
        txtEditTeacherAddress.setText(instructor.getAddress());
    }
    public void btnApplyEditTeacher_Click(View view){
        if (TextUtils.isEmpty(txtEditTeacherFName.getText().toString())||
                TextUtils.isEmpty(txtEditTeacherLName.getText().toString())||
                TextUtils.isEmpty(txtEditTeacherPhone.getText().toString())||
                TextUtils.isEmpty(txtEditTeacherAddress.getText().toString())){
            Toast.makeText(this, "Please enter all data ", Toast.LENGTH_LONG).show();
            return;
        }
        DBContext context = new DBContext(GetTeacherActivity.this);
        Instructor teahcer = new Instructor();
        teahcer.setId(instructor.getId());
        teahcer.setFirstname(txtEditTeacherFName.getText().toString());
        teahcer.setLastname(txtEditTeacherLName.getText().toString());
        teahcer.setMobileNo(txtEditTeacherPhone.getText().toString());
        teahcer.setAddress(txtEditTeacherAddress.getText().toString());
        InstructorsController.UpdateInstructor(context,teahcer);
        Toast.makeText(GetTeacherActivity.this, "Edit Successfully", Toast.LENGTH_LONG).show();
        finish();
    }
    public void btnApplyDeleteTeacher_Click(View view){
        if (instructor == null){
            Toast.makeText(this, "Please select teacher !", Toast.LENGTH_LONG).show();
            return;
        }
        DBContext context = new DBContext(GetTeacherActivity.this);
        InstructorsController.deleteInstructor(context,instructor.getId());
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