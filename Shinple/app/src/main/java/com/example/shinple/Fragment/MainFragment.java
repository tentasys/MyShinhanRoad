package com.example.shinple.Fragment;

import android.content.Intent;
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

import com.example.shinple.Adapter.FilterAdapter;
import com.example.shinple.Adapter.MainSliderAdapter;
import com.example.shinple.Adapter.RecentGridAdapter;
import com.example.shinple.Adapter.ViewAdapter;
import com.example.shinple.AutoViewpager.AutoScrollViewpager;
import com.example.shinple.BackgroundTask;
import com.example.shinple.MainActivity;
import com.example.shinple.R;
import com.example.shinple.VO.CourseVO;
import com.example.shinple.VO.FilterVO;
import com.example.shinple.VO.LectureVO;

import com.example.shinple.VO.FilterVO;
import com.example.shinple.VO.LectureVO;
import com.example.shinple.VO.MemberVO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
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
    private CourseVO course;
    private String MainResult;
    private String data;

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
        TextView point = (TextView)v.findViewById(R.id.tv_PersonalRank);
        if(member != null) {
            name.setText(member.getMem_name());
            point.setText(member.getMem_point());
        }

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

        try{
            //intent로 값을 가져옵니다 이때 JSONObject타입으로 가져옵니다
            JSONObject jsonObject = new JSONObject(MainResult);


            //List.php 웹페이지에서 response라는 변수명으로 JSON 배열을 만들었음..
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;

            String lec_title, lec_order, lec_text, lec_time;

            //JSON 배열 길이만큼 반복문을 실행
            while(count < jsonArray.length()){
                //count는 배열의 인덱스를 의미
                JSONObject object = jsonArray.getJSONObject(count);

                lec_title = object.getString("lec_title");//여기서 ID가 대문자임을 유의
                lec_order = object.getString("lec_order");
                lec_text = object.getString("lec_text");
                lec_time = object.getString("lec_time");

                //값들을 User클래스에 묶어줍니다
                LectureVO lecture = new LectureVO(lec_title, lec_order, lec_text, lec_time);
                lectureList.add(lecture);//리스트뷰에 값을 추가해줍니다
                count++;
            }

        }catch(Exception e) {
            e.printStackTrace();
        }

        // 차후 변수값을 DB를 읽는걸로 바꿈

            GV = v.findViewById(R.id.gv_recent);
            GA = new RecentGridAdapter(v.getContext(), lectureList);
            GV.setAdapter(GA);

            /*try{
                String [] lecName={"빅데이터","블록체인"};
                String [] lecInfo={"빅데이터강의 설명입니다","블록체인강의설명입니다."};
                String [] lecNum={"1","1"};
                String [] lecTime={"23:33","50:22"};
                int length = lecName.length;
                //JSON 배열 길이만큼 반복문을 실행

                for(int tagnum=0; tagnum<length;tagnum++){
                    //TODO: json 데이터 가져와 서 쪼개서 변수 넣기
                    String lecN = lecName[tagnum];
                    String lecI = lecInfo[tagnum];
                    String lecNu = lecNum[tagnum];
                    String lecT = lecTime[tagnum];


                    //값들을 User클래스에 묶어줍니다
                    LectureVO lecture = new LectureVO(lecN,lecNu,lecI,lecT);
                    lectureList.add(lecture);//리스트뷰에 값을 추가해줍니다
                }
            }catch(Exception e){
                e.printStackTrace();
            } //try_catch 끝
        */




        //최근 강의1 누르면 해당 강의 상세 페이지로
        /*recent_course_layout1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ((MainActivity) view.getContext())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame,LectureListFragment.newInstance("상세","상세", "상세","상세","상세","상세"))
                        .addToBackStack("lecturelist")
                        .commit();
            }
        });

        //최근 강의2 누르면 해당 강의 상세 페이지로
        recent_course_layout2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ((MainActivity) view.getContext())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame,LectureListFragment.newInstance("상세","상세", "상세","상세","상세","상세"))
                        .addToBackStack("lecturelist")
                        .commit();
            }
        });*/

        /* 이미지 슬라이더 실행 구현부 */
        viewPager =  v.findViewById(R.id.new_course_slider);
        Vadapter = new ViewAdapter(getChildFragmentManager());
        setupViewPager(viewPager);
        //adapter = new MainSliderAdapter(v.getContext(), "newCourse");
        //viewPager.setAdapter(adapter);
        course = new CourseVO("hi","2","ttt","asasasfasfas","11","1");

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
        Vadapter.addFragment(ViewPagerFragment.newInstance(course));
        Vadapter.addFragment(ViewPagerFragment.newInstance(course));
        Vadapter.addFragment(ViewPagerFragment.newInstance(course));
        viewPager.setAdapter(Vadapter);
    }

    public void setupViewPager2(ViewPager viewPager) {
        Vadapter2.addFragment(ViewPagerFragment.newInstance(course));
        Vadapter2.addFragment(ViewPagerFragment.newInstance(course));
        Vadapter2.addFragment(ViewPagerFragment.newInstance(course));
        viewPager.setAdapter(Vadapter2);
    }
}
