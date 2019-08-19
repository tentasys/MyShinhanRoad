package com.example.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.constraintlayout.solver.widgets.Helper;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;


public class VideoPlayerFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    VideoView videoView;
    ProgressDialog progressDialog;
    String videourl;

    private String mParam1;

    public VideoPlayerFragment() {
    }

    public static VideoPlayerFragment newInstance(String param1) {
        VideoPlayerFragment fragment = new VideoPlayerFragment();
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

        View view = inflater.inflate(R.layout.fragment_video_player, container, false);
        videoView = view.findViewById(R.id.videoView);

        progressDialog = new ProgressDialog(view.getContext());
        progressDialog.setTitle("streaming");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        MediaController mediaController = new MediaController(view.getContext());
        mediaController.setAnchorView(videoView);
        Uri uri = Uri.parse(videourl);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        progressDialog.dismiss();
        videoView.start();

        return view;
    }
}
