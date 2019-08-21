package com.example.shinple;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.shinple.VO.CourseVO;

import java.util.List;

public class CourseAAdapter  extends RecyclerView.Adapter<CourseAAdapter.ViewHolder> {

    private List<CourseVO> courseList;
    private Context context;
    private View.OnClickListener onClickItem;

    public CourseAAdapter(Context context, List<CourseVO> courseList, View.OnClickListener onClickItem) {
        this.context = context;
        this.courseList = courseList;
        this.onClickItem = onClickItem;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // context 와 parent.getContext() 는 같다.
        View view = LayoutInflater.from(context)
                .inflate(R.layout.course_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CourseAAdapter.ViewHolder holder, int position) {
        TextView courseName = (TextView) holder.itemView.findViewById(R.id.tv_courseName);
        TextView courseLevel = (TextView) holder.itemView.findViewById(R.id.tv_lv);
        TextView tagName = (TextView) holder.itemView.findViewById(R.id.tv_courseInfo);

        courseName.setText(courseList.get(position).getcourseName());
        courseLevel.setText(courseList.get(position).getcourseLevel());
        //tagName.setText(courseList.get(position).gettagName());
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);

        }
    }
}