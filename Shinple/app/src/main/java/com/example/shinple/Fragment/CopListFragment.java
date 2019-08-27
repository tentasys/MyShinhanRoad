package com.example.shinple.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gifbitmap.GifBitmapWrapper;
import com.example.shinple.R;


public class CopListFragment extends Fragment{

    private String cop_name;
    private String cop_rank;
    private String cop_intro;

    public CopListFragment() {
    }

    public static CopListFragment newInstance(String param1, String param2, String param3) {
        CopListFragment fragment = new CopListFragment();
        Bundle args = new Bundle();
        args.putString("cop_name", param1);
        args.putString("cop_rank", param2);
        args.putString("cop_intro",param3);
        fragment.setArguments(args);
        return fragment;
    }

    public static CopListFragment newInstance() {
        return new CopListFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cop_name = getArguments().getString("cop_name");
            cop_rank = getArguments().getString("cop_rank");
            cop_intro = getArguments().getString("cop_intro");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_cop_list, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_coptitle);
        TextView copName = (TextView) view.findViewById(R.id.cop_name);
        TextView copRank = (TextView) view.findViewById(R.id.cop_rank);
        TextView copText = (TextView) view.findViewById(R.id.cop_description);

        Glide.with(getContext())
                .load("https://67db0c58.ngrok.io/img/sample.jpg")
                .centerCrop()
                .into(imageView);

        copName.setText(cop_name);
        copRank.setText(cop_rank);
        copText.setText(cop_intro);

        return view;
    }

}
