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
    public static CourseinSection GetCourseinSection(DBContext context, Integer sectionNo,Integer courseId )
    {
        String Query="Select *  From CoursesinSections " +
                "Where CourseId = "+courseId +" And SectionNo = "+sectionNo+";";
        SQLiteDatabase database = context.getReadableDatabase();
        Cursor cursor = database.rawQuery(Query,null);
        if(cursor.moveToFirst()) {
            CourseinSection courseinSection = new CourseinSection();
            courseinSection.setSectionNo(cursor.getInt(0));
            courseinSection.setCourseId(cursor.getInt(1));
            courseinSection.setInstructorId(cursor.getInt(2));
            courseinSection.setRoomNo(cursor.getString(3));
            return courseinSection;
        }
        return null;
    }

    public static List<CourseinSection> GetAllCoursesinSections(DBContext context) {
        SQLiteDatabase database = context.getReadableDatabase() ;
        List<CourseinSection> coursesinSectionsList = new ArrayList<>();
        String getAll = "Select * From CoursesinSections; ";
        Cursor cursor = database.rawQuery(getAll ,null );
        if (cursor.moveToFirst())
            do{
                CourseinSection courseinSection = new CourseinSection();
                courseinSection.setSectionNo(cursor.getInt(0));
                courseinSection.setCourseId(cursor.getInt(1));
                courseinSection.setInstructorId(cursor.getInt(2));
                courseinSection.setRoomNo(cursor.getString(3));
                coursesinSectionsList.add(courseinSection);
            }while (cursor.moveToNext());
        return  coursesinSectionsList;
    }
    public static List<CourseInfoVM> GetCoursesinSections(DBContext context, Integer StudentId,Integer SectionNo) {
        SQLiteDatabase database = context.getReadableDatabase() ;
        List<CourseInfoVM> coursesinSectionsList = new ArrayList<>();
        String getAll = "Select SectionName ,Title ,FristName ,LastName ,RoomNo" +
                " From Courses c,Sections s,Instructors i,CoursesinSections cs  " +
                " where cs.CourseId = c.CourseId And cs.SectionNo = s.SectionNo And cs.InstructorId = i.InstructorId" +
                " And SectionNo = "+SectionNo+" And CourseId not in (Select CourseId From Enrollments" +
                " where StudentId = "+StudentId+" And SectionNo = cs.SectionNo ); ";
        Cursor cursor = database.rawQuery(getAll ,null );
        if (cursor.moveToFirst())
            do{
                CourseInfoVM courseinSection = new CourseInfoVM();
                CourseInfoVM courseInfoVM = new CourseInfoVM();
                courseInfoVM.setSectionName(cursor.getString(0));
                courseInfoVM.setCourseTitle(cursor.getString(1));
                courseInfoVM.setInstructorName(cursor.getString(2) + " " + cursor.getString(3));
                courseInfoVM.setRoomNo(cursor.getString(4));
                coursesinSectionsList.add(courseinSection);
            }while (cursor.moveToNext());
        return  coursesinSectionsList;
    }
    public static List<CourseInfoVM> GetCoursesinSectionsInfo(DBContext context, Integer courseId,Integer sectionNo,Integer instructorId) {
        SQLiteDatabase database = context.getReadableDatabase() ;
        List<CourseInfoVM> coursesinSectionsList = new ArrayList<>();
        String getAll = "Select SectionName ,Title ,FristName ,LastName ,RoomNo From Courses c,Sections s,Instructors i,CoursesinSections cs " +
                " where cs.CourseId = c.CourseId And cs.SectionNo = s.SectionNo And cs.InstructorId = i.InstructorId" +
                " And CourseId = "+courseId+" And SectionNo = "+sectionNo+" And InstructorId = "+instructorId+" ;";
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
