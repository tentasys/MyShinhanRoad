package com.example.shinple.VO;

public class TagVO {
    /* 필드 변수 */
    String tag_Id;
    String tag_name;

    /* 생성자 */

    public TagVO(String tag_Id, String tag_name) {
        this.tag_Id = tag_Id;
        this.tag_name = tag_name;
    }

    /* Getter */
    public String getTag_Id() { return tag_Id;}
    public String getTag_name() { return tag_name; }

    /* Setter */
    public void setTag_Id(String tag_Id) { this.tag_Id = tag_Id; }
    public void setTag_name(String tag_name) {  this.tag_name = tag_name; }

}
