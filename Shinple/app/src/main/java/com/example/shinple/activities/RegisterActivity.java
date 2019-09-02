package com.example.shinple.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.shinple.BackgroundTask;
import com.example.shinple.R;
import com.example.shinple.data.Result;
import com.example.shinple.ui.login.LoginActivity;

import org.json.JSONObject;

import java.net.URLEncoder;

public class RegisterActivity extends AppCompatActivity {

    private EditText Unum;
    private EditText Uname;
    private EditText Upassword;
//    private EditText Ucode;
    private String Ucode = "회사 코드";
    private Spinner spinner;
    private Button reg;

    private String unum = "";
    private String uname = "";
    private String upassword = "";
    private String ucode = "";
    private String data;
    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Unum = findViewById(R.id.et_Unum);
        Uname = findViewById(R.id.et_Uname);
        Upassword = findViewById(R.id.et_Upassword);
        reg = findViewById(R.id.bt_reg);
        spinner = findViewById(R.id.spinner_com);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        //회사 선택
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ucode = String.valueOf(i+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // 회원가입 버튼
        reg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                unum = Unum.getText().toString();
                uname = Uname.getText().toString();
                upassword = Upassword.getText().toString();

                try {
                    data = URLEncoder.encode("mem_num", "UTF-8") + "=" + URLEncoder.encode(unum, "UTF-8");
                    data+= "&"+URLEncoder.encode("mem_name","UTF-8")+"="+ URLEncoder.encode(uname,"UTF-8");
                    data+= "&"+URLEncoder.encode("mem_password","UTF-8")+"="+ URLEncoder.encode(upassword,"UTF-8");
                    data+= "&"+URLEncoder.encode("mem_code","UTF-8")+"="+ URLEncoder.encode(ucode,"UTF-8");
                } catch (Exception e){
                }
                String result = "";
                BackgroundTask backgroundTask = new BackgroundTask("app/UserRegister.php",data);
                Log.d("Tttt",data);
                try{
                    result = backgroundTask.execute().get();
                    onResponse(result);
                    Log.d("ssss",result);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void onResponse(String response){
        Log.d("sssssssss",response);
        try {
            JSONObject jsonObject = new JSONObject(response);
        if(jsonObject.getString("response") != null){
            Toast.makeText(this, "회원가입 성공했습니다.",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "회원가입 실패했습니다.",Toast.LENGTH_SHORT).show();
        }
        } catch (Exception e){

        }
    }

    @Override
    public void onBackPressed() {   //뒤로가기 키 동작

        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("member","done");
        startActivity(intent);
        finish();
    }

    public void RegisterOnClick(View v){
        imm.hideSoftInputFromWindow(Unum.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(Uname.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(Upassword.getWindowToken(), 0);
    }
}
