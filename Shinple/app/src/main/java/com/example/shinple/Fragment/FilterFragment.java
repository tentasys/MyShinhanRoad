package com.example.shinple.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ToggleButton;

import com.example.shinple.Adapter.FilterAdapter;
import com.example.shinple.BackgroundTask;
import com.example.shinple.MainActivity;
import com.example.shinple.R;
import com.example.shinple.VO.FilterVO;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class FilterFragment extends Fragment {

    private GridView gridView;
    private FilterAdapter adapter;
    private Button bt;
    private List<FilterVO> filterList;
    private String[] filter;
    private String[] level;
    private ToggleButton tb1;
    private ToggleButton tb2;
    private ToggleButton tb3;
    private ToggleButton tb4;
    private ToggleButton tb5;
    private ToggleButton tb6;
    private ToggleButton tb7;
    private String data;

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
        View view = inflater.inflate(R.layout.fragment_filter, container, false);

        // toggle button
        level = new String[7];

        tb1 = view.findViewById(R.id.tb_lv1);
        tb2 = view.findViewById(R.id.tb_lv2);
        tb3 = view.findViewById(R.id.tb_lv3);
        tb4 = view.findViewById(R.id.tb_lv4);
        tb5 = view.findViewById(R.id.tb_lv5);
        tb6 = view.findViewById(R.id.tb_lv6);
        tb7 = view.findViewById(R.id.tb_lv7);

        tb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                 level[0] = "1";
                }
                else {
                    level[0] = null;
                }
            }
        });

        tb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    level[1] = "2";
                }
                else {
                    level[1] = null;
                }
            }
        });


        tb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    level[2] = "3";
                }
                else {
                    level[2] = null;
                }
            }
        });

        tb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    level[3] = "4";
                }
                else {
                    level[3] = null;
                }
            }
        });


        tb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    level[4] = "5";
                }
                else {
                    level[4] = null;
                }
            }
        });


        tb6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    level[5] = "6";
                }
                else {
                    level[5] = null;
                }
            }
        });

        tb7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    level[6] = "7";
                }
                else {
                    level[6] = null;
                }
            }
        });

        // Inflate the layout for this fragment
        gridView = view.findViewById(R.id.gv_tag);
        filterList = new ArrayList<FilterVO>();
        adapter = new FilterAdapter(view.getContext(), filterList);
        gridView.setAdapter(adapter);


        try{
            String []tagIdList={"빅데이터","블록체인","AI"};
            int length = tagIdList.length;
            //JSON 배열 길이만큼 반복문을 실행
            filter = new String[length];
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

        adapter.setOnCheckListener(new FilterAdapter.OnCheckedChangeListener() {
            @Override
            public void onCheck(View view, int pos, String Filter) {
                filter[pos] = Filter;
            }
        });
        try{
            data = URLEncoder.encode("start", "UTF-8") + "=" + URLEncoder.encode("ok", "UTF-8");
        } catch (Exception e){

        }

        bt = view.findViewById(R.id.bt_filter);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i = 0; i < filter.length; i++) {
                    if(filter[i] != null) {
                        Log.d("Value", filter[i]);
                        try {
                            data += "&" + URLEncoder.encode("filter[]", "UTF-8") + "=" + URLEncoder.encode(filter[i], "UTF-8");
                        } catch (Exception e){
                        }
                    }
                }

                for(int i = 0; i < level.length; i++){
                    if(level[i] != null){
                        Log.d("level",level[i]);
                        try {
                            data += "&" + URLEncoder.encode(String.valueOf(i+1), "UTF-8") + "=" + URLEncoder.encode(level[i], "UTF-8");
                        } catch (Exception e){

                        }
                    }
                }

                Log.d("hihi",data);
                String result = "";
                BackgroundTask backgroundTask = new BackgroundTask("http://192.168.1.187/courseList.php",data);
                try{
                    result = backgroundTask.execute().get();
                } catch (Exception e){
                    e.printStackTrace();
                }
                Log.d("UUUUUU",result);
                ((MainActivity) view.getContext())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame,CourseListFragment.newInstance(result))
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

}
