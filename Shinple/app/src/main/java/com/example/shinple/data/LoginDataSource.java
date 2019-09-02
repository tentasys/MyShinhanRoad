package com.example.shinple.data;

import android.util.Log;

import com.example.shinple.BackgroundTask;
import com.example.shinple.vo.MemberVO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {


    public Result<MemberVO> login(String mem_num, String mem_password) {

            // TODO: handle loggedInUser authentication
        String data = "";
        String result = "";

        try {
            data = URLEncoder.encode("mem_num", "UTF-8") + "=" + URLEncoder.encode(mem_num, "UTF-8");
            data += "&" + URLEncoder.encode("mem_password", "UTF-8") + "=" + URLEncoder.encode(mem_password, "UTF-8");
            BackgroundTask backgroundTask1 = new BackgroundTask("app/MemberLogin.php", data);
            result = backgroundTask1.execute().get();
            Log.d("sdlmsdlms",result);
            JSONObject jsonObject = new JSONObject(result);
            if(jsonObject == null){
                return new Result.Error(new Exception("DB에러 "));
            }
            else if(jsonObject.getString("success") != null){
                JSONArray loginresult = jsonObject.getJSONArray("success");
                JSONObject obj = loginresult.getJSONObject(0);
                String mem_name = obj.getString("mem_name");
                String mem_point = obj.getString("mem_point");
                String company_num= obj.getString("company_num");
                //값들을 User클래스에 묶어줍니다
                MemberVO MemberVO = new MemberVO(mem_num, mem_name, mem_point, company_num);
                return new Result.Success<>(MemberVO);
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
