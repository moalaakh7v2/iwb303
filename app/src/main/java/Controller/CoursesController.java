package Controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Models.Course;
import Models.LoginInfo;
import Models.Student;
import Models.ViewModels.StudentInfoVM;

public class CoursesController {
    public static Course GetCourse(DBContext context, int Id)
    {
        String Query="Select *  From Courses Where Id = "+Id+";";
        SQLiteDatabase database = context.getReadableDatabase();
        Cursor cursor = database.rawQuery(Query,null);
        if(cursor.moveToFirst()) {
            Course course = new Course();
            course.setId(cursor.getInt(0));
            course.setTitle(cursor.getString(1));
            course.setHours(cursor.getInt(2));
            course.setSectionNo(cursor.getInt(3));
            return course;
        }
        return null;
    }

    public static List<Course> GetCourses(DBContext context) {
        SQLiteDatabase database = context.getReadableDatabase() ;
        List<Course> CoursesList = new ArrayList<>();
        String getAll = "Select * From Courses; ";
        Cursor cursor = database.rawQuery(getAll ,null );
        if (cursor.moveToFirst())
            do{
                Course course = new Course();
                course.setId(cursor.getInt(0));
                course.setTitle(cursor.getString(1));
                course.setHours(cursor.getInt(2));
                course.setSectionNo(cursor.getInt(3));
                CoursesList.add(course);
            }while (cursor.moveToNext());
        return  CoursesList;
    }

    public static void AddCourse(DBContext context , Course course)
    {
        SQLiteDatabase database = context.getWritableDatabase();
        ContentValues CourseValues = new ContentValues();
        CourseValues.put("Title", course.getTitle());
        CourseValues.put("Hours", course.getHours());
        CourseValues.put("SectionNo", course.getSectionNo());
        database.insert("Courses",null,CourseValues);
        database.close();
    }
    public static void UpdateCourse(DBContext context ,Course course)
    {
        SQLiteDatabase database = context.getWritableDatabase();
        String Query = "Update Courses set Title = '"+course.getTitle()+"' , Hours ="+course.getHours()+" , SectionNo = "+course.getSectionNo() +
                "Where Id = "+course.getId()+" ;";
        database.execSQL(Query);
    }
    public static boolean deleteCourse(DBContext context ,int Id)
    {
        SQLiteDatabase database = context.getWritableDatabase();
        return database.delete("Courses", " Id =?", new String[]{String.valueOf(Id)}) > 0;
    }
}
