package com.example.shinple.Fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shinple.R;

public class MediaPlayerFragment extends Fragment implements SurfaceHolder.Callback{
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    MediaPlayer mediaPlayer;
    String path = "http://192.168.1.187/video/dog.mp4";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static MediaPlayerFragment newInstance(String param1) {
        MediaPlayerFragment fragment = new MediaPlayerFragment();
        Bundle args = new Bundle();
        args.putString("path", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_video_player, container, false);
        //path = savedInstanceState.getString("path");
        surfaceView = v.findViewById(R.id.videoView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        return v;
    }
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        /* media_player 생성 */
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        } else {
            mediaPlayer.reset();
        }

        /* 비디오 준비 */
        try {
            mediaPlayer.setDataSource(path);

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
        if(mediaPlayer != null) {
            mediaPlayer.release();
        }
    }


}
