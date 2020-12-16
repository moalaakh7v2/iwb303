package Models;

public class Student {
   int Id ;
   String  Firstname;
   String Lastname;
   String  Username;
   String Password ;
   int RegYeer ;
   int Gender;
   String Address;
   String mobileNo ;

    public Student(String firstname, String lastname, String username, String password, int regYeer, int gender, String address, String mobileNo) {
        Firstname = firstname;
        Lastname = lastname;
        Username = username;
        Password = password;
        RegYeer = regYeer;
        Gender = gender;
        Address = address;
        this.mobileNo = mobileNo;
    }

    public Student() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
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

    public int getRegYeer() {
        return RegYeer;
    }

    public void setRegYeer(int regYeer) {
        RegYeer = regYeer;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
