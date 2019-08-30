package com.example.shinple.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shinple.BackgroundTask;
import com.example.shinple.R;
import com.example.shinple.VO.CourseVO;


public class ViewPagerFragment extends Fragment {

    private static final String ARG_PARAM1 = "course";
    private String server;
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
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        TextView course_N = (TextView) view.findViewById(R.id.course_n);
        TextView tch = (TextView) view.findViewById(R.id.tch_name);
        ImageView cour = (ImageView) view.findViewById(R.id.iv_cour);
        server = BackgroundTask.server + "video/" + mParam1.getCourseNum() + "/background.png";

        Glide.with(getContext())
                .load(server)
                .centerCrop()
                .into(cour);

        course_N.setText(mParam1.getcourseName());
        tch.setText(mParam1.getTchName());

        return view;
    }
}
