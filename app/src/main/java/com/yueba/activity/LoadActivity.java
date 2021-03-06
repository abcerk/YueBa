package com.yueba.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

import com.yueba.R;
import com.yueba.base.MyApplication;
import com.yueba.constant.SharePreferenceConstant;
import com.yueba.entity.User;
import com.yueba.utils.ShareProUtil;

import cn.bmob.v3.BmobUser;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class LoadActivity extends Activity {

    private ImageView mLoadImage;
    private boolean isLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_load);
        //判断用户是否登录
        isLogin = BmobUser.getCurrentUser(this) != null &&
                ShareProUtil.getInstance(this).getBooleanValue(SharePreferenceConstant.IS_LOGIN, false);
        mLoadImage = (ImageView) findViewById(R.id.id_load_image);
        AlphaAnimation animation = new AlphaAnimation(0.5f, 1.0f);
        animation.setDuration(2000);
        mLoadImage.startAnimation(animation);

        animation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if (isLogin) {
                    startActivity(new Intent(LoadActivity.this, MainActivity.class));
                    LoadActivity.this.finish();
                } else {
                    LoadActivity.this.finish();
                    Intent intent = new Intent(LoadActivity.this,
                            LoginActivity.class);
                    startActivity(intent);
                    LoadActivity.this.finish();
                }

            }

        });


    }

}
