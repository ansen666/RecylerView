package com.ansen.recyclerview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ansen.adapter.RecyclerHeadFootViewAdapter;
import com.ansen.util.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * 可以设置头部跟底部的 RecyclerView
 * Created by  ansen
 * Create Time 2016-12-20
 */
public class HeadFootActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerHeadFootViewAdapter adapter;
    private List<String> datas;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_main);

        initData();

        recyclerView= findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//设置布局管理器
        recyclerView.addItemDecoration(new DividerItemDecoration(this));//添加分割线
        recyclerView.setAdapter(adapter=new RecyclerHeadFootViewAdapter(this,datas));

        //添加header
        View header=LayoutInflater.from(this).inflate(R.layout.recycler_header,recyclerView,false);
        Log.i("ansen","header:"+header+" recyclerView:"+recyclerView);
        adapter.setHeader(header);

        //添加footer
        View footer=LayoutInflater.from(this).inflate(R.layout.recycler_footer,recyclerView,false);
        adapter.setFooter(footer);

        adapter.setRecyclerViewItemClick(recyclerViewItemClick);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private RecyclerHeadFootViewAdapter.RecyclerViewItemClick recyclerViewItemClick=new RecyclerHeadFootViewAdapter.RecyclerViewItemClick() {
        @Override
        public void onItemClick(int realPosition, int position) {
            Log.i("ansen","删除数据:"+realPosition+" view位置:"+position);
            Log.i("ansen","当前位置:"+position+" 更新item数量:"+(adapter.getItemCount()-position-1));

            datas.remove(realPosition);//删除数据源
            adapter.notifyItemRemoved(position);//item删除动画
            //更新position至adapter.getItemCount()-1的数据
            adapter.notifyItemRangeChanged(position,adapter.getItemCount()-position-1);
        }
    };

    private void initData(){
        datas=new ArrayList<>();
        for(int i=0;i<5;i++){
            datas.add("item:"+i);
        }
    }
}
