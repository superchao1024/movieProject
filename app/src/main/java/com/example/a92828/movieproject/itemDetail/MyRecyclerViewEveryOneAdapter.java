package com.example.a92828.movieproject.itemDetail;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a92828.movieproject.R;
import com.example.a92828.movieproject.bean.KouBeiBean;
import com.example.a92828.movieproject.bean.QueryItem;

import java.util.List;

/**
 * RecyclerView的适配器
 */
public class MyRecyclerViewEveryOneAdapter extends RecyclerView.Adapter<MyRecyclerViewEveryOneAdapter.MyViewHolder> {


    private final QueryItemActivity mContext;
    private final QueryItem queryItem;
    private ImageView iv_everyone_item;
    private TextView tv_everyone_name;
    private TextView tv_everyone_juese;

    class MyViewHolder extends RecyclerView.ViewHolder{



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_everyone_item = itemView.findViewById(R.id.iv_everyone_item);
            tv_everyone_name = itemView.findViewById(R.id.tv_everyone_name);
            tv_everyone_juese = itemView.findViewById(R.id.tv_everyone_juese);
        }
    }

    public MyRecyclerViewEveryOneAdapter(QueryItemActivity mContext, QueryItem queryItem) {
        this.mContext = mContext;
        this.queryItem = queryItem;
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
        View itemView = View.inflate(mContext,R.layout.everyone_item,null);

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

        if (position<queryItem.getDirectors().size()) {
            Glide.with(mContext).load(queryItem.getDirectors().get(position).getAvatars().getSmall()).into(iv_everyone_item);
            tv_everyone_name.setText(queryItem.getDirectors().get(position).getName());
            tv_everyone_juese.setText("导演");
        }else {

            Glide.with(mContext).load(queryItem.getCasts().get(position).getAvatars().getSmall()).into(iv_everyone_item);
            tv_everyone_name.setText(queryItem.getCasts().get(position).getName());
            tv_everyone_juese.setText("演员");
        }


    }
    /**
     * 得到总条数
     * @return
     */
    @Override
    public int getItemCount() {
        return 4;
    }



}
