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
        return v;
    }

}
