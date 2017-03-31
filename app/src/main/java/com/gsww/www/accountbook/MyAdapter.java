package com.gsww.www.accountbook;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gsww.com.accountbook.R;
import com.gsww.www.accountbook.bean.AccountsBean;

import java.util.List;

/**
 * Author : luweicheng on 2017/3/27 0027 17:16
 * E-mail ：1769005961@qq.com
 * GitHub : https://github.com/luweicheng24
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context mContext;
    private List<AccountsBean> mList;

    public MyAdapter(Context mContext, List<AccountsBean> mList) {
        this.mContext = mContext;
        this.mList = mList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycle_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_data.setText(mList.get(position).getCostDate());
        holder.tv_price.setText("金 额:"+mList.get(position).getCostPrice() + "￥");
        holder.tv_des.setText(mList.get(position).getCostName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_data;
        private TextView tv_price;
        private TextView tv_des;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_data = (TextView) itemView.findViewById(R.id.tv_data);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            tv_des = (TextView) itemView.findViewById(R.id.tv_des);
        }
    }
}
