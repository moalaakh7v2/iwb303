package Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.time.LocalDateTime;

import Models.Enums.Gender;
import Models.User;
import Models.UserInfo;

public class DBContext extends SQLiteOpenHelper {
    public DBContext(@Nullable Context context) {
        super(context, "SVUDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Users =
                "Create Table Users (" +
                "Id int Primary Key," +
                "Firstname Text not null," +
                "Lastname Text not null," +
                "RegYeer int," +
                "Gender Text not null," +
                "[Address] Text not null," +
                "MobileNo Text not null " +
                ") " ;
        db.execSQL(Users);
        String UsersInfo =
                "Create Table UsersInfo (" +
                "UserId int Primary Key," +
                "Username Text not null," +
                "[Password] Text not null," +
                "IsAdmin bit not null," +
                "LastLoginDate Date," +
                "FOREIGN KEY(UserId) REFERENCES Users(Id)" +
                ")";
        db.execSQL(UsersInfo);
        String Sections =
                "Create Table Sections(" +
                "SectionNo int Primary Key," +
                "SectionName Text not null" +
                ") ";
        db.execSQL(Sections);
        String Courses =
                "Create Table Courses (" +
                "Id int Primary Key," +
                "Title Text not null," +
                "[Hours] int not null ," +
                "SectionNo int not null ," +
                "FOREIGN KEY(SectionNo) REFERENCES Sections(SectionNo)" +
                ")"  ;
        db.execSQL(Courses);
        String CoursesinSections =
                "Create Table CoursesinSections(" +
                "SectionNo int not null," +
                "CourseId int not null," +
                "InstructorId int not null," +
                "[Time] Time not null, " +
                "RoomNo int not null," +
                "PRIMARY KEY (SectionNo, CourseId)," +
                "FOREIGN KEY(SectionNo) REFERENCES Sections(SectionNo)," +
                "FOREIGN KEY(CourseId) REFERENCES Courses(Id)," +
                "FOREIGN KEY(InstructorId) REFERENCES Users(Id)" +
                ")" ;
        db.execSQL(CoursesinSections);
        String Enrollments =
                "Create Table Enrollments (" +
                "Id int Primary Key," +
                "StudentId int not null," +
                "SectionNo int not null," +
                "CourseId int not null," +
                "Grade float," +
                "FOREIGN KEY(SectionNo) REFERENCES Sections(SectionNo)," +
                "FOREIGN KEY(CourseId) REFERENCES Courses(Id)," +
                "FOREIGN KEY(StudentId) REFERENCES Users(Id)" +
                ")" ;
        db.execSQL(Enrollments);
        initAdmin();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void initAdmin(){
        User user =new User("Emad","Kh", 2020, Gender.Male.toString()
                ,"Damas","0931640066" );
       SQLiteDatabase database = this.getWritableDatabase();
        ContentValues UserValues = new ContentValues();
        UserValues.put("Firstname", user.getFirstname());
        UserValues.put("Lastname", user.getLastname());
        UserValues.put("Gender", user.getGender());
        UserValues.put("Address", user.getAddress());
        UserValues.put("mobileNo", user.getMobileNo());
        database.insert("Student",null,UserValues);
        UserInfo userInfo =new UserInfo(1,"Emad7","123456",true, LocalDateTime.now())
        ContentValues userInfoValues = new ContentValues();
        userInfoValues.put("Firstname", user.getFirstname());
        userInfoValues.put("Lastname", user.getLastname());
        userInfoValues.put("RegYeer", user.getRegYeer());
        userInfoValues.put("Gender", user.getGender());
        userInfoValues.put("Address", user.getAddress());
        userInfoValues.put("mobileNo", user.getMobileNo());
    }
    public void ForAdd (User user,UserInfo userInfo)
    {

    }

    public boolean Login(String username,String password){

        String Query="Select * From Student" +
                " Where UPPER(Username) = UPPER('"+username +"') And Password = "+password +" ;";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(Query,null);
        if(cursor.moveToFirst())
            return true;
        return false;
    }
}
