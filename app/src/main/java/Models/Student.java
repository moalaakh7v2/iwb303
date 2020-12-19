package Models;

public class Student {
   int Id ;
   String  Firstname;
   String Lastname;
   Integer RegYeer ;
   String Gender;
   String Address;
   String MobileNo;

    public Student() { }

    public Student(String firstname, String lastname, String gender, String address, String mobileNo) {
        Firstname = firstname;
        Lastname = lastname;
        Gender = gender;
        Address = address;
        MobileNo = mobileNo;
    }

    public Student(String firstname, String lastname, Integer regYeer, String gender, String address, String mobileNo) {
        Firstname = firstname;
        Lastname = lastname;
        RegYeer = regYeer;
        Gender = gender;
        Address = address;
        MobileNo = mobileNo;
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

    public Integer getRegYeer() {
        return RegYeer;
    }

    public void setRegYeer(Integer regYeer) {
        RegYeer = regYeer;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }
}
