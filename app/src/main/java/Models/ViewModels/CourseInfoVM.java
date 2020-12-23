package Models.ViewModels;

public class CourseInfoVM {
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
        return  CourseTitle;
    }
}
