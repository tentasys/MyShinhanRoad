package com.example.shinple.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shinple.R;
import com.example.shinple.VO.CourseVO;


public class ViewPagerFragment extends Fragment {

    private static final String ARG_PARAM1 = "course";

    // TODO: Rename and change types of parameters
    private CourseVO mParam1;

    public ViewPagerFragment() {
        // Required empty public constructor
    }

    public static ViewPagerFragment newInstance(CourseVO param1) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (CourseVO)getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager, container, false);
    }
}
