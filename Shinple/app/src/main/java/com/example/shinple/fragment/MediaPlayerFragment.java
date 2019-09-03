package com.example.shinple.fragment;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.example.shinple.adapter.LectureListAdapter;
import com.example.shinple.R;
import com.example.shinple.vo.LectureVO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MediaPlayerFragment extends Fragment implements SurfaceHolder.Callback{
    private static final String ARG_PARAM1 = "videourl";
    private static final String ARG_PARAM2 = "result";
    private static final String ARG_PARAM3 = "video_name";
    private static final String ARG_PARAM4 = "video_info";

    ProgressDialog progressDialog;
    String videourl;
    String result;
    String video_name;
    String video_info;

    private RecyclerView recyclerView;
    private LectureListAdapter adapter;
    private List<LectureVO> lectureList;


    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    MediaPlayer mediaPlayer;
    View view;

    private String mParam1;

    public MediaPlayerFragment() {
    }

    public static MediaPlayerFragment newInstance(String param1,String param2, String param3,String param4) {
        MediaPlayerFragment fragment = new MediaPlayerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            result = getArguments().getString(ARG_PARAM2);
            video_name = getArguments().getString(ARG_PARAM3);
            video_info = getArguments().getString(ARG_PARAM4);
            videourl = mParam1;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_mediaplayer, container, false);
         progressDialog = new ProgressDialog(view.getContext());
         surfaceView = view.findViewById(R.id.videoView);

        lectureList = new ArrayList<LectureVO>();

        recyclerView = view.findViewById(R.id.rv_videolist);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new LectureListAdapter(view.getContext(),lectureList);
        recyclerView.setAdapter(adapter);
        try{
            //intent로 값을 가져옵니다 이때 JSONObject타입으로 가져옵니다
            JSONObject jsonObject = new JSONObject(result);


            //List.php 웹페이지에서 response라는 변수명으로 JSON 배열을 만들었음..
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;

            String lec_title, lec_order, lec_text, lec_time, recent_time,lec_num, learn_time;

            //JSON 배열 길이만큼 반복문을 실행
            while(count < jsonArray.length()){
                //count는 배열의 인덱스를 의미
                JSONObject object = jsonArray.getJSONObject(count);

                lec_title = object.getString("lec_title");//여기서 ID가 대문자임을 유의
                lec_order = object.getString("lec_order");
                lec_text = object.getString("lec_text");
                lec_time = object.getString("lec_time");
                recent_time = object.getString("recent_time");
                lec_num = object.getString("lec_num");
                learn_time = object.getString("learn_time");

                //값들을 User클래스에 묶어줍니다
                LectureVO lecture = new LectureVO(lec_title, lec_order, lec_text, lec_time, recent_time, lec_num, learn_time);
                lectureList.add(lecture);//리스트뷰에 값을 추가해줍니다
                count++;
            }

        }catch(Exception e) {
            e.printStackTrace();
        }

        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        return view;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        } else {
            mediaPlayer.reset();
        }

        try {
                mediaPlayer.setDataSource(videourl);
                //mediaPlayer.setVolume(0, 0); //볼륨 제거
                mediaPlayer.setDisplay(surfaceHolder); // 화면 호출
                mediaPlayer.prepare(); // 비디오 load 준비
                //mediaPlayer.setOnCompletionListener(completionListener); // 비디오 재생 완료 리스너
                mediaPlayer.start();

        } catch (Exception e) {
            Log.e("MyTag","surface view error : " + e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}
