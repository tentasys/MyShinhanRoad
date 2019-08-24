package com.example.shinple.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.shinple.MainActivity;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.example.shinple.R;


public class ExoPlayerFragment extends Fragment {
    static public String ARG_PARAM1;
    private PlayerView exoPlayerView;
    private SimpleExoPlayer player;
    View view;
    private Boolean playWhenReady = true;
    private int currentWindow = 0;
    private Long playbackPosition = 0L;
    String mParam1;
    String videourl;
    TextView textView;
    RecyclerView recyclerView;
    ScrollView scrollView;
    public static ExoPlayerFragment newInstance(String param1) {
        ExoPlayerFragment fragment = new ExoPlayerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            videourl = mParam1;
        }
        else
        {
            videourl =  "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";;
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_exoplayer, container, false);
        textView = view.findViewById(R.id.textView);
        recyclerView = view.findViewById(R.id.rv_videolist);
        exoPlayerView = view.findViewById(R.id.exoPlayerView);
        scrollView = view.findViewById(R.id.fragment_scrollview);
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

        //prepare
        player.prepare(mediaSource, true, false);

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

        }
    }

/*    public void showControls(){
        runOnUiThread(() -> {
            int rotation = (((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay()).getRotation();
            if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) { //Landscape

            } else {

            }
        });*/
//    public void showControls(){
//            ((MainActivity) view.getContext()).getWindow().getDecorView().setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//
//    }
@Override
public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    ((MainActivity)getActivity()).playerLandscapeToggle();

    if (((MainActivity) getActivity()).getWindowMode()) {    //세로일 때

        LinearLayout.LayoutParams layoutsize = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,240);
        layoutsize.rightMargin= 0;
        exoPlayerView.setLayoutParams(layoutsize);
        textView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        Resources resources = getContext().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int px = (int) (240 * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
        LinearLayout.LayoutParams explayersize = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,px);
        exoPlayerView.setLayoutParams(explayersize);

    } else      //가로일때
    {
        textView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        scrollView.setFillViewport(true);

        Resources resources = getContext().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();

        LinearLayout.LayoutParams layoutsize = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        exoPlayerView.setLayoutParams(layoutsize);


    }
}
}
