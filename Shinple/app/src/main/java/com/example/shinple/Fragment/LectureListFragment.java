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
import com.example.shinple.VO.MemberVO;
import com.google.android.exoplayer2.ExoPlayerFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class LectureListFragment extends Fragment {

    private static final String ARG_PARAM1 = "result";
    private static final String ARG_PARAM2 = "course";
    private static final String ARG_PARAM3 = "member";
    private static final String ARG_PARAM4 = "mem_like";

    // TODO: Rename and change types of parameters
    private String result = "";
    private String data;
    private CourseVO course;
    private MemberVO member;
    private int recent_num;

    private String[][] S;

    long mNow;
    Date mDate;
    String pattern ="yyyy-MM-dd hh:mm:ss";
    SimpleDateFormat mFormat = new SimpleDateFormat(pattern);
    SimpleDateFormat recent = new SimpleDateFormat(pattern);

    private RecyclerView recyclerView;
    private LectureListAdapter adapter;
    private List<LectureVO> lectureList;
    private  TextView tv_courseName;
    private  TextView tv_courseInfo;
    private  TextView tv_tch;
    private  TextView last;
    private TextView tv_level;
    private Button bt_test;
    private LinearLayout bt_continue;
    public String videourl;
    private String mem_like;
    private int like_number;
    private String lec_like;
    public boolean FileValideCheckResult = false;
    ProgressDialog progressDialog;
    ImageView like_button;
    private int button01pos;
    public LectureListFragment() {
        // Required empty public constructor
    }

    public static LectureListFragment newInstance(String param1, CourseVO course, MemberVO member) {
        LectureListFragment fragment = new LectureListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putSerializable(ARG_PARAM2, course);
        args.putSerializable(ARG_PARAM3, member);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            result = getArguments().getString(ARG_PARAM1);
            course = (CourseVO)getArguments().getSerializable(ARG_PARAM2);
            member = (MemberVO)getArguments().getSerializable(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lecture_list, container, false);
        progressDialog = new ProgressDialog(view.getContext());

        lectureList = new ArrayList<LectureVO>();
        last = view.findViewById(R.id.tv_lastVideo);
        tv_courseName = view.findViewById(R.id.tv_lec_courseN);
        tv_courseInfo = view.findViewById(R.id.tv_lec_courseInfo);
        tv_tch = view.findViewById(R.id.tv_lec_tchName);
        tv_level = view.findViewById(R.id.tv_cl2_lv4);
        bt_test = view.findViewById(R.id.bt_test);
        if(course.getLearnState().equals("2")){
            bt_test.setVisibility(view.GONE);
        }
        bt_continue = view.findViewById(R.id.bt_continue);
        like_button = view.findViewById(R.id.like_button);
        tv_courseInfo.setText(course.getCousrseText());
        tv_courseName.setText(course.getcourseName());
        tv_tch.setText(course.getTchName());
        tv_level.setText(course.getcourseLevel());

        if(course.getMem_like().equals("0")){
            like_button.setImageResource(R.drawable.unlike);
            button01pos = 0;
        }
        else if (course.getMem_like().equals("1")){
            like_button.setImageResource(R.drawable.like);
            button01pos = 1;
        }

        like_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (button01pos == 0) {
                    like_button.setImageResource(R.drawable.like);
                    button01pos = 1;
                    String result = "";
                    try{
                        data = URLEncoder.encode("courseNum", "UTF-8") + "=" + URLEncoder.encode(course.getCourseNum(), "UTF-8");
                        data += "&" + URLEncoder.encode("userNum", "UTF-8") + "=" + URLEncoder.encode(member.getMem_num(), "UTF-8");
                        data += "&" + URLEncoder.encode("Like", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
                    } catch (Exception e){
                    }
                    BackgroundTask backgroundTask = new BackgroundTask("app/userLike.php",data);
                    try{
                        result = backgroundTask.execute().get();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                } else if (button01pos == 1) {
                    like_button.setImageResource(R.drawable.unlike);
                    button01pos = 0;
                    String result = "";
                    try{
                        data = URLEncoder.encode("courseNUM", "UTF-8") + "=" + URLEncoder.encode(course.getCourseNum(), "UTF-8");
                        data += "&" + URLEncoder.encode("userNum", "UTF-8") + "=" + URLEncoder.encode(member.getMem_num(), "UTF-8");
                        data += "&" + URLEncoder.encode("Like", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8");
                    } catch (Exception e){

                    }
                    BackgroundTask backgroundTask2 = new BackgroundTask("app/userLike.php",data);
                    try{
                        result = backgroundTask2.execute().get();
                        Log.d("hihihi",result);

                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });









        recyclerView = view.findViewById(R.id.rv_lecture);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new LectureListAdapter(view.getContext(),lectureList);
        recyclerView.setAdapter(adapter);


        bt_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = "";
                try{
                    data = URLEncoder.encode("courseNUM", "UTF-8") + "=" + URLEncoder.encode(course.getCourseNum(), "UTF-8");
                    data += "&" + URLEncoder.encode("userNum", "UTF-8") + "=" + URLEncoder.encode(member.getMem_num(), "UTF-8");
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
                        .replace(R.id.frame,TestFragment.newInstance(result,member,course.getCourseNum(),course.getcourseLevel()))
                        .addToBackStack("null")
                        .commit();
            }
        });

        adapter.setOnItemClickListener(new LectureListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String lec_order, String lec_title, String lec_text) {
                videourl = BackgroundTask.server+"video/"+ course.getCourseNum() + "/" + lec_order + ".mp4";
                String url =  BackgroundTask.server+"video/"+ course.getCourseNum() + "/";
                String video = lec_order + ".mp4";

                String result = "";
                String result2 = "";
                try{
                    data = URLEncoder.encode("courseNum", "UTF-8") + "=" + URLEncoder.encode(course.getCourseNum(), "UTF-8");
                    data += "&" + URLEncoder.encode("userNum", "UTF-8") + "=" + URLEncoder.encode(member.getMem_num(), "UTF-8");
                    data += "&" + URLEncoder.encode("lecNum", "UTF-8") + "=" + URLEncoder.encode(lec_order, "UTF-8");
                    data += "&" + URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(getTime(), "UTF-8");
                    Log.d("time",data);
                } catch (Exception e){
                }
                BackgroundTask backgroundTask = new BackgroundTask("app/test.php",data);
                try{
                    result = backgroundTask.execute().get();
                } catch (Exception e){
                    e.printStackTrace();
                }
                Log.d("test",result);


                BackgroundTask backgroundTask2 = new BackgroundTask("app/lectureList.php",data);
                try{
                    result2 = backgroundTask2.execute().get();
                } catch (Exception e){
                    e.printStackTrace();
                }

                Log.d("order",result2);
                isFileValid();  //파일이 유효한 지1 체크
                if(FileValideCheckResult){
                    try {   // exo해보고
                        ((MainActivity) view.getContext())
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame, ExoPlayerFragment.newInstance(url,result2,lec_title,lec_text,video))
                                .commit();
                    }catch (Exception e){  //exo안되면 media로 가자!
                        ((MainActivity) view.getContext())
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame, MediaPlayerFragment.newInstance(url,result2,lec_title,lec_text))
                                .commit();
                    }
                }else{
                    Toast.makeText(view.getContext(), "파일 에러", Toast.LENGTH_LONG).show();

                } //ifelse 끝
            }//onItemClick 끝
        });//setOnItemClickListener끝
        /* DB에서 받아오기 */

        try{
            //intent로 값을 가져옵니다 이때 JSONObject타입으로 가져옵니다
            JSONObject jsonObject = new JSONObject(result);
            
            //List.php 웹페이지에서 response라는 변수명으로 JSON 배열을 만들었음..
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;

            String lec_title, lec_order, lec_text, lec_time, recent_time, lec_like;
            String[][] S = new String[jsonArray.length()][2];
            //JSON 배열 길이만큼 반복문을 실행
            while(count < jsonArray.length()){
                //count는 배열의 인덱스를 의미
                JSONObject object = jsonArray.getJSONObject(count);

                lec_title = object.getString("lec_title");//여기서 ID가 대문자임을 유의
                lec_order = object.getString("lec_order");
                lec_text = object.getString("lec_text");
                lec_time = object.getString("lec_time");
                recent_time = object.getString("recent_time");
                S[count][0] = lec_order;
                S[count][1] = recent_time;

                //값들을 User클래스에 묶어줍니다
                LectureVO lecture = new LectureVO(lec_title, lec_order, lec_text, lec_time, recent_time);
                lectureList.add(lecture);//리스트뷰에 값을 추가해줍니다
                count++;
            }
            Date date1 = recent.parse(S[0][1]);
            recent_num = 0;
            for (int i = 1; i < jsonArray.length(); i++){
                Date date2 = recent.parse(S[i][1]);
                if(date1.before(date2)){
                    date1 = date2;
                    recent_num = i;
                }
            }
            Log.d("time",recent.format(date1));
            Log.d("sdmlsmfl",lectureList.get(recent_num).getLec_title());
        }catch(Exception e) {
            e.printStackTrace();
        }
// 이어보기 버튼 추후 개발 예정

        //last.setText(lectureList.get(recent_num).getLec_title());

        bt_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videourl = BackgroundTask.server+"video/"+ course.getCourseNum() + "/" + lectureList.get(recent_num).getLec_order() + ".mp4";
                String url =  BackgroundTask.server+"video/"+ course.getCourseNum() + "/";
                String video = lectureList.get(recent_num).getLec_order() + ".mp4";
                String lec_title = lectureList.get(recent_num).getLec_title();
                String lec_text = lectureList.get(recent_num).getLec_text();
                isFileValid();  //파일이 유효한 지 체크
                if(FileValideCheckResult){
                    try {   // exo해보고
                        ((MainActivity) view.getContext())
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame, ExoPlayerFragment.newInstance(videourl,result,lec_title,lec_text,video))
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
                } //ifelse 끝*/
            }//onItemClick 끝
        });//setOnItemClickListener끝

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

    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

}
