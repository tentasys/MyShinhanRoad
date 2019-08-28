package com.example.shinple.ui.login;

import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shinple.data.LoginRepository;
import com.example.shinple.data.Result;
import com.example.shinple.VO.UserVO;
import com.example.shinple.R;

public class LoginViewModel  {

    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

<<<<<<< HEAD

    public LoginRepository login(String username, String password) {
=======
    public boolean login(String username, String password) {
>>>>>>> 88e3421019acf32ff10f4189f50d3639ade0fd5e
            // can be launched in a separate asynchronous job
            Result<UserVO> result = loginRepository.login(username, password);

            if (result instanceof Result.Success) {   //성공하면
                UserVO data = ((Result.Success<UserVO>) result).getData();
                return loginRepository;
            }
         return null;
    }

}
