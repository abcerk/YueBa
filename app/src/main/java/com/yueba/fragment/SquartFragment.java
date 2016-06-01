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
import android.widget.ImageButton;
import android.widget.TextView;

import com.yueba.R;
import com.yueba.activity.PublishDynamicActivity;
import com.yueba.activity.SpotDetailInfoActivity;
import com.yueba.adapter.HomeRecyclerAdapter;
import com.yueba.adapter.SquartRecyclerAdapter;
import com.yueba.entity.SpotInfo;
import com.yueba.entity.UserDynamic;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;

public class SquartFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private TextView tvTitleName;
    private ImageButton ibPublishDynamic;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager mLayoutManager;
    private Context context;
    private SquartRecyclerAdapter mAdapter;
    private List<UserDynamic> userDynamicList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_squart, container, false);
        context = getActivity();
        //初始化界面
        initView(view);
        return view;
    }

    private void initView(View view) {
        tvTitleName = (TextView) view.findViewById(R.id.titleText);
        tvTitleName.setText("旅友动态");
        ibPublishDynamic = (ImageButton) view.findViewById(R.id.rightButton);
        ibPublishDynamic.setOnClickListener(this);
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
        if (userDynamicList == null) {
            userDynamicList = new ArrayList<UserDynamic>();
        }
        new getDataTask().execute();
        mAdapter = new SquartRecyclerAdapter(context, userDynamicList);
        recyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onRefresh() {
        new getDataTask().execute();

    }

    /**
     * 从服务器获取数据
     */
    private void getData() {
        BmobQuery<UserDynamic> query = new BmobQuery<UserDynamic>();
        query.order("-createdAt");
        // 每次查询获取的数据条数
        // query.setLimit(EntityConfig.NUMBERS_PER_PAGE);
        // 跳过以获取的数据
        // query.setSkip(EntityConfig.NUMBERS_PER_PAGE * (pageNum++));

        query.findObjects(getActivity(),
                new cn.bmob.v3.listener.FindListener<UserDynamic>() {
                    @Override
                    public void onSuccess(List<UserDynamic> list) {

                        userDynamicList.clear();
                        userDynamicList.addAll(list);
                        //通知适配器数据改变
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(int arg0, String arg1) {
                        Log.e("abcerk", arg1 + "");
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rightButton:
                Intent intent = new Intent(context,
                        PublishDynamicActivity.class);
                startActivityForResult(intent, 0);
                break;
        }
    }

    public class getDataTask extends AsyncTask<Void, Void, List<UserDynamic>> {

        @Override
        protected List<UserDynamic> doInBackground(Void... params) {

            getData();
            return userDynamicList;
        }

        @Override
        protected void onPostExecute(List<UserDynamic> result) {
            //通知刷新完毕
            swipeRefreshLayout.setRefreshing(false);
            //滚动到列首部
            recyclerView.scrollToPosition(0);
        }

    }
}
