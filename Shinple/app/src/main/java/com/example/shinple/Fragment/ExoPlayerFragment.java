package com.example.shinple.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shinple.Adapter.LectureListAdapter;
import com.example.shinple.BackgroundTask;
import com.example.shinple.MainActivity;
import com.example.shinple.VO.CourseVO;
import com.example.shinple.VO.LectureVO;
import com.example.shinple.VO.MemberVO;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlaybackControlView;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.example.shinple.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ExoPlayerFragment extends Fragment {
    private static final String ARG_PARAM1 = "videourl";
    private static final String ARG_PARAM2 = "result";
    private static final String ARG_PARAM3 = "video_name";
    private static final String ARG_PARAM4 = "video_info";
    private static final String ARG_PARAM5 = "video_num";
    private static final String ARG_PARAM7 = "member";
    private static final String ARG_PARAM6 = "course";

    private PlayerView exoPlayerView;
    private SimpleExoPlayer player;
    View view;
    private Boolean playWhenReady = true;
    private int currentWindow = 0;
    private Long playbackPosition = 0L;

    private LectureListAdapter adapter;
    private List<LectureVO> lectureList;
    private CourseVO course;
    private MemberVO member;

    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("hh:mm:ss");

    private String mParam1;
    private String mParam2;
    private String videourl;
    private String result;
    private String video_name;
    private String video_info;
    private TextView tv_name;
    private TextView tv_info;
    private String data;
    TextView textView;
    TextView exo_position;
    RecyclerView recyclerView;
    ScrollView scrollView;
    FrameLayout frameLayout;
    public boolean FileValideCheckResult = false;
    private boolean enableFullScreen = true;



    public static ExoPlayerFragment newInstance(String param1,String param2, String param3, String param4,String param5,CourseVO course, MemberVO member) {
        ExoPlayerFragment fragment = new ExoPlayerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putString(ARG_PARAM5, param5);
        args.putSerializable(ARG_PARAM6, course);
        args.putSerializable(ARG_PARAM7, member);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM5);
            videourl = mParam1 + mParam2;
            result = getArguments().getString(ARG_PARAM2);
            video_name = getArguments().getString(ARG_PARAM3);
            video_info = getArguments().getString(ARG_PARAM4);
            course = (CourseVO)getArguments().getSerializable(ARG_PARAM6);
            member = (MemberVO)getArguments().getSerializable(ARG_PARAM7);
        }
        else
        {
//                videourl =  "https://file-examples.com/wp-content/uploads/2017/04/file_example_MP4_480_1_5MG.mp4";
//                  videourl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
                    videourl = "https://dbcf91c1.ngrok.io/video/course/10.mp4";
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_exoplayer, container, false);

        textView = view.findViewById(R.id.tv_video_name);
        tv_info = view.findViewById(R.id.tv_video_info);
        exo_position = view.findViewById(R.id.exo_position);

        exo_position.setText("1:20");
        textView.setText(video_name);
        tv_info.setText(video_info);

        recyclerView = view.findViewById(R.id.rv_videolist);
        exoPlayerView = view.findViewById(R.id.exoPlayerView);
        scrollView = view.findViewById(R.id.fragment_scrollview);
        frameLayout = view.findViewById(R.id.fragment_exoplayer_framelayout);

        lectureList = new ArrayList<LectureVO>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new LectureListAdapter(view.getContext(),lectureList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new LectureListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String lec_order, String lec_title, String lec_text) {
                videourl = mParam1 + lec_order + ".mp4";
                isFileValid();  //파일이 유효한 지1 체크
                if(FileValideCheckResult){
                    MediaSource mediaSource = buildMediaSource(Uri.parse(videourl));
                    //prepare
                    player.prepare(mediaSource, true, false);
                    //start,stop
                    player.setPlayWhenReady(playWhenReady);
                    textView.setText(lec_title);
                    tv_info.setText(lec_text);


                    String result = "";
                    try{
                        data = URLEncoder.encode("courseNum", "UTF-8") + "=" + URLEncoder.encode(course.getCourseNum(), "UTF-8");
                        data += "&" + URLEncoder.encode("userNum", "UTF-8") + "=" + URLEncoder.encode(member.getMem_num(), "UTF-8");
                        data += "&" + URLEncoder.encode("lecNum", "UTF-8") + "=" + URLEncoder.encode(lec_order, "UTF-8");
                        data += "&" + URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(getTime(), "UTF-8");

                    } catch (Exception e){
                    }
                    BackgroundTask backgroundTask = new BackgroundTask("app/recentVideo.php",data);
                    try{
                        result = backgroundTask.execute().get();
                    } catch (Exception e){
                        e.printStackTrace();
                    }


                }else{
                    Toast.makeText(view.getContext(), "파일 에러", Toast.LENGTH_LONG).show();
                } //ifelse 끝
            }//onItemClick 끝
        });//setOnItemClickListener끝


        try{
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;
            String lec_title, lec_order, lec_text, lec_time,recent_time;
            while(count < jsonArray.length()){
                JSONObject object = jsonArray.getJSONObject(count);

                lec_title = object.getString("lec_title");
                lec_order = object.getString("lec_order");
                lec_text = object.getString("lec_text");
                lec_time = object.getString("lec_time");
                recent_time = object.getString("recent_time");
                LectureVO lecture = new LectureVO(lec_title, lec_order, lec_text, lec_time, recent_time);
                lectureList.add(lecture);//리스트뷰에 값을 추가해줍니다
                count++;
            }
            exo_position.setText("05:30");
        }catch(Exception e) {
            e.printStackTrace();
        }
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        initializePlayer();

    }

    @Override
    public void onStop() {
        super.onStop();

        releasePlayer();
    }


    private void initializePlayer() {
        if (player == null) {
            player = ExoPlayerFactory.newSimpleInstance(view.getContext());

            //플레이어 연결
            exoPlayerView.setPlayer(player);
            FrameLayout playerBt = (FrameLayout) exoPlayerView.findViewById(R.id.exo_fullscreen_button);
            ImageView playerImg = (ImageView) exoPlayerView.findViewById(R.id.exo_fullscreen_icon);
            playerBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity)getActivity()).playerLandscapeToggle(enableFullScreen);
                    if (enableFullScreen) {   //fullscreen start
                        textView.setVisibility(View.INVISIBLE);
                        recyclerView.setVisibility(View.INVISIBLE);
                        scrollView.setFillViewport(true);
                        FrameLayout.LayoutParams param = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
                        param.bottomMargin=0;
                        frameLayout.setLayoutParams(param);
                        LinearLayout.LayoutParams layoutsize = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
                        exoPlayerView.setLayoutParams(layoutsize);
                        playerImg.setImageResource(R.drawable.ic_fullscreen_skrink);
                        enableFullScreen = false;

                    } else      //fullscreen stop
                    {
                        LinearLayout.LayoutParams layoutsize = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,240);
                        layoutsize.rightMargin= 0;
                        exoPlayerView.setLayoutParams(layoutsize);
                        textView.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.VISIBLE);
                        scrollView.setFillViewport(false);
                        Resources resources = getContext().getResources();
                        DisplayMetrics metrics = resources.getDisplayMetrics();
                        int px = (int) (240 * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
                        LinearLayout.LayoutParams explayersize = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,px);
                        exoPlayerView.setLayoutParams(explayersize);
                        FrameLayout.LayoutParams param = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
                        px = (int) (55 * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
                        param.bottomMargin= 0;
                        frameLayout.setLayoutParams(param);
                        playerImg.setImageResource(R.drawable.ic_fullscreen_expand);
                        enableFullScreen = true;
                    }
                }
            });
            //컨트롤러 없애기
            exoPlayerView.setUseController(true);
            exoPlayerView.setControllerAutoShow(false);

            //사이즈 조절
            exoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL); //
            /*exoPlayerView.dispatchKeyEvent*/
