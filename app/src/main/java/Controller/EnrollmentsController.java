package Controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Models.Enrollment;


public class EnrollmentsController {

    public static List<Enrollment> getEnrollments(DBContext context) {
        SQLiteDatabase database = context.getReadableDatabase() ;
        List<Enrollment> EnrollmentsList = new ArrayList<>();
        String getAll = "Select * From Enrollments; ";

        Cursor cursor = database.rawQuery(getAll ,null );
        if (cursor.moveToFirst())
            do{
                Enrollment enrollment = new Enrollment();
                enrollment.setSectionNo(cursor.getInt(0));
                enrollment.setCourseId(cursor.getInt(1));
                enrollment.setStudentId(cursor.getInt(2));
                enrollment.setGrade(cursor.getFloat(3));
                EnrollmentsList.add(enrollment);
            }while (cursor.moveToNext());
        return  EnrollmentsList;
    }
    public static void AddEnrollment(DBContext context , Enrollment enrollment)
    {
        SQLiteDatabase database = context.getWritableDatabase();
        ContentValues enrollmentValues = new ContentValues();
        enrollmentValues.put("SectionNo", enrollment.getSectionNo());
        enrollmentValues.put("CourseId", enrollment.getCourseId());
        enrollmentValues.put("StudentId", enrollment.getStudentId());
        enrollmentValues.put("Grade", enrollment.getGrade());
        database.insert("Enrollments",null,enrollmentValues);
        database.close();
    }
}
