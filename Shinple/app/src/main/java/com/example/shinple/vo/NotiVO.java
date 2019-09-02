package com.example.shinple.vo;

import java.io.Serializable;

public class NotiVO implements Serializable {
    private String Title;
    private String Nocontext;
    private String date;



    public String getTitle() {
        return Title ;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getnoContext() {
        return Nocontext;
    }

    public void setnoContext(String nocontext) {
        this.Nocontext = nocontext;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public NotiVO(String Title, String nocontext, String date) {
        this.Title = Title;
        this.Nocontext = nocontext;
        this.date = date;
    }

}
