package Controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Models.Course;
import Models.Enrollment;
import Models.ViewModels.StudentsEnrollmentInfoVM;


public class EnrollmentsController {

    public static List<StudentsEnrollmentInfoVM> GetStudentEnrollments(DBContext context, int StudentId)
    {
        SQLiteDatabase database = context.getReadableDatabase() ;
        List<StudentsEnrollmentInfoVM> studentsEnrollmentsList = new ArrayList<>();
        String getAll = "Select Students.Id ,Students.FirstName || ' ' || Students.LastName ,Sections.SectionName" +
                " ,Courses.Title,Instructors.FirstName || ' ' || Instructors.LastName" +
                " From Students,Enrollments,Instructors,Sections ,Courses,CoursesinSections " +
                " where CoursesinSections.CourseId = Courses.Id And CoursesinSections.SectionNo = Sections.SectionNo" +
                " And CoursesinSections.InstructorId = Instructors.Id " +
                " And Enrollments.StudentId = Students.Id And Enrollments.CourseId = Courses.Id " +
                " And Enrollments.SectionNo = Sections.SectionNo And Students.Id = "+StudentId+"; ";

        Cursor cursor = database.rawQuery(getAll ,null );
        if (cursor.moveToFirst())
            do{
                StudentsEnrollmentInfoVM studentsEnrollmentInfo = new StudentsEnrollmentInfoVM();
                studentsEnrollmentInfo.setStudentId(cursor.getInt(0));
                studentsEnrollmentInfo.setStudentName(cursor.getString(1));
                studentsEnrollmentInfo.setSectionName(cursor.getString(2));
                studentsEnrollmentInfo.setCourseTitle(cursor.getString(3));
                studentsEnrollmentInfo.setInstructorName(cursor.getString(4));
                studentsEnrollmentsList.add(studentsEnrollmentInfo);
            }while (cursor.moveToNext());
        return  studentsEnrollmentsList;
    }
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
    public static List<StudentsEnrollmentInfoVM> getEnrollmentsStudentsInfo(DBContext context) {
        SQLiteDatabase database = context.getReadableDatabase() ;
        List<StudentsEnrollmentInfoVM> studentsEnrollmentsList = new ArrayList<>();
        String getAll = "Select Students.Id ,Students.FirstName || ' ' || Students.LastName ,Sections.SectionName" +
                " ,Courses.Title,Instructors.FirstName || ' ' || Instructors.LastName" +
                " From Students,Enrollments,Instructors,Sections ,Courses,CoursesinSections " +
                " where CoursesinSections.CourseId = Courses.Id And CoursesinSections.SectionNo = Sections.SectionNo" +
                " And CoursesinSections.InstructorId = Instructors.Id " +
                " And Enrollments.StudentId = Students.Id And Enrollments.CourseId = Courses.Id " +
                " And Enrollments.SectionNo = Sections.SectionNo; ";

        Cursor cursor = database.rawQuery(getAll ,null );
        if (cursor.moveToFirst())
            do{
                StudentsEnrollmentInfoVM studentsEnrollmentInfo = new StudentsEnrollmentInfoVM();
                studentsEnrollmentInfo.setStudentId(cursor.getInt(0));
                studentsEnrollmentInfo.setStudentName(cursor.getString(1));
                studentsEnrollmentInfo.setSectionName(cursor.getString(2));
                studentsEnrollmentInfo.setCourseTitle(cursor.getString(3));
                studentsEnrollmentInfo.setInstructorName(cursor.getString(4));
                studentsEnrollmentsList.add(studentsEnrollmentInfo);
            }while (cursor.moveToNext());
        return  studentsEnrollmentsList;
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
