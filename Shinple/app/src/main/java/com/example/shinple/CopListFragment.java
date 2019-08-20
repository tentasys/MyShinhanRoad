package com.example.shinple;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class CopListFragment extends Fragment {

    private String mParam1;
    private String mParam2;

    private TextView tv1;
    private TextView tv2;
    private Button bt_sear;
    private EditText et;

    public CopListFragment() {
    }

    public static CopListFragment newInstance(String param1, String param2) {
        CopListFragment fragment = new CopListFragment();
        Bundle args = new Bundle();
        args.putString("PA1", param1);
        args.putString("PA2", param2);
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
            mParam1 = getArguments().getString("PA1");
            mParam2 = getArguments().getString("PA2");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_cop_list, container, false);
        tv1 = view.findViewById(R.id.hi);
        tv2 = view.findViewById(R.id.hello);
        bt_sear = view.findViewById(R.id.bt_sear);
        et = view.findViewById(R.id.editText);
        tv1.setText(mParam1);
        tv2.setText(mParam2);

                tv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fm.beginTransaction();
                        fragmentTransaction.replace(R.id.frame, VideoPlayerFragment.newInstance("http://736e4deb.ngrok.io/video/Pexels_Videos.mp4"));
                        fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frame, VideoPlayerFragment.newInstance("http://736e4deb.ngrok.io/video/sample_video.mp4"));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        bt_sear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strText = et.getText().toString() ;
                if (strText != null) {
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.frame, VideoPlayerFragment.newInstance("http://736e4deb.ngrok.io/video/"+ strText+".mp4"));
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });

        return view;
    }
}
