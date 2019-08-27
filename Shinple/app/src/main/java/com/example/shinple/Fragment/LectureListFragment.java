package com.example.shinple.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shinple.BackgroundTask;
import com.example.shinple.Adapter.LectureListAdapter;
import com.example.shinple.MainActivity;
import com.example.shinple.R;
import com.example.shinple.VO.LectureVO;
import com.google.android.exoplayer2.ExoPlayerFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class LectureListFragment extends Fragment {

    private static final String ARG_PARAM1 = "courseName";
    private static final String ARG_PARAM2 = "courseInfo";
    private static final String ARG_PARAM3 = "lectureList";

    // TODO: Rename and change types of parameters
    private String courseName;
    private String courseInfo;
    private String result = "";

    private RecyclerView recyclerView;
    private LectureListAdapter adapter;
    private List<LectureVO> lectureList;
    private  TextView tv_courseName;
    private  TextView tv_courseInfo;
    private TextView tv_level;
    private Button bt_test;
    private LinearLayout bt_continue;
    public String videourl="http://192.168.1.187/video/dog.mp4";
    public boolean FileValideCheckResult = false;
    ProgressDialog progressDialog;

    public LectureListFragment() {
        // Required empty public constructor
    }

    public static LectureListFragment newInstance(String param1, String param2, String param3) {
        LectureListFragment fragment = new LectureListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            courseName = getArguments().getString(ARG_PARAM1);
            courseInfo = getArguments().getString(ARG_PARAM2);
            result = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lecture_list, container, false);
        progressDialog = new ProgressDialog(view.getContext());

        lectureList = new ArrayList<LectureVO>();

        tv_courseName = view.findViewById(R.id.tv_lec_courseN);
        tv_courseInfo = view.findViewById(R.id.tv_lec_courseInfo);
        tv_level = view.findViewById(R.id.tv_cl2_lv4);
        bt_test = view.findViewById(R.id.bt_test);
        bt_continue = view.findViewById(R.id.bt_continue);

        tv_courseName.setText(courseName);
        tv_level.setText(courseInfo);

        recyclerView = view.findViewById(R.id.rv_lecture);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new LectureListAdapter(view.getContext(),lectureList);
        recyclerView.setAdapter(adapter);

        bt_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackgroundTask backgroundTask = new BackgroundTask("lectureList.php");
                backgroundTask.execute();
                isFileValid();  //파일이 유효한 지 체크
                if(FileValideCheckResult){
                    try {   // exo해보고
                        ((MainActivity) view.getContext())
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame, ExoPlayerFragment.newInstance(videourl))
                                .commit();
                    }catch (Exception e){  //exo안되면 media로 가자!
                        ((MainActivity) view.getContext())
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame, MediaPlayerFragment.newInstance(videourl))
                                .commit();
                    }
                }else{
                    progressDialog.setMessage("파일 경로를 확인해주세요.");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                } //ifelse 끝
            }//onItemClick 끝
        });//setOnItemClickListener끝

        bt_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) view.getContext())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame,TestFragment.newInstance())
                        .commit();
            }
        });

        adapter.setOnItemClickListener(new LectureListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String lectureName, String lectureInfo) {
                BackgroundTask backgroundTask = new BackgroundTask("ectureList.php");
                backgroundTask.execute();
                isFileValid();  //파일이 유효한 지 체크
                if(FileValideCheckResult){
                    try {   // exo해보고
                        ((MainActivity) view.getContext())
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame, ExoPlayerFragment.newInstance(videourl))
                                .commit();
                    }catch (Exception e){  //exo안되면 media로 가자!
                        ((MainActivity) view.getContext())
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame, MediaPlayerFragment.newInstance(videourl))
                                .commit();
                    }
                }else{
                    progressDialog.setMessage("파일 경로를 확인해주세요.");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                } //ifelse 끝
            }//onItemClick 끝
        });//setOnItemClickListener끝


        try{
            //intent로 값을 가져옵니다 이때 JSONObject타입으로 가져옵니다
            JSONObject jsonObject = new JSONObject(result);


            //List.php 웹페이지에서 response라는 변수명으로 JSON 배열을 만들었음..
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;

            String lectureNum, lectureName, lectureTag;

            //JSON 배열 길이만큼 반복문을 실행
            while(count < jsonArray.length()){
                //count는 배열의 인덱스를 의미
                JSONObject object = jsonArray.getJSONObject(count);

                lectureNum = object.getString("lectureNum");//여기서 ID가 대문자임을 유의
                lectureName = object.getString("lectureName");
                lectureTag = object.getString("lectureTag");

                //값들을 User클래스에 묶어줍니다
                LectureVO lecture = new LectureVO(lectureNum, lectureName, lectureTag);
                lectureList.add(lecture);//리스트뷰에 값을 추가해줍니다
                count++;
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    /* 파일이 유효한 지 체크해서 전역변수 FileValideCheckResult에 저장하는 함수 */
    public  void isFileValid() {
        Thread th = new Thread() {
            @Override
            public void run() {
                if(videourl == null){
                    FileValideCheckResult = false;
                    this.stop();
                }
                try {
                    URL url = new URL(videourl);
                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                    if (http.getResponseCode() == 200) {
                        Log.i("PlayUrlCheck", "valid");
                        FileValideCheckResult = true;
                    } else {
                        Log.i("PlayUrlCheck", "invalid");
                        FileValideCheckResult =false;
                    }
                } catch ( Exception e) {
                    Log.i("PlayUrlCheck", "error");
                    FileValideCheckResult = false;
                }
            }
        };
        th.start();
        try {
            th.join();
        }catch (Exception e){
            Log.e("Error", "ThreadError");
        }
    }
}
