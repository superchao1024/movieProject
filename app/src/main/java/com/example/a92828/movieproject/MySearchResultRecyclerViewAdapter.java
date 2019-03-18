package com.example.a92828.movieproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a92828.movieproject.bean.MovieBean;
import com.example.a92828.movieproject.hot.adapter.MyShowingRecyclerViewAdapter;

import java.util.List;

public class MySearchResultRecyclerViewAdapter extends RecyclerView.Adapter<MySearchResultRecyclerViewAdapter.MyViewHolder> {

    private final Context context;
    private final List<MovieBean.SubjectsBean> subjects;

    public MySearchResultRecyclerViewAdapter(Context context, List<MovieBean.SubjectsBean> subjects) {
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
        View view = View.inflate(context,R.layout.search_item,null);
        return new MyViewHolder(view);
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv_search;
        private final TextView movie_search_name;
        private final TextView movie_search_average;
        private final TextView movie_search_directors;
        private final TextView movie_search_casts;
        private final RatingBar search_item_ratingbar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_search = itemView.findViewById(R.id.iv_search);
            movie_search_name = itemView.findViewById(R.id.movie_search_name);
            movie_search_average = itemView.findViewById(R.id.movie_search_average);
            movie_search_directors = itemView.findViewById(R.id.movie_search_directors);
            movie_search_casts = itemView.findViewById(R.id.movie_search_casts);
            search_item_ratingbar = itemView.findViewById(R.id.search_item_ratingbar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (onItemClickListener!=null){
                        onItemClickListener.onItemClick(v,subjects.get(getAdapterPosition()).getId());
                    }

                }
            });

        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        MovieBean.SubjectsBean subjectsBean = subjects.get(position);
        /**
         * 电影名
         */
        myViewHolder.movie_search_name.setText(subjectsBean.getTitle());
        /**
         * 主演
         */
        if (subjectsBean.getCasts()!=null){
            if (subjectsBean.getCasts().size()>=3)
            {
                myViewHolder.movie_search_casts.setText("主演:"+subjectsBean.getCasts().get(0).getName()
                        +'/'+subjectsBean.getCasts().get(1).getName()
                        +'/'+subjectsBean.getCasts().get(2).getName());
            }else if (subjectsBean.getCasts().size()==2){
                myViewHolder.movie_search_casts.setText("主演:"+subjectsBean.getCasts().get(0).getName()
                        +'/'+subjectsBean.getCasts().get(1).getName());
            }else if (subjectsBean.getCasts().size()==1){
                myViewHolder.movie_search_casts.setText("主演:"+subjectsBean.getCasts().get(0).getName());
            }
        }else {
            myViewHolder.movie_search_casts.setText("  ");
        }
        /**
         *导演
         */
        if (subjectsBean.getDirectors()!=null){
            if (subjectsBean.getDirectors().size()>=3){
                myViewHolder.movie_search_directors.setText("导演:"+subjectsBean.getDirectors().get(0).getName()
                +'/'+subjectsBean.getDirectors().get(1).getName()
                +'/'+subjectsBean.getDirectors().get(2).getName());
            }else if (subjectsBean.getDirectors().size()==2){
                    myViewHolder.movie_search_directors.setText("导演:"+subjectsBean.getDirectors().get(0).getName()
                            +'/'+subjectsBean.getDirectors().get(1).getName());
            }else if (subjectsBean.getDirectors().size()==1){
                    myViewHolder.movie_search_directors.setText("导演:"+subjectsBean.getDirectors().get(0).getName());
            }
        }
        /**
         * 评分
         */
        if (subjectsBean.getRating()!=null&&subjectsBean.getRating().getAverage()!=0){
            myViewHolder.search_item_ratingbar.setRating((float) subjectsBean.getRating().getAverage()/2);
            myViewHolder.movie_search_average.setText(""+subjectsBean.getRating().getAverage());
        }else{
            myViewHolder.movie_search_average.setText("暂无评分");
        }


        Glide.with(context).load(subjectsBean.getImages().getLarge()).into(myViewHolder.iv_search);

    }
    /**
     * 点击RecyclerView某条的监听
     */
    public interface OnItemClickListener{
        /**
         * 当RecyclerView某个被点击的时候回调
         * @param view
         * @param data
         */

        public void onItemClick(View view , String data);

    }


    private MyShowingRecyclerViewAdapter.OnItemClickListener onItemClickListener;

    /**
     * 设置RecyclerView某个的监听
     * @param onItemClickListener
     */



    public void setOnItemClickListener(MyShowingRecyclerViewAdapter.OnItemClickListener onItemClickListener){

        this.onItemClickListener = onItemClickListener;

    }




}
