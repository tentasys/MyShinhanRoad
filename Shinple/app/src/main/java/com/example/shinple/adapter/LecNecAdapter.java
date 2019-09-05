package com.example.shinple.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shinple.R;
import com.example.shinple.vo.LectureVO;

import java.util.List;

public class LecNecAdapter  extends RecyclerView.Adapter<LecNecAdapter.ViewHolder> {

    private List<LectureVO> lectureList;
    private Context context;

    public LecNecAdapter(Context context, List<LectureVO> lectureList) {
        this.context = context;
        this.lectureList = lectureList;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, LectureVO lecture);
    }

    private LecNecAdapter.OnItemClickListener mListener = null;

    public void setOnItemClickListener(LecNecAdapter.OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public LecNecAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // context 와 parent.getContext() 는 같다.
        View view = LayoutInflater.from(context).inflate(R.layout.cop_item, parent, false);

        return new LecNecAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LecNecAdapter.ViewHolder holder, int position) {
        TextView lectureNum = (TextView) holder.itemView.findViewById(R.id.tv_copnamee);


        //lectureText.setText(lectureList.get(position).getLec_text());
        lectureNum.setText(lectureList.get(position).getLec_title());
        //lectureTag.setText(lectureList.get(position).getlectureTag());

    }

    @Override
    public int getItemCount() {
        return lectureList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            mListener.onItemClick(view, lectureList.get(pos));
                        }
                    }

                }
            });

        }
    }
}