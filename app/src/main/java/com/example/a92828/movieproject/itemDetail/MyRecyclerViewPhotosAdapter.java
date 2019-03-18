package com.example.a92828.movieproject.itemDetail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.a92828.movieproject.R;
import com.example.a92828.movieproject.bean.QueryItem;

public class MyRecyclerViewPhotosAdapter extends RecyclerView.Adapter<MyRecyclerViewPhotosAdapter.MyViewHolder> {


    private final QueryItemActivity mContext;
    private final QueryItem queryItem;
    private ImageView iv_photos_item;

    class MyViewHolder extends RecyclerView.ViewHolder{


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_photos_item = itemView.findViewById(R.id.iv_photos_item);

        }
    }

    public MyRecyclerViewPhotosAdapter(QueryItemActivity mContext, QueryItem queryItem) {
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
        View itemView = View.inflate(mContext,R.layout.photos_item,null);


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

      Glide.with(mContext).load(queryItem.getPhotos().get(position).getThumb()).into(iv_photos_item);


    }
    /**
     * 得到总条数
     * @return
     */
    @Override
    public int getItemCount() {
        return 5;
    }



}
