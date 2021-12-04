package com.ansen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ansen.recyclerview.R;
import java.util.List;

/**
 * Created by  ansen
 * Create Time 2016-12-22
 */
public class RecyclerHeadFootViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<String> datas;
    private LayoutInflater inflater;

    public static final int TYPE_HEADER=1;//header类型
    public static final int TYPE_FOOTER=2;//footer类型
    private View header=null;//头View
    private View footer=null;//脚View

    private RecyclerViewItemClick recyclerViewItemClick;

    public void setRecyclerViewItemClick(RecyclerViewItemClick recyclerViewItemClick) {
        this.recyclerViewItemClick = recyclerViewItemClick;
    }

    public  RecyclerHeadFootViewAdapter(Context context, List<String> datas){
        inflater=LayoutInflater.from(context);
        this.datas=datas;
    }

    //创建每一行的View 用RecyclerView.ViewHolder包装
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_HEADER){
            return new RecyclerView.ViewHolder(header){};
        }else if(viewType==TYPE_FOOTER){
            return new RecyclerView.ViewHolder(footer){};
        }
        View itemView=inflater.inflate(R.layout.recycler_item,parent,false);
        return new MyViewHolder(itemView);
    }

    //给每一行View填充数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position){
        if(getItemViewType(position)==TYPE_HEADER||getItemViewType(position)==TYPE_FOOTER){
            return;
        }
        MyViewHolder myholder= (MyViewHolder) holder;
        myholder.textview.setText(datas.get(getRealPosition(position)));
        if(recyclerViewItemClick!=null) {
            myholder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewItemClick.onItemClick(getRealPosition(position),position);
                }
            });
        }
    }

    //如果有头部 position的位置是从1开始的  所以需要-1
    public int getRealPosition(int position){
        return header==null?position:position-1;
    }

    //数据源的数量
    @Override
    public int getItemCount() {
        if(header == null && footer == null){//没有head跟foot
            return datas.size();
        }else if(header == null && footer != null){//head为空&&foot不为空
            return datas.size() + 1;
        }else if (header != null && footer == null){//head不为空&&foot为空
            return datas.size() + 1;
        }else {
            return datas.size() + 2;//head不为空&&foot不为空
        }
    }

    @Override
    public int getItemViewType(int position){
        //如果头布局不为空&&位置是第一个那就是head类型
        if(header!=null&&position==0){
            return TYPE_HEADER;
        }else if(footer!=null&&position==getItemCount()-1){//如果footer不为空&&最后一个
            return TYPE_FOOTER;
        }
        return super.getItemViewType(position);
    }

    public void setHeader(View header) {
        this.header = header;
        notifyItemInserted(0);//在第一行插入一条数据，然后刷新
    }

    public void setFooter(View footer) {
        this.footer = footer;
        notifyItemInserted(datas.size()-1);//在尾部插入一条数据，然后刷新
    }

    public interface RecyclerViewItemClick{
        /**
         * item点击
         * @param realPosition 数据源position
         * @param position view position
         */
        void onItemClick(int realPosition,int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textview;

        public MyViewHolder(View itemView) {
            super(itemView);
            textview= (TextView) itemView.findViewById(R.id.textview);
        }
    }
}