package com.example.iwb303.ui.dept;

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
import com.example.iwb303.ui.subject.GetCourseActivity;

import java.util.List;

import Controller.CoursesController;
import Controller.DBContext;
import Controller.FillSpinner;
import Controller.SectionsController;
import Controller.StudentsController;
import Controller.btnSounds;
import Models.Course;
import Models.LoginInfo;
import Models.Section;
import Models.Student;
import Models.ViewModels.StudentInfoVM;

public class GetDeptActivity extends AppCompatActivity {

    EditText txtEditSectionName;
    Button btnApplyEditSection , btnApplyDeleteSection;
    Spinner spDepts;
    Section dept;
    Boolean isMute;
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
        spDepts = findViewById(R.id.spDepts);
        txtEditSectionName = findViewById(R.id.txtEditSectionName);
        btnApplyEditSection = findViewById(R.id.btnApplyEditSection);
        btnApplyDeleteSection = findViewById(R.id.btnApplyDeleteSection);
        EnableButton(false);
        SharedPreferences Sounds = getSharedPreferences("Sounds", 0);
        isMute= Sounds.getBoolean("Status", false);
        List<Section> sections = SectionsController.GetSections(new DBContext(GetDeptActivity.this));
        FillSpinner<Section> fillSpinnerSections = new FillSpinner<>(GetDeptActivity.this,spDepts,sections);
        spDepts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                dept = (Section) parent.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public void btnGetDeptById_Click(View view){
        btnSounds.SetSounds(GetDeptActivity.this,isMute, R.raw.tab_move);
        if (dept == null){
            Toast.makeText(this, "Not Found", Toast.LENGTH_LONG).show();
            return;
        }
        EnableButton(true);
        txtEditSectionName.setText(dept.getSectionName());
    }
    public void btnApplyEditSection_Click(View view){
        btnSounds.SetSounds(GetDeptActivity.this,isMute, R.raw.tab_move);
        if (TextUtils.isEmpty(txtEditSectionName.getText().toString())){
            Toast.makeText(this, "Please enter Dept Name", Toast.LENGTH_LONG).show();
            return;
        }
        DBContext context = new DBContext(GetDeptActivity.this);
        Section section = new Section();
        section.setSectionNo(dept.getSectionNo());
        section.setSectionName(txtEditSectionName.getText().toString());
        SectionsController.UpdateSection(context,section);
        Toast.makeText(GetDeptActivity.this, "Edit Successfully", Toast.LENGTH_LONG).show();
        finish();
    }
    public void btnApplyDeleteSection_Click(View view){
        btnSounds.SetSounds(GetDeptActivity.this,isMute, R.raw.tab_move);
        if (dept == null){
            Toast.makeText(this, "Please Select Dept ", Toast.LENGTH_LONG).show();
            return;
        }
        DBContext context = new DBContext(GetDeptActivity.this);
        SectionsController.deleteSection(context,dept.getSectionNo());
        Toast.makeText(GetDeptActivity.this, "Deleted Successfully", Toast.LENGTH_LONG).show();
        finish();
    }
    void EnableButton(boolean IsEnable){
        txtEditSectionName.setEnabled(IsEnable);
        btnApplyEditSection.setEnabled(IsEnable);
        btnApplyDeleteSection.setEnabled(IsEnable);
    }
}