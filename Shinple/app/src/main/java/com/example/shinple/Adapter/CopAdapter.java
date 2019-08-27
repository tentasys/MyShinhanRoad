package com.example.shinple.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shinple.R;
import com.example.shinple.VO.CopVO;

import java.util.List;

//CopFragment에서 나의 CoP 리스트를 보여주는 Adapter
public class CopAdapter extends RecyclerView.Adapter<CopAdapter.ViewHolder> {

    private List<CopVO> my_cop_list;
    private Context context;
    private OnItemClickListener mListener = null ;

    //constructor
    public CopAdapter(Context context, List<CopVO> list) {
        this.context = context;
        this.my_cop_list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.cop_item, parent, false);

        return new CopAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView copName = (TextView) holder.itemView.findViewById(R.id.tv_copname);
        TextView copRank = (TextView) holder.itemView.findViewById(R.id.tv_coprank);
        ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.iv_cop);
        String ss = "wampstack";
        Glide.with(context)
                .load("https://67db0c58.ngrok.io/img/" + ss + ".png")
                .into(imageView);

        copName.setText(my_cop_list.get(position).getCopName());
        copRank.setText(my_cop_list.get(position).getCopRank());
    }

    //리스트 아이템 개수 반환
    @Override
    public int getItemCount() {
        return my_cop_list.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, String copName, String copRank, String copIntro);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }

    //viewholder 클래스 정의
    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        if(mListener != null){
                            mListener.onItemClick(view,my_cop_list.get(pos).getCopName(),my_cop_list.get(pos).getCopRank(), my_cop_list.get(pos).getCopInfo());
                        }
                    }

                }
            });
        }
    }
}
