package com.example.a92828.movieproject.itemDetail;


import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
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
import com.example.a92828.movieproject.find.Adapter.MyRecyclerViewkoubeiOneAdapter;
import com.example.a92828.movieproject.find.Adapter.MyRecyclerViewkoubeiTwoAdapter;

import org.w3c.dom.Text;

class QueryItemAdapter extends RecyclerView.Adapter{
    private final QueryItemActivity mContext;
    private final QueryItem queryItem;
    private final LayoutInflater mLayoutInflater;
    /**
     * 海报
     */
    public static final int POSTERS = 0;
    /**
     * 影片详细信息
     */
    public static final int DETAIL = 1;
    /**
     * 简介
     */
    public static final int INTRODUCTION = 2;
    /**
     * 导演和演员
     */
    public static final int EVERYONE = 3;
    /**
     * 剧照
     */
    public static final int PHOTOS = 4;
    /**
     * 短评
     */
    public static final int SHORTCOMMENTS = 5;
    /**
     * 影评
     */
    public static final int LONGCOMMENTS = 6;



    /**
     * 当前类型
     */
    private int currentType = POSTERS;


    public QueryItemAdapter(QueryItemActivity mContext, QueryItem queryItem) {
        this.mContext = mContext;
        this.queryItem = queryItem;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
        /**
         * 得到类型
         *
         * @param position
         * @return
         */
        @Override
        public int getItemViewType(int position) {

            switch (position) {
                case POSTERS:
                    currentType = POSTERS;
                    break;
                case DETAIL:
                    currentType = DETAIL;
                    break;
                case INTRODUCTION:
                    currentType = INTRODUCTION;
                    break;
                case EVERYONE:
                    currentType = EVERYONE;
                    break;
                case PHOTOS:
                    currentType = PHOTOS;
                    break;
                case SHORTCOMMENTS:
                    currentType = SHORTCOMMENTS;
                    break;
                case LONGCOMMENTS:
                    currentType = LONGCOMMENTS;
                    break;
            }
            return currentType;
        }

        /**
         * 总共有多少个Item
         *
         * @return
         */
        @Override
        public int getItemCount() {
            //开发过程中从1-->2
            return 7;
        }

        /**
         * 相当于getView
         * 创建ViewHolder
         *
         * @param viewGroup
         * @param viewType  当前类型
         * @return
         */
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == POSTERS) {
                return new PostersViewHolder(mLayoutInflater.inflate(R.layout.rv_queryitem_poster, viewGroup,false), mContext, queryItem);
            }else if (viewType == DETAIL){
                return new DetailViewHolder(mLayoutInflater.inflate(R.layout.rv_queryitem_detail,viewGroup,false),mContext,queryItem);
            }else if (viewType == INTRODUCTION){
                return new IntroductionViewHolder(mLayoutInflater.inflate(R.layout.rv_queryitem_introduction,viewGroup,false),mContext,queryItem);
            }else if (viewType == EVERYONE){
                return new EveryOneViewHolder(mLayoutInflater.inflate(R.layout.rv_queryitem_everyone,viewGroup,false),mContext,queryItem);
            }else if (viewType == PHOTOS){
                return new PhotosViewHolder(mLayoutInflater.inflate(R.layout.rv_queryitem_photos,viewGroup,false),mContext,queryItem);
            }else if (viewType == SHORTCOMMENTS){
                return new ShortCommentsViewHolder(mLayoutInflater.inflate(R.layout.rv_queryitem_shortcomments,viewGroup,false),mContext,queryItem);
            }else if (viewType == LONGCOMMENTS){
                return new LongCommentsViewHolder(mLayoutInflater.inflate(R.layout.rv_queryitem_longcomments,viewGroup,false),mContext,queryItem);

            }
            return null;
        }


        /**
         * 相当于getView中的绑定数据模块
         *
         * @param holder
         * @param position
         */
        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (getItemViewType(position) == POSTERS) {
                PostersViewHolder holeViewHolder = (PostersViewHolder) holder;
                holeViewHolder.setData(queryItem);
            }else if (getItemViewType(position)==DETAIL){
                DetailViewHolder detailViewHolder = (DetailViewHolder) holder;
                detailViewHolder.setData(queryItem);
            }else if (getItemViewType(position)==INTRODUCTION){
                IntroductionViewHolder introductionViewHolder = (IntroductionViewHolder) holder;
                introductionViewHolder.setData(queryItem);
            }else if (getItemViewType(position)==EVERYONE){
                EveryOneViewHolder everyOneViewHolder = (EveryOneViewHolder) holder;
                everyOneViewHolder.setData(queryItem);
            }else if (getItemViewType(position)==PHOTOS){
                PhotosViewHolder photosViewHolder = (PhotosViewHolder) holder;
                photosViewHolder.setData(queryItem);
            }else if (getItemViewType(position)==SHORTCOMMENTS){
                ShortCommentsViewHolder shortCommentsViewHolder = (ShortCommentsViewHolder) holder;
                shortCommentsViewHolder.setData(queryItem);
            }else if (getItemViewType(position)==LONGCOMMENTS){
                LongCommentsViewHolder longCommentsViewHolder = (LongCommentsViewHolder) holder;
                longCommentsViewHolder.setData(queryItem);
            }

        }

    private class PostersViewHolder extends RecyclerView.ViewHolder {
        private final QueryItemActivity mContext;
        private final ImageView iv_queryitem_poster;
        private final TextView tv_item_type;

        public PostersViewHolder(View itemView, QueryItemActivity mContext, QueryItem queryItem) {
            super(itemView);
            iv_queryitem_poster = itemView.findViewById(R.id.iv_queryitem_poster);
            tv_item_type = itemView.findViewById(R.id.tv_item_type);

            this.mContext = mContext;
        }

        public void setData(QueryItem queryItem) {




            Glide.with(mContext).load(queryItem.getImages().getSmall()).into(iv_queryitem_poster);
        }
    }

    private class DetailViewHolder extends RecyclerView.ViewHolder {
        private final QueryItemActivity mContext;
        private final TextView tv_moviename_item;
        private final TextView tv_yearandtype_item;
        private final TextView tv_original_title_item;
        private final TextView tv_pubdatas_item;
        private final TextView tv_movie_long;
        private final TextView tv_pingfen_item;
        private final RatingBar rb_item;
        private final TextView tv_muchwatch_item;

        public DetailViewHolder(View itemView, QueryItemActivity mContext, QueryItem queryItem) {
            super(itemView);
            tv_moviename_item = itemView.findViewById(R.id.tv_moviename_item);
            tv_yearandtype_item = itemView.findViewById(R.id.tv_yearandtype_item);
            tv_original_title_item = itemView.findViewById(R.id.tv_original_title_item);
            tv_pubdatas_item = itemView.findViewById(R.id.tv_pubdatas_item);
            tv_movie_long = itemView.findViewById(R.id.tv_movie_long);
            tv_pingfen_item = itemView.findViewById(R.id.tv_pingfen_item);
            rb_item = itemView.findViewById(R.id.rb_item);
            tv_muchwatch_item = itemView.findViewById(R.id.tv_muchwatch_item);
            this.mContext = mContext;
        }

        public void setData(QueryItem queryItem) {
            /**
             * 电影名
             */
            tv_moviename_item.setText(queryItem.getTitle());
            /**
             * 电影年份和类型
             */
            tv_yearandtype_item.setText(queryItem.getYear()+"/"+queryItem.getGenres());
            if (queryItem.getOriginal_title()!=null) {
                tv_original_title_item.setText("原名:"+queryItem.getOriginal_title());
            }else {
                tv_original_title_item.setText("原名:无");
            }
            /**
             * 上映时间
             */
            tv_pubdatas_item.setText("上映时间:"+queryItem.getPubdates());
            /**
             * 电影时长
             */
            tv_movie_long.setText("片场:"+queryItem.getDurations());
            /**
             * 电影评分
             */

            if (queryItem.getRating()!=null&&queryItem.getRating().getAverage()!=0){
                rb_item.setRating((float) queryItem.getRating().getAverage()/2);
                tv_pingfen_item.setText(""+queryItem.getRating().getAverage());
            }else{
                rb_item.setRating(0);
                tv_pingfen_item.setText("暂无评分");
            }
            /**
             * 多少人看过
             */
            tv_muchwatch_item.setText(""+queryItem.getRatings_count()+"人");


        }
    }

    private class IntroductionViewHolder extends RecyclerView.ViewHolder {
        private final QueryItemActivity mContext;
        private final TextView tv_introduction_item;

        public IntroductionViewHolder(View itemView, QueryItemActivity mContext, QueryItem queryItem) {
            super(itemView);
            tv_introduction_item = itemView.findViewById(R.id.tv_introduction_item);
            this.mContext = mContext;
        }

        public void setData(QueryItem queryItem) {
            /**
             * 设置简介内容
             */
            if (queryItem.getSummary()!=null){
            tv_introduction_item.setText(queryItem.getSummary());
            }else {
                tv_introduction_item.setText("无");
            }
        }
    }

    private class EveryOneViewHolder extends RecyclerView.ViewHolder {
        private final QueryItemActivity mContext;
        private final RecyclerView rv_queryItem_everyone;

        public EveryOneViewHolder(View itemView, QueryItemActivity mContext, QueryItem queryItem) {
            super(itemView);
            rv_queryItem_everyone = itemView.findViewById(R.id.rv_queryItem_everyone);
            this.mContext = mContext;
        }

        public void setData(QueryItem queryItem) {

            rv_queryItem_everyone.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            MyRecyclerViewEveryOneAdapter myRecyclerViewEveryOneAdapter = new MyRecyclerViewEveryOneAdapter(mContext,queryItem);
            rv_queryItem_everyone.setAdapter(myRecyclerViewEveryOneAdapter);


        }
    }

    private class PhotosViewHolder extends RecyclerView.ViewHolder {
        private final QueryItemActivity mContext;
        private final RecyclerView rv_queryItem_photos;

        public PhotosViewHolder(View itemView, QueryItemActivity mContext, QueryItem queryItem) {
            super(itemView);
            rv_queryItem_photos = itemView.findViewById(R.id.rv_queryItem_photos);
            this.mContext = mContext;
        }

        public void setData(QueryItem queryItem) {

            rv_queryItem_photos.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            MyRecyclerViewPhotosAdapter myRecyclerViewPhotosAdapter = new MyRecyclerViewPhotosAdapter(mContext,queryItem);
            rv_queryItem_photos.setAdapter(myRecyclerViewPhotosAdapter);

        }
    }

    private class ShortCommentsViewHolder extends RecyclerView.ViewHolder {
        private final QueryItemActivity mContext;
        private final QueryItem queryItem;
        private final RecyclerView rv_queryItem_shortcomments;

        public ShortCommentsViewHolder(View itemView, QueryItemActivity mContext, QueryItem queryItem) {
            super(itemView);
            rv_queryItem_shortcomments = itemView.findViewById(R.id.rv_queryItem_shortcomments);
            this.mContext = mContext;
            this.queryItem = queryItem;
        }

        public void setData(QueryItem queryItem) {
            rv_queryItem_shortcomments.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            MyRecyclerViewShortCommentsAdapter myRecyclerViewShortCommentsAdapter = new MyRecyclerViewShortCommentsAdapter(mContext, queryItem);
            rv_queryItem_shortcomments.setAdapter(myRecyclerViewShortCommentsAdapter);
        }
    }

    private class LongCommentsViewHolder extends RecyclerView.ViewHolder {
        private final QueryItemActivity mContext;
        private final QueryItem queryItem;
        private final RecyclerView rv_queryItem_longcomments;

        public LongCommentsViewHolder(View itemView, QueryItemActivity mContext, QueryItem queryItem) {
            super(itemView);
            rv_queryItem_longcomments = itemView.findViewById(R.id.rv_queryItem_longcomments);
            this.mContext = mContext;
            this.queryItem = queryItem;
        }

        public void setData(QueryItem queryItem) {

            rv_queryItem_longcomments.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            MyRecyclerViewLongCommentsAdapter myRecyclerViewLongCommentsAdapter = new MyRecyclerViewLongCommentsAdapter(mContext, queryItem);
            rv_queryItem_longcomments.setAdapter(myRecyclerViewLongCommentsAdapter);

        }
    }
}
