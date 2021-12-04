package com.ansen.recyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ansen.adapter.RecyclerViewAdapter;
import com.ansen.util.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  ansen
 * Create Time 2016-12-20
 */
public class GridLayoutActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private List<String> datas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_main);

        initData();

        recyclerView= findViewById(R.id.recyclerview);
        //横向显示两列
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        //纵向显示4列
//        recyclerView.setLayoutManager(new GridLayoutManager(this,4,GridLayoutManager.HORIZONTAL,false));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        recyclerView.setAdapter(adapter=new RecyclerViewAdapter(this,datas));
    }

    private void initData(){
        datas=new ArrayList<>();
        for(int i=0;i<100;i++){
            datas.add("item:"+i);
        }
    }
}
