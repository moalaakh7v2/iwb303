package com.example.iwb303.ui.subject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.iwb303.R;

import Controller.CoursesController;
import Controller.CoursesinSectionsController;
import Controller.DBContext;
import Controller.InstructorsController;
import Controller.SectionsController;
import Models.Course;
import Models.CourseinSection;
import Models.Instructor;
import Models.Section;

public class AddNewCourseInSectionActivity extends AppCompatActivity {
    Spinner spinnerSections,spinnerCourses, spinnerInstructors;
    EditText txtAddRoomId;
    DBContext context;
    Section section ;
    Course course;
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
        setContentView(R.layout.activity_add_new_course_in_section);
        spinnerSections = findViewById(R.id.SectionsSp);
        spinnerCourses = findViewById(R.id.CoursesSp);
        spinnerInstructors = findViewById(R.id.InstructorsSp);
        txtAddRoomId = findViewById(R.id.txtAddRoomId);
        context = new DBContext(AddNewCourseInSectionActivity.this);
        ArrayAdapter<Section> sectionAdapter = new ArrayAdapter<Section>(this,
                android.R.layout.simple_spinner_item, SectionsController.GetSections(context));
        sectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSections.setAdapter(sectionAdapter);
        spinnerSections.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                section = (Section) spinnerSections.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
        ArrayAdapter<Course> courseAdapter = new ArrayAdapter<Course>(this,
                android.R.layout.simple_spinner_item, CoursesController.GetِAllCourses(context));
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourses.setAdapter(courseAdapter);
        spinnerCourses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                course = (Course) spinnerCourses.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
        ArrayAdapter<Instructor> instructorAdapter = new ArrayAdapter<Instructor>(this,
                android.R.layout.simple_spinner_item, InstructorsController.GetInstructors(context));
        instructorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInstructors.setAdapter(instructorAdapter);
        spinnerInstructors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                instructor= (Instructor) spinnerInstructors.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
    }

    public void btnAddCourseInSoction(View view){

        if (section == null || course == null ||
                instructor == null || TextUtils.isEmpty(txtAddRoomId.getText().toString())){
            Toast.makeText(this, "Please Enter All Data", Toast.LENGTH_LONG).show();
            return;
        }
        CourseinSection courseinSection = new CourseinSection();
        courseinSection.setSectionNo(section.getSectionNo());
        courseinSection.setCourseId(course.getId());
        courseinSection.setInstructorId(instructor.getId());
        courseinSection.setRoomNo(txtAddRoomId.getText().toString());
        Integer count =CoursesinSectionsController.GetCountCourseinSection(context,courseinSection);
        if(count > 0) {
            Toast.makeText(this, "Already Exists", Toast.LENGTH_LONG).show();
        return;
        }
        CoursesinSectionsController.AddCourseinSection(new DBContext(this),courseinSection);
        Toast.makeText(this, "Added Successfully", Toast.LENGTH_LONG).show();
        finish();
    }
}