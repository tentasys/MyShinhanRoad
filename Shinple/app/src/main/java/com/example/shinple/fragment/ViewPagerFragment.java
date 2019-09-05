package com.example.shinple.fragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shinple.BackgroundTask;
import com.example.shinple.activities.MainActivity;
import com.example.shinple.R;
import com.example.shinple.vo.CourseVO;
import com.example.shinple.vo.MemberVO;

import java.net.URLEncoder;


public class ViewPagerFragment extends Fragment {

    private static final String ARG_PARAM1 = "course";
    private static final String ARG_PARAM2 = "member";
    private String server;
    private String data;
    private String data2;
    // TODO: Rename and change types of parameters
    private CourseVO course;
    private MemberVO member;

    public ViewPagerFragment() {
        // Required empty public constructor
    }

    public static ViewPagerFragment newInstance(CourseVO course, MemberVO member) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, course);
        args.putSerializable(ARG_PARAM2,member);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            course = (CourseVO)getArguments().getSerializable(ARG_PARAM1);
            member = (MemberVO)getArguments().getSerializable(ARG_PARAM2);
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
        server = BackgroundTask.server + "video/course/" + course.getCourseNum() + ".png";

        Glide.with(getContext())
                .load(server)
                .centerCrop()
                .into(cour);

        course_N.setText(course.getcourseName());
        tch.setText(course.getTchName());
        CardView cv = view.findViewById(R.id.cv_course);
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = "";
                if (course.getLearnState() == null){
                    temp = "0";
                }
                else{
                    temp = course.getLearnState();
                }
                try{
                    data = URLEncoder.encode("courseNum", "UTF-8") + "=" + URLEncoder.encode(course.getCourseNum(), "UTF-8");
                    data += "&" +  URLEncoder.encode("userNum", "UTF-8") + "=" + URLEncoder.encode(member.getMem_num(), "UTF-8");
                    if(course.getLearnState().equals("0")){
                        data += "&" +  URLEncoder.encode("state", "UTF-8") + "=" + URLEncoder.encode(temp, "UTF-8");
                    }
                    else {
                        data += "&" + URLEncoder.encode("state", "UTF-8") + "=" + URLEncoder.encode(course.getLearnState(), "UTF-8");
                    }
                    data2 = URLEncoder.encode("course_num", "UTF-8") + "=" + URLEncoder.encode(course.getCourseNum(), "UTF-8");
                    data2 += "&" +  URLEncoder.encode("mem_num", "UTF-8") + "=" + URLEncoder.encode(member.getMem_num(), "UTF-8");
                }
                catch (Exception e){
                }
                String result = "";
                String result2 = "";
                BackgroundTask backgroundTask = new BackgroundTask("app/lectureList.php",data);
                BackgroundTask backgroundTask2 = new BackgroundTask("app/timetest.php",data2);
                try{
                    result = backgroundTask.execute().get();
                    result2 = backgroundTask2.execute().get();
                } catch (Exception e){
                    e.printStackTrace();
                }
                Log.d("lecture",result);
                Log.d("timeVPF",result2);
                ((MainActivity) view.getContext())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame,LectureListFragment.newInstance(result,course,member,result2))
                        .addToBackStack("lecture_list")
                        .commit();
            }
        });

        return view;
    }
}
