package Models.ViewModels;

public class CourseInfoVM {
    Integer SectionNo;
    Integer CourseId;
    Integer InstructorId;
    String SectionName;
    String CourseTitle;
    String InstructorName;
    String RoomNo;

    public CourseInfoVM() {
    }

    public CourseInfoVM(String sectionName, String courseTitle, String instructorName, String roomNo) {
        SectionName = sectionName;
        CourseTitle = courseTitle;
        InstructorName = instructorName;
        RoomNo = roomNo;
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

    public Integer getInstructorId() {
        return InstructorId;
    }

    public void setInstructorId(Integer instructorId) {
        InstructorId = instructorId;
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

    public String getRoomNo() {
        return RoomNo;
    }

    public void setRoomNo(String roomNo) {
        RoomNo = roomNo;
    }

    @Override
    public String toString() {
        if(CourseTitle != null)
             return  CourseTitle;
        return InstructorName;
    }
}
