package com.example.shinple.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alexvasilkov.android.commons.texts.SpannableBuilder;
import com.alexvasilkov.android.commons.ui.Views;
import com.alexvasilkov.foldablelayout.UnfoldableView;
import com.alexvasilkov.foldablelayout.shading.GlanceFoldShading;
import com.example.shinple.R;
import com.example.shinple.activities.MainActivity;
import com.example.shinple.adapter.PaintingsAdapter;
import com.example.shinple.utils.GlideHelper;
import com.example.shinple.vo.Painting;

public class UnfoldableDetailsFragment extends Fragment {

    private View listTouchInterceptor;
    private View detailsLayout;
    private UnfoldableView unfoldableView;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // View v = inflater.inflate(R.layout.activity_unfoldable_details, container , false);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FrameLayout wrapper = new FrameLayout(getActivity()); // for example
        View v = inflater.inflate(R.layout.activity_unfoldable_details, wrapper, true);

        ListView listView = v.findViewById(R.id.list_view);
        listView.setAdapter(new PaintingsAdapter((MainActivity)getContext()));

        listTouchInterceptor = v.findViewById(R.id.touch_interceptor_view);
        listTouchInterceptor.setClickable(false);

        detailsLayout = v.findViewById( R.id.details_layout);
        detailsLayout.setVisibility(View.INVISIBLE);

        unfoldableView = v.findViewById(R.id.unfoldable_view);

        Bitmap glance = BitmapFactory.decodeResource(getResources(), R.drawable.unfold_glance);
        unfoldableView.setFoldShading(new GlanceFoldShading(glance));
        unfoldableView.setOnFoldingListener(new UnfoldableView.SimpleFoldingListener() {
            @Override
            public void onUnfolding(UnfoldableView unfoldableView) {
                listTouchInterceptor.setClickable(true);
                detailsLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onUnfolded(UnfoldableView unfoldableView) {
                listTouchInterceptor.setClickable(false);
            }

            @Override
            public void onFoldingBack(UnfoldableView unfoldableView) {
                listTouchInterceptor.setClickable(true);
            }

            @Override
            public void onFoldedBack(UnfoldableView unfoldableView) {
                listTouchInterceptor.setClickable(false);
                detailsLayout.setVisibility(View.INVISIBLE);
            }
        });
        return v;
    }

    public void onBackPressed() {
        if (unfoldableView != null
                && (unfoldableView.isUnfolded() || unfoldableView.isUnfolding())) {
            unfoldableView.foldBack();
        } else {
            ((MainActivity)getActivity()).onBackPressed();
        }
    }

    public void openDetails(View coverView, Painting painting) {
        final ImageView image = Views.find(detailsLayout, R.id.details_image);
        final TextView title = Views.find(detailsLayout, R.id.details_title);
        final TextView description = Views.find(detailsLayout, R.id.details_text);

        GlideHelper.loadPaintingImage(image, painting);
        title.setText(painting.getTitle());

        SpannableBuilder builder = new SpannableBuilder((MainActivity)getContext());
        builder
                .createStyle().setFont(Typeface.DEFAULT_BOLD).apply()
                .append("year").append(": ")
                .clearStyle()
                .append(painting.getYear()).append("\n")
                .createStyle().setFont(Typeface.DEFAULT_BOLD).apply()
                .append("location").append(": ")
                .clearStyle()
                .append(painting.getLocation());
        description.setText(builder.build());

        unfoldableView.unfold(coverView, detailsLayout);
    }

}
