package com.yueba.activity;

import android.os.PersistableBundle;
import android.os.Bundle;

import com.yueba.R;
import com.yueba.base.BaseActivity;

public class MyInfoActivity extends BaseActivity {


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_my_info);
    }
}
