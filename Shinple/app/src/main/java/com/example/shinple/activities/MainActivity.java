package com.example.shinple.activities;


import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import com.example.shinple.BackPressHandler;
import com.example.shinple.BackgroundTask;
import com.example.shinple.R;
import com.example.shinple.fragment.CourseListFragment;
import com.example.shinple.fragment.ExoPlayerFragment;
import com.example.shinple.fragment.FilterFragment;
import com.example.shinple.fragment.FoldableListFragment;
import com.example.shinple.fragment.LectureRoomFragment;
import com.example.shinple.fragment.MainFragment;

import com.example.shinple.fragment.SettingFragment;
import com.example.shinple.fragment.UnfoldableDetailsFragment;
import com.example.shinple.fragment.NotificationFragment;

import com.example.shinple.utils.CustomProgressDialog;
import com.example.shinple.vo.CourseVO;
import com.example.shinple.vo.MemberVO;
import com.example.shinple.ui.login.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;


import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String result;
    private String res;
    private String res2;
    private String data;
    private String data_for_token;
    private MemberVO member;
    private CourseVO course;
    private TextView tv_com;
    private TextView tv_name;
    private TextView tv_lp;
    private Handler mHandler = new Handler();
    private ArrayList<String> alll;
    private SearchView searchView;
    private String cou;
    private Menu it;
    private FrameLayout redCircle;
    private TextView countTextView;
    private int alertCount = 0;
    private CustomProgressDialog customProgressDialog;

    Fragment fr;
    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    BackPressHandler backPressHandler = new BackPressHandler(this);
    BottomNavigationView navView;
    TextView toolbar_title;
    boolean windowMode;
    private InputMethodManager imm;
    private FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // clear FLAG_TRANSLUCENT_STATUS flag:

        customProgressDialog = new CustomProgressDialog(this);
        customProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        customProgressDialog.show();

        Intent intent = getIntent();
        member = (MemberVO) intent.getSerializableExtra("member");// finally change the color
        fm = getSupportFragmentManager();

        /////////////
        ////////////////

//        window.setStatusBarColor(ContextCompat.getColor(this,R.color.toolbar_color));    // System toolbar 색상 설정
        setContentView(R.layout.activity_main);

        //toolbar 설정
        toolbar = findViewById(R.id.toolbar);

        //toolbar.setBackgroundColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);

        //firebase token 확인
