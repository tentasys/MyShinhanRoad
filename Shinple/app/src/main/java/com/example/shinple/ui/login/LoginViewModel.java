package com.example.shinple.ui.login;

import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shinple.data.LoginRepository;
import com.example.shinple.data.Result;
import com.example.shinple.VO.MemberVO;
import com.example.shinple.R;

public class LoginViewModel  {

    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }
<<<<<<< HEAD
    public MemberVO  login(String mem_num, String mem_password) {
=======


        public UserVO login(String username, String password){
>>>>>>> 5cee65893da87c656dd20b470b46cf0ebb65fdee
            // can be launched in a separate asynchronous job
            Result<MemberVO> result = loginRepository.login(mem_num, mem_password);

            if (result instanceof Result.Success) {   //성공하면
<<<<<<< HEAD
                MemberVO data = ((Result.Success<MemberVO>) result).getData();
=======
                UserVO data = ((Result.Success<UserVO>) result).getData();
>>>>>>> 5cee65893da87c656dd20b470b46cf0ebb65fdee
                return data;
            }
            return null;
        }


}
