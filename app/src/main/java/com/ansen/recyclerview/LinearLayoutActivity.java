package com.ansen.recyclerview;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.ansen.adapter.RecyclerViewAdapter;
import com.ansen.util.DividerGridItemDecoration;
import com.ansen.util.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  ansen
 * Create Time 2016-12-20
 */
public class LinearLayoutActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private List<String> datas;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_main);

        initData();

        recyclerView= (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//设置布局管理器
        recyclerView.addItemDecoration(new DividerItemDecoration(this));//添加分割线
        recyclerView.setAdapter(adapter=new RecyclerViewAdapter(this,datas));
    }

    private void initData(){
        datas=new ArrayList<>();
        for(int i=0;i<100;i++){
            datas.add("item:"+i);
        }
    }
}
