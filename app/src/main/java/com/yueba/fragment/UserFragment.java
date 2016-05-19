package com.yueba.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yueba.R;

public class UserFragment extends Fragment  {

	private TextView tvTitleName;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_user, container, false);
		tvTitleName = (TextView) view.findViewById(R.id.titleText);
		tvTitleName.setText("个人中心");
		return view;
	}


}
