package com.example.shinple.vo;

import java.io.Serializable;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class MemberVO implements Serializable {

    private String mem_num; //사번
    //private String mem_password;  //비밀번호
    private String mem_name;  //이름
    private String mem_point;  //개인 점수
    private String company_num;  //회사 코드
    private String mem_noti;

    /* 생성자 */
    public MemberVO(String mem_num, String mem_name, String mem_point, String company_num, String mem_noti) {
        this.mem_num = mem_num;
        //this.mem_password = mem_password;
        this.mem_name = mem_name;
        this.mem_point = mem_point;
        this.company_num = company_num;
        this.mem_noti = mem_noti;
    }

    /* Getter */
    public String getMem_num() {
        return mem_num;
    }

    /*public String getMem_password() {
        return mem_password;
    }*/

    public String getMem_name() {
        return mem_name;
    }

    public String getMem_point() {
        return mem_point;
    }
    public void setMem_point(String point) {this.mem_point = mem_point;}

    public String getCompany_num() {
        return company_num;
    }
    public String getMem_noti(){return  mem_noti;}
    public void setMem_noti(String mem_noti){this.mem_noti = mem_noti;}
}
