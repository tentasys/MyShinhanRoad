package com.example.shinple.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.shinple.Adapter.CourseAAdapter;
import com.example.shinple.Adapter.TestAdapter;
import com.example.shinple.BackgroundTask;
import com.example.shinple.MainActivity;
import com.example.shinple.R;
import com.example.shinple.VO.FilterVO;
import com.example.shinple.VO.QuizVO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;
import java.util.ArrayList;
import java.lang.Math;

public class TestFragment extends Fragment {

    private TestAdapter adapter;
    private List<QuizVO> testList;
    private RecyclerView rv_quiz;
    private String result = "";
    private Button submit;
// 정답 받는 부분
    private int answer_check[];
    private int answer_list[];
    private int count = 0;
    private float score = 0;

    public TestFragment() {
    }

    public static TestFragment newInstance(String param1) {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        args.putString("test", param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            result = getArguments().getString("test");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_test, container, false);
        // Inflate the layout for this fragment

        testList = new ArrayList<QuizVO>();

        rv_quiz = v.findViewById(R.id.rv_quiz);
        LinearLayoutManager layoutManager = new LinearLayoutManager(v.getContext());
        rv_quiz.setLayoutManager(layoutManager);
        adapter = new TestAdapter(v.getContext(),testList);
        rv_quiz.setAdapter(adapter);

        try{
            //intent로 값을 가져옵니다 이때 JSONObject타입으로 가져옵니다
            JSONObject jsonObject = new JSONObject(result);


            //List.php 웹페이지에서 response라는 변수명으로 JSON 배열을 만들었음..
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;
            answer_check = new int[jsonArray.length()];
            answer_list = new int[jsonArray.length()];
            String quizNum, question, quiz1, quiz2, quiz3, quiz4, answer;

            //JSON 배열 길이만큼 반복문을 실행
            while(count < jsonArray.length()){
                //count는 배열의 인덱스를 의미
                JSONObject object = jsonArray.getJSONObject(count);

                quizNum = object.getString("quiz_num");//여기서 ID가 대문자임을 유의
                question = object.getString("question");
                quiz1 = object.getString("quiz1");
                quiz2 = object.getString("quiz2");
                quiz3 = object.getString("quiz3");
                quiz4 = object.getString("quiz4");
                answer = object.getString("answer");
                answer_check[count] = Integer.parseInt(answer);
                //값들을 User클래스에 묶어줍니다
                QuizVO quiz = new QuizVO(quizNum, question, quiz1, quiz2, quiz3, quiz4, answer);
                testList.add(quiz);//리스트뷰에 값을 추가해줍니다
                count++;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }


        adapter.setOnItemClickListener(new TestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int answer) {
                Log.d("ttt",String.valueOf(position));
                Log.d("aaaa",String.valueOf(answer));
                // 답찾기//
                answer_list[position] = answer;
                }

        });

        submit = v.findViewById(R.id.bt_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < answer_check.length; i++) {
                    if (answer_check[i] == answer_list[i]) {
                        count+=1;
                    }
                }
                score = Math.round((Double.valueOf(count)/Double.valueOf(answer_list.length))*100);
                Log.d("action", String.valueOf(score));
                count = 0;
                score = 0;
            }
        });

        return v;
    }
}
