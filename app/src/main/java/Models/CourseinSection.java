package Models;

import java.sql.Time;

public class CourseinSection {
   int SectionNo;
   int CourseId;
   int InstructorId;
   Time Time;
   String RoomNo;

    public CourseinSection() {
    }

    public CourseinSection(int sectionNo, int courseId, int instructorId, String roomNo) {
        SectionNo = sectionNo;
        CourseId = courseId;
        InstructorId = instructorId;
        RoomNo = roomNo;
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

    public int getInstructorId() {
        return InstructorId;
    }

    public void setInstructorId(int instructorId) {
        InstructorId = instructorId;
    }

    public String getRoomNo() {
        return RoomNo;
    }

    public void setRoomNo(String roomNo) {
        RoomNo = roomNo;
    }
}
