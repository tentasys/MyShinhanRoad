package com.example.shinple.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.shinple.R;
import com.example.shinple.activities.MainActivity;

public class SettingFragment extends Fragment {

    private SharedPreferences sharedPreferences;
    public SettingFragment() {
        // Required empty public constructor
    }

    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setting, container, false);

        Switch mySwitch = v.findViewById(R.id.switch1);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean check = sharedPreferences.getBoolean("notification", true);

        mySwitch.setChecked(check);

        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true)
                    Toast.makeText(getContext(), "알림이 설정되었습니다.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(), "알림이 해제되었습니다.", Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putBoolean("notification", b);
                edit.apply();
            }
        });

        return v;
    }
}
