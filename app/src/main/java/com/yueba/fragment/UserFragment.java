package com.yueba.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yueba.R;
import com.yueba.activity.CustomGroupActivity;
import com.yueba.activity.FriendsListActivity;
import com.yueba.activity.LoginActivity;
import com.yueba.activity.MyInfoActivity;
import com.yueba.activity.MyOrderActivity;
import com.yueba.activity.SettingCenterActivity;
import com.yueba.constant.SharePreferenceConstant;
import com.yueba.entity.User;
import com.yueba.entity.UserFans;
import com.yueba.utils.ShareProUtil;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.CountListener;

public class UserFragment extends Fragment implements View.OnClickListener {

    private TextView tvTitleName;
    private Button mLogout, mSendGroup, mMyInfo, mMyOrder, mSetttingCenter;
    private Intent intent;
    private User user;
    private ImageView mAvatar;
    private TextView mNickname, mSignature, mDynamicNum, mAttentionNum, mFansNum;
    private View mAttention, mFans, mDynamic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        // 得到当前登录的用户信息
        user = BmobUser.getCurrentUser(getActivity(), User.class);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tvTitleName = (TextView) view.findViewById(R.id.titleText);
        tvTitleName.setText("个人中心");
        mLogout = (Button) view.findViewById(R.id.btn_logout);
        mSendGroup = (Button) view.findViewById(R.id.btn_send_group);
        mMyInfo = (Button) view.findViewById(R.id.btn_my_info);
        mMyOrder = (Button) view.findViewById(R.id.btn_my_order);
        mSetttingCenter = (Button) view.findViewById(
                R.id.btn_setting_center);
        mAvatar = (ImageView) view.findViewById(R.id.iv_avatar);
        mNickname = (TextView) view.findViewById(R.id.tv_nickname);
        mSignature = (TextView) view.findViewById(R.id.tv_signature);
        mFansNum = (TextView) view.findViewById(R.id.tv_fans_num);
        mAttentionNum = (TextView) view.findViewById(R.id.tv_attention_num);
        mAttention = view.findViewById(R.id.layout_attention);
        mFans = view.findViewById(R.id.layout_fans);
        mDynamic = view.findViewById(R.id.layout_dynamic);
        mDynamicNum = (TextView) view
                .findViewById(R.id.tv_dynamic_num);

        mAttentionNum.setText(user.getAttentionNum()+"");

        //查询粉丝数目并显示
        BmobQuery<UserFans> query = new BmobQuery<UserFans>();
        query.addWhereEqualTo("userId", user.getObjectId());
        query.count(getActivity(), UserFans.class, new CountListener() {
            @Override
            public void onSuccess(int count) {
                mFansNum.setText(count+"");
            }
            @Override
            public void onFailure(int code, String msg) {
                // TODO Auto-generated method stub
            }
        });

        mLogout.setOnClickListener(this);
        mAttention.setOnClickListener(this);
        mFans.setOnClickListener(this);
        mDynamic.setOnClickListener(this);
        mSendGroup.setOnClickListener(this);
        mMyInfo.setOnClickListener(this);
        mMyOrder.setOnClickListener(this);
        mSetttingCenter.setOnClickListener(this);

        if (user.getAvatar() == null) {
            mAvatar.setImageResource(R.mipmap.default_avatar);
        } else {
            ImageLoader.getInstance().displayImage(
                    user.getAvatar().getFileUrl(getActivity()), mAvatar);
        }
        if(user.getNickName() == null || user.getNickName().trim().equals("")) {

            mNickname.setText(user.getMobilePhoneNumber());
        }else {
            mNickname.setText(user.getNickName());

        }
        if(user.getSignature() == null || user.getSignature().trim().equals("")) {

            mSignature.setText("暂无签名");
        }else {
            mSignature.setText(user.getSignature());

        }
        mDynamicNum.setText(user.getUserDynamicNum() + "");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_logout: // 退出登录
                ShareProUtil.getInstance(getActivity()).putValue(SharePreferenceConstant.IS_LOGIN, true);
                BmobUser.logOut(getActivity());
                // 启动登陆界面
                getActivity().finish();
                intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_send_group: // 我要发团
                intent = new Intent(getActivity(), CustomGroupActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_my_info: // 我的资料

                intent = new Intent(getActivity(), MyInfoActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_my_order: // 我的订单

                intent = new Intent(getActivity(), MyOrderActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_setting_center: // 设置中心

                intent = new Intent(getActivity(), SettingCenterActivity.class);
                startActivity(intent);
                break;

            case R.id.layout_attention:   //显示关注用户列表
                intent = new Intent(getActivity(), FriendsListActivity.class);
                intent.putExtra("flag", "attention");
                startActivity(intent);
                break;

            case R.id.layout_fans:     //显示粉丝用户列表
                intent = new Intent(getActivity(), FriendsListActivity.class);
                intent.putExtra("flag", "fans");
                startActivity(intent);
                break;

            case R.id.layout_dynamic:  //显示我的所有动态
//                intent = new Intent(getActivity(), MyDynamicActivity.class);
//                startActivity(intent);
                break;

        }
    }
}
