package Controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class DBContext extends SQLiteOpenHelper {
    public DBContext(@Nullable Context context) {
        super(context, "SVUDB", null, 1);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(SQLiteDatabase db) {
        String Students =
                "Create Table Students (" +
                "Id INTEGER Primary Key ," +
                "Firstname Text not null," +
                "Lastname Text not null," +
                "RegYeer int," +
                "Gender Text not null," +
                "Address Text not null," +
                "MobileNo Text not null " +
                ") " ;
        db.execSQL(Students);
        String LoginInfos ="Create Table LoginInfos (" +
                "Id INTEGER Primary Key AUTOINCREMENT NOT NULL," +
                "Username Text not null," +
                "Password Text not null," +
                "StudentId int," +
                "FOREIGN KEY(StudentId) REFERENCES Students(Id)" +
                ")";
        db.execSQL(LoginInfos);
               String Instructors =
                "Create Table Instructors (" +
                "Id INTEGER Primary Key AUTOINCREMENT NOT NULL," +
                "Firstname Text not null," +
                "Lastname Text not null," +
                "Gender Text not null," +
                "Address Text not null," +
                "MobileNo Text not null" +
                ")";
        db.execSQL(Instructors);
        String Sections =
                "Create Table Sections(" +
                "SectionNo INTEGER Primary Key AUTOINCREMENT NOT NULL," +
                "SectionName Text not null" +
                ") ";
        db.execSQL(Sections);
        String Courses =
                "Create Table Courses (" +
                "Id INTEGER Primary Key AUTOINCREMENT NOT NULL," +
                "Title Text not null," +
                "[Hours] int not null )" ;
        db.execSQL(Courses);
        String CoursesinSections =
                "Create Table CoursesinSections(" +
                "Id INTEGER Primary Key AUTOINCREMENT NOT NULL," +
                "SectionNo int not null," +
                "CourseId int not null," +
                "InstructorId int not null," +
                "RoomNo int not null," +
                "FOREIGN KEY(SectionNo) REFERENCES Sections(SectionNo)," +
                "FOREIGN KEY(CourseId) REFERENCES Courses(Id)," +
                "FOREIGN KEY(InstructorId) REFERENCES Instructors(Id)," +
                "CONSTRAINT UQ_CinS UNIQUE (SectionNo,CourseId,InstructorId,RoomNo)" +
                ")" ;
        db.execSQL(CoursesinSections);
        String Enrollments =
                "Create Table Enrollments (" +
                "Id INTEGER Primary Key AUTOINCREMENT NOT NULL," +
                "SectionNo int not null," +
                "CourseId int not null," +
                "InstructorId int not null," +
                "StudentId int not null," +
                "Grade float," +
                "FOREIGN KEY(SectionNo) REFERENCES Sections(SectionNo)," +
                "FOREIGN KEY(CourseId) REFERENCES Courses(Id)," +
                "FOREIGN KEY(StudentId) REFERENCES Students(Id)," +
                "FOREIGN KEY(InstructorId) REFERENCES Instructors(Id)" +
                ")" ;
        db.execSQL(Enrollments);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
