package com.example.shinple.ui.login;

import android.Manifest;
import android.app.Activity;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.preference.PreferenceManager;
import android.renderscript.ScriptGroup;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shinple.MainActivity;
import com.example.shinple.R;
import com.example.shinple.RegisterActivity;
import com.example.shinple.VO.MemberVO;
import com.example.shinple.data.LoginDataSource;
import com.example.shinple.data.LoginRepository;

public class LoginActivity extends AppCompatActivity{


    LoginViewModel loginViewModel;
    EditText userIdEditText;
    EditText passwordEditText;
    Button loginButton ;
    Button regButton;
    ProgressBar loadingProgressBar;
    MemberVO loginResult;
    InputMethodManager imm;
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        userIdEditText = findViewById(R.id.user_id);
        passwordEditText = findViewById(R.id.user_pw);
        loginButton = findViewById(R.id.login);
        regButton = findViewById(R.id.bt_register);
        loadingProgressBar = findViewById(R.id.loading);
        loginViewModel = new LoginViewModel(LoginRepository.getInstance(new LoginDataSource()));
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String userID = sharedPreferences.getString("userID", "");
        userIdEditText.setText(userID);


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                loginButton.setEnabled(true);
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length()!= 0){
                    loginButton.setEnabled(true);
                }
            }
        };
        passwordEditText.addTextChangedListener(textWatcher);
        userIdEditText.addTextChangedListener(textWatcher);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginResult = loginViewModel.login(userIdEditText.getText().toString(),
                        passwordEditText.getText().toString());

                if(loginResult!=null) { //성공하면 이동할 뷰
                    updateUiWithUser();
                }
                else     //실패하면 띄울 다이얼로그
                    showLoginFailed();

            }
        });

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
    private void updateUiWithUser() {
        // TODO : initiate successful logged in experience
        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("member",loginResult);
        startActivity(intent);
    }

    //Toast.LENGTH_SHORT로 에러메세지 저장
    private void showLoginFailed() {
        Toast.makeText(getApplicationContext(), "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show();
        loadingProgressBar.setVisibility(View.GONE);
    }

    public void LoginOnClick(View v){
        imm.hideSoftInputFromWindow(userIdEditText.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(passwordEditText.getWindowToken(), 0);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userID", userIdEditText.getText().toString());
        editor.apply();
    }
}
