package com.example.shinple.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shinple.adapter.MainSliderAdapter;
import com.example.shinple.adapter.RecentGridAdapter;
import com.example.shinple.adapter.ViewAdapter;
import com.example.shinple.BackgroundTask;
import com.example.shinple.activities.MainActivity;
import com.example.shinple.R;
import com.example.shinple.vo.CourseVO;
import com.example.shinple.vo.LectureVO;

import com.example.shinple.vo.MemberVO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainFragment extends Fragment {
    ConstraintLayout learning_status;
    private RecentGridAdapter GA;
    private GridView GV;
    private List<LectureVO> lectureList = new ArrayList<LectureVO>();
    private MemberVO member;
    private String result1;
    private List<CourseVO> new_course;
    private List<CourseVO> hot_course;
    private String MainResult;
    private String data;
    public String videourl;
    public boolean FileValideCheckResult = false;
    private Handler mHandler = new Handler();
    private TextView point;
    private LectureVO lectureVO;
    private String datadata;

    long mNow;
    private String data1;
    Date mDate;
    String pattern ="yyyy-MM-dd hh:mm:ss";
    SimpleDateFormat mFormat = new SimpleDateFormat(pattern);

    /* 이미지 슬라이더 관련 부분 */
    MainSliderAdapter adapter;
    ViewPager viewPager;
    ViewAdapter Vadapter;
    ViewAdapter Vadapter2;
    MainSliderAdapter adapter2;
    ViewPager viewPager2;

    int currentPage1=0;
    Handler handler1;
    Runnable Update1;
    Timer timer1;

    int currentPage2=0;
    Handler handler2;
    Runnable Update2;
    Timer timer2;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.

    public MainFragment() {
        // Required empty public constructor
    }
    public static MainFragment newInstance(String result,MemberVO member, String result2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString("result", result);
        args.putSerializable("member",member);
        args.putString("course",result2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            MainResult = getArguments().getString("result");
            member = (MemberVO)getArguments().getSerializable("member");
            result1 = getArguments().getString("course");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_main, container, false);
        learning_status = v.findViewById(R.id.learning_status);

        TextView name = (TextView)v.findViewById(R.id.my_learning_status2);
        point = (TextView)v.findViewById(R.id.tv_PersonalRank);
        if(member != null) {
            name.setText(member.getMem_name());
            point.setText(member.getMem_point());
        }

        renewMem();

        //recent_course_layout1 = v.findViewById(R.id.recent_course_layout1);
        //recent_course_layout2 = v.findViewById(R.id.recent_course_layout2);
//        UserVO user = LoginRepository.getInstance();

        //recent_course_layout1 = v.findViewById(R.id.recent_course_layout1);
        //recent_course_layout2 = v.findViewById(R.id.recent_course_layout2);

//        scrollView = v.findViewById(R.id.ScrollView);
//        scrollView.setFillViewport (true);
        // 학습 현황 누르면 나의 강의실로
        learning_status.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String result1 = "";
                String result2 = "";
                try{
                    data = URLEncoder.encode("userNum", "UTF-8") + "=" + URLEncoder.encode(member.getMem_num(), "UTF-8");
                } catch (Exception e){

                }
                BackgroundTask backgroundTask = new BackgroundTask("app/mycoplist.php",data);
                BackgroundTask backgroundTask2 = new BackgroundTask("app/mycourselist.php",data);
                try{
                    result1 = backgroundTask.execute().get();
                    Log.d("result1",result1);
                    result2 = backgroundTask2.execute().get();
                    Log.d("result2",result2);
                } catch (Exception e){
                    e.printStackTrace();
                }
                ((MainActivity) view.getContext())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame,LectureRoomFragment.newInstance(result1,result2,member))
                        .addToBackStack("lectureroom")
                        .commit();
            }
        });
        //////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////
        new_course = new ArrayList<CourseVO>();
        hot_course = new ArrayList<CourseVO>();
        try{
            //intent로 값을 가져옵니다 이때 JSONObject타입으로 가져옵니다
            JSONObject jsonObject = new JSONObject(MainResult);


            //List.php 웹페이지에서 response라는 변수명으로 JSON 배열을 만들었음..
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;

            String lec_title, lec_order, lec_text, lec_time, recent_time, lec_num, course_num, learn_time;

            //JSON 배열 길이만큼 반복문을 실행
            while(count < jsonArray.length()){
                //count는 배열의 인덱스를 의미
                JSONObject object = jsonArray.getJSONObject(count);

                lec_title = object.getString("lec_title");//여기서 ID가 대문자임을 유의
                lec_order = object.getString("lec_order");
                lec_text = object.getString("lec_text");
                lec_time = object.getString("lec_time");
                recent_time = object.getString("recent_time");
                lec_num = object.getString("lec_num");
                course_num = object.getString("course_num");
                learn_time = object.getString("learn_time");

                //값들을 User클래스에 묶어줍니다
                LectureVO lecture = new LectureVO(lec_title, lec_order, lec_text, lec_time, recent_time, lec_num, course_num, learn_time);
                lectureList.add(lecture);//리스트뷰에 값을 추가해줍니다
                count++;
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
        //////////////////////////////////////////////////////////////////////////////////////
                        //////  course list  ////////
        ///////////////////////////////////////////////////////////
        try{
            //intent로 값을 가져옵니다 이때 JSONObject타입으로 가져옵니다
            JSONObject jsonObject = new JSONObject(result1);

            //List.php 웹페이지에서 response라는 변수명으로 JSON 배열을 만들었음..
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            JSONArray jsonArray1 = jsonObject.getJSONArray("response2");

            int count = 0;

            String courseName, courseLevel, tchName, courseText, courseNum, LearnState, Like, mem_like;

            //JSON 배열 길이만큼 반복문을 실행
            while(count < jsonArray.length()){
                //count는 배열의 인덱스를 의미
                JSONObject object = jsonArray.getJSONObject(count);

                courseName = object.getString("course_title");//여기서 ID가 대문자임을 유의
                courseLevel = object.getString("course_level");
                tchName = object.getString("course_tch");
                courseText= object.getString("course_text");
                courseNum = object.getString("course_num");
                LearnState = object.getString("learn_state");
                Like = object.getString("Like");
                mem_like = object.getString("mem_like");

                //값들을 User클래스에 묶어줍니다
                CourseVO course = new CourseVO(courseName, courseLevel, tchName, courseText, courseNum, LearnState, Like,mem_like);
                new_course.add(course);//리스트뷰에 값을 추가해줍니다
                Log.d("sdsf",new_course.get(count).getCourseNum());
                count++;
            }

            int count1 = 0;
            while(count1 < jsonArray1.length()){
                //count는 배열의 인덱스를 의미
                JSONObject object = jsonArray1.getJSONObject(count1);

                courseName = object.getString("course_title");//여기서 ID가 대문자임을 유의
                courseLevel = object.getString("course_level");
                tchName = object.getString("course_tch");
                courseText= object.getString("course_text");
                courseNum = object.getString("course_num");
                LearnState = object.getString("learn_state");
                Like = object.getString("Like");
                mem_like = object.getString("mem_like");

                //값들을 User클래스에 묶어줍니다
                CourseVO course = new CourseVO(courseName, courseLevel, tchName, courseText, courseNum, LearnState, Like, mem_like );
                hot_course.add(course);//리스트뷰에 값을 추가해줍니다
                count1++;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

        // 차후 변수값을 DB를 읽는걸로 바꿈

        GV = v.findViewById(R.id.gv_recent);
        GA = new RecentGridAdapter(v.getContext(), lectureList);
        GV.setAdapter(GA);
        GA.setOnCheckListener(new RecentGridAdapter.OnCheckedChangeListener() {
            @Override
            public void onItemClick(View view, LectureVO lecture) {
                videourl = BackgroundTask.server+"video/course/" + lecture.getLec_num() + ".mp4";
                String url =  BackgroundTask.server+"video/course/";
                String video = lecture.getLec_num() + ".mp4";

                String result2 = "";

                recent_video(lecture);


                try{
                    datadata = URLEncoder.encode("courseNum", "UTF-8") + "=" + URLEncoder.encode(lecture.getCourse_num(), "UTF-8");
                    datadata += "&" + URLEncoder.encode("userNum", "UTF-8") + "=" + URLEncoder.encode(member.getMem_num(), "UTF-8");
                    datadata += "&" + URLEncoder.encode("state", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
                    Log.d("time2134124214",lecture.getLearn_time());
                } catch (Exception e){
                    e.printStackTrace();
                }
                BackgroundTask backgroundTask2 = new BackgroundTask("app/lectureList.php",datadata);
                try{
                    result2 = backgroundTask2.execute().get();
                    Log.d("time11",result2);
                } catch (Exception e){
                    e.printStackTrace();
                }

                isFileValid();  //파일이 유효한 지1 체크
                if(FileValideCheckResult){
                    try {   // exo해보고
                        ((MainActivity) view.getContext())
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame, ExoPlayerFragment.newInstance(url,result2,lecture,video, member))
                                .commit();
                    }catch (Exception e){  //exo안되면 media로 가자!
                        ((MainActivity) view.getContext())
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame, MediaPlayerFragment.newInstance(url,result2,lecture.getLec_title(),lecture.getLec_text()))
                                .commit();
                    }
                }else{
                    Toast.makeText(view.getContext(), "파일 에러", Toast.LENGTH_LONG).show();

                } //ifelse 끝
            }
        });


        /* 이미지 슬라이더 실행 구현부 */
        viewPager =  v.findViewById(R.id.new_course_slider);
        Vadapter = new ViewAdapter(getChildFragmentManager());
        setupViewPager(viewPager);
        //adapter = new MainSliderAdapter(v.getContext(), "newCourse");
        //viewPager.setAdapter(adapter);

        viewPager2 =  v.findViewById(R.id.hot_course_slider);
        Vadapter2 = new ViewAdapter(getChildFragmentManager());
        //adapter2 = new MainSliderAdapter(v.getContext(), "hotCourse");
        setupViewPager2(viewPager2);
        //viewPager2.setAdapter(Vadapter2);


        // 신규 강의   start auto scroll of viewpager
        currentPage1 = 0;
        handler1 = new Handler();
        Update1 = new Runnable() {
            public void run() {
                viewPager.setCurrentItem(++currentPage1, true);
                // go to initial page i.e. position 0
                if (currentPage1 == 2) { //NUM_PAGES -1
                    currentPage1 = -1;
                    // ++currentPage will make currentPage = 0
                }
            }
        };


        timer1 = new Timer(); // This will create a new Thread
        timer1.schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler1.post(Update1);
            }
        }, 500, 2000);


        // hot 강의   start auto scroll of viewpager
        currentPage2 = 0;
        handler2 = new Handler();
        final Runnable Update2 = new Runnable() {
            public void run() {
                viewPager2.setCurrentItem(++currentPage2, true);
                // go to initial page i.e. position 0
                if (currentPage2 == 2) { //NUM_PAGES -1
                    currentPage2 = -1;
                }
            }
        };

        timer2 = new Timer(); // This will create a new Thread
        timer2.schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler2.post(Update2);
            }
        }, 500, 2500);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    public void setupViewPager(ViewPager viewPager) {
        try{
            Vadapter.addFragment(ViewPagerFragment.newInstance(new_course.get(0),member));
            Vadapter.addFragment(ViewPagerFragment.newInstance(new_course.get(1),member));
            Vadapter.addFragment(ViewPagerFragment.newInstance(new_course.get(2),member));
            viewPager.setAdapter(Vadapter);
        } catch (Exception e){

        }
    }

    public void setupViewPager2(ViewPager viewPager) {
        try{
            Vadapter2.addFragment(ViewPagerFragment.newInstance(hot_course.get(0),member));
            Vadapter2.addFragment(ViewPagerFragment.newInstance(hot_course.get(1),member));
            Vadapter2.addFragment(ViewPagerFragment.newInstance(hot_course.get(2),member));
            viewPager.setAdapter(Vadapter2);
        } catch (Exception e) {

        }
    }

    private void recent_video(LectureVO lecture){

        String result = "";
        try{
            data = URLEncoder.encode("courseNum", "UTF-8") + "=" + URLEncoder.encode(lecture.getCourse_num(), "UTF-8");
            data += "&" + URLEncoder.encode("userNum", "UTF-8") + "=" + URLEncoder.encode(member.getMem_num(), "UTF-8");
            data += "&" + URLEncoder.encode("lec_num", "UTF-8") + "=" + URLEncoder.encode(lecture.getLec_num(), "UTF-8");
            data += "&" + URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(getTime(), "UTF-8");
            Log.d("time1",data);
        } catch (Exception e){
        }
        BackgroundTask backgroundTask = new BackgroundTask("app/recentVideo.php",data);
        try{
            result = backgroundTask.execute().get();
            Log.d("test123",result);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /* 파일이 유효한 지 체크해서 전역변수 FileValideCheckResult에 저장하는 함수 */
    public  void isFileValid() {
        Thread th = new Thread() {
            @Override
            public void run() {
                if(videourl == null){
                    FileValideCheckResult = false;
                    this.stop();
                }
                try {
                    URL url = new URL(videourl);
                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                    if (http.getResponseCode() == 200) {
                        Log.i("PlayUrlCheck", "valid");
                        FileValideCheckResult = true;
                    } else {
                        Log.i("PlayUrlCheck", "invalid");
                        FileValideCheckResult =false;
                    }
                } catch ( Exception e) {
                    Log.i("PlayUrlCheck", "error");
                    FileValideCheckResult = false;
                }
            }
        };
        th.start();
        try {
            th.join();
        }catch (Exception e){
            Log.e("Error", "ThreadError");
        }
    }

    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }
    public void renewMem(){
        new Thread(new Runnable() {
            @Override
            public void run() {
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
                                point.setText(member.getMem_point());
                            }
                        });
                    }
                };

                Timer t = new Timer();
                t.schedule(tt,0,1000);
            }
        }).start();
    }
}
