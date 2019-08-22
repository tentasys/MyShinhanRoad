package com.example.shinple;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shinple.VO.LectureVO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class LectureListFragment extends Fragment {

    private static final String ARG_PARAM1 = "courseName";
    private static final String ARG_PARAM2 = "courseInfo";
    private static final String ARG_PARAM3 = "lectureList";

    // TODO: Rename and change types of parameters
    private String courseName;
    private String courseInfo;
    private String result = "";

    private RecyclerView recyclerView;
    private LectureListAdapter adapter;
    private List<LectureVO> lectureList;
    private  TextView tv_courseName;
    private  TextView tv_courseInfo;

    public LectureListFragment() {
        // Required empty public constructor
    }

    public static LectureListFragment newInstance(String param1, String param2, String param3) {
        LectureListFragment fragment = new LectureListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            courseName = getArguments().getString(ARG_PARAM1);
            courseInfo = getArguments().getString(ARG_PARAM2);
            result = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lecture_list, container, false);
        lectureList = new ArrayList<LectureVO>();

        tv_courseName = view.findViewById(R.id.tv_lec_courseN);
        tv_courseInfo = view.findViewById(R.id.tv_lec_courseInfo);
        tv_courseName.setText(courseName);
        tv_courseInfo.setText(courseInfo);

        recyclerView = view.findViewById(R.id.rv_lecture);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new LectureListAdapter(view.getContext(),lectureList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new LectureListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String lectureName, String lectureInfo) {
                BackgroundTask backgroundTask = new BackgroundTask("https://192.168.1.187/lectureList.php");
                backgroundTask.execute();
                ((MainActivity) view.getContext())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame,VideoPlayerFragment.newInstance(lectureName))
                        .commit();
            }
        });


        try{
            //intent로 값을 가져옵니다 이때 JSONObject타입으로 가져옵니다
            JSONObject jsonObject = new JSONObject(result);


            //List.php 웹페이지에서 response라는 변수명으로 JSON 배열을 만들었음..
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;

            String lectureNum, lectureName, lectureTag;

            //JSON 배열 길이만큼 반복문을 실행
            while(count < jsonArray.length()){
                //count는 배열의 인덱스를 의미
                JSONObject object = jsonArray.getJSONObject(count);

                lectureNum = object.getString("lectureNum");//여기서 ID가 대문자임을 유의
                lectureName = object.getString("lectureName");
                lectureTag = object.getString("lectureTag");

                //값들을 User클래스에 묶어줍니다
                LectureVO lecture = new LectureVO(lectureNum, lectureName, lectureTag);
                lectureList.add(lecture);//리스트뷰에 값을 추가해줍니다
                count++;
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
        return view;
    }
}
