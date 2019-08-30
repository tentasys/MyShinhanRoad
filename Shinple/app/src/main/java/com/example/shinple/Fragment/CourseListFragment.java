package com.example.shinple.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shinple.Adapter.CourseAAdapter;
import com.example.shinple.Adapter.FilterAdapter;
import com.example.shinple.Adapter.StringAdapter;
import com.example.shinple.MainActivity;
import com.example.shinple.R;
import com.example.shinple.VO.CourseVO;
import com.example.shinple.BackgroundTask;
import com.example.shinple.VO.FilterVO;
import com.example.shinple.VO.LectureVO;
import com.example.shinple.VO.MemberVO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * 하단 강좌(강의 리스트) 누르면 연결되는 페이지
 *
 */
public class CourseListFragment extends Fragment{


    private CourseAAdapter adapter;
    private StringAdapter adapter2;
    private List<CourseVO> courseList;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private String result_course = "";
    private String data;
    private String mem_like;
    private MemberVO member;
    private ArrayList<String> all = new ArrayList<String>();


    private static final String ARG_PARM = "course";
    private static final String ARG_PARM2 = "lv";
    private static final String ARG_PARM3 = "member";

    public CourseListFragment() {
        // Required empty public constructor
    }
    public static CourseListFragment newInstance(String result,ArrayList<String> all,MemberVO member) {
        CourseListFragment fragment = new CourseListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARM, result);
        args.putStringArrayList(ARG_PARM2,all);
        args.putSerializable(ARG_PARM3,member);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            result_course = getArguments().getString(ARG_PARM);
            all.addAll(getArguments().getStringArrayList(ARG_PARM2));
            member = (MemberVO)getArguments().getSerializable(ARG_PARM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_course_list, container, false);

        // Inflate the layout for this fragment

        courseList = new ArrayList<CourseVO>();

        recyclerView = v.findViewById(R.id.rv_list);
        recyclerView2 = v.findViewById(R.id.rv_tag);
        LinearLayoutManager layoutManager = new LinearLayoutManager(v.getContext());
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(v.getContext(),RecyclerView.HORIZONTAL,false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);
        adapter = new CourseAAdapter(v.getContext(),courseList);
        adapter2 = new StringAdapter(v.getContext(),all);
        recyclerView.setAdapter(adapter);
        recyclerView2.setAdapter(adapter2);


        adapter.setOnItemClickListener(new CourseAAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, CourseVO course) {
                //new CourseListFragment.BackgroundTask().execute();
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
                    Log.d("cccc",data);
                }
                catch (Exception e){
                }
                String result = "";
                BackgroundTask backgroundTask = new BackgroundTask("app/lectureList.php",data);
                try{
                    result = backgroundTask.execute().get();
                } catch (Exception e){
                    e.printStackTrace();
                }
                Log.d("lecture",result);
                ((MainActivity) view.getContext())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame,LectureListFragment.newInstance(result,course,member))
                        .addToBackStack("lecture_list")
                        .commit();
            }
        });

        try{
            //intent로 값을 가져옵니다 이때 JSONObject타입으로 가져옵니다
            JSONObject jsonObject = new JSONObject(result_course);

            //List.php 웹페이지에서 response라는 변수명으로 JSON 배열을 만들었음..
            JSONArray jsonArray = jsonObject.getJSONArray("response");
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
                CourseVO course = new CourseVO(courseName, courseLevel, tchName, courseText, courseNum, LearnState, Like, mem_like);
                courseList.add(course);//리스트뷰에 값을 추가해줍니다
                count++;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }


        return v;
    }
}
