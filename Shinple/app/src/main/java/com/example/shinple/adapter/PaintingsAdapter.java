package com.example.shinple.adapter;


import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.alexvasilkov.android.commons.adapters.ItemsAdapter;
import com.alexvasilkov.android.commons.ui.ContextHelper;
import com.alexvasilkov.android.commons.ui.Views;
import com.example.shinple.BackgroundTask;
import com.example.shinple.R;
import com.example.shinple.activities.MainActivity;
import com.example.shinple.fragment.FoldableListFragment;
import com.example.shinple.fragment.UnfoldableDetailsFragment;
import com.example.shinple.utils.GlideHelper;
import com.example.shinple.vo.Painting;

import java.net.URI;
import java.net.URL;
import java.util.Arrays;

public class PaintingsAdapter extends ItemsAdapter<Painting, PaintingsAdapter.ViewHolder>
        implements View.OnClickListener {
    Context context;
    BackgroundTask backgroundTask;
    public PaintingsAdapter(Context context) {
        setItemsList(Arrays.asList(Painting.getAllPaintings(context.getResources())));
        this.context = context;
    }

    @Override
    protected ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        final ViewHolder holder = new ViewHolder(parent);
        holder.image.setOnClickListener(this);
        return holder;
    }

    @Override
    protected void onBindHolder(ViewHolder holder, int position) {
        final Painting item = getItem(position);
        holder.image.setTag(R.id.list_item_image, item);
        GlideHelper.loadPaintingImage(holder.image, item);
        holder.title.setText(item.getTitle());
/*
        holder.image.setTag(R.id.list_item_image, item);
        GlideHelper.loadPaintingImage(holder.image, item);
        holder.title.setText(item.getTitle());*/
    }

    @Override
    public void onClick(View view) {
        final Painting item = (Painting) view.getTag(R.id.list_item_image);
        final Fragment fragment = ((MainActivity)context).getCurrentFragment();

        if (fragment instanceof UnfoldableDetailsFragment) {
            ((UnfoldableDetailsFragment) fragment).openDetails(view, item);
        } else if (fragment instanceof FoldableListFragment) {
//            Toast.makeText((MainActivity) activity, item.getTitle(), Toast.LENGTH_SHORT).show();
        }
    }

    static class ViewHolder extends ItemsAdapter.ViewHolder {
        final ImageView image;
        final TextView title;

        ViewHolder(ViewGroup parent) {
            super(Views.inflate(parent, R.layout.list_item));
            image = Views.find(itemView, R.id.list_item_image);
            title = Views.find(itemView, R.id.list_item_title);
        }
    }

}
