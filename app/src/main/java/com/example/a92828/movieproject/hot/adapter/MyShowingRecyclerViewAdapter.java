package com.example.a92828.movieproject.hot.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a92828.movieproject.R;
import com.example.a92828.movieproject.bean.ShowingBean;

import java.util.List;

public class MyShowingRecyclerViewAdapter extends RecyclerView.Adapter<MyShowingRecyclerViewAdapter.MyViewHolder> {

    private final Context context;
    private final List<ShowingBean.SubjectsBean> subjects;

    public MyShowingRecyclerViewAdapter(Context context, List<ShowingBean.SubjectsBean> subjects) {
        this.context = context;
        this.subjects = subjects;
    }


    @Override
    public int getItemCount() {
        return subjects.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context,R.layout.showing_item,null);
        return new MyViewHolder(view);
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv_showing;
        private final TextView movie_showing_name;
        private final TextView movie_showing_average;
        private final TextView movie_showing_directors;
        private final TextView movie_showing_casts;
        private final TextView movie_showing_count;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_showing = itemView.findViewById(R.id.iv_showing);
            movie_showing_name = itemView.findViewById(R.id.movie_showing_name);
            movie_showing_average = itemView.findViewById(R.id.movie_showing_average);
            movie_showing_directors = itemView.findViewById(R.id.movie_showing_directors);
            movie_showing_casts = itemView.findViewById(R.id.movie_showing_casts);
            movie_showing_count = itemView.findViewById(R.id.movie_showing_count);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        ShowingBean.SubjectsBean subjectsBean = subjects.get(position);
        /**
         * 电影名
         */
        myViewHolder.movie_showing_name.setText(subjectsBean.getTitle());
        /**
         * 主演
         */
        if (subjectsBean.getCasts()!=null){
            if (subjectsBean.getCasts().size()>=3)
            {
                myViewHolder.movie_showing_casts.setText("主演:"+subjectsBean.getCasts().get(0).getName()
                        +'/'+subjectsBean.getCasts().get(1).getName()
                        +'/'+subjectsBean.getCasts().get(2).getName());
            }else if (subjectsBean.getCasts().size()==2){
                myViewHolder.movie_showing_casts.setText("主演:"+subjectsBean.getCasts().get(0).getName()
                        +'/'+subjectsBean.getCasts().get(1).getName());
            }else if (subjectsBean.getCasts().size()==1){
                myViewHolder.movie_showing_casts.setText("主演:"+subjectsBean.getCasts().get(0).getName());
            }
        }else {
            myViewHolder.movie_showing_casts.setText("  ");
        }
        /**
         *导演
         */
        if (subjectsBean.getDirectors()!=null){
            if (subjectsBean.getDirectors().size()>=3){
                myViewHolder.movie_showing_directors.setText("导演:"+subjectsBean.getDirectors().get(0).getName()
                +'/'+subjectsBean.getDirectors().get(1).getName()
                +'/'+subjectsBean.getDirectors().get(2).getName());
            }else if (subjectsBean.getDirectors().size()==2){
                    myViewHolder.movie_showing_directors.setText("导演:"+subjectsBean.getDirectors().get(0).getName()
                            +'/'+subjectsBean.getDirectors().get(1).getName());
            }else if (subjectsBean.getDirectors().size()==1){
                    myViewHolder.movie_showing_directors.setText("导演:"+subjectsBean.getDirectors().get(0).getName());
            }
        }
        /**
         * 评分
         */
        if (subjectsBean.getRating()!=null&&subjectsBean.getRating().getAverage()!=0){
            myViewHolder.movie_showing_average.setText(""+subjectsBean.getRating().getAverage());
        }else{
            myViewHolder.movie_showing_average.setText("暂无评分");
        }



        myViewHolder.movie_showing_count.setText(""+subjectsBean.getCollect_count()+"人看过");
        Glide.with(context).load(subjectsBean.getImages().getLarge()).into(myViewHolder.iv_showing);

    }




}
