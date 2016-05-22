package com.yueba.base;


import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.yueba.constant.AppConstant;

import cn.bmob.v3.Bmob;

public abstract class BaseActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);

	}


}
