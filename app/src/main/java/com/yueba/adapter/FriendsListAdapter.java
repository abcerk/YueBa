package com.yueba.adapter;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yueba.R;
import com.yueba.base.MyApplication;
import com.yueba.entity.User;
import com.yueba.entity.UserAttentions;
import com.yueba.entity.UserFans;
import com.yueba.utils.ViewHolder;
import com.yueba.widget.NoScrollGridView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FriendsListAdapter extends BaseAdapter{

	private Context context;
	private List<UserAttentions> attentionList;
	private List<UserFans> fansList;
	private String userId;
	
	
	public FriendsListAdapter(Context context,
			List<UserAttentions> attentionList, List<UserFans> fansList) {
		this.context = context;
		this.attentionList = attentionList;
		this.fansList = fansList;
	}

	public void updateAttentionAdapter(List<UserAttentions> list) {
		this.attentionList = list;
		notifyDataSetChanged();
	}
	
	public void updateFansAdapter(List<UserFans> list) {
		this.fansList = list;
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		
		if(attentionList!=null) {
			Log.d("yueba:", "-----"+attentionList.size());
			return attentionList.size();
			
		}else {
			return fansList.size();
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if(attentionList!=null) {
			return attentionList.get(position);
		}else {
			return fansList.get(position);
		}
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
					R.layout.item_friends_list, null);
		}
		final ImageView avatar = ViewHolder.get(convertView,
				R.id.iv_item_friends_list_avatar);
		final TextView nickname = ViewHolder.get(convertView,
				R.id.tv_item_friends_list_nickname);
		final TextView signature = ViewHolder.get(convertView,
				R.id.tv_item_friends_list_signature);
		
		if(attentionList!=null) {
			
			UserAttentions userAttention = attentionList.get(position);
			userId = userAttention.getAttentionUser().getObjectId();
			
		}else {
			
			UserFans userFans = fansList.get(position);
			userId = userFans.getFans().getObjectId();
		}
		
		BmobQuery<User> query = new BmobQuery<User>();
		query.getObject(context, userId, new GetListener<User>() {
			
			@Override
			public void onSuccess(User user) {
				if (user.getAvatar() != null) {
					ImageLoader.getInstance().displayImage(
							user.getAvatar().getFileUrl(context),
							avatar,
							MyApplication.getInstance().getOptions(
									R.mipmap.bg_pic_loading));
				} else {
					avatar.setImageResource(R.mipmap.default_avatar);
				}
				
				nickname.setText(user.getNickName());
				if(user.getGender().equals("男")) {
					Drawable drawable = context.getResources().getDrawable(
							R.mipmap.ic_sex_boy);
					drawable.setBounds(0, 0, 32, 32);
					nickname.setCompoundDrawables(null, null, drawable, null);
				}else{
					Drawable drawable = context.getResources().getDrawable(
							R.mipmap.ic_sex_girl);
					drawable.setBounds(0, 0, 32, 32);
					nickname.setCompoundDrawables(null, null, drawable, null);
				}
				signature.setText(user.getSignature()==null?"":user.getSignature());
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
		});
		
		return convertView;
	}

}
