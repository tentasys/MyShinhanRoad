package com.example.shinple;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class MainFragment extends Fragment {
    /* 이미지 슬라이더 관련 부분 */
    AdapterMainNewCourseSlider adapter;
    ViewPager viewPager;

    public MainFragment() {
        // Required empty public constructor
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

        /* 이미지 슬라이더 실행 구현부 */
        viewPager = (ViewPager) v.findViewById(R.id.new_course_slider);
        adapter = new AdapterMainNewCourseSlider(v.getContext());
        viewPager.setAdapter(adapter);
        return v;
    }

}
