package com.example.shinple.Fragment;

import android.app.ProgressDialog;
import android.media.MediaDataSource;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.shinple.MainActivity;
import com.example.shinple.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class MediaPlayerFragment extends Fragment implements SurfaceHolder.Callback{
    private static final String ARG_PARAM1 = "param1";
    VideoView videoView;
    ProgressDialog progressDialog;
    String videourl;
    boolean FileValideCheckResult=false;

    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    MediaPlayer mediaPlayer;
    View view;

    private String mParam1;

    public MediaPlayerFragment() {
    }

    public static MediaPlayerFragment newInstance(String param1) {
        MediaPlayerFragment fragment = new MediaPlayerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            videourl = mParam1;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_mediaplayer, container, false);
//        videoView = view.findViewById(R.id.videoView);
//
          progressDialog = new ProgressDialog(view.getContext());
//        progressDialog.setTitle("streaming");
//        progressDialog.setMessage("Loading...");
//        progressDialog.setCancelable(false);
//        progressDialog.show();
//        MediaController mediaController = new MediaController(view.getContext());
//        mediaController.setAnchorView(videoView);
//        Uri uri = Uri.parse(videourl);
//        videoView.setMediaController(mediaController);
//        videoView.setVideoURI(uri);
//        videoView.requestFocus();
//        progressDialog.dismiss();
//        videoView.start();

        surfaceView = view.findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(view.getContext());
        mediaController.setAnchorView(videoView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        return view;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        //임시 데이터 경로
        videourl ="http://192.168.1.187/video/do.mp4";
        isFileValid(); // url valid 확인

        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        } else {
            mediaPlayer.reset();
        }

        try {
            /* 파일 유효하지 않으면 되돌아감 */
            if ( FileValideCheckResult) {
                mediaPlayer.setDataSource(videourl);
                //mediaPlayer.setVolume(0, 0); //볼륨 제거
                mediaPlayer.setDisplay(surfaceHolder); // 화면 호출
                mediaPlayer.prepare(); // 비디오 load 준비
                //mediaPlayer.setOnCompletionListener(completionListener); // 비디오 재생 완료 리스너
                mediaPlayer.start();
            }else
            {
                progressDialog.setMessage("경로에 파일이 없습니다. TODO: 돌아가는 거 구현하기...");
                progressDialog.show();
            }


        } catch (Exception e) {
            Log.e("MyTag","surface view error : " + e.getMessage());
        }
    }

    /* 파일이 유효한 지 체크해서 전역변수 FileValideCheckResult에 저장하는 함수 */
    public  void isFileValid() {

        Thread th = new Thread() {
            @Override
            public void run() {
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
