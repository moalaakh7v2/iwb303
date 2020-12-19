package Models;

import java.time.LocalDateTime;

public class LoginInfo {
   int Id;
   String Username;
   String Password;
   Integer StudentId;
   LocalDateTime LastLoginDate;

    public LoginInfo() {
    }

    public LoginInfo(int userId, String username, String password, Integer studentId, LocalDateTime lastLoginDate) {
        Id = userId;
        Username = username;
        Password = password;
        StudentId = studentId;
        LastLoginDate = lastLoginDate;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Integer getStudentId() {
        return StudentId;
    }

    public void setStudentId(Integer studentId) {
        StudentId = studentId;
    }

    public LocalDateTime getLastLoginDate() {
        return LastLoginDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        LastLoginDate = lastLoginDate;
    }
}
