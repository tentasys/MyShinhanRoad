package com.example.shinple;

import android.app.Activity;
import android.widget.Toast;

public class BackPressHandler {
    private Long backKeyPressedTime = 0L;
    private Activity activity;
    private Toast toast;

    public BackPressHandler(Activity context) { this.activity = context; }

    public void onBackPressed(){
        if (System.currentTimeMillis() > backKeyPressedTime + 1000) {
            backKeyPressedTime = System.currentTimeMillis(
            );
            showGuide();
            return;
        }
        if(System.currentTimeMillis() <= backKeyPressedTime + 1000) {
            activity.finish();
            toast.cancel();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    }

    public void showGuide(){
        toast = Toast.makeText(activity, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.",Toast.LENGTH_SHORT);
        toast.show();
    }
}

