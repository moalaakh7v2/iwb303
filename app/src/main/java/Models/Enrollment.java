package Models;

public class Enrollment {
    int Id;
    int StudentId;
    int SectionNo;
    int CourseId;
    float Grade ;

    public Enrollment() {
    }

    public Enrollment(int studentId, int sectionNo, int courseId, float grade) {
        StudentId = studentId;
        SectionNo = sectionNo;
        CourseId = courseId;
        Grade = grade;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int studentId) {
        StudentId = studentId;
    }

    public int getSectionNo() {
        return SectionNo;
    }

    public void setSectionNo(int sectionNo) {
        SectionNo = sectionNo;
    }

    public int getCourseId() {
        return CourseId;
    }

    public void setCourseId(int courseId) {
        CourseId = courseId;
    }

    public float getGrade() {
        return Grade;
    }

    public void setGrade(float grade) {
        Grade = grade;
    }
}
