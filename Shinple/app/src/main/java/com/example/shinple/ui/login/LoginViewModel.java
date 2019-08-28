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
    public MemberVO  login(String mem_num, String mem_password) {
            // can be launched in a separate asynchronous job
            Result<MemberVO> result = loginRepository.login(mem_num, mem_password);

            if (result instanceof Result.Success) {   //성공하면
                MemberVO data = ((Result.Success<MemberVO>) result).getData();
                return data;
            }
         return null;
    }

}
