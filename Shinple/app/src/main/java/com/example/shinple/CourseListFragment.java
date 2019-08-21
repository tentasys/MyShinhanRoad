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
    private CourseListAdapter adapter;
    private CourseAAdapter adapter2;
    private CourseAAdapter adapter3;
    private List<CourseVO> courseList;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
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

        recyclerView = v.findViewById(R.id.rv_level2);
        recyclerView2 = v.findViewById(R.id.rv_level3);
        LinearLayoutManager layoutManager = new LinearLayoutManager(v.getContext(),LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(v.getContext(),LinearLayoutManager.HORIZONTAL,false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);
        adapter2 = new CourseAAdapter(v.getContext(),courseList);
        adapter3 = new CourseAAdapter(v.getContext(),courseList);
        recyclerView.setAdapter(adapter2);
        recyclerView2.setAdapter(adapter3);

        listView = v.findViewById(R.id.lv_course);
        adapter = new CourseListAdapter(v.getContext(), courseList);
        listView.setAdapter(adapter);

        adapter2.setOnItemClickListener(new CourseAAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String courseName, String courseInfo) {
                new CourseListFragment.BackgroundTask().execute();
                ((MainActivity) view.getContext())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame,LectureListFragment.newInstance(courseName, courseInfo, resultt))
                        .commit();
            }
        });

        return v;
    }


    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            //List.php은 파싱으로 가져올 웹페이지
            target = "http://192.168.1.187/lectureList.php";
        }

        @Override
        protected String doInBackground(Void... voids) {

            try{
                URL url = new URL(target);//URL 객체 생성

                //URL을 이용해서 웹페이지에 연결하는 부분
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

                //바이트단위 입력스트림 생성 소스는 httpURLConnection
                InputStream inputStream = httpURLConnection.getInputStream();

                //웹페이지 출력물을 버퍼로 받음 버퍼로 하면 속도가 더 빨라짐
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;

                //문자열 처리를 더 빠르게 하기 위해 StringBuilder클래스를 사용함
                StringBuilder stringBuilder = new StringBuilder();

                //한줄씩 읽어서 stringBuilder에 저장함
                while((temp = bufferedReader.readLine()) != null){
                    stringBuilder.append(temp + "\n");//stringBuilder에 넣어줌
                }

                //사용했던 것도 다 닫아줌
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();//trim은 앞뒤의 공백을 제거함

            }catch(Exception e){
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("WHYWHY",result);
        }
    }

}
