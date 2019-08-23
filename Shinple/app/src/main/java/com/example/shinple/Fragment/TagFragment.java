package com.example.shinple.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.shinple.R;
import com.example.shinple.Adapter.TagListAdapter;
import com.example.shinple.VO.TagVO;

import java.util.ArrayList;
import java.util.List;

public class TagFragment extends Fragment {

    private ListView listView;
    private TagListAdapter adapter;
    private List<TagVO> tagList;


    public static TagFragment newInstance() {
        return new TagFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tag_list, container, false);
        listView = view.findViewById(R.id.listView);
        tagList = new ArrayList<TagVO>();

        //어댑터 초기화부분 userList와 어댑터를 연결해준다.
        adapter = new TagListAdapter(view.getContext(), tagList);
        listView.setAdapter(adapter);

        try{
            String []tagIdList={"1234","2345","3456"};
            String []tagNameList={"블록체인","빅데이터","AI"};
            int length = tagIdList.length;
            //JSON 배열 길이만큼 반복문을 실행
            for(int tagnum=0; tagnum<length;tagnum++){
                /*TODO: json 데이터 가져와 서 쪼개서 변수 넣기 */
                String tagId = tagIdList[tagnum];
                String tagName = tagNameList[tagnum];

                //값들을 User클래스에 묶어줍니다
                TagVO user = new TagVO(tagId, tagName);
                tagList.add(user);//리스트뷰에 값을 추가해줍니다
            }


        }catch(Exception e){
            e.printStackTrace();
        } //try_catch 끝

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });//  listView.setOnItemClickListener 끝
        return view;
    }
}
