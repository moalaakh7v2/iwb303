package Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import Models.Student;

public class DBContext extends SQLiteOpenHelper {
    public DBContext(@Nullable Context context) {
        super(context, "SVUDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Query = "create table if not exists  Student (" +
                "Id int Primary Key ," +
                "Firstname Text, " +
                "Lastname Text, " +
                "Username Text, " +
                "Password Text, " +
                "RegYeer int, " +
                "Gender int ," +
                "Address Text ," +
                "mobileNo Text );";
        db.execSQL(Query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void Create(){

        ForAdd(new Student("Emad","Kh","Emad7","123456", 2020,0,"Damas","09316" +
                "40066"));
    }

    public boolean Login(String username,String password){
        Create();
        String Query="Select * From Student" +
                     " Where UPPER(Username) = UPPER('"+username +"') And Password = "+password +" ;";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(Query,null);
        if(cursor.moveToFirst())
            return true;
        return false;
    }
    public void ForAdd (Student student)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Firstname",student.getFirstname());
        contentValues.put("Lastname",student.getLastname());
        contentValues.put("Username",student.getUsername());
        contentValues.put("Password",student.getPassword());
        contentValues.put("RegYeer",student.getRegYeer());
        contentValues.put("Gender",student.getGender());
        contentValues.put("Address",student.getAddress());
        contentValues.put("mobileNo",student.getMobileNo());
        database.insert("Student",null,contentValues);
    }
}
