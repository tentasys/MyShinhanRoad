package com.example.shinple.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;


import com.example.shinple.R;
import com.example.shinple.VO.FilterVO;
import java.util.List;

public class FilterAdapter extends BaseAdapter {

    private Context context;
    private List<FilterVO> filterList;
    private ToggleButton tb;

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
        /*
        layout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Fragment fm = CourseListFragment.newInstance();
                Bundle bundle = new Bundle();
                bundle.putString("tag", "tag_name");
                ((MainActivity)view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.frame, fm).addToBackStack(null).commit();
            }
        });*/

        return v;
    }
}
