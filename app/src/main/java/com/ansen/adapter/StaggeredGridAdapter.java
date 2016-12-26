package com.ansen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ansen.entity.ItemData;
import com.ansen.recyclerview.R;

import java.util.List;
import java.util.Random;

/**
 * Created by  ansen
 * Create Time 2016-12-19
 */
public class StaggeredGridAdapter extends RecyclerView.Adapter<StaggeredGridAdapter.MyViewHolder>{
    private List<ItemData> datas;
    private LayoutInflater inflater;

    public StaggeredGridAdapter(Context context, List<ItemData> datas){
        inflater=LayoutInflater.from(context);
        this.datas=datas;
    }

    //创建每一行的View 用RecyclerView.ViewHolder包装
    @Override
    public StaggeredGridAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView=inflater.inflate(R.layout.recycler_staggered_item,parent,false);
        return new MyViewHolder(itemView);
    }

    //给每一行View填充数据
    @Override
    public void onBindViewHolder(StaggeredGridAdapter.MyViewHolder holder, int position) {
        ItemData itemData=datas.get(position);
        holder.textview.setText(itemData.getContent());
        //手动更改高度，不同位置的高度有所不同
        holder.textview.setHeight(itemData.getHeight());
    }

    //数据源的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textview;

        public MyViewHolder(View itemView) {
            super(itemView);
            textview= (TextView) itemView.findViewById(R.id.textview);
        }
    }
}
