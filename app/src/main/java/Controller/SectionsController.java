package Controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Models.Course;
import Models.Section;

public class SectionsController {
    public static Section GetSection(DBContext context, int sectionNo)
    {
        String Query="Select *  From Sections Where SectionNo = "+sectionNo+";";
        SQLiteDatabase database = context.getReadableDatabase();
        Cursor cursor = database.rawQuery(Query,null);
        if(cursor.moveToFirst()) {
            Section section = new Section();
            section.setSectionNo(cursor.getInt(0));
            section.setSectionName(cursor.getString(1));
            return section;
        }
        return null;
    }

    public static List<Section> GetSections(DBContext context) {
        SQLiteDatabase database = context.getReadableDatabase() ;
        List<Section> SectionsList = new ArrayList<>();
        String getAll = "Select * From Sections; ";
        Cursor cursor = database.rawQuery(getAll ,null );
        if (cursor.moveToFirst())
            do{
                Section section = new Section();
                section.setSectionNo(cursor.getInt(0));
                section.setSectionName(cursor.getString(1));
                SectionsList.add(section);
            }while (cursor.moveToNext());
        return  SectionsList;
    }

    public static void AddSection(DBContext context , Section section)
    {
        SQLiteDatabase database = context.getWritableDatabase();
        ContentValues SectionValues = new ContentValues();
        SectionValues.put("SectionNo", section.getSectionNo());
        SectionValues.put("SectionName", section.getSectionName());
        database.insert("Sections",null,SectionValues);
        database.close();
    }
    public static void UpdateSection(DBContext context , Section section)
    {
        SQLiteDatabase database = context.getWritableDatabase();
        String Query = "Update Sections set  SectionName ='"+section.getSectionName()+"' "+
                "Where Id = "+section.getSectionNo()+" ;";
        database.execSQL(Query);
    }
    public static boolean deleteSection(DBContext context ,int sectionNo)
    {
        SQLiteDatabase database = context.getWritableDatabase();
        Boolean IsCISDeleted = database.delete("CoursesinSections", " SectionNo =?", new String[]{String.valueOf(sectionNo)}) > 0;
        Boolean IsSDeleted = database.delete("Sections", " SectionNo =?", new String[]{String.valueOf(sectionNo)}) > 0;
        if(IsCISDeleted & IsSDeleted)
            return true;
        return false;
    }
}
