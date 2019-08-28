package com.example.shinple.data;

import com.example.shinple.BackgroundTask;
import com.example.shinple.VO.CourseVO;
import com.example.shinple.VO.UserVO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {


    public Result<UserVO> login(String uid, String upw) {

            // TODO: handle loggedInUser authentication
        String data = "";
        String result = "";

        try {
            data = URLEncoder.encode("mem_num", "UTF-8") + "=" + URLEncoder.encode(uid, "UTF-8");
            data += "&" + URLEncoder.encode("mem_name", "UTF-8") + "=" + URLEncoder.encode(upw, "UTF-8");
            BackgroundTask backgroundTask1 = new BackgroundTask("app/UserLogin.php", data);
            result = backgroundTask1.execute().get();
            JSONObject jsonObject = new JSONObject(result);

            if(jsonObject.getString("result").equals("success")){
                JSONArray loginresult = jsonObject.getJSONArray("info");
                JSONObject obj = loginresult.getJSONObject(0);
                String uname = obj.getString("name");
                String comCode= obj.getString("comCode");
                //값들을 User클래스에 묶어줍니다
                UserVO userVO = new UserVO(uid, upw, uname, comCode);
                return new Result.Success<>(userVO);
            }
            else {
                return new Result.Error(new Exception("로그인 정보가 맞지 않습니다."));
            }

        }catch (Exception e){
            e.printStackTrace();
            return new Result.Error(new Exception("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
