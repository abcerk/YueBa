package com.yueba.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yueba.R;
import com.yueba.adapter.FriendsListAdapter;
import com.yueba.base.BaseActivity;
import com.yueba.entity.User;
import com.yueba.entity.UserAttentions;
import com.yueba.entity.UserFans;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;


public class FriendsListActivity extends BaseActivity {

    private ListView mListView;
    private User user;
    private FriendsListAdapter adapter;
    private List<UserAttentions> attentionList;
    private List<UserFans> fansList;
    private String flag; // 表示当前显示关注列表还是粉丝列表
    private User selectedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendslist);

        user = BmobUser.getCurrentUser(this, User.class);
        mListView = (ListView) findViewById(R.id.lv_friends_list);

        Intent intent = getIntent();
        // 获取启动该activity的信息
        flag = intent.getStringExtra("flag");
        if (flag.equals("attention")) {
            attentionList = new ArrayList<UserAttentions>();
            adapter = new FriendsListAdapter(this, attentionList, null);
            mListView.setAdapter(adapter);
            new getAttentionInfo().execute();

        } else if (flag.equals("fans")) {
            fansList = new ArrayList<UserFans>();
            adapter = new FriendsListAdapter(this, null, fansList);
            mListView.setAdapter(adapter);
            new getFansInfo().execute();
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if (flag.equals("attention")) {

                    selectedUser = attentionList.get(position).getAttentionUser();


                } else if (flag.equals("fans")) {
                    selectedUser = fansList.get(position).getFans();
                }

                BmobQuery<User> query = new BmobQuery<User>();
                query.getObject(FriendsListActivity.this, selectedUser.getObjectId(), new GetListener<User>(){

                    @Override
                    public void onFailure(int arg0, String arg1) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onSuccess(User user) {
                        // TODO Auto-generated method stub
//                        Intent intent = new Intent(FriendsListActivity.this, UserInfoActivity.class);
//                        Bundle bundle = new Bundle();
//                        // 获取点击用户以获取详细资料
//                        bundle.putSerializable("user", user);
//                        intent.putExtras(bundle);
//                        startActivity(intent);

                    }

                });




            }
        });

    }

    /**
     * 从服务器获取关注列表数据
     */
    private void getAttentionData() {
        BmobQuery<UserAttentions> query = new BmobQuery<UserAttentions>();
        query.addWhereEqualTo("user", user);
        query.findObjects(this, new FindListener<UserAttentions>() {
            @Override
            public void onSuccess(List<UserAttentions> list) {
                attentionList.addAll(list);
                adapter.updateAttentionAdapter(attentionList);
            }

            @Override
            public void onError(int arg0, String arg1) {
                // TODO Auto-generated method stub
            }
        });
    }

    /**
     * 从服务器获取粉丝列表数据
     */
    private void getFansData() {
        BmobQuery<UserFans> query = new BmobQuery<UserFans>();
        query.addWhereEqualTo("user", user);

        query.findObjects(this, new FindListener<UserFans>() {
            @Override
            public void onSuccess(List<UserFans> list) {
                // TODO Auto-generated method stub
                fansList.addAll(list);
                adapter.updateFansAdapter(fansList);
            }

            @Override
            public void onError(int arg0, String arg1) {
                // TODO Auto-generated method stub
            }
        });
    }

    /**
     * 服务器获取关注列表
     *
     */
    private class getAttentionInfo extends
            AsyncTask<Void, Void, List<UserAttentions>> {

        @Override
        protected List<UserAttentions> doInBackground(Void... params) {
            // TODO Auto-generated method stub

            getAttentionData();
            return attentionList;
        }

        @Override
        protected void onPostExecute(List<UserAttentions> result) {
            // 通知适配器数据改变
            adapter.updateAttentionAdapter(attentionList);
            super.onPostExecute(result);
        }

    }

    /**
     * 服务器获取粉丝列表
     *
     */
    private class getFansInfo extends AsyncTask<Void, Void, List<UserFans>> {

        @Override
        protected List<UserFans> doInBackground(Void... params) {
            // TODO Auto-generated method stub

            getFansData();
            return fansList;
        }

        @Override
        protected void onPostExecute(List<UserFans> result) {
            // 通知适配器数据改变
            adapter.updateFansAdapter(fansList);
            super.onPostExecute(result);
        }

    }

}