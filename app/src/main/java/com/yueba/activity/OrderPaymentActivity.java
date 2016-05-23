package com.yueba.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.yueba.R;
import com.yueba.base.BaseActivity;

public class OrderPaymentActivity extends BaseActivity {

    private TextView tvTitleName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_payment);
        tvTitleName = (TextView) findViewById(R.id.titleText);
        tvTitleName.setText("订单详情");

    }
}
