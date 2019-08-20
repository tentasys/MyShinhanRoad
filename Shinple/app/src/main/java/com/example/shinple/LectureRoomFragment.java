package com.example.shinple;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class LectureRoomFragment extends Fragment{

    /* View 변수 선언 */
    ImageView my_info;
    TextView video1;
    /* 생성자  */
    public LectureRoomFragment() {
        // Required empty public constructor
    }

    /* Fragment 간의 이동을 위한 메소드 */
    public static LectureRoomFragment newInstance() {
        return new LectureRoomFragment();
    }

    /*fragment 생성 시 시작될 내용*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lecture_room, container, false);
        my_info =  v.findViewById(R.id.my_info);
        my_info.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick( View v ) {
                /* MainFragment로 이동 */
                ((MainActivity)getActivity()).replaceFragment(MainFragment.newInstance());

            }
        });

        video1 = v.findViewById(R.id.video1);
        video1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* 어디로 갈건 지 아직 모름  */
                ((MainActivity)getActivity()).replaceFragment(MainFragment.newInstance());

            }
        });
        return v;
    }

}
