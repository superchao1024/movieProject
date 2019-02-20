package com.example.a92828.movieproject.find.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a92828.movieproject.R;
import com.example.a92828.movieproject.bean.TopBean;
import java.util.List;

/**
 * RecyclerView的适配器
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private final Context context;
    private List<TopBean.SubjectsBean> subjects;

    class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView movie_top250_name;
        private final TextView movie_top250_average;
        private final TextView movie_top250_directors;
        private final TextView movie_top250_casts;
        private final TextView movie_top250_count;
        private final ImageView movie_top250_iv_hot;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_top250_iv_hot = itemView.findViewById(R.id.iv_hot);
            movie_top250_name = itemView.findViewById(R.id.movie_top250_name);
            movie_top250_average = itemView.findViewById(R.id.movie_top250_average);
            movie_top250_directors = itemView.findViewById(R.id.movie_top250_directors);
            movie_top250_casts = itemView.findViewById(R.id.movie_top250_casts);
            movie_top250_count = itemView.findViewById(R.id.movie_top250_count);
        }
    }

    public MyRecyclerViewAdapter(Context context, List<TopBean.SubjectsBean> subjects) {
        this.context = context;
        this.subjects = subjects;
    }
    /**
     * 相当于listview中的getview
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = View.inflate(context,R.layout.detail_item,null);
        return new MyViewHolder(itemView);
    }

    /**相当于getView绑定数据部分
     * 数据和view绑定
     * @param myViewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        //根据位置得到对应的数据

        TopBean.SubjectsBean subjectsBean = subjects.get(position);
       // TopBean.SubjectsBean.CastsBean castsBean = subjectsBean.getCasts().get(position);   //卡司
       // TopBean.SubjectsBean.DirectorsBean directorsBean = subjectsBean.getDirectors().get(position);   //导演

        myViewHolder.movie_top250_name.setText(subjectsBean.getTitle());
        myViewHolder.movie_top250_average.setText(""+subjectsBean.getRating().getAverage());
        myViewHolder.movie_top250_directors.setText("导演:"+subjectsBean.getDirectors().get(0).getName());
        myViewHolder.movie_top250_casts.setText("主演:"+subjectsBean.getCasts().get(0).getName());
        myViewHolder.movie_top250_count.setText(""+subjects.get(position).getCollect_count()+"人看过");

        Glide.with(context).load(subjectsBean.getImages().getSmall()).into(myViewHolder.movie_top250_iv_hot);
    }
    /**
     * 得到总条数
     * @return
     */
    @Override
    public int getItemCount() {
        return subjects.size();
    }



}
