package com.example.shinple.vo;

import java.io.Serializable;

public class CourseVO implements Serializable {

    String courseName;
    String courseLevel;
    String tchName;
    String cousrseText;
    String courseNum;
    String LearnState;
    String Like;
    String mem_like;

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

    public String getLearnState() { return  LearnState; }
    public  void setLearnState(String learnState){this.LearnState = learnState;}

    public String getLike() { return  Like; }
    public String getMem_like() { return  mem_like; }
    public void setMem_like(String mem_like){ this.mem_like = mem_like;}

    public CourseVO(String courseName, String courseLevel,String tchName, String courseText, String courseNum, String LearnState, String Like, String mem_like) {
        this.courseName = courseName;
        this.courseLevel = courseLevel;
        this.tchName = tchName;
        this.cousrseText = courseText;
        this.courseNum = courseNum;
        this.LearnState = LearnState;
        this.Like = Like;
        this.mem_like = mem_like;
    }
}
