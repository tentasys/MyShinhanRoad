package com.example.shinple;

import android.content.Intent;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.shinple.VO.TagVO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
                new BackgroundTask().execute();
            }
        });//  listView.setOnItemClickListener 끝
        return view;
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            //List.php은 파싱으로 가져올 웹페이지
            target = "https://ed3ded66.ngrok.io/courseList.php";
        }

        @Override
        protected String doInBackground(Void... voids) {

            try{
                URL url = new URL(target);//URL 객체 생성

                //URL을 이용해서 웹페이지에 연결하는 부분
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

                //바이트단위 입력스트림 생성 소스는 httpURLConnection
                InputStream inputStream = httpURLConnection.getInputStream();

                //웹페이지 출력물을 버퍼로 받음 버퍼로 하면 속도가 더 빨라짐
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;

                //문자열 처리를 더 빠르게 하기 위해 StringBuilder클래스를 사용함
                StringBuilder stringBuilder = new StringBuilder();

                //한줄씩 읽어서 stringBuilder에 저장함
                while((temp = bufferedReader.readLine()) != null){
                    stringBuilder.append(temp + "\n");//stringBuilder에 넣어줌
                }

                //사용했던 것도 다 닫아줌
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();//trim은 앞뒤의 공백을 제거함

            }catch(Exception e){
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

        }
    }
}
