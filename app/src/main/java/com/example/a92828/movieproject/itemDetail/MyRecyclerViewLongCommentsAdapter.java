package com.example.a92828.movieproject.itemDetail;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.example.a92828.movieproject.R;
import com.example.a92828.movieproject.bean.QueryItem;

public class MyRecyclerViewLongCommentsAdapter extends RecyclerView.Adapter<MyRecyclerViewLongCommentsAdapter.MyViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final QueryItemActivity mContext;
    private final QueryItem queryItem;
    private TextView longcomments_title;
    private TextView longcomments_user_name;
    private RatingBar longcomments_ratingbar;
    private TextView longcomments_comments;
    private LinearLayout ll_longcomments;

    class MyViewHolder extends RecyclerView.ViewHolder{


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //  iv_photos_item = itemView.findViewById(R.id.iv_photos_item);
        }
    }

    public MyRecyclerViewLongCommentsAdapter(QueryItemActivity mContext, QueryItem queryItem) {
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

        View itemView = mLayoutInflater.inflate(R.layout.longcomments_item, viewGroup, false);
        ll_longcomments = itemView.findViewById(R.id.ll_longcomments);
        longcomments_title = itemView.findViewById(R.id.longcomments_title);
        longcomments_user_name = itemView.findViewById(R.id.longcomments_user_name);
        longcomments_ratingbar = itemView.findViewById(R.id.longcomments_ratingbar);
        longcomments_comments = itemView.findViewById(R.id.longcomments_comments);
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
        longcomments_title.setText(queryItem.getPopular_reviews().get(position).getTitle());
        longcomments_user_name.setText(queryItem.getPopular_reviews().get(position).getAuthor().getName());
        longcomments_ratingbar.setRating(queryItem.getPopular_reviews().get(position).getRating().getValue());
        longcomments_comments.setText(queryItem.getPopular_reviews().get(position).getSummary());

        ll_longcomments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,LongCommentsActivity.class);
                String url = queryItem.getPopular_reviews().get(position).getAlt();
                intent.putExtra("url",url);
                mContext.startActivity(intent);
            }
        });
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

