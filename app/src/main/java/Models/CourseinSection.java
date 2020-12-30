package Models;

import java.sql.Time;

public class CourseinSection {
   Integer Id;
   Integer SectionNo;
   Integer CourseId;
   Integer InstructorId;
   String RoomNo;

    public CourseinSection() {
    }

    public CourseinSection(int sectionNo, int courseId, int instructorId, String roomNo) {
        SectionNo = sectionNo;
        CourseId = courseId;
        InstructorId = instructorId;
        RoomNo = roomNo;
    }

    public Integer getId() { return Id; }

    public void setId(Integer id) { Id = id; }

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
