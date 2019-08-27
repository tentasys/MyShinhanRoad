package com.example.shinple.VO;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class UserVO {

    private String userId;
    private String userPassword;
    private String userName;
    private String comCode;


    public UserVO(String userId, String userPassword, String userName, String comCode) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.comCode = comCode;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getComCode() {
        return comCode;
    }
}
