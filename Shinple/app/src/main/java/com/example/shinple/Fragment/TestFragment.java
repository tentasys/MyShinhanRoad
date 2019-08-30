package com.example.shinple.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shinple.Adapter.CourseAAdapter;
import com.example.shinple.Adapter.TestAdapter;
import com.example.shinple.BackgroundTask;
import com.example.shinple.MainActivity;
import com.example.shinple.R;
import com.example.shinple.VO.CourseVO;
import com.example.shinple.VO.FilterVO;
import com.example.shinple.VO.MemberVO;
import com.example.shinple.VO.QuizVO;
import com.example.shinple.ui.login.LoginActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.*;
import java.util.ArrayList;
import java.lang.Math;

public class TestFragment extends Fragment {

    private TestAdapter adapter;
    private List<QuizVO> testList;
    private RecyclerView rv_quiz;
    private String result = "";
    private MemberVO member;
    private CourseVO course;
    private Button submit;
    private int Point = 0;
    private String data;
// 정답 받는 부분
    private int answer_check[];
    private int answer_list[];
    private int count = 0;
    private float score = 0;

    public TestFragment() {
    }

    public static TestFragment newInstance(String param1, MemberVO member, CourseVO course) {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        args.putString("test", param1);
        args.putSerializable("member",member);
        args.putSerializable("course",course);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            result = getArguments().getString("test");
            member = (MemberVO)getArguments().getSerializable("member");
            course = (CourseVO)getArguments().getSerializable("course");
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

                if (course.getcourseLevel().equals("1")){
                    Point = 10;
                }
                else if (course.getcourseLevel().equals("2")){
                    Point = 12;
                }
                else if (course.getcourseLevel().equals("3")){
                    Point = 14;
                }
                else if (course.getcourseLevel().equals("4")){
                    Point = 16;
                }
                else if (course.getcourseLevel().equals("5")){
                    Point = 18;
                }
                else if (course.getcourseLevel().equals("6")){
                    Point = 20;
                }
                else if (course.getcourseLevel().equals("7")){
                    Point = 22;
                }


                score = Math.round((Double.valueOf(count)/Double.valueOf(answer_list.length))*100);
                Log.d("action", String.valueOf(score));

                showCustomDialog(view,score);

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
                        .commit();
            }
                /*AlertDialog.Builder ab = new AlertDialog.Builder(view.getContext());
                ab.setTitle("시험결과");
                ab.setMessage(score + "점으로 " + AN + "하셨습니다.");
                ab.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(score > 60){
                            String result = "";
                            try{
                                data = URLEncoder.encode("courseNUM", "UTF-8") + "=" + URLEncoder.encode(courseNum, "UTF-8");
                                data += "&" + URLEncoder.encode("userNum", "UTF-8") + "=" + URLEncoder.encode(member.getMem_num(), "UTF-8");
                                data += "&" + URLEncoder.encode("score", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(Point), "UTF-8");
                                data += "&" + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode( "1", "UTF-8");
                                data += "&" + URLEncoder.encode("state", "UTF-8") + "=" + URLEncoder.encode("2", "UTF-8");
                            } catch (Exception e){

                            }
                            BackgroundTask backgroundTask = new BackgroundTask("app/problem.php",data);
                            try{
                                result = backgroundTask.execute().get();
                                Log.d("sdsd",result);
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                            FragmentManager fm = ((MainActivity) view.getContext())
                                    .getSupportFragmentManager();
                            fm.beginTransaction()
                                    .remove(TestFragment.this).commit();
                            fm.popBackStack();
                        }
                        else{
                            FragmentManager fm = ((MainActivity) view.getContext())
                                    .getSupportFragmentManager();
                            fm.beginTransaction()
                                    .remove(TestFragment.this).commit();
                            fm.popBackStack();
                        }
                    }
                });
                ab.show();*/
        });

        count = 0;
        score = 0;
        return v;
    }


    private void showCustomDialog(View view, float score) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = view.findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.my_dialog, viewGroup, false);

        TextView title = dialogView.findViewById(R.id.dialog_title);
        TextView context = dialogView.findViewById(R.id.dialog_context);
        TextView bt_yes = dialogView.findViewById(R.id.buttonOk);
        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);
        title.setText("시험결과");
        String AN = "";
        if(score > 60)
            AN = "합격";
        else
            AN = "불합격";
        context.setText(Math.round(score) + "점으로 " + AN + "하셨습니다.");
        AlertDialog alertDialog = builder.create();
        bt_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(score > 60){
                    String result = "";
                    try{
                        data = URLEncoder.encode("courseNUM", "UTF-8") + "=" + URLEncoder.encode(course.getCourseNum(), "UTF-8");
                        data += "&" + URLEncoder.encode("userNum", "UTF-8") + "=" + URLEncoder.encode(member.getMem_num(), "UTF-8");
                        data += "&" + URLEncoder.encode("score", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(Point), "UTF-8");
                        data += "&" + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode( "1", "UTF-8");
                        data += "&" + URLEncoder.encode("state", "UTF-8") + "=" + URLEncoder.encode("2", "UTF-8");
                    } catch (Exception e){

                    }
                    BackgroundTask backgroundTask = new BackgroundTask("app/problem.php",data);
                    try{
                        result = backgroundTask.execute().get();
                        Log.d("sdsd",result);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    alertDialog.dismiss();
                    FragmentManager fm = ((MainActivity) view.getContext())
                            .getSupportFragmentManager();
                    fm.beginTransaction()
                            .remove(TestFragment.this).commit();
                    fm.popBackStack();
                }
                else{
                    alertDialog.dismiss();
                    FragmentManager fm = ((MainActivity) view.getContext())
                            .getSupportFragmentManager();
                    fm.beginTransaction()
                            .remove(TestFragment.this).commit();
                    fm.popBackStack();
                }
            }
        });

        alertDialog.show();

    }
}
