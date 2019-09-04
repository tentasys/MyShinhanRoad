package com.example.shinple.fragment;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shinple.BackgroundTask;
import com.example.shinple.activities.MainActivity;
import com.example.shinple.adapter.CopAdapter;
import com.example.shinple.adapter.CourseAAdapter;
import com.example.shinple.adapter.LecNecAdapter;
import com.example.shinple.adapter.LectureListAdapter;
import com.example.shinple.adapter.LectureRoomSpinnerAdapter;
import com.example.shinple.R;
import com.example.shinple.vo.CopVO;
import com.example.shinple.vo.CourseVO;
import com.example.shinple.vo.LectureVO;
import com.example.shinple.vo.MemberVO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class LectureRoomFragment extends Fragment{

    final static int SPINNER_MENU_NUM = 4;
    private CourseAAdapter[] adapter_course;
    private List<CourseVO>[] courseList;
    private RecyclerView rv_course;

    private LecNecAdapter essential_course_adapter;
    private List<LectureVO> essential_course_list;
    private RecyclerView my_rv;
    private MemberVO member;
    private String result1;
    private String result2;
    private int rv_course_height[];
    private int px;
    private String data;
    private String videourl;
    private String data1;


    public boolean FileValideCheckResult = false;

    long mNow;
    Date mDate;
    String pattern ="yyyy-MM-dd hh:mm:ss";
    SimpleDateFormat mFormat = new SimpleDateFormat(pattern);


    public LectureRoomFragment() {
        // Required empty public constructor
    }

    /* Fragment 간의 이동을 위한 메소드 */
    public static LectureRoomFragment newInstance(String result1,String result2, MemberVO member) {
        LectureRoomFragment fragment = new LectureRoomFragment();
        Bundle args = new Bundle();
        args.putString("result1", result1);
        args.putString("result2", result2);
        args.putSerializable("result3",member);
        fragment.setArguments(args);
        return fragment;
    }

    /*fragment 생성 시 시작될 내용*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            result1 = getArguments().getString("result1");
            result2 = getArguments().getString("result2");
            member = (MemberVO)getArguments().getSerializable("result3");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lecture_room, container, false);
        essential_course_list = new ArrayList<LectureVO>();

        Resources resources = getContext().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        px = (int) (120 * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
        /* 0 : 전체강좌, 1: 수강 중 2:테스트 완료 3:수강 완료*/
        courseList = new ArrayList[SPINNER_MENU_NUM];
        rv_course_height = new int[SPINNER_MENU_NUM];
        adapter_course = new CourseAAdapter[SPINNER_MENU_NUM];
        for(int listIndex = 0; listIndex<courseList.length;listIndex++){
            courseList[listIndex] = new ArrayList<CourseVO>();
        }
        TextView tv_point = (TextView)v.findViewById(R.id.tv_person_point);
        TextView tv_com = (TextView)v.findViewById(R.id.tv_company);
        TextView tv_name = (TextView)v.findViewById(R.id.tv_name);
        tv_point.setText(member.getMem_point());
        tv_name.setText(member.getMem_name());

        switch (member.getCompany_num()){
            case "1":
                tv_com.setText("신한DS");
                break;
            case "2":
                tv_com.setText("신한은행");
                break;
            case "3":
                tv_com.setText("신한카드");
                break;
            case "4":
                tv_com.setText("신한금융투자");
                break;
            case "5":
                tv_com.setText("신한생명");
                break;
       }

        rv_course = v.findViewById(R.id.rv_courseList);
        my_rv = v.findViewById(R.id.rv_mycop);

        LinearLayoutManager layoutManager = new LinearLayoutManager(v.getContext());
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(v.getContext());

        rv_course.setLayoutManager(layoutManager);

        my_rv.setLayoutManager(layoutManager2);
        jsonParsing();  //jsonparsing해서 list변수들에 값을 넣어줌.
        essential_course_adapter = new LecNecAdapter(v.getContext(),essential_course_list);

        for(int listIndex = 0; listIndex<courseList.length;listIndex++){
            adapter_course[listIndex] = new CourseAAdapter(v.getContext(),courseList[listIndex]);

        }

        rv_course.setAdapter(adapter_course[0]);
        my_rv.setAdapter(essential_course_adapter);
        essential_course_adapter.setOnItemClickListener(new LecNecAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, LectureVO lecture) {

                videourl = BackgroundTask.server+"video/course/" + lecture.getLec_num() + ".mp4";
                String url =  BackgroundTask.server+"video/course/";
                String video = lecture.getLec_num() + ".mp4";

                String result2 = "";

                recent_video(lecture.getLec_num());

                /*try{
                    data1 = URLEncoder.encode("courseNum", "UTF-8") + "=" + URLEncoder.encode(lecture.getCourse_num(), "UTF-8");
                    data1 += "&" + URLEncoder.encode("userNum", "UTF-8") + "=" + URLEncoder.encode(member.getMem_num(), "UTF-8");
                    data1 += "&" + URLEncoder.encode("state", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
                    data1 += "&" + URLEncoder.encode("lec_num", "UTF-8") + "=" + URLEncoder.encode(lecture.getLec_num(), "UTF-8");
                } catch (Exception e){
                }
                BackgroundTask backgroundTask2 = new BackgroundTask("app/lectureList.php",data1);
                try{
                    result2 = backgroundTask2.execute().get();
                } catch (Exception e){
                    e.printStackTrace();
                }*/

                isFileValid();  //파일이 유효한 지1 체크
                if(FileValideCheckResult){
                    try {   // exo해보고
                        ((MainActivity) view.getContext())
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame, ExoPlayerFragment.newInstance(url,result2,lecture,video,member))
                                .commit();
                        Thread.sleep(1000);
                        //customProgressDialog.dismiss();

                    }catch (Exception e){  //exo안되면 media로 가자!
                        ((MainActivity) view.getContext())
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame, MediaPlayerFragment.newInstance(url,result2,lecture.getLec_title(),lecture.getLec_text()))
                                .commit();
                        //customProgressDialog.dismiss();
                    }
                }else{
                    //customProgressDialog.dismiss();
                    Toast.makeText(view.getContext(), "파일 에러", Toast.LENGTH_LONG).show();


                } //ifelse 끝
            }
        });

        adapter_course[0].setOnItemClickListener(new CourseAAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, CourseVO course) {
                CL(view,course);
            }
        });

        Log.e("myTag",Integer.toString(rv_course.getHeight()));


        //UI생성
        Spinner spinner = (Spinner)v.findViewById(R.id.spinner);

        //Adapter
        LectureRoomSpinnerAdapter adapterSpinner = new LectureRoomSpinnerAdapter(v);

        //Adapter 적용
        spinner.setAdapter(adapterSpinner);
        /* spinner의 선택에 따라 내 강좌, 수강중, 테스트 완료,수강 완료 별로 필터링해서 보여줌 */
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent , View view, int pos ,long id) {

                if(parent.getItemAtPosition(pos).toString().equals("수강 중")){
                    rv_course.setAdapter(adapter_course[1]);
                    rv_course.setMinimumHeight(rv_course_height[1]);
                    adapter_course[1].setOnItemClickListener(new CourseAAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, CourseVO course) {
                            CL(view,course);
                        }
                    });
                }else if(parent.getItemAtPosition(pos).toString().equals("테스트 완료")){
                    rv_course.setAdapter(adapter_course[2]);
                    rv_course.setMinimumHeight(rv_course_height[2]);
                    adapter_course[2].setOnItemClickListener(new CourseAAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, CourseVO course) {
                            CL(view,course);
                        }
                    });
                }else if(parent.getItemAtPosition(pos).toString().equals("수강 완료")){
                    rv_course.setAdapter(adapter_course[3]);
                    rv_course.setMinimumHeight(rv_course_height[3]);
                    adapter_course[3].setOnItemClickListener(new CourseAAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, CourseVO course) {
                            CL(view,course);
                        }
                    });
                }else{
                    rv_course.setAdapter(adapter_course[0]);
                    rv_course.setMinimumHeight(rv_course_height[0]);
                }
            }// onItemSelected end
            public void onNothingSelected(AdapterView<?> arg0) {

            }  //onNothingSelected end
        });
        return v;
    }  //onCreateView end

    /**
     * DB에서 받아온 데이터 Json형식으로 쪼개고 courselist, essential_course_list에 넣는 함수
     */
    public void jsonParsing(){
        try{
            JSONObject jsonObject = new JSONObject(result1);
            //List.php 웹페이지에서 response라는 변수명으로 JSON 배열을 만 들었음..
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;
            String lec_title, lec_order, lec_text, lec_time, recent_time, lec_num, course_num, learn_time;
            while(count < jsonArray.length()){
                JSONObject object = jsonArray.getJSONObject(count);

                lec_title = object.getString("lec_title");//여기서 ID가 대문자임을 유의
                lec_order = object.getString("lec_order");
                lec_text = object.getString("lec_text");
                lec_time = object.getString("lec_time");
                recent_time = object.getString("recent_time");
                lec_num = object.getString("lec_num");
                course_num = object.getString("course_num");
                learn_time = object.getString("learn_time");
                LectureVO lecture = new LectureVO(lec_title, lec_order, lec_text, lec_time, recent_time, lec_num, course_num, learn_time);
                essential_course_list.add(lecture);//리스트뷰에 값을 추가해줍니다
                count++;
            }

            jsonObject = new JSONObject(result2);
            jsonArray = jsonObject.getJSONArray("response");
            count = 0;
            String courseName, courseLevel, tchName, courseText, courseNum, learnState, like, mem_like;
        /*
        ====== learnState ========
        0 : 수강 전
        1 : 수강 중
        2 : 테스트 완료(수료처리 대기)
        3 :수료완료(웹에서 처리)
        */
        /*
        ====== courseList ========
        0 : 전체강좌
        1 : 수강 중
        2 : 테스트 완료(수료처리 대기)
        3 : 수료완료(웹에서 처리)
        */
            while(count < jsonArray.length()) {
                JSONObject object = jsonArray.getJSONObject(count);
                Log.e("myTag",object.toString());
                courseName = object.getString("course_title");//여기서 ID가 대문자임을 유의
                courseLevel = object.getString("course_level");
                tchName = object.getString("course_tch");
                courseText = object.getString("course_text");
                courseNum = object.getString("course_num");
                learnState = object.getString("learn_state");
                like = object.getString("Like");
                mem_like = object.getString("mem_like");
                if(learnState.equals("1")){
                    courseList[1].add(new CourseVO(courseName, courseLevel, tchName, courseText, courseNum, learnState,like,mem_like) );
                }else if(learnState.equals("2")){
                    Log.e("myTag",learnState);
                    courseList[2].add(new CourseVO(courseName, courseLevel, tchName, courseText, courseNum, learnState,like,mem_like) );
                }else if(learnState.equals("3")){
                    courseList[3].add(new CourseVO(courseName, courseLevel, tchName, courseText, courseNum, learnState,like,mem_like));
                }else{
                    Log.e("CourseListError","목록 에러");
                }
                count++;
            }
            courseList[0].addAll(courseList[1]); courseList[0].addAll(courseList[2]); courseList[0].addAll(courseList[3]);
            for(int i=0;i< SPINNER_MENU_NUM ; i++){
                rv_course_height[i] = px * courseList[i].size();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void CL(View view, CourseVO course){
                //new CourseListFragment.BackgroundTask().execute();
                String temp = "";
                data = "";
                if (course.getLearnState() == null){
                    temp = "0";
                }
                else{
                    temp = course.getLearnState();
                }
                try{
                    data = URLEncoder.encode("courseNum", "UTF-8") + "=" + URLEncoder.encode(course.getCourseNum(), "UTF-8");
                    data += "&" +  URLEncoder.encode("userNum", "UTF-8") + "=" + URLEncoder.encode(member.getMem_num(), "UTF-8");
                    if(course.getLearnState().equals("0")){
                        data += "&" +  URLEncoder.encode("state", "UTF-8") + "=" + URLEncoder.encode(temp, "UTF-8");
                    }
                    else {
                        data += "&" + URLEncoder.encode("state", "UTF-8") + "=" + URLEncoder.encode(course.getLearnState(), "UTF-8");
                    }
                    Log.d("cccc",data);
                }
                catch (Exception e){
                }
                String result = "";
                BackgroundTask backgroundTask = new BackgroundTask("app/lectureList.php",data);
                try{
                    result = backgroundTask.execute().get();
                } catch (Exception e){
                    e.printStackTrace();
                }
                Log.d("lecture",result);
                ((MainActivity) view.getContext())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame,LectureListFragment.newInstance(result,course,member))
                        .addToBackStack("lecture_list")
                        .commit();
    }

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

    private void recent_video(String lec_num){
        String result = "";
        try{
            data = URLEncoder.encode("courseNum", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8");
            data += "&" + URLEncoder.encode("userNum", "UTF-8") + "=" + URLEncoder.encode(member.getMem_num(), "UTF-8");
            data += "&" + URLEncoder.encode("lec_num", "UTF-8") + "=" + URLEncoder.encode(lec_num, "UTF-8");
            data += "&" + URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(getTime(), "UTF-8");
        } catch (Exception e){
        }
        BackgroundTask backgroundTask = new BackgroundTask("app/recentVideo.php",data);
        try{
            result = backgroundTask.execute().get();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
