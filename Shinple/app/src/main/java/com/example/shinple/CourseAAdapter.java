package com.example.shinple;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.shinple.VO.CourseVO;

import java.util.List;

public class CourseAAdapter  extends RecyclerView.Adapter<CourseAAdapter.ViewHolder> {

    private List<CourseVO> courseList;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(View view, String courseName, String courseInfo);
    }

    private OnItemClickListener mListener = null ;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }

    public CourseAAdapter(Context context, List<CourseVO> courseList) {
        this.context = context;
        this.courseList = courseList;
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
        TextView courseLevel = (TextView) holder.itemView.findViewById(R.id.tv_cl2_lv);
        TextView tagName = (TextView) holder.itemView.findViewById(R.id.tv_courseInfo);

        courseName.setText(courseList.get(position).getcourseName());
        courseLevel.setText(courseList.get(position).getcourseLevel());
    }


    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button bt_sub;
        public ViewHolder(final View itemView) {
            super(itemView);
            bt_sub = itemView.findViewById(R.id.bt_subCC);
            bt_sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        if(mListener != null){
                            mListener.onItemClick(view,courseList.get(pos).getcourseName(),courseList.get(pos).getcourseLevel());
                        }
                    }
                }
            });
        }
    }
}