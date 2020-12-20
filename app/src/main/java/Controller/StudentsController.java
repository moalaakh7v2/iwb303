package Controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Models.Enums.Gender;
import Models.Student;
import Models.LoginInfo;
import Models.ViewModels.StudentInfoVM;

public class StudentsController {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void initAdmin(DBContext context){
        String Query= "Select * From LoginInfos;";
        SQLiteDatabase database = context.getReadableDatabase();
        Cursor cursor = database.rawQuery(Query,null);
        if(!cursor.moveToFirst()) {
            LoginInfo userInfo =new LoginInfo(3,"Emad7","123456",null, LocalDateTime.now());
            AddAdmin(context,userInfo);
        }
    }
    static void AddAdmin(DBContext context,LoginInfo loginInfo){
        SQLiteDatabase database = context.getWritableDatabase();
        ContentValues userInfoValues = new ContentValues();
        userInfoValues.put("Id", loginInfo.getId());
        userInfoValues.put("Username", loginInfo.getUsername());
        userInfoValues.put("Password", loginInfo.getPassword());
        userInfoValues.put("LastLoginDate", String.valueOf(LoginInfo.getLastLoginDate()));
        database.insert("LoginInfos",null,userInfoValues);
        database.close();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static LoginInfo Login(DBContext context, String username, String password){
        initAdmin(context);
        String Query="Select * From LoginInfos" +
                " Where UPPER(Username) = UPPER('"+username +"') And Password = '"+password +"' ;";
        SQLiteDatabase database = context.getReadableDatabase();
        Cursor cursor = database.rawQuery(Query,null);
        if(cursor.moveToFirst()) {
            LoginInfo userInfo = new LoginInfo();
            userInfo.setId(cursor.getInt(0));
            userInfo.setUsername(cursor.getString(1));
            userInfo.setPassword(cursor.getString(2));
            userInfo.setStudentId(cursor.getInt(3));
            userInfo.setLastLoginDate(LocalDateTime.parse(cursor.getString(4)));
            return userInfo;
        }
        return null;
    }
    public static StudentInfoVM GetStudentInfo(DBContext context, int Id)
    {
        String Query="Select Student.* , Username From Student,LoginInfos" +
                " Where Student.Id = StudentId And Id = "+Id+";";
        SQLiteDatabase database = context.getReadableDatabase();
        Cursor cursor = database.rawQuery(Query,null);
        if(cursor.moveToFirst()) {
            StudentInfoVM studentInfo = new StudentInfoVM();
            studentInfo.setId(cursor.getInt(0));
            studentInfo.setFirstname(cursor.getString(1));
            studentInfo.setLastname(cursor.getString(2));
            studentInfo.setRegYeer(cursor.getInt(3));
            studentInfo.setGender(cursor.getString(4));
            studentInfo.setAddress(cursor.getString(5));
            studentInfo.setMobileNo(cursor.getString(6));
            studentInfo.setUsername(cursor.getString(7));
            return studentInfo;
        }
        return null;
    }

    public static List<StudentInfoVM> getStudents(DBContext context) {
        SQLiteDatabase database = context.getReadableDatabase() ;
        List<StudentInfoVM> StudentsList = new ArrayList<>();
        String getAll = "Select Student.* , Username From Student,LoginInfos " +
                " Where Student.Id = StudentId  ";

        Cursor cursor = database.rawQuery(getAll ,null );
        if (cursor.moveToFirst())
            do{
                StudentInfoVM studentInfo = new StudentInfoVM();
                studentInfo.setId(cursor.getInt(0));
                studentInfo.setFirstname(cursor.getString(1));
                studentInfo.setLastname(cursor.getString(2));
                studentInfo.setRegYeer(cursor.getInt(3));
                studentInfo.setGender(cursor.getString(4));
                studentInfo.setAddress(cursor.getString(5));
                studentInfo.setMobileNo(cursor.getString(6));
                studentInfo.setUsername(cursor.getString(7));
                StudentsList.add(studentInfo);

            }while (cursor.moveToNext());
        return  StudentsList;
    }

    public static void AddStudent(DBContext context , Student user, LoginInfo LoginInfo)
    {
        SQLiteDatabase database = context.getWritableDatabase();
        int studentId =GetMaxId(context) + 1;
        ContentValues UserValues = new ContentValues();
        UserValues.put("Id", studentId);
        UserValues.put("Firstname", user.getFirstname());
        UserValues.put("Lastname", user.getLastname());
        UserValues.put("Gender", user.getGender());
        UserValues.put("Address", user.getAddress());
        UserValues.put("MobileNo", user.getMobileNo());
        UserValues.put("RegYeer", user.getRegYeer());
        database.insert("Students",null,UserValues);
        ContentValues userInfoValues = new ContentValues();
        userInfoValues.put("Id", LoginInfo.getId());
        userInfoValues.put("Username", LoginInfo.getUsername());
        userInfoValues.put("Password", LoginInfo.getPassword());
        userInfoValues.put("StudentId", studentId);
        userInfoValues.put("LastLoginDate", String.valueOf(LoginInfo.getLastLoginDate()));
        database.insert("LoginInfos",null,userInfoValues);
        database.close();
    }
    public static void UpdateStudent(DBContext context , Student user, LoginInfo userInfo)
    {
        SQLiteDatabase database = context.getWritableDatabase();
        String Query = "Update Students set Firstname = '"+user.getFirstname()+"' , Lastname ='"+user.getLastname()+"' , Address = '"+user.getAddress()+"' ,Gender ='"+user.getGender()+"',RegYeer ="+user.getRegYeer()+",MobileNo='"+user.getMobileNo()+"'";
        database.execSQL(Query);
    }
    public static boolean deleteStudent(DBContext context ,int Id)
    {
        SQLiteDatabase database = context.getWritableDatabase();
        Boolean IsLDeleted = database.delete("LoginInfos", " StudentId =?", new String[]{String.valueOf(Id)}) > 0;
        Boolean  IsSDeleted = database.delete("Students", " Id =?", new String[]{String.valueOf(Id)}) > 0;
        if(IsLDeleted & IsSDeleted)
            return true;
        else
            return false;
    }
    public static int GetMaxId(DBContext context)
    {
        int maxId = 0;
        String Query = "Select Max(Id) From Students;";
        SQLiteDatabase database = context.getReadableDatabase();
        Cursor cursor = database.rawQuery(Query,null);
        if(cursor.moveToFirst()) {
            maxId = cursor.getInt(0);
        }
        return maxId;
    }
}
