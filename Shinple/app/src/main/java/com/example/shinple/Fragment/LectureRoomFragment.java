package com.example.shinple.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shinple.Adapter.CopAdapter;
import com.example.shinple.Adapter.CourseAAdapter;
import com.example.shinple.Adapter.CourseBAdapter;
import com.example.shinple.Adapter.LectureListAdapter;
import com.example.shinple.Adapter.StringAdapter;
import com.example.shinple.MainActivity;
import com.example.shinple.R;
import com.example.shinple.VO.CopVO;
import com.example.shinple.VO.CourseVO;
import com.example.shinple.BackgroundTask;
import com.example.shinple.VO.LectureVO;
import com.example.shinple.VO.MemberVO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;


public class LectureRoomFragment extends Fragment{

    private CourseAAdapter adapter_course;
    private List<CourseVO> courseList;
    private RecyclerView rv_course;

    private CopAdapter my_cop_adapter;
    private List<CopVO> my_cop_list;
    private RecyclerView my_rv;
    private MemberVO member;
    private String result1;
    private String result2;

    public LectureRoomFragment() {
        // Required empty public constructor
    }

    /* Fragment 간의 이동을 위한 메소드 */
    public static LectureRoomFragment newInstance(String result1,String result2, MemberVO member) {
        LectureRoomFragment fragment = new LectureRoomFragment();
        Bundle args = new Bundle();
        args.putString("result1", result1);
        args.putString("result2", result2);
        args.putSerializable("result3",member);
        fragment.setArguments(args);
        return fragment;
    }

    /*fragment 생성 시 시작될 내용*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            result1 = getArguments().getString("result1");
            result2 = getArguments().getString("result2");
            member = (MemberVO)getArguments().getSerializable("result3");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lecture_room, container, false);

        courseList = new ArrayList<CourseVO>();
        my_cop_list = new ArrayList<CopVO>();

        TextView tv_point = (TextView)v.findViewById(R.id.tv_person_point);
        TextView tv_com = (TextView)v.findViewById(R.id.tv_company);
        TextView tv_name = (TextView)v.findViewById(R.id.tv_name);
        tv_point.setText(member.getMem_point());
        tv_name.setText(member.getMem_name());

        switch (member.getCompany_num()){
            case "0":
                tv_com.setText("신한DS");
                break;
            case "1":
                tv_com.setText("신한은행");
                break;
            case "2":
                tv_com.setText("신한카드");
                break;
            case "3":
                tv_com.setText("신한금융투자");
                break;
            case "4":
                tv_com.setText("신한생명");
                break;
       }

        rv_course = v.findViewById(R.id.rv_courseList);
        my_rv = v.findViewById(R.id.rv_mycop);
        LinearLayoutManager layoutManager = new LinearLayoutManager(v.getContext());
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(v.getContext());

        rv_course.setLayoutManager(layoutManager);
        my_rv.setLayoutManager(layoutManager2);
        adapter_course = new CourseAAdapter(v.getContext(),courseList);
        my_cop_adapter = new CopAdapter(v.getContext(),my_cop_list);
        rv_course.setAdapter(adapter_course);
        my_rv.setAdapter(my_cop_adapter);

        try{
            //intent로 값을 가져옵니다 이때 JSONObject타입으로 가져옵니다
            JSONObject jsonObject = new JSONObject(result1);


            //List.php 웹페이지에서 response라는 변수명으로 JSON 배열을 만들었음..
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;

            String copName, copRank, copIntro, copNum;

            //JSON 배열 길이만큼 반복문을 실행
            while(count < jsonArray.length()){
                //count는 배열의 인덱스를 의미
                JSONObject object = jsonArray.getJSONObject(count);

                copName = object.getString("cop_name");//여기서 ID가 대문자임을 유의
                copRank = object.getString("cop_rank");
                copIntro = object.getString("cop_intro");
                copNum = object.getString("cop_num");

                //값들을 User클래스에 묶어줍니다
                CopVO cop = new CopVO(copName, copRank, copIntro, copNum);
                my_cop_list.add(cop);//리스트뷰에 값을 추가해줍니다
                count++;
            }

        }catch(Exception e) {
            e.printStackTrace();
        }




        try{
            //intent로 값을 가져옵니다 이때 JSONObject타입으로 가져옵니다
            JSONObject jsonObject = new JSONObject(result2);

            //List.php 웹페이지에서 response라는 변수명으로 JSON 배열을 만들었음..
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;

            String courseName, courseLevel, tchName, courseText, courseNum, learnState;

            //JSON 배열 길이만큼 반복문을 실행
            while(count < jsonArray.length()){
                //count는 배열의 인덱스를 의미
                JSONObject object = jsonArray.getJSONObject(count);

                courseName = object.getString("course_title");//여기서 ID가 대문자임을 유의
                courseLevel = object.getString("course_level");
                tchName = object.getString("course_tch");
                courseText= object.getString("course_text");
                courseNum = object.getString("course_num");
                learnState = object.getString("learn_state");


                //값들을 User클래스에 묶어줍니다
                CourseVO course = new CourseVO(courseName, courseLevel, tchName, courseText, courseNum, learnState);
                courseList.add(course);//리스트뷰에 값을 추가해줍니다
                count++;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

        Spinner spinner = (Spinner)v.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent , View view, int pos ,long id) {
                Toast.makeText(parent.getContext(), "선택된 건" +parent.getItemAtPosition(pos).toString(),Toast.LENGTH_LONG).show();
            }
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        } );
        return v;
    }

}
