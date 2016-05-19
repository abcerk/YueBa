package com.yueba.base;

import android.app.Application;

import com.yueba.constant.AppConstant;

import cn.bmob.v3.Bmob;

public class MyApplication extends Application {


	@Override
	public void onCreate() {
		super.onCreate();
		// 初始化 Bmob SDK
		Bmob.initialize(this, AppConstant.BMOB_KEY);

	}


}
