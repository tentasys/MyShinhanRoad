package com.example.shinple;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private  BackPressHandler backPressHandler = new BackPressHandler(this);
    Fragment fr;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(getApplicationContext(), LectureRoomActivity.class);
                    String message = "Lecture로 간다";
                    intent.putExtra("text",message);
                    startActivity(intent);
                    return true;
                case R.id.navigation_dashboard:
                    Intent intent2 = new Intent(getApplicationContext(), TagListActivity.class);
                    String message2 = "Tag로 간다";
                    intent2.putExtra("text",message2);
                    startActivity(intent2);
                    return true;
                case R.id.navigation_notifications:
                    fr = new CopFragment();
                    switchFragment(fr);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void switchFragment(Fragment frr) {
        Fragment fr = frr;

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fr);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressHandler.onBackPressed();
    }
}
