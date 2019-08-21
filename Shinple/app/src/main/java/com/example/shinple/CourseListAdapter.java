package com.example.shinple;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.shinple.VO.CourseVO;

import java.util.List;
import java.util.zip.Inflater;

public class CourseListAdapter extends BaseAdapter {


    private Context context;
    private List<CourseVO> courseList;
    private  View MainActivity;

    public CourseListAdapter(Context context, List<CourseVO> courseList){
        this.context = context;
        this.courseList = courseList;
}

    @Override
    public int getCount() {
        return courseList.size();
    }

    //특정한 유저를 반환하는 메소드
    @Override
    public Object getItem(int i) {
        return courseList.get(i);
    }

    //아이템별 아이디를 반환하는 메소드
    @Override
    public long getItemId(int i) {
        return i;
    }

    //가장 중요한 부분
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.course_item, null);
        //뷰에 다음 컴포넌트들을 연결시켜줌
        TextView courseName = (TextView)v.findViewById(R.id.tv_courseName);
        TextView courseLevel = (TextView)v.findViewById(R.id.tv_lv);
        TextView tagName = (TextView)v.findViewById(R.id.tv_courseinfo);
        ImageButton btsub = (ImageButton) v.findViewById(R.id.bt_subC);
        courseName.setText(courseList.get(i).getcourseName());
        courseLevel.setText(courseList.get(i).getcourseLevel());
        tagName.setText(courseList.get(i).gettagName());
        btsub.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "강좌 상세 페이지로 이동합니다 - ", Toast.LENGTH_SHORT).show();

                Fragment fr = LectureListFragment.newInstance();   //fragment에 새로운 fragment 객체 할당
                Bundle bundle = new Bundle();  // 변수 전달을 위한 bundle 생성
                bundle.putString("courseName","courseName");  // course_id를 넣어주기!!  ==> 여기서 변수를 다르게 전달 가능
                bundle.putString("courseInfo","courseInfo");
                bundle.putString("lectureList","lectureList");
                fr.setArguments(bundle);  // fragment에 bundle 추가
                ((MainActivity) view.getContext())  // context 받고
                        .getSupportFragmentManager()  // framgmentmanager받고 ==> getSupportFragment를 쓰면 이전 버전도 사용 가능하다 함.
                        .beginTransaction()   //transaction 시작!
                        .replace(R.id.frame,fr)  //MainActivity의 frame에 fr 넣어주기
                        .commit();

            }
        });
        return v;
    }
}
