package Controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Models.Instructor;

public class InstructorsController {
    public static Instructor GetInstructor(DBContext context, int Id)
    {
        String Query="Select *  From Instructors Where Id = "+Id+";";
        SQLiteDatabase database = context.getReadableDatabase();
        Cursor cursor = database.rawQuery(Query,null);
        if(cursor.moveToFirst()) {
            Instructor instructor = new Instructor();
            instructor.setId(cursor.getInt(0));
            instructor.setFirstname(cursor.getString(1));
            instructor.setLastname(cursor.getString(2));
            instructor.setGender(cursor.getString(3));
            instructor.setAddress(cursor.getString(4));
            instructor.setMobileNo(cursor.getString(5));
            return instructor;
        }
        return null;
    }

    public static List<Instructor> GetInstructors(DBContext context) {
        SQLiteDatabase database = context.getReadableDatabase() ;
        List<Instructor> InstructorsList = new ArrayList<>();
        String getAll = "Select * From Instructors; ";
        Cursor cursor = database.rawQuery(getAll ,null );
        if (cursor.moveToFirst())
            do{
                Instructor instructor = new Instructor();
                instructor.setId(cursor.getInt(0));
                instructor.setFirstname(cursor.getString(1));
                instructor.setLastname(cursor.getString(2));
                instructor.setGender(cursor.getString(3));
                instructor.setAddress(cursor.getString(4));
                instructor.setMobileNo(cursor.getString(5));
                InstructorsList.add(instructor);
            }while (cursor.moveToNext());
        return  InstructorsList;
    }

    public static void AddInstructor(DBContext context , Instructor instructor)
    {
        SQLiteDatabase database = context.getWritableDatabase();
        ContentValues instructorValues = new ContentValues();
        instructorValues.put("Firstname", instructor.getFirstname());
        instructorValues.put("Lastname", instructor.getLastname());
        instructorValues.put("Gender", instructor.getGender());
        instructorValues.put("Address", instructor.getAddress());
        instructorValues.put("MobileNo", instructor.getMobileNo());
        database.insert("Instructors",null,instructorValues);
        database.close();
    }
    public static void UpdateInstructor(DBContext context , Instructor instructor)
    {
        SQLiteDatabase database = context.getWritableDatabase();
        String Query = "Update Instructors set Firstname = '"+instructor.getFirstname()+"' , Lastname ='"+instructor.getLastname()+"' , Address = '"+instructor.getAddress()+"' , MobileNo=' "+instructor.getMobileNo()+"'" +
                " Where Id = "+instructor.getId()+" ;";
        database.execSQL(Query);
    }
    public static boolean deleteInstructor(DBContext context ,int Id)
    {
        SQLiteDatabase database = context.getWritableDatabase();
        Boolean IsCISDeleted = database.delete("CoursesinSections", " InstructorId =?", new String[]{String.valueOf(Id)}) > 0;
        Boolean IsIDeleted = database.delete("Instructors", " Id =?", new String[]{String.valueOf(Id)}) > 0;
        if(IsCISDeleted & IsIDeleted)
            return true;
        return false;
    }
}