//        Log.d("TOKEN", FirebaseInstanceId.getInstance().getToken());

        toolbar.findViewById(R.id.toolbar_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    data = URLEncoder.encode("userNum", "UTF-8") + "=" + URLEncoder.encode(member.getMem_num(), "UTF-8");
                }
                catch (Exception e){
                }
                res = "";
                res2 = "";

                BackgroundTask backgroundTask = new BackgroundTask("app/recentLecture.php",data);
                BackgroundTask backgroundTask1 = new BackgroundTask("app/mainCourse.php",data);

                try{
                    res = backgroundTask.execute().get();
                    res2 = backgroundTask1.execute().get();

                } catch (Exception e){
                    e.printStackTrace();
                }
                Log.e("left", Integer.toString(fm.getBackStackEntryCount()));
                fm.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
                Log.e("left", Integer.toString(fm.getBackStackEntryCount()));
                fr = MainFragment.newInstance(res,member,res2);
                switchFragment(fr);
            }
        });

        //firbase token 저장
        try{
            data_for_token = URLEncoder.encode("userNum", "UTF-8") + "=" + URLEncoder.encode(member.getMem_num(), "UTF-8");
            data_for_token += "&"+URLEncoder.encode("userToken", "UTF-8") + "=" + URLEncoder.encode(FirebaseInstanceId.getInstance().getToken(), "UTF-8");
        }catch(Exception e){
            e.printStackTrace();
        }

        BackgroundTask btsk_noti = new BackgroundTask("app/notification_setup.php", data_for_token);

        try{
            String temp_res = btsk_noti.execute().get();
            Log.d("token2", temp_res);
        }catch (Exception e){
            e.printStackTrace();
        }

        //사이드바 설정 및 토글 기능 정의
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        View headerV = navigationView.getHeaderView(0);

        tv_com = headerV.findViewById(R.id.tv_com);
        tv_name = headerV.findViewById(R.id.tv_main_name);
        tv_lp = headerV.findViewById(R.id.tv_LP);

        if(member.getCompany_num().equals("1")){
            tv_com.setText("신한DS");
        }
        else if(member.getCompany_num().equals("2")){
            tv_com.setText("신한은행");
        }
        else if(member.getCompany_num().equals("3")){
            tv_com.setText("신한카드");
        }
        else if(member.getCompany_num().equals("4")){
            tv_com.setText("신한금융투자");
        }
        else {
            tv_com.setText("신한생명");
        }

        tv_name.setText(member.getMem_name());
        tv_lp.setText(member.getMem_point());
        renewMem();
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //하단바 정의
        navView = findViewById(R.id.nav_view2);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Fragment 전환을 위한 초기 설정
        try{
            data = URLEncoder.encode("userNum", "UTF-8") + "=" + URLEncoder.encode(member.getMem_num(), "UTF-8");
        }
        catch (Exception e){
        }
        res = "";
        res2 = "";
        BackgroundTask backgroundTask = new BackgroundTask("app/recentLecture.php",data);
        BackgroundTask backgroundTask1 = new BackgroundTask("app/mainCourse.php",data);
        try{
            res = backgroundTask.execute().get();
            Log.d("slamflasflas",res);
            res2 = backgroundTask1.execute().get();
            Log.d("mainCourse",res2);
        } catch (Exception e){
            e.printStackTrace();
        }
        it = navigationView.getMenu();
        final MenuItem itt = it.findItem(R.id.nav_noti);
        FrameLayout rootView = (FrameLayout) itt.getActionView();
        redCircle = (FrameLayout)  rootView.findViewById(R.id.view_alert_red_circle);
        countTextView = (TextView) rootView.findViewById(R.id.view_alert_count_textview);
        alertCount = Integer.parseInt(member.getMem_noti());
        updateAlertIcon();
