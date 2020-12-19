package Models;

//الكورس
public class Course {
    int Id;
    String Title;
    int Hours;
    int SectionNo;

    public Course() {
    }
    public Course(String title, int hours, int sectionNo) {
        Title = title;
        Hours = hours;
        SectionNo = sectionNo;
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

    public int getSectionNo() {
        return SectionNo;
    }

    public void setSectionNo(int sectionNo) {
        SectionNo = sectionNo;
    }
}
