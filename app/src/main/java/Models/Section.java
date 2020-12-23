package Models;

public class Section {
    int SectionNo;
    String SectionName;

    public Section() {
    }

    public Section(int sectionNo, String sectionName) {
        SectionNo = sectionNo;
        SectionName = sectionName;
    }

    public int getSectionNo() {
        return SectionNo;
    }

    public void setSectionNo(int sectionNo) {
        SectionNo = sectionNo;
    }

    public String getSectionName() {
        return SectionName;
    }

    public void setSectionName(String sectionName) {
        SectionName = sectionName;
    }

    @Override
    public String toString() {
        return SectionName;
    }
}
