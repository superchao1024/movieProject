package com.example.a92828.movieproject.center;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a92828.movieproject.R;
import com.example.a92828.movieproject.bean.PiaoFangBean;

import java.util.List;

public class MyRecyclerViewHoleAdapter extends RecyclerView.Adapter<MyRecyclerViewHoleAdapter.MyViewHolder> {

    private final Context context;
    private final List<PiaoFangBean.DataBean> data;
    private final LayoutInflater mLayoutInflater;

    public  MyRecyclerViewHoleAdapter(Context mContext, List<PiaoFangBean.DataBean> data) {
        this.context = mContext;
        this.data = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.piaofang_item, viewGroup, false);
        //View view = View.inflate(context,R.layout.piaofang_item,false);
        return new MyViewHolder(view);
    }


    class MyViewHolder extends RecyclerView.ViewHolder{


        private final TextView piaofang_name;
        private final TextView piaofang_boxOffice;
        private final TextView piaofang_boxPer;
        private final TextView piaofang_movieday;
        private final TextView piaofang_sumBoxOffice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //电影名
            piaofang_name = itemView.findViewById(R.id.piaofang_name);
            //实时票房
            piaofang_boxOffice = itemView.findViewById(R.id.piaofang_boxOffice);
            //票房占比
            piaofang_boxPer = itemView.findViewById(R.id.piaofang_boxPer);
            //票房天数
            piaofang_movieday = itemView.findViewById(R.id.piaofang_movieday);
            //累计票房
            piaofang_sumBoxOffice = itemView.findViewById(R.id.piaofang_sumBoxOffice);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.piaofang_name.setText(data.get(position).getMovieName());
        myViewHolder.piaofang_boxOffice.setText(data.get(position).getBoxOffice());
        myViewHolder.piaofang_boxPer.setText(data.get(position).getBoxPer());
        myViewHolder.piaofang_movieday.setText(data.get(position).getMovieDay());
        myViewHolder.piaofang_sumBoxOffice.setText(data.get(position).getSumBoxOffice());

    }




}

