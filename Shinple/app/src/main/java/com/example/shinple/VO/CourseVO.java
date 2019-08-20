package com.example.shinple.VO;

public class CourseVO {

    String courseName;
    String courseLevel;
    String tagName;

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

    public String gettagName() {
        return tagName;
    }

    public void settagName(String tagName) {
        this.tagName = tagName;
    }

    public CourseVO(String courseName, String courseLevel, String tagName) {
        this.courseName = courseName;
        this.courseLevel = courseLevel;
        this.tagName = tagName;
    }
}
