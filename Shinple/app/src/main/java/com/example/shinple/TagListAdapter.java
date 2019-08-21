package com.example.shinple;


import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.shinple.VO.TagVO;

import java.util.List;

public class TagListAdapter extends BaseAdapter{
    private Context context;
    private List<TagVO> tagList;

    public TagListAdapter(Context context, List<TagVO> userList){
        this.context = context;
        this.tagList = userList;
    }

    //출력할 총갯수를 설정하는 메소드
    @Override
    public int getCount() {
        return tagList.size();
    }

    //특정한 유저를 반환하는 메소드
    @Override
    public Object getItem(int i) {
        return tagList.get(i);
    }

    //아이템별 아이디를 반환하는 메소드
    @Override
    public long getItemId(int i) {
        return i;
    }

    //가장 중요한 부분
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.tag_item, null);
        View layout = v.findViewById(R.id.tag_each);
        //뷰에 다음 컴포넌트들을 연결시켜줌
        TextView userID = (TextView)v.findViewById(R.id.tag_Id);
        /*
        TextView userPassword = (TextView)v.findViewById(R.id.userPassword);
        TextView userName = (TextView)v.findViewById(R.id.userName);
        TextView userAge = (TextView)v.findViewById(R.id.userAge);
        */
        userID.setText(tagList.get(i).getTag_Id());
        /*
        userPassword.setText(userList.get(i).getUserPassword());
        userName.setText(userList.get(i).getUserName());
        userAge.setText(userList.get(i).getUserAge());
        */


        /*
        //이렇게하면 findViewWithTag를 쓸 수 있음 없어도 되는 문장임
        v.setTag(userList.get(i).getUserID());
        */
        //만든뷰를 반환함

        layout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Fragment fm = CourseListFragment.newInstance();
                Bundle bundle = new Bundle();
                bundle.putString("tag", "tag_name");
                ((MainActivity)view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.frame, fm).addToBackStack(null).commit();
            }
        });

        return v;
    }
}
