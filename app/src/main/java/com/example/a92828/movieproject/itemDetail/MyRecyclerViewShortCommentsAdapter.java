package com.example.a92828.movieproject.itemDetail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a92828.movieproject.R;
import com.example.a92828.movieproject.bean.QueryItem;

public class MyRecyclerViewShortCommentsAdapter extends RecyclerView.Adapter<MyRecyclerViewShortCommentsAdapter.MyViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final QueryItemActivity mContext;
    private final QueryItem queryItem;
    private ImageView iv_photos_item;
    private ImageView iv_usericon;
    private TextView shortcomments_user_name;
    private RatingBar shortcomments_ratingbar;
    private TextView shortcomments_comments;
    private TextView shortcomments_time;

    class MyViewHolder extends RecyclerView.ViewHolder{


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
          //  iv_photos_item = itemView.findViewById(R.id.iv_photos_item);

        }
    }

    public MyRecyclerViewShortCommentsAdapter(QueryItemActivity mContext, QueryItem queryItem) {
        this.mContext = mContext;
        this.queryItem = queryItem;
        mLayoutInflater = LayoutInflater.from(mContext);
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

        View itemView = mLayoutInflater.inflate(R.layout.shortcomments_item, viewGroup, false);
        iv_usericon = itemView.findViewById(R.id.iv_usericon);
        shortcomments_user_name = itemView.findViewById(R.id.shortcomments_user_name);
        shortcomments_ratingbar = itemView.findViewById(R.id.shortcomments_ratingbar);
        shortcomments_comments = itemView.findViewById(R.id.shortcomments_comments);
        shortcomments_time = itemView.findViewById(R.id.shortcomments_time);


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

        Glide.with(mContext).load(queryItem.getPopular_comments().get(position).getAuthor().getAvatar()).into(iv_usericon);
        shortcomments_user_name.setText(queryItem.getPopular_comments().get(position).getAuthor().getName());
        shortcomments_ratingbar.setRating(queryItem.getPopular_comments().get(position).getRating().getValue());
        shortcomments_comments.setText(queryItem.getPopular_comments().get(position).getContent());
        shortcomments_time.setText(queryItem.getPopular_comments().get(position).getCreated_at());


    }
    /**
     * 得到总条数
     * @return
     */
    @Override
    public int getItemCount() {
        return 3;
    }



}

