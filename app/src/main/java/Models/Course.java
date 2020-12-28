package Models;

//الكورس
public class Course {
    int Id;
    String Title;
    int Hours;

    public Course() {
    }
    public Course(String title, int hours) {
        Title = title;
        Hours = hours;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getHours() {
        return Hours;
    }

    public void setHours(int hours) {
        Hours = hours;
    }

    @Override
    public String toString() {
        return Title;
    }
}
