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

    private String cop_name;
    private String cop_rank;

    public CopListFragment() {
    }

    public static CopListFragment newInstance(String param1, String param2) {
        CopListFragment fragment = new CopListFragment();
        Bundle args = new Bundle();
        args.putString("cop_name", param1);
        args.putString("cop_rank", param2);
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_cop_list, container, false);

        TextView copName = (TextView) view.findViewById(R.id.cop_name);
        TextView copRank = (TextView) view.findViewById(R.id.cop_rank);
        TextView copText = (TextView) view.findViewById(R.id.cop_description);

        copName.setText(cop_name);
        copRank.setText(cop_rank);
        copText.setText("    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");

        return view;
    }
}
