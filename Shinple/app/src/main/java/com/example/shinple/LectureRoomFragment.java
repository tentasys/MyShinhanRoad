package com.example.shinple;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shinple.VO.CourseVO;
import com.example.shinple.BackgroundTask;

import java.util.ArrayList;
import java.util.List;


public class LectureRoomFragment extends Fragment{

    private CourseBAdapter adapter;
    private List<CourseVO> courseList;
    private RecyclerView recyclerView;



    public LectureRoomFragment() {
        // Required empty public constructor
    }

    /* Fragment 간의 이동을 위한 메소드 */
    public static LectureRoomFragment newInstance() {
        return new LectureRoomFragment();
    }

    /*fragment 생성 시 시작될 내용*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lecture_room, container, false);

        courseList = new ArrayList<CourseVO>();
        /*  */
        String courseName[] ={"Name1","Name2","Name3"};
        String courseLevel[] ={"1","2","3"};
        String tagName[]={"bigdata","blockchain","AI"};

        int length =courseName.length;
        //JSON 배열 길이만큼 반복문을 실행
        for(int each_course=0;each_course<length;each_course++) {
            String each_name = courseName[each_course];
            String each_level = courseLevel[each_course];
            String each_tagName = tagName[each_course];

            CourseVO course = new CourseVO(each_name, each_level, each_tagName);
            courseList.add(course);//리스트뷰에 값을 추가해줍니다

            recyclerView = v.findViewById(R.id.rv_courseList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(v.getContext());
            recyclerView.setLayoutManager(layoutManager);
            adapter = new CourseBAdapter(v.getContext(),courseList);
            recyclerView.setAdapter(adapter);

            adapter.setOnItemClickListener(new CourseBAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, String courseName, String courseInfo) {
                    //new CourseListFragment.BackgroundTask().execute();
                    BackgroundTask backgroundTask = new BackgroundTask("https://192.168.1.187/lectureList.php");
                    backgroundTask.execute();
                    ((MainActivity) view.getContext())
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame,LectureListFragment.newInstance(courseName, courseInfo, "hello"))
                            .commit();
                }
            });
        }
        return v;
    }

}
