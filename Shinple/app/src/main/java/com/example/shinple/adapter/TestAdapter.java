package com.example.shinple.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.shinple.R;
import com.example.shinple.vo.QuizVO;

import java.util.List;
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {


    private List<QuizVO> testList;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(View view, int position, int answer);
    }

    private OnItemClickListener mListener = null ;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }

    public TestAdapter(Context context, List<QuizVO> testList) {
        this.context = context;
        this.testList = testList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // context 와 parent.getContext() 는 같다.
        View view = LayoutInflater.from(context)
                .inflate(R.layout.quiz, parent, false);

        return new ViewHolder(view);
    }

    ///////////////////////////////////////
    @Override
    public void onBindViewHolder(TestAdapter.ViewHolder holder, int position) {
        TextView question = (TextView) holder.itemView.findViewById(R.id.tv_quiz);
        TextView quesNum = (TextView) holder.itemView.findViewById(R.id.tv_quiz_num);
        RadioButton rb1 = (RadioButton)holder.itemView.findViewById(R.id.rb_1);
        RadioButton rb2 = (RadioButton)holder.itemView.findViewById(R.id.rb_2);
        RadioButton rb3 = (RadioButton)holder.itemView.findViewById(R.id.rb_3);
        RadioButton rb4 = (RadioButton)holder.itemView.findViewById(R.id.rb_4);

        quesNum.setText(testList.get(position).getQuizNum());
        question.setText(testList.get(position).getquestion());
        rb1.setText(testList.get(position).getQuiz1());
        rb2.setText(testList.get(position).getQuiz2());
        rb3.setText(testList.get(position).getQuiz3());
        rb4.setText(testList.get(position).getQuiz4());
    }


    @Override
    public int getItemCount() {
        return testList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RadioGroup rg = (RadioGroup)itemView.findViewById(R.id.rg_quiz);
        public ViewHolder(final View itemView) {
            super(itemView);

            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    int pos = getAdapterPosition();
                    int answer = 0;
                    if (pos != RecyclerView.NO_POSITION){
                        if(mListener != null){
                            switch (i){
                                case R.id.rb_1:
                                    answer = 1;
                                    break;
                                case R.id.rb_2:
                                    answer = 2;
                                    break;
                                case R.id.rb_3:
                                    answer = 3;
                                    break;
                                case R.id.rb_4:
                                    answer = 4;
                                    break;
                            }
                            mListener.onItemClick(itemView,pos,answer);
                        }
                    }
                }
            });
        }
    }
}