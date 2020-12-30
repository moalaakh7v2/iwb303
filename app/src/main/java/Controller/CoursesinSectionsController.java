package Controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Models.CourseinSection;
import Models.Section;
import Models.Student;
import Models.ViewModels.CourseInfoVM;

public class CoursesinSectionsController {

    public static int GetCountCourseinSection(DBContext context,CourseinSection courseinSection)
    {
        String Query="Select Count(*) From coursesinsections " +
                "Where CourseId = "+courseinSection.getCourseId() +" And SectionNo = "+courseinSection.getSectionNo()+
                " And InstructorId = " + courseinSection.getInstructorId() + " And RoomNo = '"+courseinSection.getRoomNo()+"' ;";
        SQLiteDatabase database = context.getReadableDatabase();
        Cursor cursor = database.rawQuery(Query,null);
        if(cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        return 0;
    }

    public static List<CourseinSection> GetAllCoursesinSections(DBContext context) {
        SQLiteDatabase database = context.getReadableDatabase() ;
        List<CourseinSection> coursesinSectionsList = new ArrayList<>();
        String getAll = "Select * From CoursesinSections; ";
        Cursor cursor = database.rawQuery(getAll ,null );
        if (cursor.moveToFirst())
            do{
                CourseinSection courseinSection = new CourseinSection();
                courseinSection.setId(cursor.getInt(0));
                courseinSection.setSectionNo(cursor.getInt(1));
                courseinSection.setCourseId(cursor.getInt(2));
                courseinSection.setInstructorId(cursor.getInt(3));
                courseinSection.setRoomNo(cursor.getString(4));
                coursesinSectionsList.add(courseinSection);
            }while (cursor.moveToNext());
        return  coursesinSectionsList;
    }
    public static List<CourseInfoVM> GetCoursesinSections(DBContext context, Integer StudentId,Integer SectionNo) {
        SQLiteDatabase database = context.getReadableDatabase() ;
        List<CourseInfoVM> coursesinSectionsList = new ArrayList<>();
        String getAll = "Select SectionName ,Title ,FirstName ,LastName ,RoomNo ,CourseId ,InstructorId" +
                " From Courses c,Sections s,Instructors i,CoursesinSections cs  " +
                " where cs.CourseId = c.Id And cs.SectionNo = s.SectionNo And cs.InstructorId = i.Id" +
                " And cs.SectionNo = "+SectionNo+" And CourseId not in (Select CourseId From Enrollments" +
                " where StudentId = "+StudentId+" And SectionNo = cs.SectionNo ); ";
        Cursor cursor = database.rawQuery(getAll ,null );
        if (cursor.moveToFirst())
            do{
                CourseInfoVM courseInfoVM = new CourseInfoVM();
                courseInfoVM.setSectionName(cursor.getString(0));
                courseInfoVM.setCourseTitle(cursor.getString(1));
                courseInfoVM.setInstructorName(cursor.getString(2) + " " + cursor.getString(3));
                courseInfoVM.setRoomNo(cursor.getString(4));
                courseInfoVM.setSectionNo(SectionNo);
                courseInfoVM.setCourseId(cursor.getInt(5));
                courseInfoVM.setInstructorId(cursor.getInt(6));
                coursesinSectionsList.add(courseInfoVM);
            }while (cursor.moveToNext());
        return  coursesinSectionsList;
    }
    public static List<CourseInfoVM> GetInstructorsinCoursesinSections(DBContext context, Integer SectionNo,Integer CourseId) {
        SQLiteDatabase database = context.getReadableDatabase() ;
        List<CourseInfoVM> InstructorsinCoursesinSectionsList = new ArrayList<>();
        String getAll = "Select FirstName ,LastName ,InstructorId" +
                " From Courses c,Sections s,Instructors i,CoursesinSections cs  " +
                " where cs.CourseId = c.Id And cs.SectionNo = s.SectionNo And cs.InstructorId = i.Id" +
                " And cs.SectionNo = "+SectionNo+" And cs.CourseId = "+CourseId+" ; ";
        Cursor cursor = database.rawQuery(getAll ,null );
        if (cursor.moveToFirst())
            do{
                CourseInfoVM courseInfoVM = new CourseInfoVM();
                courseInfoVM.setInstructorName(cursor.getString(0) + " " + cursor.getString(1));
                courseInfoVM.setInstructorId(cursor.getInt(2));
                InstructorsinCoursesinSectionsList.add(courseInfoVM);
            }while (cursor.moveToNext());
        return  InstructorsinCoursesinSectionsList;
    }
    public static List<CourseInfoVM> GetCoursesinSectionsInfo(DBContext context, Integer courseId,Integer sectionNo,Integer instructorId) {
        SQLiteDatabase database = context.getReadableDatabase() ;
        List<CourseInfoVM> coursesinSectionsList = new ArrayList<>();
        String getAll = "Select SectionName ,Title ,FirstName ,LastName ,RoomNo From Courses c,Sections s,Instructors i,CoursesinSections cs " +
                " where cs.CourseId = c.Id And cs.SectionNo = s.SectionNo And cs.InstructorId = i.Id" +
                " And cs.CourseId = "+courseId+" And cs.SectionNo = "+sectionNo+" And cs.InstructorId = "+instructorId+" ;";
        Cursor cursor = database.rawQuery(getAll ,null );
        if (cursor.moveToFirst())
            do{
                CourseInfoVM courseInfoVM = new CourseInfoVM();
                courseInfoVM.setSectionName(cursor.getString(0));
                courseInfoVM.setCourseTitle(cursor.getString(1));
                courseInfoVM.setInstructorName(cursor.getString(2) + " " + cursor.getString(3));
                courseInfoVM.setRoomNo(cursor.getString(4));
                coursesinSectionsList.add(courseInfoVM);
            }while (cursor.moveToNext());
        return  coursesinSectionsList;
    }
    public static void AddCourseinSection(DBContext context , CourseinSection courseinSection)
    {
        SQLiteDatabase database = context.getWritableDatabase();
        ContentValues courseinSectionValues = new ContentValues();
        courseinSectionValues.put("SectionNo", courseinSection.getSectionNo());
        courseinSectionValues.put("CourseId", courseinSection.getCourseId());
        courseinSectionValues.put("InstructorId", courseinSection.getInstructorId());
        courseinSectionValues.put("RoomNo", courseinSection.getRoomNo());
        database.insert("CoursesinSections",null,courseinSectionValues);
        database.close();
    }
}
