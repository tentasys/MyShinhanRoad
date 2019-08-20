package com.example.shinple;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.shinple.VO.CourseVO;

import java.util.List;
import java.util.zip.Inflater;

public class CourseListAdapter extends BaseAdapter {


    private Context context;
    private List<CourseVO> courseList;

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

        Log.i("my_tag",courseList.get(i).getcourseName());
        courseName.setText(courseList.get(i).getcourseName());
        Log.w("view",courseList.get(i).getcourseName());
        courseLevel.setText(courseList.get(i).getcourseLevel());
        tagName.setText(courseList.get(i).gettagName());

        return v;
    }
}
