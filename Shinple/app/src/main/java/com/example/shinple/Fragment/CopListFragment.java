package com.example.shinple.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shinple.R;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_cop_list, container, false);

        return view;
    }
}
