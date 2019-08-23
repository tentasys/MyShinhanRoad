package com.example.shinple.VO;

public class CopVO {
    String copName;
    String copRank;

    public String getCopName() {
        return copName;
    }

    public void setCopName(String copName) {
        this.copName = copName;
    }

    public String getCopRank() {
        return copRank;
    }

    public void setCopRank(String copRank) {
        this.copRank = copRank;
    }

    public CopVO(String copName, String copRank) {
        this.copName = copName;
        this.copRank = copRank;
    }
}
