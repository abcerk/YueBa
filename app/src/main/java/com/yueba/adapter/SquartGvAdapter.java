package com.yueba.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yueba.R;
import com.yueba.base.MyApplication;
import com.yueba.utils.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class SquartGvAdapter extends BaseAdapter {

	private List<String> imageUrls;
	private Context context;

	public SquartGvAdapter(Context context, List<String> imageUrls) {

		this.context = context;
		this.imageUrls = imageUrls;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imageUrls.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return imageUrls.get(position);
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
		ImageLoader.getInstance().displayImage(
				imageUrls.get(position),
				mGvImage);

		return convertView;
	}

}
