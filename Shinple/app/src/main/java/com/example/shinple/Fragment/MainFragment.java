package com.example.shinple.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shinple.Adapter.MainSliderAdapter;
import com.example.shinple.R;


public class MainFragment extends Fragment {
    /* 이미지 슬라이더 관련 부분 */
    MainSliderAdapter adapter;
    ViewPager viewPager;
    MainSliderAdapter adapter2;
    ViewPager viewPager2;
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

//        scrollView = v.findViewById(R.id.ScrollView);
//        scrollView.setFillViewport (true);

        /* 이미지 슬라이더 실행 구현부 */
        viewPager = (ViewPager) v.findViewById(R.id.new_course_slider);
        adapter = new MainSliderAdapter(v.getContext(), "newCourse");
        viewPager.setAdapter(adapter);

        viewPager2 = (ViewPager) v.findViewById(R.id.hot_course_slider);
        adapter2 = new MainSliderAdapter(v.getContext(), "hotCourse");
        viewPager2.setAdapter(adapter2);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
