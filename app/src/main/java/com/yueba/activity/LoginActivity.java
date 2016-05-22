package com.yueba.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yueba.R;
import com.yueba.base.BaseActivity;
import com.yueba.constant.AppConstant;
import com.yueba.constant.SharePreferenceConstant;
import com.yueba.entity.User;
import com.yueba.utils.ShareProUtil;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.LogInListener;

public class LoginActivity extends BaseActivity implements OnClickListener {

    private TextView tvTitleName, mGetCode;
    private Button mLogin;
    private EditText edtLoginPhone, edtLoginCode;
    private String phone;
    private Context context;
    private static final String TAG = "LoginActivity";
    //发送验证码倒计时
    private Handler getCodeHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mGetCode.setText(msg.what + "秒后重新获取");
            if (msg.what == 0) {
                mGetCode.setEnabled(true);
                mGetCode.setText("重新发送");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        initView();
    }

    private void initView() {
        tvTitleName = (TextView) findViewById(R.id.titleText);
        mLogin = (Button) findViewById(R.id.btn_login);
        edtLoginPhone = (EditText) findViewById(R.id.edt_login_phone);
        edtLoginCode = (EditText) findViewById(R.id.edt_login_code);
        mGetCode = (TextView) findViewById(R.id.tv_login_get_code);
        tvTitleName.setText("用户登录");
        /**
         *输入了6位验证码时关闭输入法
         */
        edtLoginPhone.setInputType(InputType.TYPE_CLASS_NUMBER);
        edtLoginCode.setInputType(InputType.TYPE_CLASS_NUMBER);
        edtLoginCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                if (arg0.length() == 6) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });
        mLogin.setOnClickListener(this);
        mGetCode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_login:
                BmobUser.signOrLoginByMobilePhone(context, phone, edtLoginCode.getText() + "", new LogInListener<User>() {

                    @Override
                    public void done(User user, cn.bmob.v3.exception.BmobException e) {
                        if(user != null) {
                            context.startActivity(new Intent(context, MainActivity.class));
                            LoginActivity.this.finish();
                            ShareProUtil.getInstance(context).putValue(SharePreferenceConstant.IS_LOGIN, true);
                        }

                    }

                });

                break;
            case R.id.tv_login_get_code: //获取验证码
                Log.e(TAG, "click");
                phone = edtLoginPhone.getText().toString();
                if (!TextUtils.isEmpty(phone)) {
                    if (phone.length() != 11 || phone.charAt(0) != '1') {
                        Toast.makeText(context, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    //发送验证码
                    BmobSMS.requestSMSCode(context, phone, AppConstant.BMOB_SMS_TEMPLATE, new RequestSMSCodeListener() {
                        @Override
                        public void done(Integer integer, BmobException e) {
                            Toast.makeText(context, "验证码发送成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                    //验证码输入框获取焦点
                    edtLoginCode.requestFocus();
                    mGetCode.setEnabled(false);
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            int count = 300;
                            for (int i = count; i >= 0; i--) {
                                try {
                                    Thread.sleep(1000);
                                    getCodeHandle.sendEmptyMessage(i);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }.start();
                } else {
                    Toast.makeText(context, "手机号码不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

}
