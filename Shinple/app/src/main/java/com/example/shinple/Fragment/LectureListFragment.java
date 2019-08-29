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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shinple.BackgroundTask;
import com.example.shinple.Adapter.LectureListAdapter;
import com.example.shinple.MainActivity;
import com.example.shinple.R;
import com.example.shinple.VO.CourseVO;
import com.example.shinple.VO.LectureVO;
import com.google.android.exoplayer2.ExoPlayerFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class LectureListFragment extends Fragment {

    private static final String ARG_PARAM1 = "result";
    private static final String ARG_PARAM2 = "course";

    // TODO: Rename and change types of parameters
    private String result = "";
    private String data;
    private CourseVO course;

    private RecyclerView recyclerView;
    private LectureListAdapter adapter;
    private List<LectureVO> lectureList;
    private  TextView tv_courseName;
    private  TextView tv_courseInfo;
    private  TextView tv_tch;
    private TextView tv_level;
    private Button bt_test;
    private LinearLayout bt_continue;
    public String videourl;
    int like_number;
    TextView like_num;
    public boolean FileValideCheckResult = false;
    ProgressDialog progressDialog;
    ImageView like_button;
    public int button01pos;
    public LectureListFragment() {
        // Required empty public constructor
    }

    public static LectureListFragment newInstance(String param1, CourseVO course) {
        LectureListFragment fragment = new LectureListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putSerializable(ARG_PARAM2, course);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            result = getArguments().getString(ARG_PARAM1);
            course = (CourseVO)getArguments().getSerializable(ARG_PARAM2);
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
        tv_tch = view.findViewById(R.id.tv_lec_tchName);
        tv_level = view.findViewById(R.id.tv_cl2_lv4);
        bt_test = view.findViewById(R.id.bt_test);
        bt_continue = view.findViewById(R.id.bt_continue);
        like_button = view.findViewById(R.id.like_button);
        like_num = view.findViewById(R.id.like_num);
        tv_courseInfo.setText(course.getCousrseText());
        tv_courseName.setText(course.getcourseName());
        tv_tch.setText(course.getTchName());
        tv_level.setText(course.getcourseLevel());

        recyclerView = view.findViewById(R.id.rv_lecture);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new LectureListAdapter(view.getContext(),lectureList);
        recyclerView.setAdapter(adapter);


        /*  이어보기 버튼 추후 개발 예정
        bt_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isFileValid();  //파일이 유효한 지 체크
                if(FileValideCheckResult){
                    try {   // exo해보고
                        ((MainActivity) view.getContext())
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame, ExoPlayerFragment.newInstance(videourl,result,lec_title,lec_text))
                                .commit();
                    }catch (Exception e){  //exo안되면 media로 가자!
                        ((MainActivity) view.getContext())
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame, MediaPlayerFragment.newInstance(videourl,result,lec_title,lec_text))
                                .commit();
                    }
                }else{
                    progressDialog.setMessage("파일 경로를 확인해주세요.");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                } //ifelse 끝
            }//onItemClick 끝
        });//setOnItemClickListener끝
        */

        bt_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = "";
                try{
                    data = URLEncoder.encode("courseNUM", "UTF-8") + "=" + URLEncoder.encode(course.getCourseNum(), "UTF-8");
                } catch (Exception e){

                }
                BackgroundTask backgroundTask = new BackgroundTask("app/test.php",data);
                try{
                    result = backgroundTask.execute().get();
                } catch (Exception e){
                    e.printStackTrace();
                }
                Log.d("test",result);
                ((MainActivity) view.getContext())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame,TestFragment.newInstance(result))
                        .commit();
            }
        });

        adapter.setOnItemClickListener(new LectureListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String lec_order, String lec_title, String lec_text) {
                videourl = "https://5004bd02.ngrok.io/video/"+ course.getCourseNum() + "/" + lec_order + ".mp4";
                String url =  "https://5004bd02.ngrok.io/video/"+ course.getCourseNum() + "/";
                String video = lec_order + ".mp4";
                Log.d("order",result);
                isFileValid();  //파일이 유효한 지1 체크
                if(FileValideCheckResult){
                    try {   // exo해보고
                        ((MainActivity) view.getContext())
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame, ExoPlayerFragment.newInstance(url,result,lec_title,lec_text,video))
                                .commit();
                    }catch (Exception e){  //exo안되면 media로 가자!
                        ((MainActivity) view.getContext())
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame, MediaPlayerFragment.newInstance(url,result,lec_title,lec_text))
                                .commit();
                    }
                }else{
                    Toast.makeText(view.getContext(), "파일 에러", Toast.LENGTH_LONG).show();

                } //ifelse 끝
            }//onItemClick 끝
        });//setOnItemClickListener끝
        /* DB에서 받아오기 */
        button01pos = 0;
        like_number = 0;
        like_num.setText(0);
        like_button.setOnClickListener(new View.OnClickListener(){
            @Override

               public void onClick(View view) {
                    if (button01pos == 0) {
                        like_button.setImageResource(R.drawable.like);
                        button01pos = 1;
                        like_number++;
                        like_num.setText(like_number);
                    } else if (button01pos == 1) {
                        like_button.setImageResource(R.drawable.unlike);
                        button01pos = 0;
                        like_number--;
                        like_num.setText(like_number);
                    }
                }
        });


        try{
            //intent로 값을 가져옵니다 이때 JSONObject타입으로 가져옵니다
            JSONObject jsonObject = new JSONObject(result);
            
            //List.php 웹페이지에서 response라는 변수명으로 JSON 배열을 만들었음..
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;

            String lec_title, lec_order, lec_text, lec_time;

            //JSON 배열 길이만큼 반복문을 실행
            while(count < jsonArray.length()){
                //count는 배열의 인덱스를 의미
                JSONObject object = jsonArray.getJSONObject(count);

                lec_title = object.getString("lec_title");//여기서 ID가 대문자임을 유의
                lec_order = object.getString("lec_order");
                lec_text = object.getString("lec_text");
                lec_time = object.getString("lec_time");

                //값들을 User클래스에 묶어줍니다
                LectureVO lecture = new LectureVO(lec_title, lec_order, lec_text, lec_time);
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
