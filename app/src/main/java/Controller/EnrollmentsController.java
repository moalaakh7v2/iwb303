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
        String getAll = "Select Students.Id ,Students.FirstName || ' ' || Students.LastName studentName,Sections.SectionName" +
                " ,Courses.Title,Instructors.FirstName || ' ' || Instructors.LastName TeacherName " +
                " From Students,Enrollments,Instructors,Sections ,Courses,CoursesinSections " +
                " where CoursesinSections.CourseId = Courses.Id And CoursesinSections.SectionNo = Sections.SectionNo" +
                " And CoursesinSections.InstructorId = Instructors.Id " +
                " And Enrollments.StudentId = Students.Id And Enrollments.CourseId = Courses.Id " +
                " And Enrollments.SectionNo = Sections.SectionNo And Students.Id = "+StudentId+"" +
                " group by studentName,TeacherName,Title,SectionName; ";

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
                enrollment.setInstructorId(cursor.getInt(2));
                enrollment.setStudentId(cursor.getInt(3));
                enrollment.setGrade(cursor.getFloat(4));
                EnrollmentsList.add(enrollment);
            }while (cursor.moveToNext());
        return  EnrollmentsList;
    }
    public static List<StudentsEnrollmentInfoVM> getEnrollmentsStudentsInfo(DBContext context) {
        SQLiteDatabase database = context.getReadableDatabase() ;
        List<StudentsEnrollmentInfoVM> studentsEnrollmentsList = new ArrayList<>();
        String getAll = "Select Students.Id ,Students.FirstName || ' ' || Students.LastName studentName,Sections.SectionName" +
                " ,Courses.Title,Instructors.FirstName || ' ' || Instructors.LastName TeacherName" +
                " From Students,Enrollments,Instructors,Sections ,Courses,CoursesinSections " +
                " where CoursesinSections.CourseId = Courses.Id And CoursesinSections.SectionNo = Sections.SectionNo" +
                " And CoursesinSections.InstructorId = Instructors.Id " +
                " And Enrollments.StudentId = Students.Id And Enrollments.CourseId = Courses.Id " +
                " And Enrollments.SectionNo = Sections.SectionNo" +
                " group by studentName,TeacherName,Title,SectionName; ";

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
        enrollmentValues.put("InstructorId", enrollment.getInstructorId());
        enrollmentValues.put("StudentId", enrollment.getStudentId());
        enrollmentValues.put("Grade", enrollment.getGrade());
        database.insert("Enrollments",null,enrollmentValues);
        database.close();
    }
    public static Boolean IsEnrollmentExist(DBContext context,Enrollment enrollment)
    {
        SQLiteDatabase database = context.getReadableDatabase() ;
        String getAll = "Select * From Enrollments " +
                " Where SectionNo = "+enrollment.getSectionNo()+" And CourseId = "+enrollment.getCourseId() +
                " And InstructorId = "+enrollment.getInstructorId()+" And StudentId = "+enrollment.getStudentId()+" ; ";
        Cursor cursor = database.rawQuery(getAll ,null );
        if (cursor.moveToFirst())
              return  true;
        return  false;
    }
}
