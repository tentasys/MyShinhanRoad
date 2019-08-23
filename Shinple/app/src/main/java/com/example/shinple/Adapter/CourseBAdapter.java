package com.example.shinple.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shinple.R;
import com.example.shinple.VO.CourseVO;

import java.util.List;

public class CourseBAdapter extends RecyclerView.Adapter<CourseBAdapter.ViewHolder> {

    private List<CourseVO> courseList;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(View view, String courseName, String courseInfo);
    }

    private CourseBAdapter.OnItemClickListener mListener = null ;

    public void setOnItemClickListener(CourseBAdapter.OnItemClickListener listener) {
        this.mListener = listener ;
    }

    public CourseBAdapter(Context context, List<CourseVO> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @Override
    public CourseBAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // context 와 parent.getContext() 는 같다.
        View view = LayoutInflater.from(context)
                .inflate(R.layout.course_item2, parent, false);

        return new CourseBAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CourseBAdapter.ViewHolder holder, int position) {
        TextView courseName = (TextView) holder.itemView.findViewById(R.id.tv_cl2_courseName);
        TextView courseLevel = (TextView) holder.itemView.findViewById(R.id.tv_cl2_lv);
        TextView tagName = (TextView) holder.itemView.findViewById(R.id.tv_cl2_courseInfo);

        courseName.setText(courseList.get(position).getcourseName());
        courseLevel.setText(courseList.get(position).getcourseLevel());
    }


    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
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
