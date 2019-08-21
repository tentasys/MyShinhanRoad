package com.example.shinple;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.shinple.VO.CourseVO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 하단 강좌(강의 리스트) 누르면 연결되는 페이지
 *
 */
public class CourseListFragment extends Fragment {


    private ListView listView;
    private CourseListAdapter adapter;
    private CourseAAdapter adapter2;
    private CourseAAdapter adapter3;
    private List<CourseVO> courseList;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;


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

        recyclerView = v.findViewById(R.id.rv_level2);
        recyclerView2 = v.findViewById(R.id.rv_level3);
        LinearLayoutManager layoutManager = new LinearLayoutManager(v.getContext(),LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(v.getContext(),LinearLayoutManager.HORIZONTAL,false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);
        adapter2 = new CourseAAdapter(v.getContext(),courseList,onClickItem);
        adapter3 = new CourseAAdapter(v.getContext(),courseList,onClickItem);
        recyclerView.setAdapter(adapter2);
        recyclerView2.setAdapter(adapter3);

        listView = v.findViewById(R.id.lv_course);
        adapter = new CourseListAdapter(v.getContext(), courseList);
        listView.setAdapter(adapter);
        return v;
    }

    private View.OnClickListener onClickItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

}
