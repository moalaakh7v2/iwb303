package Models.ViewModels;

public class StudentsEnrollmentInfoVM {
    Integer StudentId;
    String StudentName;
    String SectionName;
    String CourseTitle;
    String InstructorName;

    public StudentsEnrollmentInfoVM() {
    }

    public StudentsEnrollmentInfoVM(Integer studentId, String studentName, String sectionName, String courseTitle, String instructorName) {
        StudentId = studentId;
        StudentName = studentName;
        SectionName = sectionName;
        CourseTitle = courseTitle;
        InstructorName = instructorName;
    }

    public Integer getStudentId() {
        return StudentId;
    }

    public void setStudentId(Integer studentId) {
        StudentId = studentId;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getSectionName() {
        return SectionName;
    }

    public void setSectionName(String sectionName) {
        SectionName = sectionName;
    }

    public String getCourseTitle() {
        return CourseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        CourseTitle = courseTitle;
    }

    public String getInstructorName() {
        return InstructorName;
    }

    public void setInstructorName(String instructorName) {
        InstructorName = instructorName;
    }
}
