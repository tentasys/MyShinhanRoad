package com.example.shinple;


import android.content.res.Configuration;

import android.graphics.Color;

import android.os.Build;
import android.os.Bundle;

import com.example.shinple.Fragment.CopFragment;
import com.example.shinple.Fragment.ExoPlayerFragment;
import com.example.shinple.Fragment.FilterFragment;
import com.example.shinple.Fragment.LectureRoomFragment;
import com.example.shinple.Fragment.MainFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;


import android.view.Surface;
import android.view.View;
import android.view.WindowManager;


import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Fragment fr;
    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    BackPressHandler backPressHandler = new BackPressHandler(this);
    BottomNavigationView navView;
    TextView toolbar_title;
    boolean windowMode = true;    //true가 세로모드, false가 가로모드
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar 설정
        toolbar = findViewById(R.id.toolbar);

        //toolbar.setBackgroundColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        toolbar.findViewById(R.id.toolbar_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fr = MainFragment.newInstance();
                switchFragment(fr);
            }
        });


        //사이드바 설정 및 토글 기능 정의
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //하단바 정의
        navView = findViewById(R.id.nav_view2);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Fragment 전환을 위한 초기 설정
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame, MainFragment.newInstance()).commit();

//        fr = new MainFragment();
        fr = new ExoPlayerFragment();
        switchFragment(fr);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        SearchView searchView = (SearchView)menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("태그명으로 검색합니다.");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    /* fragment 내에서 이동하기 위한 메소드 */
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment).commit();      // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
    }

    @SuppressWarnings("StatementWithEmptyBody")
    /**
     * 사이드바 정의
     */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {   //home 메뉴
            fr = new MainFragment();
            switchFragment(fr);
        } else if (id == R.id.nav_gallery) { // 강좌 - 전체 강좌, 태그 검색, 학습 로드맵 - 이름 바꿔야함.

        } else if (id == R.id.nav_slideshow) {  //CoP 랭킹 리스트

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                    = new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            fr = new LectureRoomFragment();  //내 강의실
                            switchFragment(fr);
                            return true;
                        case R.id.navigation_dashboard:   //강좌(강의리스트    )
                            fr = new FilterFragment();
                            switchFragment(fr);
                             return true;
                        case R.id.navigation_notifications:
                            fr = new CopFragment();  //CoP 리스트
                             switchFragment(fr);
                             return true;
        }
        return false;
        }
    };
    public void switchFragment(Fragment frr) {
        Fragment fr = frr;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fr);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {   //뒤로가기 키 동작
        DrawerLayout drawer = findViewById(R.id.drawer_layout); //사이드바 닫기
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            int count = getSupportFragmentManager().getBackStackEntryCount();

            if (count == 0) {
                backPressHandler.onBackPressed();
            } else {
                getSupportFragmentManager().popBackStack();
            }

        }
    }


    public boolean getWindowMode() {
        return windowMode;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    public void playerLandscapeToggle(){
        int rotation = (((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay()).getRotation();
        if(rotation== Surface.ROTATION_90 || rotation ==Surface.ROTATION_270 ) {  //가로일 때
            toolbar.setVisibility(View.GONE);
            navView.setVisibility(View.GONE);
            navigationView.setVisibility(View.GONE);
            windowMode= false;

            /* TODO: navigationbar 지우기 */
/*
                  getWindow().findViewById(R.id.frame).setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
*/

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

        }else {  //세로로 바뀔 때,
            toolbar.setVisibility(View.VISIBLE);
            navView.setVisibility(View.VISIBLE);
            navigationView.setVisibility(View.VISIBLE);
            windowMode = true;
            int flag = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                flag |= View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            }
            getWindow().getDecorView().setSystemUiVisibility(flag);
        }
    }
}
