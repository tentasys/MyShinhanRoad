package com.example.shinple.Adapter;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shinple.Fragment.LectureListFragment;
import com.example.shinple.MainActivity;
import com.example.shinple.R;

public class MainSliderAdapter extends PagerAdapter {
//    private int[] images = {R.drawable.new_course_image1,R.drawable.new_course_image2, R.drawable.new_course_image3};
    private LayoutInflater inflater;
    private Context context;
    private String viewpager;
    /* 생성자 */

    public MainSliderAdapter(Context context, String viewpager){
        this.context = context;
        this.viewpager = viewpager;
    }

    @Override
    public int getCount() {
//        return images.length;
        return 2;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(object);
    }

    //각각의 이미지를 instance화 해줌
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        View v = null;

        if(viewpager=="newCourse") {
            int[] images = {R.drawable.new_course_image1,R.drawable.new_course_image2};
            v= inflater.inflate(R.layout.main_slider, container, false);
            ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
            imageView.setImageResource(images[position]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    ((MainActivity) view.getContext())
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame,LectureListFragment.newInstance("상세","상세", "상세"))
                            .addToBackStack(null)
                            .commit();
                }
            });
            container.addView(v);

        }
        else if(viewpager=="hotCourse")
        {
            int[] images2 = {R.drawable.new_course_image2,R.drawable.new_course_image3};
            v= inflater.inflate(R.layout.main_slider, container, false);
            ImageView imageView2 = (ImageView) v.findViewById(R.id.imageView);
            imageView2.setImageResource(images2[position]);
            imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView2.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    ((MainActivity) view.getContext())
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame,LectureListFragment.newInstance("상세","상세", "상세"))
                            .addToBackStack(null)
                            .commit();
                }
            });
            container.addView(v);
        }

        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.invalidate();
    }


}

