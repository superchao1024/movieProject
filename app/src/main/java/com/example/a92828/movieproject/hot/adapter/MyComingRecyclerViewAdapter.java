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
import com.example.a92828.movieproject.bean.ComingBean;


import org.w3c.dom.Text;

import java.util.List;

public class MyComingRecyclerViewAdapter extends RecyclerView.Adapter<MyComingRecyclerViewAdapter.MyViewHolder> {

    private final Context context;
    private final List<ComingBean.SubjectsBean> subjects;

    public MyComingRecyclerViewAdapter(Context context, List<ComingBean.SubjectsBean> subjects) {
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
        View view = View.inflate(context,R.layout.coming_item,null);
        return new MyViewHolder(view);
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv_coming;
        private final TextView movie_coming_name;
        private final TextView movie_coming_directors;
        private final TextView movie_coming_casts;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_coming = itemView.findViewById(R.id.iv_coming);
            movie_coming_name = itemView.findViewById(R.id.movie_coming_name);
            movie_coming_directors = itemView.findViewById(R.id.movie_coming_directors);
            movie_coming_casts = itemView.findViewById(R.id.movie_coming_casts);
    }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        ComingBean.SubjectsBean subjectsBean = subjects.get(position);

        /**
         * 电影名
         */
        myViewHolder.movie_coming_name.setText(subjectsBean.getTitle());
        /**
         * 主演
         */
        if (subjectsBean.getCasts()!=null){
            if (subjectsBean.getCasts().size()>=3)
            {
                myViewHolder.movie_coming_casts.setText("主演:"+subjectsBean.getCasts().get(0).getName()
                        +'/'+subjectsBean.getCasts().get(1).getName()
                        +'/'+subjectsBean.getCasts().get(2).getName());
            }else if (subjectsBean.getCasts().size()==2){
                myViewHolder.movie_coming_casts.setText("主演:"+subjectsBean.getCasts().get(0).getName()
                        +'/'+subjectsBean.getCasts().get(1).getName());
            }else if (subjectsBean.getCasts().size()==1){
                myViewHolder.movie_coming_casts.setText("主演:"+subjectsBean.getCasts().get(0).getName());
            }
        }else {
            myViewHolder.movie_coming_casts.setText("  ");
        }
        /**
         *导演
         */
        if (subjectsBean.getDirectors()!=null){
            if (subjectsBean.getDirectors().size()>=3){
                myViewHolder.movie_coming_directors.setText("导演:"+subjectsBean.getDirectors().get(0).getName()
                        +'/'+subjectsBean.getDirectors().get(1).getName()
                        +'/'+subjectsBean.getDirectors().get(2).getName());
            }else if (subjectsBean.getDirectors().size()==2){
                myViewHolder.movie_coming_directors.setText("导演:"+subjectsBean.getDirectors().get(0).getName()
                        +'/'+subjectsBean.getDirectors().get(1).getName());
            }else if (subjectsBean.getDirectors().size()==1){
                myViewHolder.movie_coming_directors.setText("导演:"+subjectsBean.getDirectors().get(0).getName());
            }
        }
        /**
         * 电影名
         */
        myViewHolder.movie_coming_name.setText(subjectsBean.getTitle());
        Glide.with(context).load(subjectsBean.getImages().getLarge()).into(myViewHolder.iv_coming);

    }




}
