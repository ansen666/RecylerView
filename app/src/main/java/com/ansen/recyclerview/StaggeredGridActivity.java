package com.ansen.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.ansen.adapter.RecyclerViewAdapter;
import com.ansen.adapter.StaggeredGridAdapter;
import com.ansen.entity.ItemData;
import com.ansen.util.DividerGridItemDecoration;
import com.ansen.util.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by  ansen
 * Create Time 2016-12-20
 */
public class StaggeredGridActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StaggeredGridAdapter adapter;
    private List<ItemData> datas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_main);

        initData();

        recyclerView= (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter=new StaggeredGridAdapter(this,datas));
    }

    private void initData(){
        datas=new ArrayList<>();
        for(int i=0;i<100;i++){
            ItemData itemData=new ItemData(""+i,(int) (150 + Math.random() * 200));
            datas.add(itemData);
        }
    }
}
