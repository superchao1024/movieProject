package com.example.a92828.movieproject.find.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.example.a92828.movieproject.R;
import com.example.a92828.movieproject.YuGaoActivity;
import com.example.a92828.movieproject.bean.KouBeiBean;
import java.util.List;

public class FindFragmentAdapter extends RecyclerView.Adapter {


    /**
     *口碑第一条
     */
    public static final int KOUBEIONE = 0;
    /**
     * 口碑第二条
     */
    public static final int KOUBEITWO = 1;
    private final Context mContext;
    private final List<KouBeiBean.SubjectsBean> subjects;
    private final LayoutInflater mLayoutInflater;

    /**
     * 当前类型
     */
    private int currentType = KOUBEIONE;

    public FindFragmentAdapter(Context mContext, List<KouBeiBean.SubjectsBean> subjects) {
        this.mContext = mContext;
        this.subjects = subjects;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
    /**
     * 得到类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {

        switch (position){
            case KOUBEIONE:
                currentType = KOUBEIONE;
                break;
            case KOUBEITWO:
                currentType = KOUBEITWO;
                break;
        }

        return currentType;
    }
    /**
     * 总共有多少个Item
     * @return
     */
    @Override
    public int getItemCount() {
        //开发过程中从1-->2
        return 2;
    }
    /**
     * 相当于getView
     * 创建ViewHolder
     * @param viewGroup
     * @param viewType 当前类型
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == KOUBEIONE){
            return new KoubeiOneViewHolder(mLayoutInflater.inflate(R.layout.koubeione_item_rv, null), mContext, subjects);
        }else if (viewType == KOUBEITWO){
            return new KoubeiTwoViewHolder(mLayoutInflater.inflate(R.layout.koubeitwo_item_rv,null),mContext,subjects);
        }

        return null;
    }



    /**
     * 相当于getView中的绑定数据模块
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (getItemViewType(position)==KOUBEIONE){
             KoubeiOneViewHolder koubeiOneViewHolder = (KoubeiOneViewHolder) holder;
             koubeiOneViewHolder.setData(subjects.get(position).getSubject());
         }else if (getItemViewType(position)==KOUBEITWO){
             KoubeiTwoViewHolder koubeiTwoViewHolder = (KoubeiTwoViewHolder) holder;
             koubeiTwoViewHolder.setData(subjects.get(position).getSubject());
         }

    }



    class KoubeiOneViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private final RecyclerView rv_kobeione;
        private final LinearLayout all_koubei;

        public KoubeiOneViewHolder(View itemView, Context mContext,List<KouBeiBean.SubjectsBean> subjects) {
            super(itemView);
            rv_kobeione = itemView.findViewById(R.id.rv_koubeione);
            all_koubei = itemView.findViewById(R.id.all_koubei);
            this.mContext = mContext;
        }
        public void setData(KouBeiBean.SubjectsBean.SubjectBean subject) {

            all_koubei.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,YuGaoActivity.class);
                    mContext.startActivity(intent);
                }
            });

            rv_kobeione.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            MyRecyclerViewkoubeiOneAdapter myRecyclerViewkoubeiOneAdapter = new MyRecyclerViewkoubeiOneAdapter(mContext,subjects);
            rv_kobeione.setAdapter(myRecyclerViewkoubeiOneAdapter);



        }
    }



    private class KoubeiTwoViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private final RecyclerView rv_koubeitwo;

        public KoubeiTwoViewHolder(View itemView, Context mContext, List<KouBeiBean.SubjectsBean> subjects) {
            super(itemView);
            rv_koubeitwo = itemView.findViewById(R.id.rv_koubeitwo);
            this.mContext = mContext;
        }

        public void setData(KouBeiBean.SubjectsBean.SubjectBean subject) {

            rv_koubeitwo.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            MyRecyclerViewkoubeiTwoAdapter myRecyclerViewkoubeiTwoAdapter = new MyRecyclerViewkoubeiTwoAdapter(mContext,subjects);
            rv_koubeitwo.setAdapter(myRecyclerViewkoubeiTwoAdapter);

        }
    }
}
