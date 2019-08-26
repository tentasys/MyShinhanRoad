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
import com.example.shinple.MainActivity;
import com.example.shinple.R;

import com.example.shinple.VO.CopVO;

import java.util.ArrayList;
import java.util.List;

public class CopFragment extends Fragment {

    private CopAdapter my_cop_adapter;
    private CopAdapter total_cop_adapter;
    private List<CopVO> my_cop_list;
    private List<CopVO> total_cop_list;
    private RecyclerView my_rv;
    private RecyclerView total_rv;

    /* 기본 생성자 */
    public CopFragment() {
    }

    /**
     * list를 위한 newInstance 메소드
     * */
    public static CopFragment newInstance(String param1, String param2) {
        CopFragment fragment = new CopFragment();
        Bundle args = new Bundle();
        args.putString("PA1", param1);
        args.putString("PA2", param2);
        fragment.setArguments(args);
        return fragment;
    }
    /**
     * Fragment 내에서 다른 fragment로 전환하기 위한 메소드
     * @return  new CopListFragment()
     */
    public static CopListFragment newInstance() {
        return new CopListFragment();
    }

    /**
     *  Fragment 생성 시 호출 되는 메소드
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    /**
     * fragment view 구성 메소드
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cop, container, false);  //현재 전체 화면 뷰 받아옴
        ////////////////////////////////////
        //내 CoP 리스트
        //리스트 생성
        my_cop_list = new ArrayList<CopVO>();

        String my_cop_name[] = {"블록체인 모임", "클라우드 모임", "핀테크 모임", "핀테크 모임2"};
        String my_cop_rank[] = {"1", "3", "17", "22"};

        //JSON 배열 길이만큼 반복문을 실행
        for (int i = 0; i < my_cop_name.length; i++) {
            String each_name = my_cop_name[i];
            String each_rank = my_cop_rank[i];

            CopVO cop = new CopVO(each_name, each_rank);
            my_cop_list.add(cop);//리스트뷰에 값을 추가해줍니다
        }

        //레이아웃, 아답터 설정
        my_rv = view.findViewById(R.id.my_cop_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        my_rv.setLayoutManager(layoutManager);
        my_cop_adapter = new CopAdapter(view.getContext(), my_cop_list);
        my_rv.setAdapter(my_cop_adapter);

        //Cop를 눌렀을때 해당 CoP 정보 란으로 이동
        my_cop_adapter.setOnItemClickListener(new CopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, String copName, String copRank) {

                ((MainActivity) v.getContext())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, CopListFragment.newInstance(copName, copRank))
                        .addToBackStack(null)
                        .commit();
            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////////
        //전체 Cop 리스트
        total_cop_list = new ArrayList<CopVO>();

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
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }


}
