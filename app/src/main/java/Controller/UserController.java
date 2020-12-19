package Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;

import Models.Enums.Gender;
import Models.User;
import Models.UserInfo;

public class UserController {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void initAdmin(DBContext context){
        User user =new User("Emad","Kh", Gender.Male.toString()
                ,"Damas","0931640066" );
        UserInfo userInfo =new UserInfo(1,"Emad7","123456",true, LocalDateTime.now());
        AddUser(context,user,userInfo);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static UserInfo Login(DBContext context, String username, String password){
        initAdmin(context);
        String Query="Select * From UsersInfo" +
                " Where UPPER(Username) = UPPER('"+username +"') And Password = '"+password +"' ;";
        SQLiteDatabase database = context.getReadableDatabase();
        Cursor cursor = database.rawQuery(Query,null);
        if(cursor.moveToFirst()) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(cursor.getInt(0));
            userInfo.setUsername(cursor.getString(1));
            userInfo.setPassword(cursor.getString(2));
            String  m = cursor.getString(3);
            Boolean k =Boolean.valueOf(cursor.getString(3));
            userInfo.setIsAdmin(k);
            userInfo.setLastLoginDate(LocalDateTime.parse(cursor.getString(4)));
            return userInfo;
        }
        return null;
    }

    public static void AddUser(DBContext context ,User user, UserInfo userInfo)
    {
        SQLiteDatabase database = context.getWritableDatabase();
        ContentValues UserValues = new ContentValues();
        UserValues.put("Firstname", user.getFirstname());
        UserValues.put("Lastname", user.getLastname());
        UserValues.put("Gender", user.getGender());
        UserValues.put("Address", user.getAddress());
        UserValues.put("MobileNo", user.getMobileNo());
        if(user.getRegYeer() != null)
            UserValues.put("RegYeer", user.getRegYeer());
        database.insert("Users",null,UserValues);
        ContentValues userInfoValues = new ContentValues();
        userInfoValues.put("UserId", userInfo.getUserId());
        userInfoValues.put("Username", userInfo.getUsername());
        userInfoValues.put("Password", userInfo.getPassword());
        userInfoValues.put("IsAdmin", userInfo.getIsAdmin());
        userInfoValues.put("LastLoginDate", String.valueOf(userInfo.getLastLoginDate()));
        database.insert("UsersInfo",null,userInfoValues);
        database.close();
    }
}
