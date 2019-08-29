package com.example.shinple.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shinple.R;
import com.example.shinple.VO.FilterVO;
import com.example.shinple.VO.LectureVO;

import java.util.List;

public class RecentGridAdapter extends BaseAdapter {

    private Context context;
    private List<LectureVO> lectureList;

    public interface OnCheckedChangeListener {
        void onItemClick(View view, String lec_order, String lec_title, String lec_text);
    }

    private RecentGridAdapter.OnCheckedChangeListener mListener = null;

    public void setOnCheckListener(RecentGridAdapter.OnCheckedChangeListener listener) {
        this.mListener = listener;
    }


    public RecentGridAdapter(Context context, List<LectureVO> lecterList) {
        this.context = context;
        this.lectureList = lecterList;
    }

    //출력할 총갯수를 설정하는 메소드
    @Override
    public int getCount() {
        return lectureList.size();
    }

    //특정한 유저를 반환하는 메소드
    @Override
    public Object getItem(int i) {
        return lectureList.get(i);
    }

    //아이템별 아이디를 반환하는 메소드
    @Override
    public long getItemId(int i) {
        return i;
    }

    //가장 중요한 부분
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.recent_lecture, null);

        ConstraintLayout constraintLayout = (ConstraintLayout)v.findViewById(R.id.cl_recent);

        TextView lectureName = (TextView)v.findViewById(R.id.tv_recent_name);
        TextView lectureInfo = (TextView)v.findViewById(R.id.tv_recent_info);
        TextView lectureTime = (TextView)v.findViewById(R.id.tv_recent_time);

        lectureName.setText(lectureList.get(i).getLec_title());
        lectureInfo.setText(lectureList.get(i).getLec_text());
        lectureTime.setText(lectureList.get(i).getLec_time());


        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = i;
                if (pos != RecyclerView.NO_POSITION){
                    if(mListener != null){
                        mListener.onItemClick(view,lectureList.get(pos).getLec_order(),lectureList.get(pos).getLec_title(),lectureList.get(pos).getLec_text());
                    }
                }
            }
        });

        return v;
    }
}