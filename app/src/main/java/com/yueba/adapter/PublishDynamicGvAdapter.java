package com.yueba.adapter;

import java.util.List;
import java.util.Map;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yueba.R;
import com.yueba.activity.MultiPicSelectActivity;
import com.yueba.utils.ViewHolder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class PublishDynamicGvAdapter extends BaseAdapter {

	private static final int MULTI_PICTURE_SELECT = 1;
	private Context context;
	private List<String> list;

	public PublishDynamicGvAdapter(Context context, List<String> list) {
		this.context = context;
		this.list = list;
	}

	/**
	 * 通知adapter数据进行更新
	 *
	 * @param list
	 */
	public void updateAdapter(List<String> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list == null ? 1 : list.size() + 1;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_grid_view_adapter, null);
		}

		ImageView mGvImage = (ImageView) ViewHolder.get(convertView,
				R.id.iv_img_gv_pic);

		if(list != null && position <list.size()) {
			ImageLoader.getInstance().displayImage("file://" + list.get(position),
					mGvImage);
		}else {
			//添加的按钮
			mGvImage.setScaleType(ScaleType.FIT_XY);
			mGvImage.setImageResource(R.mipmap.ic_add_photo);
			mGvImage.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent(context,
							MultiPicSelectActivity.class);
					intent.putExtra("num", 9 - list.size());
					((Activity) context).startActivityForResult(intent, MULTI_PICTURE_SELECT);
				}
			});
		}


		return convertView;
	}

}
