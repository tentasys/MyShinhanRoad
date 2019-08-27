package com.example.shinple.data;

import com.example.shinple.VO.UserVO;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<UserVO> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication

            /*TODO : DB에 정보 넘겨주고 받아오기 */
            UserVO fakeUser = new UserVO("id","password","name","company_code");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
