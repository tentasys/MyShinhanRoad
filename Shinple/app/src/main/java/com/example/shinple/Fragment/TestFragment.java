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
import com.example.shinple.VO.QuizVO;

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
    private int answer_check[] = {1,2,4};
    private int answer_list[] = new int[3];
    private int count = 0;
    private float score = 0;

    public TestFragment() {
    }

    public static TestFragment newInstance() { return new TestFragment();}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_test, container, false);
        // Inflate the layout for this fragment

        testList = new ArrayList<QuizVO>();
        String question[] ={"aaaaa","bbbbb","ccccc"};
        String example[] ={"ㅁㄴㄴㄴㄴㄴㅇ","ㅁㄴㅇㅁㄴㅇ","sdsdsf","aabasbasdb"};

        int length =question.length;
        for(int each_test=0;each_test<length;each_test++) {
            String each_question = question[each_test];
            String each_example = example[each_test];

            QuizVO course = new QuizVO(each_question, each_example);
            testList.add(course);
        }


        rv_quiz = v.findViewById(R.id.rv_quiz);

        LinearLayoutManager layoutManager = new LinearLayoutManager(v.getContext());

        rv_quiz.setLayoutManager(layoutManager);

        adapter = new TestAdapter(v.getContext(),testList);

        rv_quiz.setAdapter(adapter);

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
