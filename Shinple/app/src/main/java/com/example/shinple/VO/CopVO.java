package com.example.shinple.VO;

public class CopVO {
    String copName;
    String copRank;
    String copInfo;

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

    public String getCopInfo() {
        return copInfo;
    }

    public void setCopInfo(String copInfo) {
        this.copInfo = copInfo;
    }

    public CopVO(String copName, String copRank, String copInfo) {
        this.copName = copName;
        this.copRank = copRank;
        this.copInfo = copInfo;
    }
}
