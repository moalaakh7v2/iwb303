package Models;

//التسجيل
public class Enrollment {
    Integer Id;
    Integer StudentId;
    Integer SectionNo;
    Integer CourseId;
    Integer InstructorId;
    float Grade;

    public Enrollment() {
        Grade =0;
    }

    public Enrollment(Integer studentId, Integer sectionNo,Integer instructorId, Integer courseId, float grade) {
        StudentId = studentId;
        SectionNo = sectionNo;
        CourseId = courseId;
        InstructorId = instructorId;
        Grade = grade;
    }

    public Integer getInstructorId() {
        return InstructorId;
    }

    public void setInstructorId(Integer instructorId) {
        InstructorId = instructorId;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getStudentId() {
        return StudentId;
    }

    public void setStudentId(Integer studentId) {
        StudentId = studentId;
    }

    public Integer getSectionNo() {
        return SectionNo;
    }

    public void setSectionNo(Integer sectionNo) {
        SectionNo = sectionNo;
    }

    public Integer getCourseId() {
        return CourseId;
    }

    public void setCourseId(Integer courseId) {
        CourseId = courseId;
    }

    public float getGrade() {
        return Grade;
    }

    public void setGrade(float grade) {
        Grade = grade;
    }
}
