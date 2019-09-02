package com.example.shinple.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shinple.R;

import java.util.ArrayList;

public class StringAdapter extends RecyclerView.Adapter<StringAdapter.ViewHolder> {

    private ArrayList<String> filterlist;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(View view);
    }

    private StringAdapter.OnItemClickListener mListener = null ;

    public void setOnItemClickListener(StringAdapter.OnItemClickListener listener) {
        this.mListener = listener ;
    }

    public StringAdapter(Context context, ArrayList<String> filterlist) {
        this.context = context;
        this.filterlist = filterlist;
    }

    @Override
    public StringAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // context 와 parent.getContext() 는 같다.
        View view = LayoutInflater.from(context)
                .inflate(R.layout.afterfilter, parent, false);

        return new StringAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StringAdapter.ViewHolder holder, int position) {

        TextView tb = (TextView) holder.itemView.findViewById(R.id.bt_tagg);
        String fi = filterlist.get(position);
        if (fi != null){
            tb.setText(fi);
        }
    }


    @Override
    public int getItemCount() {
        return filterlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(final View itemView) {
            super(itemView);
        }
    }

}
