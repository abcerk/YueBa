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

import java.util.List;

/**
 * Created by abcerk on 2016/5/22.
 */
public class HomeRecyclerAdapter extends RecyclerView.Adapter {

    private List<SpotInfo> list;
    private OnItemClickListener mListener;
    private Context context;

    public void setOnItemClickListener(OnItemClickListener listener) {

        this.mListener = listener;
    }

    public HomeRecyclerAdapter(Context context, List<SpotInfo> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_list, parent, false);
        return new HomeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HomeViewHolder viewHolder = (HomeViewHolder) holder;
        SpotInfo spotInfo = list.get(position);
        viewHolder.tvItemName.setText(spotInfo.getSpotName());
        ImageLoader.getInstance().displayImage(
                spotInfo.getImage1() == null ? ""
                        : spotInfo.getImage1().getFileUrl(context),
                viewHolder.ivHomeItem,
                MyApplication.getInstance().getOptions(
                        R.mipmap.bg_pic_loading));
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

        ImageView ivHomeItem;
        TextView tvItemPrice, tvItemName;

        public HomeViewHolder(View itemView) {
            super(itemView);
            ivHomeItem = (ImageView) itemView.findViewById(R.id.iv_home_item_image);
            tvItemName = (TextView) itemView.findViewById(R.id.tv_home_item_name);
            tvItemPrice = (TextView) itemView.findViewById(R.id.tv_home_item_price);
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
