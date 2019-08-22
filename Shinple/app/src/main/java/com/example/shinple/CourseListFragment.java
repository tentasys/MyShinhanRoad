package com.example.shinple;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.shinple.VO.CourseVO;
import com.example.shinple.BackgroundTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 하단 강좌(강의 리스트) 누르면 연결되는 페이지
 *
 */
public class CourseListFragment extends Fragment{


    private ListView listView;
    private CourseAAdapter adapter;
    private CourseAAdapter adapter2;
    private CourseAAdapter adapter3;
    private List<CourseVO> courseList;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;
    private String resultt = "";

    public CourseListFragment() {
        // Required empty public constructor
    }
    public static CourseListFragment newInstance() {
        return new CourseListFragment();
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
        View v = inflater.inflate(R.layout.fragment_course_list, container, false);


        // Inflate the layout for this fragment

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
        }

        recyclerView = v.findViewById(R.id.rv_level1);
        recyclerView2 = v.findViewById(R.id.rv_level2);
        recyclerView3 = v.findViewById(R.id.rv_level3);
        LinearLayoutManager layoutManager = new LinearLayoutManager(v.getContext(),LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(v.getContext(),LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(v.getContext(),LinearLayoutManager.HORIZONTAL,false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView3.setLayoutManager(layoutManager3);
        adapter = new CourseAAdapter(v.getContext(),courseList);
        adapter2 = new CourseAAdapter(v.getContext(),courseList);
        adapter3 = new CourseAAdapter(v.getContext(),courseList);
        recyclerView.setAdapter(adapter);
        recyclerView2.setAdapter(adapter2);
        recyclerView3.setAdapter(adapter3);

        adapter.setOnItemClickListener(new CourseAAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String courseName, String courseInfo) {
                //new CourseListFragment.BackgroundTask().execute();
                BackgroundTask backgroundTask = new BackgroundTask("https://192.168.1.187/lectureList.php");
                backgroundTask.execute();
                ((MainActivity) view.getContext())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame,LectureListFragment.newInstance(courseName, courseInfo, resultt))
                        .commit();
            }
        });
        adapter2.setOnItemClickListener(new CourseAAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String courseName, String courseInfo) {
                BackgroundTask backgroundTask = new BackgroundTask("https://192.168.1.187/lectureList.php");
                backgroundTask.execute();
                ((MainActivity) view.getContext())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame,LectureListFragment.newInstance(courseName, courseInfo, resultt))
                        .commit();
            }
        });
        adapter3.setOnItemClickListener(new CourseAAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String courseName, String courseInfo) {
                BackgroundTask backgroundTask = new BackgroundTask("https://192.168.1.187/lectureList.php");
                backgroundTask.execute();
                ((MainActivity) view.getContext())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame,LectureListFragment.newInstance(courseName, courseInfo, resultt))
                        .commit();
            }
        });
        return v;
    }
}
