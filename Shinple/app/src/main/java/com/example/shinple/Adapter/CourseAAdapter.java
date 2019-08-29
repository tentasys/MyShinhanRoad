package com.example.shinple.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.shinple.R;
import com.example.shinple.VO.CourseVO;

import java.util.List;

public class CourseAAdapter  extends RecyclerView.Adapter<CourseAAdapter.ViewHolder> {

    private List<CourseVO> courseList;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(View view,CourseVO course);
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
                .inflate(R.layout.course_item3, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CourseAAdapter.ViewHolder holder, int position) {
        TextView courseName = (TextView) holder.itemView.findViewById(R.id.tv_courseName3);
        TextView courseLevel = (TextView) holder.itemView.findViewById(R.id.tv_cl2_lv3);
        TextView tchName = (TextView)holder.itemView.findViewById(R.id.tv_courseinfo3) ;
//        TextView tagName = (TextView) holder.itemView.findViewById(R.id.tv_lec_courseInfo);
        tchName.setText(courseList.get(position).getTchName());
        courseName.setText(courseList.get(position).getcourseName());
        courseLevel.setText(courseList.get(position).getcourseLevel());
        Button LS = (Button) holder.itemView.findViewById(R.id.bt_subCC3);
        if(courseList.get(position).getLearnState().equals("1")){
            LS.setBackgroundResource(R.drawable.nowlearning);
            LS.setText("수강중");
        }
        else if (courseList.get(position).getLearnState().equals("2")){
            LS.setBackgroundResource(R.drawable.afterlearning);
            LS.setText("수강완료");
        }
    }


    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button bt_sub;
        public ViewHolder(final View itemView) {
            super(itemView);
            bt_sub = itemView.findViewById(R.id.bt_subCC3);
            bt_sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        if(mListener != null){
                            mListener.onItemClick(view,courseList.get(pos));
                        }
                    }
                }
            });
        }
    }
}