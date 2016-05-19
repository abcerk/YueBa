package com.yueba.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yueba.R;
import com.yueba.base.BaseActivity;

public class LoginActivity extends BaseActivity implements OnClickListener {

	private TextView tvTitleName;
	private Button mLogin;
	private EditText mAccount, mPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		initView();
	}

	private void initView() {
		tvTitleName = (TextView) findViewById(R.id.titleText);
		mLogin = (Button) findViewById(R.id.btn_login);
		mAccount = (EditText) findViewById(R.id.edt_account);
		mPassword = (EditText) findViewById(R.id.edt_password);
		tvTitleName.setText("用户登录");
		mLogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.btn_login:
			startActivity(new Intent(this, MainActivity.class));
			break;
		}

	}

}
