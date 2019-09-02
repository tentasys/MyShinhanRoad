package com.example.shinple.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shinple.R;
import com.example.shinple.adapter.NotiAdapter;
import com.example.shinple.vo.LectureVO;
import com.example.shinple.vo.NotiVO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends Fragment {

    private static final String ARG_PARAM1 = "noti";
    private List<NotiVO> noti;
    private String notiS;
    private RecyclerView recyclerView;
    private NotiAdapter adapter;



    public NotificationFragment() {
        // Required empty public constructor
    }

    public static NotificationFragment newInstance(String notiS) {
        NotificationFragment fragment = new NotificationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, notiS);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            notiS = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        noti = new ArrayList<NotiVO>();
        recyclerView = view.findViewById(R.id.rv_noti);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NotiAdapter(view.getContext(),noti);
        recyclerView.setAdapter(adapter);

        try{
            //intent로 값을 가져옵니다 이때 JSONObject타입으로 가져옵니다
            JSONObject jsonObject = new JSONObject(notiS);


            //List.php 웹페이지에서 response라는 변수명으로 JSON 배열을 만들었음..
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;

            String noti_title, noti_context, noti_date;

            //JSON 배열 길이만큼 반복문을 실행
            while(count < jsonArray.length()){
                //count는 배열의 인덱스를 의미
                JSONObject object = jsonArray.getJSONObject(count);

                noti_title = object.getString("noti_name");//여기서 ID가 대문자임을 유의
                noti_context = object.getString("noti_text");
                noti_date = object.getString("noti_date");

                //값들을 User클래스에 묶어줍니다
                NotiVO noti1 = new NotiVO(noti_title, noti_context, noti_date);
                noti.add(noti1);//리스트뷰에 값을 추가해줍니다
                count++;
            }

        }catch(Exception e) {
            e.printStackTrace();
        }

        return view;
    }
}
