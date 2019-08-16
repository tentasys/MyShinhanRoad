package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;


public class CopFragment extends Fragment {

    public CopFragment() {
    }

    public static CopFragment newInstance(String param1, String param2) {
        CopFragment fragment = new CopFragment();
        Bundle args = new Bundle();
        args.putString("PA1", param1);
        args.putString("PA2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cop, container, false);
        ImageButton imageButton =  view.findViewById(R.id.IB_1);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("IG","IB Clicked");
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frame, CopListFragment.newInstance("안녕","하세요"));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        Log.d("IG","IB Clicked2");
        return view;
    }

}
