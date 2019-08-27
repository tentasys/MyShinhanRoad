package com.example.shinple.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shinple.Adapter.CopAdapter;
import com.example.shinple.Adapter.LectureListAdapter;
import com.example.shinple.MainActivity;
import com.example.shinple.R;

import com.example.shinple.VO.CopVO;
import com.example.shinple.VO.LectureVO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CopFragment extends Fragment {

    private static final String ARG_PARAM1 = "cop";
    private CopAdapter my_cop_adapter;
    private CopAdapter total_cop_adapter;
    private List<CopVO> my_cop_list;
    private List<CopVO> total_cop_list;
    private RecyclerView my_rv;
    private RecyclerView total_rv;
    private String result;

    /* 기본 생성자 */
    public CopFragment() {
    }

    public static CopFragment newInstance(String param1) {
        CopFragment fragment = new CopFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            result = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cop, container, false);  //현재 전체 화면 뷰 받아옴
        ////////////////////////////////////
        //내 CoP 리스트
        //리스트 생성
        my_cop_list = new ArrayList<CopVO>();

        /*String my_cop_name[] = {"블록체인 모임", "클라우드 모임", "핀테크 모임", "핀테크 모임2"};
        String my_cop_rank[] = {"1", "3", "17", "22"};

        //JSON 배열 길이만큼 반복문을 실행
        for (int i = 0; i < my_cop_name.length; i++) {
            String each_name = my_cop_name[i];
            String each_rank = my_cop_rank[i];

            CopVO cop = new CopVO(each_name, each_rank);
            my_cop_list.add(cop);//리스트뷰에 값을 추가해줍니다
        }*/

        //레이아웃, 아답터 설정
        my_rv = view.findViewById(R.id.my_cop_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        my_rv.setLayoutManager(layoutManager);
        my_cop_adapter = new CopAdapter(view.getContext(), my_cop_list);
        my_rv.setAdapter(my_cop_adapter);

        //Cop를 눌렀을때 해당 CoP 정보 란으로 이동
        my_cop_adapter.setOnItemClickListener(new CopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, String copName, String copRank, String copIntro) {

                ((MainActivity) v.getContext())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, CopListFragment.newInstance(copName, copRank, copIntro))
                        .addToBackStack(null)
                        .commit();
            }
        });

        try{
            //intent로 값을 가져옵니다 이때 JSONObject타입으로 가져옵니다
            JSONObject jsonObject = new JSONObject(result);


            //List.php 웹페이지에서 response라는 변수명으로 JSON 배열을 만들었음..
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;

            String copName, copRank, copIntro;

            //JSON 배열 길이만큼 반복문을 실행
            while(count < jsonArray.length()){
                //count는 배열의 인덱스를 의미
                JSONObject object = jsonArray.getJSONObject(count);

                copName = object.getString("cop_name");//여기서 ID가 대문자임을 유의
                copRank = object.getString("cop_rank");
                copIntro = object.getString("cop_intro");

                //값들을 User클래스에 묶어줍니다
                CopVO cop = new CopVO(copName, copRank, copIntro);
                my_cop_list.add(cop);//리스트뷰에 값을 추가해줍니다
                count++;
            }

        }catch(Exception e) {
            e.printStackTrace();
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////
        //전체 Cop 리스트
        /*total_cop_list = new ArrayList<CopVO>();

        String total_cop_name[] = {"블록체인 모임", "클라우드 모임", "핀테크 모임", "빅데이터 모임 "};
        String total_cop_rank[] = {"1", "3", "17", "22"};
        //JSON 배열 길이만큼 반복문을 실행
        for (int i = 0; i < total_cop_name.length; i++) {
            String each_name = total_cop_name[i];
            String each_rank = total_cop_rank[i];

            CopVO cop = new CopVO(each_name, each_rank);
            total_cop_list.add(cop);//리스트뷰에 값을 추가해줍니다
        }

        //레이아웃, 아답터 설정
        total_rv = view.findViewById(R.id.total_cop_list);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        total_rv.setLayoutManager(layoutManager2);
        total_cop_adapter = new CopAdapter(view.getContext(), total_cop_list);
        total_rv.setAdapter(total_cop_adapter);

        //Cop를 눌렀을때 해당 CoP 정보 란으로 이동
        total_cop_adapter.setOnItemClickListener(new CopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, String copName, String copRank) {

                ((MainActivity) v.getContext())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, CopListFragment.newInstance(copName, copRank))
                        .addToBackStack("cop_info")
                        .commit();
            }
        });
        */
        return view;
    }


}
