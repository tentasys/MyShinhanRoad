package com.example.shinple.VO;

import java.util.logging.Level;

public class LectureVO {
    String lec_title;
    String lec_order;
    String lec_text;
    String lec_time;
    String recent_time;

    public String getLec_title() {
        return lec_title ;
    }

    public void setLec_title(String lec_title) {
        this.lec_title = lec_title;
    }

    public String getLec_order() {
        return lec_order;
    }

    public void setLec_order(String lec_order) {
        this.lec_order = lec_order;
    }

    public String getLec_time() {
        return lec_time;
    }

    public void setLec_time(String lec_time) {
        this.lec_time = lec_time;
    }


    public String getLec_text() {
        return lec_text;
    }

    public void setLec_text(String lec_text) {
        this.lec_text = lec_text;
    }


    public String getRecent_time() {
        return recent_time;
    }

    public LectureVO(String lec_title, String lec_order, String lec_text, String lec_time,String recent_time) {
        this.lec_title = lec_title;
        this.lec_order = lec_order;
        this.lec_time = lec_time;
        this.lec_text = lec_text;
        this.recent_time = recent_time;
    }
}