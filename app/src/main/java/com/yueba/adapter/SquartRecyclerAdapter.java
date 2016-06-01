package com.yueba.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yueba.R;
import com.yueba.base.MyApplication;
import com.yueba.entity.SpotInfo;
import com.yueba.entity.User;
import com.yueba.entity.UserDynamic;
import com.yueba.widget.NoScrollGridView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;

/**
 * Created by abcerk on 2016/5/22.
 */
public class SquartRecyclerAdapter extends RecyclerView.Adapter {

    private List<UserDynamic> list;
    private OnItemClickListener mListener;
    private Context context;
    private User user;

    public void setOnItemClickListener(OnItemClickListener listener) {

        this.mListener = listener;
    }

    public SquartRecyclerAdapter(Context context, List<UserDynamic> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_squart_listview, parent, false);
        return new HomeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final HomeViewHolder viewHolder = (HomeViewHolder) holder;
        final UserDynamic userDynamic = list.get(position);

        // 获取发布该动态的用户信息
        BmobQuery<UserDynamic> query = new BmobQuery<UserDynamic>();
        query.include("user");
        query.getObject(context, userDynamic.getObjectId(),
                new GetListener<UserDynamic>() {

                    @Override
                    public void onSuccess(UserDynamic arg0) {
                        user = arg0.getUser();
                        viewHolder.tvUserName.setText(user.getNickName());
                        // 设置头像及点击头像显示个人资料
                        if (user.getAvatar() != null) {
                            ImageLoader.getInstance().displayImage(
                                    user.getAvatar().getFileUrl(context),
                                    viewHolder.ivUserAvatar);
                        } else {
                            viewHolder.ivUserAvatar.setImageResource(R.drawable.rc_default_portrait);
                        }
                    }

                    @Override
                    public void onFailure(int arg0, String arg1) {
                    }
                });
        viewHolder.tvUserDynamicTime.setText(userDynamic.getTime());
        viewHolder.tvUserDynamicContent.setText(userDynamic.getContent());
        // 九宫格显示用户上传的图片
        ArrayList<String> imageUrls = userDynamic.getImageUrls();

        if (imageUrls != null) {
            viewHolder.gvDynamicImages.setVisibility(View.VISIBLE);
            // 设置gridview的适配器
            SquartGvAdapter gvAdapter = new SquartGvAdapter(context, imageUrls);
            viewHolder.gvDynamicImages.setAdapter(gvAdapter);
        } else {
            viewHolder.gvDynamicImages.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        } else {

            return list.size();
        }
    }

    public interface OnItemClickListener {

        void OnItemClick(int position);
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {

        ImageView ivUserAvatar;
        TextView tvUserName, tvUserDynamicTime, tvUserDynamicContent;
        NoScrollGridView gvDynamicImages;

        public HomeViewHolder(View itemView) {
            super(itemView);
            ivUserAvatar = (ImageView) itemView.findViewById(R.id.iv_item_squart_avatar);
            tvUserName = (TextView) itemView.findViewById(R.id.tv_item_squart_nickname);
            tvUserDynamicTime = (TextView) itemView.findViewById(R.id.tv_item_squart_time);
            tvUserDynamicContent = (TextView) itemView.findViewById(R.id.tv_item_squart_content);
            gvDynamicImages = (NoScrollGridView) itemView.findViewById(R.id.gv_user_dynamic_images);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.OnItemClick(getPosition());
                    }
                }
            });
        }
    }
}
