package com.yueba.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yueba.R;
import com.yueba.activity.SpotDetailInfoActivity;
import com.yueba.adapter.HomeRecyclerAdapter;
import com.yueba.entity.SpotInfo;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private TextView tvTitleName;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager mLayoutManager;
    private Context context;
    private HomeRecyclerAdapter mAdapter;
    private List<SpotInfo> spotList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        context = getActivity();
        //初始化界面
        initView(view);
        return view;
    }


    private void initView(View view) {
        tvTitleName = (TextView) view.findViewById(R.id.titleText);
        tvTitleName.setText("旅游景点");
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_home);
        mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        //创建并设置Adapter
        if (spotList == null) {
            spotList = new ArrayList<SpotInfo>();
        }
        new UpdateTask().execute();
        mAdapter = new HomeRecyclerAdapter(context, spotList);
        mAdapter.setOnItemClickListener(new HomeRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Intent intent = new Intent(getActivity(),
                        SpotDetailInfoActivity.class);
                intent.putExtra("spot", spotList.get(position));
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onRefresh() {
        new UpdateTask().execute();

    }


    /**
     * 从Bmob服务器获取数据
     */
    private void getData() {

        BmobQuery<SpotInfo> query = new BmobQuery<SpotInfo>();
        query.order("-createdAt");
        query.findObjects(getActivity(),
                new cn.bmob.v3.listener.FindListener<SpotInfo>() {
                    @Override
                    public void onSuccess(List<SpotInfo> list) {
                        spotList.clear();
                        spotList.addAll(list);
                        //通知适配器数据改变
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(int arg0, String arg1) {
                        Log.e("abcerk", arg1+"");
                    }
                });

    }

    private class UpdateTask extends AsyncTask<Void,Void,List<SpotInfo>>
    {
        @Override
        protected List<SpotInfo> doInBackground(Void... params)
        {
            getData();
            return spotList;
        }

        @Override
        protected void onPostExecute(List<SpotInfo> spotList)
        {


            //通知刷新完毕
            swipeRefreshLayout.setRefreshing(false);
            //滚动到列首部
            recyclerView.scrollToPosition(0);
        }
    }

}
