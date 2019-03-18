package com.example.a92828.movieproject.center;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.a92828.movieproject.R;
import com.example.a92828.movieproject.bean.PiaoFangBean;
import com.example.a92828.movieproject.utils.DividerListItemDecoration;

import java.util.Calendar;
import java.util.List;

public class UserFragmentAdapter extends RecyclerView.Adapter {


    /**
     * 口碑第一条
     */
    public static final int HOLE = 0;

    private final Context mContext;
    private final List<PiaoFangBean.DataBean> data;
    private final LayoutInflater mLayoutInflater;

    /**
     * 当前类型
     */
    private int currentType = HOLE;

    public UserFragmentAdapter(Context mContext, List<PiaoFangBean.DataBean> data) {
        this.mContext = mContext;
        this.data = data;
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
            case HOLE:
                currentType = HOLE;
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
        return 1;
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
        if (viewType == HOLE) {
            return new HoleViewHolder(mLayoutInflater.inflate(R.layout.hole_item_rv, null), mContext, data);
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
        if (getItemViewType(position) == HOLE) {
            HoleViewHolder holeViewHolder = (HoleViewHolder) holder;
            holeViewHolder.setData(data);
        }

    }


    private class HoleViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private final TextView now_time;
        private final TextView hole_piaofang;
        private final TextView update_time;
        private final RecyclerView rv_hole_data;

        public HoleViewHolder(View itemView, Context mContext, List<PiaoFangBean.DataBean> data) {
            super(itemView);
            rv_hole_data = itemView.findViewById(R.id.rv_hole_item);
            now_time = itemView.findViewById(R.id.now_time);
            hole_piaofang = itemView.findViewById(R.id.hole_piaofang);
            update_time = itemView.findViewById(R.id.update_time);
            this.mContext = mContext;
        }

        public void setData(List<PiaoFangBean.DataBean> data) {

            update_time.setText(data.get(getLayoutPosition()).getTime());

            Calendar calendar = Calendar.getInstance();
            //获取系统的日期
            //年
            int year = calendar.get(Calendar.YEAR);
            //月
            int month = calendar.get(Calendar.MONTH)+1;
            //日
            int day = calendar.get(Calendar.DAY_OF_MONTH);


            now_time.setText("日票房"+year+"年"+month+"月"+day+"日");

            /**
             * 当日总票房
             */

//            float piaofang = 0;
//            for (int i = 0; i <data.size(); i++) {
//                piaofang = Float.parseFloat(piaofang+data.get(getLayoutPosition()).getSumBoxOffice());
//
//            }


         //   hole_piaofang.setText("sasasa");


            MyRecyclerViewHoleAdapter myRecyclerViewHoleAdapter = new MyRecyclerViewHoleAdapter(mContext,data);
            rv_hole_data.setAdapter(myRecyclerViewHoleAdapter);
            rv_hole_data.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            rv_hole_data.addItemDecoration(new DividerListItemDecoration(mContext,DividerListItemDecoration.VERTICAL_LIST));


        }

    }
}
