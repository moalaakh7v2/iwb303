package Models;

import java.time.LocalDateTime;

public class UserInfo {
   int UserId;
   String Username;
   String Password;
   boolean IsAdmin;
   LocalDateTime LastLoginDate;

    public UserInfo() {
    }

    public UserInfo(int userId, String username, String password, boolean isAdmin, LocalDateTime lastLoginDate) {
        UserId = userId;
        Username = username;
        Password = password;
        IsAdmin = isAdmin;
        LastLoginDate = lastLoginDate;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
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

    public boolean isAdmin() {
        return IsAdmin;
    }

    public void setAdmin(boolean admin) {
        IsAdmin = admin;
    }

    public LocalDateTime getLastLoginDate() {
        return LastLoginDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        LastLoginDate = lastLoginDate;
    }
}
