package Controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Models.CourseinSection;
import Models.Section;

public class CoursesinSectionsController {
    public static CourseinSection CourseinSection(DBContext context, Integer sectionNo,Integer courseId )
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

    public static List<CourseinSection> GetCourseinSection(DBContext context) {
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
