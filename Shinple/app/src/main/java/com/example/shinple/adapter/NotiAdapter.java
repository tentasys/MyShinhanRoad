package com.example.shinple.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shinple.R;
import com.example.shinple.vo.CourseVO;
import com.example.shinple.vo.NotiVO;

import java.util.List;

public class NotiAdapter extends RecyclerView.Adapter<NotiAdapter.ViewHolder> {

    private List<NotiVO> notiList;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(View view, NotiVO noti);
    }

    private NotiAdapter.OnItemClickListener mListener = null ;

    public void setOnItemClickListener(NotiAdapter.OnItemClickListener listener) {
        this.mListener = listener ;
    }

    public NotiAdapter(Context context, List<NotiVO> notiList) {
        this.context = context;
        this.notiList = notiList;
    }

    @Override
    public NotiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // context 와 parent.getContext() 는 같다.
        View view = LayoutInflater.from(context)
                .inflate(R.layout.noti, parent, false);
        return new NotiAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotiAdapter.ViewHolder holder, int position) {
        TextView tv_title = (TextView) holder.itemView.findViewById(R.id.tv_no_title);
        TextView tv_context = (TextView) holder.itemView.findViewById(R.id.tv_no_context);
        TextView date = (TextView) holder.itemView.findViewById(R.id.tv_no_date);
//        TextView tagName = (TextView) holder.itemView.findViewById(R.id.tv_lec_courseInfo);
        tv_title.setText(notiList.get(position).getTitle());
        tv_context.setText(notiList.get(position).getnoContext());
        date.setText(notiList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return notiList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(final View itemView) {
            super(itemView);
        }
    }


}
