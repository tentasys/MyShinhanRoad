package com.example.shinple.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
import com.example.shinple.Adapter.LectureRoomSpinnerAdapter;
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

    private CourseAAdapter[] adapter_course;
    private List<CourseVO>[] courseList;
    private RecyclerView rv_course;

    private CopAdapter essential_course_adapter;
    private List<CopVO> essential_course_list;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lecture_room, container, false);
        member = new MemberVO("1","name","12","122");
        essential_course_list = new ArrayList<CopVO>();

        /* 0 : 전체강좌, 1: 수강 중 2:테스트 완료 3:수강 완료*/
        courseList = new ArrayList[4];
        adapter_course = new CourseAAdapter[4];
        for(int listIndex = 0; listIndex<courseList.length;listIndex++){
            courseList[listIndex] = new ArrayList<CourseVO>();
        }
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
        jsonParsing();  //jsonparsing해서 list변수들에 값을 넣어줌.
        essential_course_adapter = new CopAdapter(v.getContext(),essential_course_list);

        for(int listIndex = 0; listIndex<courseList.length;listIndex++){
            adapter_course[listIndex] = new CourseAAdapter(v.getContext(),courseList[listIndex]);
        }
        rv_course.setAdapter(adapter_course[0]);
        my_rv.setAdapter(essential_course_adapter);

        /*
        0 : 수강 전
        1 : 수강 중
        2 : 테스트 완료(수료처리 대기)
        3 :수료완료(웹에서 처리)
        */
        //UI생성
        Spinner spinner = (Spinner)v.findViewById(R.id.spinner);

        //Adapter
        LectureRoomSpinnerAdapter adapterSpinner = new LectureRoomSpinnerAdapter(v);

        //Adapter 적용
        spinner.setAdapter(adapterSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent , View view, int pos ,long id) {

                if(parent.getItemAtPosition(pos).toString().equals("수강 중")){
                    Toast.makeText(parent.getContext(), "선택된 건 " +parent.getItemAtPosition(pos).toString(),Toast.LENGTH_LONG).show();
                    rv_course.setAdapter(adapter_course[1]);

                }else if(parent.getItemAtPosition(pos).toString().equals("테스트 완료")){
                    Toast.makeText(parent.getContext(), "선택된 건 " +parent.getItemAtPosition(pos).toString(),Toast.LENGTH_LONG).show();
                    rv_course.setAdapter(adapter_course[2]);
                }else if(parent.getItemAtPosition(pos).toString().equals("수강 완료")){
                    Toast.makeText(parent.getContext(), "선택된 건 " +parent.getItemAtPosition(pos).toString(),Toast.LENGTH_LONG).show();
                    rv_course.setAdapter(adapter_course[3]);
                }else{
                    Toast.makeText(parent.getContext(), "선택된 건 " +parent.getItemAtPosition(pos).toString(),Toast.LENGTH_LONG).show();
                    rv_course.setAdapter(adapter_course[0]);
                }
            }// onItemSelected end
            public void onNothingSelected(AdapterView<?> arg0) {

            }  //onNothingSelected end
        });

        return v;
    }  //onCreateView end

    public void jsonParsing(){

        try{
            //intent로 값을 가져옵니다 이때 JSONObject타입으로 가져옵니다
            JSONObject jsonObject = new JSONObject(result1);


            //List.php 웹페이지에서 response라는 변수명으로 JSON 배열을 만 들었음..
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
                essential_course_list.add(cop);//리스트뷰에 값을 추가해줍니다
                count++;
            }


            //intent로 값을 가져옵니다 이때 JSONObject타입으로 가져옵니다
            jsonObject = new JSONObject(result2);

            //List.php 웹페이지에서 response라는 변수명으로 JSON 배열을 만들었음..
            jsonArray = jsonObject.getJSONArray("response");
            count = 0;

            String courseName, courseLevel, tchName, courseText, courseNum, learnState;

            //JSON 배열 길이만큼 반복문을 실행
            while(count < jsonArray.length()) {
                //count는 배열의 인덱스를 의미
                JSONObject object = jsonArray.getJSONObject(count);
                Log.e("myTag",object.toString());
                courseName = object.getString("course_title");//여기서 ID가 대문자임을 유의
                courseLevel = object.getString("course_level");
                tchName = object.getString("course_tch");
                courseText = object.getString("course_text");
                courseNum = object.getString("course_num");
                learnState = object.getString("learn_state");

                if(learnState.equals("1")){
                    courseList[1].add(new CourseVO(courseName, courseLevel, tchName, courseText, courseNum, learnState) );
                }else if(learnState.equals("2")){
                    courseList[2].add(new CourseVO(courseName, courseLevel, tchName, courseText, courseNum, learnState) );

                }else if(learnState.equals("3")){
                    courseList[3].add(new CourseVO(courseName, courseLevel, tchName, courseText, courseNum, learnState) );

                }else{
                    Log.e("CourseListError","목록 에러");
                }
                    courseList[0].addAll(courseList[1]); courseList[0].addAll(courseList[2]); courseList[0].addAll(courseList[3]);
                count++;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}
