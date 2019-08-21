package com.example.shinple.VO;

public class LectureVO {
    String lectureNum;
    String lectureName;
    String lectureTag;

    public String getlectureNum() {
        return lectureNum ;
    }

    public void setlectureNum(String lectureNum) {
        this.lectureNum = lectureNum;
    }

    public String getlectureName() {
        return lectureName;
    }

    public void setlectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public String getlectureTag() {
        return lectureTag;
    }

    public void setlectureTag(String lectureTag) {
        this.lectureTag = lectureTag;
    }

    public LectureVO(String lectureNum, String lectureName, String lectureTag) {
        this.lectureNum = lectureNum;
        this.lectureName = lectureName;
        this.lectureTag = lectureTag;
    }
}