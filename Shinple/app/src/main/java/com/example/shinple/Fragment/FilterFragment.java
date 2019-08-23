package com.example.shinple.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import com.example.shinple.Adapter.FilterAdapter;
import com.example.shinple.MainActivity;
import com.example.shinple.R;
import com.example.shinple.VO.FilterVO;

import java.util.ArrayList;
import java.util.List;


public class FilterFragment extends Fragment {

    private GridView gridView;
    private FilterAdapter adapter;
    private Button bt;
    private List<FilterVO> filterList;

    public FilterFragment() {
        // Required empty public constructor
    }

    public static FilterFragment newInstance() {
        FilterFragment fragment = new FilterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

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
        View view = inflater.inflate(R.layout.fragment_filter, container, false);
        gridView = view.findViewById(R.id.gv_tag);
        filterList = new ArrayList<FilterVO>();
        adapter = new FilterAdapter(view.getContext(), filterList);
        gridView.setAdapter(adapter);


        try{
            String []tagIdList={"1234","2345","3456","ffff","sadasf"};
            int length = tagIdList.length;
            //JSON 배열 길이만큼 반복문을 실행
            for(int tagnum=0; tagnum<length;tagnum++){
                /*TODO: json 데이터 가져와 서 쪼개서 변수 넣기 */
                String tagId = tagIdList[tagnum];

                //값들을 User클래스에 묶어줍니다
                FilterVO filter = new FilterVO(tagId);
                filterList.add(filter);//리스트뷰에 값을 추가해줍니다
            }



        }catch(Exception e){
            e.printStackTrace();
        } //try_catch 끝

        gridView.setAdapter(adapter);

        bt = view.findViewById(R.id.bt_filter);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fm = CourseListFragment.newInstance();
                Bundle bundle = new Bundle();
                bundle.putString("tag", "tag_name");
                ((MainActivity)view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.frame, fm).addToBackStack(null).commit();
            }
        });

        return view;
    }

}
