package com.example.shinple.VO;

public class CourseVO {

    String courseName;
    String courseLevel;
    String tchName;
    String cousrseText;
    String courseNum;

    public String getcourseName() {
        return courseName;
    }

    public void setcourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getcourseLevel() {
        return courseLevel;
    }

    public void setcourseLevel(String courseLevel) {
        this.courseLevel = courseLevel;
    }

    public String getTchName() {
        return tchName;
    }

    public void setTchName(String tchName) {
        this.tchName = tchName;
    }

    public String getCousrseText() {
        return cousrseText;
    }

    public void setCousrseText(String cousrseText) {
        this.cousrseText = cousrseText;
    }


    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNumo) {
        this.courseNum = courseNum;
    }

    public CourseVO(String courseName, String courseLevel,String tchName, String courseText, String courseNum) {
        this.courseName = courseName;
        this.courseLevel = courseLevel;
        this.tchName = tchName;
        this.cousrseText = courseText;
        this.courseNum = courseNum;
    }
}