//        fr = new ExoPlayerFragment();
        fr = MainFragment.newInstance(res,member,res2);
        switchFragment(fr);

    }
    @Override
    public void onStart(){
        super.onStart();
    }
    public void ClearBackstack(){

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        searchView = (SearchView)menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("제목으로 검색합니다.");
        searchView.setOnQueryTextListener(queryTextListener);
        int id = searchView.getContext()
                .getResources()
                .getIdentifier("android:id/search_src_text",null,null);
        TextView textView = (TextView)searchView.findViewById(id);
        textView.setTextColor(getResources().getColor(R.color.shinhan1));
        textView.setHintTextColor(getResources().getColor(R.color.shinhan1));
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);
        if(null!=searchManager ) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
        }
        // 검색필드를 항상 표시하고싶을 경우false, 아이콘으로 보이고 싶을 경우 true
        searchView.setIconifiedByDefault(true);

        return true;
    }

    private SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
        @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
        @Override
        public boolean onQueryTextSubmit(String query) {
            imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
            alll = new ArrayList<String>();
            alll.add(query);
            String cou = "";
            try{
                data = URLEncoder.encode("userNum", "UTF-8") + "=" + URLEncoder.encode(member.getMem_num(), "UTF-8");
                data += "&" + URLEncoder.encode("searchCourse", "UTF-8") + "=" + URLEncoder.encode(query, "UTF-8");
                Log.d("Test1",data);
            }
            catch (Exception e){
            }
            BackgroundTask backgroundTask = new BackgroundTask("app/searchCourse.php",data);
            try{
                cou = backgroundTask.execute().get();
                Log.d("Test",cou);
            } catch (Exception e){
                e.printStackTrace();
            }
            fr = CourseListFragment.newInstance(cou,alll,member);
            switchFragment(fr);
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            // TODO Auto-generated method stub
            return false;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.app_bar_logout) {
            showCustomDialog2();
//            Intent intent1 = new Intent(this, LoginActivity.class);
//            startActivity(intent1);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    /**
     * 사이드바 정의
     */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.

        int id = item.getItemId();
        fm.popBackStack("ExoPlayerFragment",FragmentManager.POP_BACK_STACK_INCLUSIVE);
        playerLandscapeToggle(false);
        if (id == R.id.nav_home) {   //home 메뉴
            try{
                data = URLEncoder.encode("userNum", "UTF-8") + "=" + URLEncoder.encode(member.getMem_num(), "UTF-8");
            }
            catch (Exception e){
            }
            res = "";
            res2 = "";

            BackgroundTask backgroundTask = new BackgroundTask("app/recentLecture.php",data);
            BackgroundTask backgroundTask1 = new BackgroundTask("app/mainCourse.php",data);

            try{
                res = backgroundTask.execute().get();
                res2 = backgroundTask1.execute().get();

            } catch (Exception e){
                e.printStackTrace();
            }

            fm.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
            Log.e("left", Integer.toString(fm.getBackStackEntryCount()));
            fr = MainFragment.newInstance(res,member,res2);
            switchFragment(fr);

        } else if (id == R.id.nav_my_room) { // 내 강의실

            String result1 = "";
            String result2 = "";
            String result3 = "";
            try{
                data = URLEncoder.encode("userNum", "UTF-8") + "=" + URLEncoder.encode(member.getMem_num(), "UTF-8");
            } catch (Exception e){

            }
            BackgroundTask backgroundTask3 = new BackgroundTask("app/lecNec.php",data);
            BackgroundTask backgroundTask4 = new BackgroundTask("app/mycourselist.php",data);
            BackgroundTask backgroundTask5 = new BackgroundTask("app/mycoplist.php",data);
            try{
                result1 = backgroundTask3.execute().get();
                Log.d("result1",result1);
                result2 = backgroundTask4.execute().get();
                Log.d("result2",result2);
                result3 = backgroundTask5.execute().get();
                Log.d("result2",result2);
            } catch (Exception e){
                e.printStackTrace();
            }
            switchFragment(LectureRoomFragment.newInstance(result1,result2,member,result3));

        } else if (id == R.id.nav_all_course) {  //전체 강좌 리스트

            alll = new ArrayList<String>();

            String result = "";
            BackgroundTask backgroundTask = new BackgroundTask("app/courseList.php",data);
            try{
                result = backgroundTask.execute().get();
            } catch (Exception e){
                e.printStackTrace();
            }
            //모든

            fm.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
            Log.e("left", Integer.toString(fm.getBackStackEntryCount()));
            fm.beginTransaction()
                .replace(R.id.frame, CourseListFragment.newInstance(result,alll,member))
                .commit();

        } else if (id == R.id.nav_CoP) {
            result = "";
            BackgroundTask backgroundTask2 = new BackgroundTask("app/cop.php");
            try{
                result = backgroundTask2.execute().get();
            } catch (Exception e){
                e.printStackTrace();
            }
            switchFragment(new UnfoldableDetailsFragment());
        } else if (id == R.id.log_out) {

            Log.e("left", Integer.toString(fm.getBackStackEntryCount()));
            showCustomDialog();
            return  true;
        } else if (id == R.id.exit) {
            fm.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
            Log.e("left", Integer.toString(fm.getBackStackEntryCount()));
            showCustomDialog2();
            return true;
        } else if (id == R.id.nav_config) {
            fm.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
            Log.e("left", Integer.toString(fm.getBackStackEntryCount()));
            fm.beginTransaction()
                    .replace(R.id.frame, SettingFragment.newInstance())
                    .commit();

        } else if (id == R.id.nav_noti){
            fm.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
            Log.e("left", Integer.toString(fm.getBackStackEntryCount()));
            String resultnoti = "";
            String resultnoti2 = "";
            String datad = "";
            try {
                datad = URLEncoder.encode("mem_num", "UTF-8") + "=" + URLEncoder.encode(member.getMem_num(), "UTF-8");
            } catch (Exception e){
            e.printStackTrace();
            }
            BackgroundTask backgroundTask5 = new BackgroundTask("app/notification.php");
            BackgroundTask backgroundTask6 = new BackgroundTask("app/memNoti.php",datad);
            try{
                resultnoti = backgroundTask5.execute().get();
                resultnoti2 = backgroundTask6.execute().get();
                Log.d("noti",resultnoti2);
            } catch (Exception e){
                e.printStackTrace();
            }
            Log.d("noti",resultnoti2);
            switchFragment(NotificationFragment.newInstance(resultnoti));
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
                            String result1 = "";
                            String result2 = "";
                            String result3 = "";
                            try{
                                data = URLEncoder.encode("userNum", "UTF-8") + "=" + URLEncoder.encode(member.getMem_num(), "UTF-8");
                            } catch (Exception e){

                            }
                            BackgroundTask backgroundTask3 = new BackgroundTask("app/lecNec.php",data);
                            BackgroundTask backgroundTask4 = new BackgroundTask("app/mycourselist.php",data);
                            BackgroundTask backgroundTask5 = new BackgroundTask("app/mycoplist.php",data);
                            try{
                                result1 = backgroundTask3.execute().get();
                                Log.d("result1",result1);
                                result2 = backgroundTask4.execute().get();
                                Log.d("result2",result2);
                                result3 = backgroundTask5.execute().get();
                                Log.d("result2",result2);
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                            switchFragment(LectureRoomFragment.newInstance(result1,result2,member,result3));
                            return true;
                        case R.id.navigation_dashboard:   //강좌(강의리스트    )
                            result = "";
                            BackgroundTask backgroundTask = new BackgroundTask("app/tag.php",data);
                            try{
                                result = backgroundTask.execute().get();
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                            switchFragment(FilterFragment.newInstance(result,member));

                             return true;
                        case R.id.navigation_notifications:
                            result = "";
                            BackgroundTask backgroundTask2 = new BackgroundTask("app/cop.php");
                            try{
                                result = backgroundTask2.execute().get();
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                            switchFragment(new UnfoldableDetailsFragment());

                             return true;
        }
        return false;
        }
    };

    public void switchFragment(Fragment frr) {
        if(!windowMode){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            toolbar.setVisibility(VISIBLE);
            navView.setVisibility(VISIBLE);
            navigationView.setVisibility(VISIBLE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            windowMode = true;
        }
        Fragment fr = frr;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fr);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {   //뒤로가기 키 동작
        DrawerLayout drawer = findViewById(R.id.drawer_layout); //사이드바 닫기
        FragmentManager fm = getSupportFragmentManager();
        if(!windowMode){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            toolbar.setVisibility(VISIBLE);
            navView.setVisibility(VISIBLE);
            navigationView.setVisibility(VISIBLE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            windowMode = true;
        }
        Fragment current_fragment = fm.findFragmentById(R.id.frame);
        if(current_fragment instanceof  UnfoldableDetailsFragment){
            ((UnfoldableDetailsFragment) current_fragment).setunfoldableViewFoldBack();
        }
        if(current_fragment instanceof ExoPlayerFragment){
          fm.beginTransaction().remove(current_fragment).commit();
        }
/*        else if(current_fragment instanceof FoldableListFragment){
            backPressHandler.onBackPressed();
            fm.popBackStack();
        }*/
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            int count = fm.getBackStackEntryCount();

            if (count == 1) {
                backPressHandler.onBackPressed();

            } else {
                fm.popBackStack();
            }

        }


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

/*
        Notification notification = new Notification(R.drawable.shinhan_logo , getText(R.string.app_name),
                System.currentTimeMillis());
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        notification.setLatestEventInfo(this, getText(R.string.app_name),
                getText(R.string.app_name), pendingIntent);
        startForegroundService(ONGOING_NOTIFICATION_ID, notification);

        */
    }
    public void playerLandscapeToggle(boolean EnableFullscreen){
//        int rotation = (((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay()).getRotation();
        if(EnableFullscreen) {  //가로 start
            toolbar.setVisibility(GONE);
            navView.setVisibility(GONE);
            navigationView.setVisibility(GONE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

            windowMode = false;
        /* TODO: navigationbar 지우기 */
/*      getWindow().findViewById(R.id.frame).setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);*/

//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION ,R.color.toolbar_color);

        }else {  //가로 end
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            toolbar.setVisibility(VISIBLE);
            navView.setVisibility(VISIBLE);
            navigationView.setVisibility(VISIBLE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            windowMode = true;

        }
    }

    private void showCustomDialog() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.yes_no_dialog, viewGroup, false);

        TextView title = dialogView.findViewById(R.id.dialog_title);
        TextView context = dialogView.findViewById(R.id.dialog_context);
        TextView bt_yes = dialogView.findViewById(R.id.buttonYES);
        TextView bt_no = dialogView.findViewById(R.id.buttonNO);
        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        context.setText("로그아웃하시겠습니까?");
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        bt_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                member = null;
                startActivity(intent);
            }
        });
        bt_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        //finally creating the alert dialog and displaying it
        alertDialog.show();
    }

    private void showCustomDialog2() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.yes_no_dialog, viewGroup, false);

        TextView title = dialogView.findViewById(R.id.dialog_title);
        TextView context = dialogView.findViewById(R.id.dialog_context);
        TextView bt_yes = dialogView.findViewById(R.id.buttonYES);
        TextView bt_no = dialogView.findViewById(R.id.buttonNO);
        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        context.setText("ShinPle을 종료하시겠습니까?");
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        bt_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.runFinalizersOnExit(true);
                System.exit(0);
            }
        });
        bt_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        //finally creating the alert dialog and displaying it
        alertDialog.show();
    }

    public void setMember2(MemberVO member) {
        this.member = member;
    }

    public Fragment getCurrentFragment(){
        return getSupportFragmentManager().findFragmentById(R.id.frame);
    }
    public MemberVO getMember(){
        return member;
    }

    public void renewMem(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Timer t = new Timer();
                if(member == null){
                    Thread.interrupted();
                    t.cancel();
                }
                TimerTask tt = new TimerTask() {
                    @Override
                    public void run() {
                        String data = "";
                        String result = "";
                        try {
                            data = URLEncoder.encode("mem_num", "UTF-8") + "=" + URLEncoder.encode(member.getMem_num(), "UTF-8");
                            BackgroundTask backgroundTask1 = new BackgroundTask("app/member.php", data);
                            result = backgroundTask1.execute().get();
                            JSONObject jsonObject = new JSONObject(result);
                            JSONArray loginresult = jsonObject.getJSONArray("response");
                            JSONObject obj = loginresult.getJSONObject(0);
                            String mem_name = obj.getString("mem_name");
                            String mem_point = obj.getString("mem_point");
                            String company_num= obj.getString("company_num");
                            String mem_noti = obj.getString("mem_noti");
                                //값들을 User클래스에 묶어줍니다
                            MemberVO MemberVO = new MemberVO(member.getMem_num(), mem_name, mem_point, company_num, mem_noti);
                            member = MemberVO;
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                if(member == null){
                                    Thread.interrupted();
                                    t.cancel();
                                }else {
                                    tv_lp.setText(member.getMem_point());
                                    alertCount = Integer.parseInt(member.getMem_noti());
                                    updateAlertIcon();
                                }
                            }
                        });
                    }
                };

                t.schedule(tt,0,1000);
            }
        }).start();
    }

    private void updateAlertIcon(){
        if (0 < alertCount) {
            countTextView.setText(String.valueOf(alertCount));
        } else {
            countTextView.setText("");
        }

        redCircle.setVisibility((alertCount > 0) ? VISIBLE : GONE);
    }
}
