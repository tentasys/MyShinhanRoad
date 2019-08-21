package com.example.shinple;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * 하단 CoP 버튼과 연결
 * 앱 구현 예시에 View 위젯은 뭐가 들어가는 거지 ... CoPlist로 연결된다는 것까지는 이해
 * CoPList로 연결까지 구혐
 */
public class CopFragment extends Fragment {

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

        //TODO: DB 쿼리로 CoP의 개수를 받아온 다음에 반복문으로 처리하기

        View cop_view_01 = view.findViewById(R.id.my_cop_01);       //첫번째 cop의 id 받아오기
        View cop_view_02 = view.findViewById(R.id.my_cop_02);       //두번째 cop의 id 받아오기

        //첫번째 Cop를 눌렀을때 해당 CoP 정보 란으로 이동
        cop_view_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* CoP list로 연결 */
                Log.d("move","첫번째 CoP로 이동");
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frame, CopListFragment.newInstance("안녕","하세요"));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        //첫번째 Cop를 눌렀을때 해당 CoP 정보 란으로 이동
        cop_view_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* CoP list로 연결 */
                Log.d("move","첫번째 CoP로 이동");
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frame, CopListFragment.newInstance("안녕","하세요"));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

}
