package com.yueba.adapter;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yueba.R;
import com.yueba.base.MyApplication;
import com.yueba.utils.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter{
	
	private Context context;
	private int[] imageIds;  
	
	

	public ImageAdapter(Context context, int[] imageIds) {
		this.context = context;
		this.imageIds = imageIds;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imageIds.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
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

		ImageView imageView = (ImageView) ViewHolder.get(convertView,
				R.id.iv_img_gv_pic);
        imageView.setImageResource(imageIds[position]);
        return convertView;
	}


}
