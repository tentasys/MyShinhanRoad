package com.example.shinple.utils;

import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.shinple.BackgroundTask;
import com.example.shinple.vo.Painting;

public class GlideHelper {

    private GlideHelper() {}

    public static void loadPaintingImage(ImageView image, Painting painting) {
/*
        Glide.with(context)
                .load(server +  num + "/logo.png")
                .into(imageView);*/
        Glide.with(image.getContext().getApplicationContext())
                .load(BackgroundTask.server+"app/cop/"+painting.getImageId()+".jpg")
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(image);
    }
}
