package com.example.shinple.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.alexvasilkov.android.commons.ui.Views;
import com.alexvasilkov.foldablelayout.FoldableListLayout;
import com.example.shinple.R;
import com.example.shinple.activities.MainActivity;
import com.example.shinple.adapter.PaintingsAdapter;

public class FoldableListFragment extends Fragment {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                ((MainActivity)getActivity()).onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FrameLayout wrapper = new FrameLayout(getActivity()); // for example
        View v  = inflater.inflate(R.layout.activity_foldable_list, wrapper, false);
        ListView listView = v.findViewById(R.id.list_view);
        listView.setAdapter(new PaintingsAdapter((MainActivity)getContext()));

        FoldableListLayout foldableListLayout = v.findViewById( R.id.foldable_list);
        foldableListLayout.setAdapter(new PaintingsAdapter((MainActivity)getContext()));
/*        ListView listView = Views.find((MainActivity)getContext(), R.id.list_view);
        listView.setAdapter(new PaintingsAdapter((MainActivity)getContext()));

        FoldableListLayout foldableListLayout = Views.find((MainActivity)getContext(), R.id.foldable_list);
        foldableListLayout.setAdapter(new PaintingsAdapter((MainActivity)getContext()));*/

        return v;
    }
}
