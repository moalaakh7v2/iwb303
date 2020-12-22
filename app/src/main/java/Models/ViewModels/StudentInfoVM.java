package Models.ViewModels;

public class StudentInfoVM {
    Integer Id ;
    String  Firstname;
    String Lastname;
    String Username;
    String Password;
    Integer RegYeer ;
    String Address;
    String MobileNo ;

    public StudentInfoVM() {
    }

    public StudentInfoVM(Integer id, String firstname, String lastname, String username, Integer regYeer, String address, String mobileNo) {
        Id = id;
        Firstname = firstname;
        Lastname = lastname;
        Username = username;
        RegYeer = regYeer;
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

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public Integer getRegYeer() {
        return RegYeer;
    }

    public void setRegYeer(Integer regYeer) {
        RegYeer = regYeer;
    }

    public String getPassword() { return Password; }

    public void setPassword(String password) {
        Password = password;
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