//            exoPlayerView.
            //음량조절
            player.setVolume(10);
            //프레임 포지션 설정

        }
        MediaSource mediaSource = buildMediaSource(Uri.parse(videourl));
        player.seekTo(360000);
        //prepare
        player.prepare(mediaSource, false, false);
        //start,stop
        player.setPlayWhenReady(playWhenReady);
    }

    private MediaSource buildMediaSource(Uri uri) {

        String userAgent = Util.getUserAgent(view.getContext(), "ExoVideoPlayer");

        if ( uri.getLastPathSegment().contains("mp4")) {

            return new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory(userAgent))
                    .createMediaSource(uri);

        } else if (uri.getLastPathSegment().contains("m3u8")) {

            //com.google.android.exoplayer:exoplayer-hls 확장 라이브러리를 빌드 해야 합니다.
            return new HlsMediaSource.Factory(new DefaultHttpDataSourceFactory(userAgent))
                    .createMediaSource(uri);

        } else {

            return new ExtractorMediaSource.Factory(new DefaultDataSourceFactory(view.getContext(), userAgent))
                    .createMediaSource(uri);
        }

    }

    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();

            exoPlayerView.setPlayer(null);
            player.release();
            player = null;
            /*TODO : 본 시간 저장 변수 exo_position_time*/
            CharSequence exo_position_time = exo_position.getText();
            Log.i("getPosition",(String)exo_position_time);

        }
    }

@Override
public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
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

}
