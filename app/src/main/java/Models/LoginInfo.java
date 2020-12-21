package Models;


public class LoginInfo {
   int Id;
   String Username;
   String Password;
   Integer StudentId;

    public LoginInfo() {
    }

    public LoginInfo(String username, String password, Integer studentId) {
        Username = username;
        Password = password;
        StudentId = studentId;
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

}
