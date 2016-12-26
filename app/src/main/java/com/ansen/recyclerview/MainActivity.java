package com.ansen.recyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.ansen.adapter.RecyclerViewAdapter;
import com.ansen.util.DividerGridItemDecoration;
import com.ansen.util.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_linearlayout).setOnClickListener(this);
        findViewById(R.id.btn_gridlayout).setOnClickListener(this);
        findViewById(R.id.btn_staggeredgrid).setOnClickListener(this);
        findViewById(R.id.btn_headfoot).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_linearlayout:
                startNewActivity(LinearLayoutActivity.class);
                break;
            case R.id.btn_gridlayout:
                startNewActivity(GridLayoutActivity.class);
                break;
            case R.id.btn_staggeredgrid:
                startNewActivity(StaggeredGridActivity.class);
                break;
            case R.id.btn_headfoot:
                startNewActivity(HeadFootActivity.class);
                break;
        }
    }

    private void startNewActivity(Class cla){
        Intent intent=new Intent(this,cla);
        startActivity(intent);
    }
}
