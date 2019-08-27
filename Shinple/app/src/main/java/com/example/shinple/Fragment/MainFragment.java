package com.example.shinple.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shinple.Adapter.MainSliderAdapter;
import com.example.shinple.AutoViewpager.AutoScrollViewpager;
import com.example.shinple.MainActivity;
import com.example.shinple.R;

import java.util.Timer;
import java.util.TimerTask;


public class MainFragment extends Fragment {
    ConstraintLayout learning_status;
    ConstraintLayout recent_course_layout1;
    ConstraintLayout recent_course_layout2;

    /* 이미지 슬라이더 관련 부분 */
    MainSliderAdapter adapter;
    ViewPager viewPager;
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
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_main, container, false);
        learning_status = v.findViewById(R.id.learning_status);
        recent_course_layout1 = v.findViewById(R.id.recent_course_layout1);
        recent_course_layout2 = v.findViewById(R.id.recent_course_layout2);

//        scrollView = v.findViewById(R.id.ScrollView);
//        scrollView.setFillViewport (true);
        // 학습 현황 누르면 나의 강의실로
        learning_status.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ((MainActivity) view.getContext())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame,LectureRoomFragment.newInstance())
                        .addToBackStack("lectureroom")
                        .commit();
            }
        });

        //최근 강의1 누르면 해당 강의 상세 페이지로
        recent_course_layout1.setOnClickListener(new View.OnClickListener(){
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
        });

        /* 이미지 슬라이더 실행 구현부 */
        viewPager =  v.findViewById(R.id.new_course_slider);
        adapter = new MainSliderAdapter(v.getContext(), "newCourse");
        viewPager.setAdapter(adapter);

        viewPager2 =  v.findViewById(R.id.hot_course_slider);
        adapter2 = new MainSliderAdapter(v.getContext(), "hotCourse");
        viewPager2.setAdapter(adapter2);


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

}
