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
    ProgressDialog progressDialog;
    String videourl;

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
         progressDialog = new ProgressDialog(view.getContext());
         surfaceView = view.findViewById(R.id.videoView);

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
