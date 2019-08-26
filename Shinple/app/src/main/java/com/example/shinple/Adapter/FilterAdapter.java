package com.example.shinple.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;


import androidx.recyclerview.widget.RecyclerView;

import com.example.shinple.R;
import com.example.shinple.VO.FilterVO;
import java.util.List;

public class FilterAdapter extends BaseAdapter {

    private Context context;
    private List<FilterVO> filterList;
    private ToggleButton tb;

    public interface OnCheckedChangeListener {
        void onCheck(View view, int pos, String Filter);
    }

    private FilterAdapter.OnCheckedChangeListener mListener = null ;

    public void setOnCheckListener (FilterAdapter.OnCheckedChangeListener listener){
        this.mListener = listener;
    }


    public FilterAdapter(Context context, List<FilterVO> filterList){
        this.context = context;
        this.filterList = filterList;
    }

    //출력할 총갯수를 설정하는 메소드
    @Override
    public int getCount() {
        return filterList.size();
    }

    //특정한 유저를 반환하는 메소드
    @Override
    public Object getItem(int i) {
        return filterList.get(i);
    }

    //아이템별 아이디를 반환하는 메소드
    @Override
    public long getItemId(int i) {
        return i;
    }

    //가장 중요한 부분
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.fileter_item, null);
        //뷰에 다음 컴포넌트들을 연결시켜줌
        tb = v.findViewById(R.id.tb_tag);
        String fi = filterList.get(i).getFilter();
        if (fi != null){
            tb.setText(fi);
            tb.setTextOff(fi);
            tb.setTextOn(fi);
        }

        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    int pos = i;
                    if (pos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            mListener.onCheck(v, i, fi);
                        }
                    }
                } else {
                    int pos = i;
                    if (pos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            mListener.onCheck(v, i, null);
                        }
                    }
                }
            }
        });
        return v;
    }
}
