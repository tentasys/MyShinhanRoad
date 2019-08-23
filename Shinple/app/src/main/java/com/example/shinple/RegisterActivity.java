package com.example.shinple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.shinple.ui.login.LoginActivity;

import java.net.URLEncoder;

public class RegisterActivity extends AppCompatActivity {

    private EditText Unum;
    private EditText Uname;
    private EditText Upassword;
    private RadioGroup rg;
    private Button reg;

    private String unum = "";
    private String uname = "";
    private String upassword = "";
    private String ucode = "";
    private String data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Unum = findViewById(R.id.et_Unum);
        Uname = findViewById(R.id.et_Uname);
        Upassword = findViewById(R.id.et_Upassword);
        rg = findViewById(R.id.rg_reg);
        reg = findViewById(R.id.bt_reg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_1:
                        ucode = "1";
                        break;
                    case R.id.rb_2:
                        ucode = "2";
                        break;
                    case R.id.rb_3:
                        ucode = "3";
                        break;
                    case R.id.rb_4:
                        ucode = "4";
                        break;
                }
            }
        });




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
                    data+= "&"+URLEncoder.encode("mem_code","UTF-8")+"="+ URLEncoder.encode("2312","UTF-8");
                    Log.d("WoWWEWEW",data);
                } catch (Exception e){
                    Log.d("WoW","ASDASDASDASD");
                }
                String result = "";
                BackgroundTask backgroundTask = new BackgroundTask("http://192.168.1.187/Register.php",data);
                try{
                    result = backgroundTask.execute().get();
                } catch (Exception e){
                    e.printStackTrace();
                }
                Log.d("result",result);
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
