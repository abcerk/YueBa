package com.yueba.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yueba.R;
import com.yueba.base.BaseActivity;
import com.yueba.entity.SpotInfo;
import com.yueba.widget.ImageScrollView;

import java.util.ArrayList;

public class SpotDetailInfoActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mCallPhone;
    private Intent intent;
    private View mSpotComment, mSpotGuide, mSpotEnrollInfo, mSpotGame, mSpotChatroom;
    private TextView tvTitleName, mSpotName, mSpotPrice, mDeletePrice;
    private SpotInfo spotInfo;
    private Button mEnroll;
    private ImageScrollView mImageScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_detail_info);
        tvTitleName = (TextView) findViewById(R.id.titleText);
        tvTitleName.setText("景点详情");
        Intent intent = getIntent();
        spotInfo = (SpotInfo) intent.getSerializableExtra("spot");

        initView();

    }

    private void initView() {
        // TODO Auto-generated method stub
        mCallPhone = (ImageView) findViewById(R.id.iv_spot_call_phone);
        mSpotComment = findViewById(R.id.layout_spot_comment);
        mSpotName = (TextView) findViewById(R.id.tv_spot_title);
        mSpotPrice = (TextView) findViewById(R.id.tv_spot_price);
        mEnroll = (Button) findViewById(R.id.btn_spot_enroll);
        mImageScrollView = (ImageScrollView) findViewById(R.id.image_scroll_view);
        mSpotChatroom = findViewById(R.id.layout_spot_chatroom);
        mSpotEnrollInfo = findViewById(R.id.layout_spot_enroll_info);
        mSpotGame = findViewById(R.id.layout_spot_game);
        mSpotGuide = findViewById(R.id.layout_spot_guide);

        //为原价钱添加删除线
        mDeletePrice = (TextView) findViewById(R.id.tv_delete_price);
        mDeletePrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        ArrayList<String> imageUrls = new ArrayList<String>();
        imageUrls.add(spotInfo.getImage1().getFileUrl(this));
        imageUrls.add(spotInfo.getImage2().getFileUrl(this));
        imageUrls.add(spotInfo.getImage3().getFileUrl(this));
        imageUrls.add(spotInfo.getImage4().getFileUrl(this));
        //传送将要显示的图片urls
        mImageScrollView.initView(this, imageUrls);

        mEnroll.setOnClickListener(this);
        mCallPhone.setOnClickListener(this);
        mSpotComment.setOnClickListener(this);
        mSpotChatroom.setOnClickListener(this);
        mSpotEnrollInfo.setOnClickListener(this);
        mSpotGame.setOnClickListener(this);
        mSpotGuide.setOnClickListener(this);

        mSpotName.setText(spotInfo.getSpotName());
//		mSpotDescription.setText(spotInfo.getDescription());


    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.iv_spot_call_phone: // 拨打咨询电话
                intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:13888888888"));
                startActivity(intent);
                break;
            case R.id.layout_spot_comment: //打开景点评论界面
//                intent = new Intent(SpotDetailInfoActivity.this, SquareCommentActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("activity_flag", "SpotComment");
//                intent.putExtras(bundle);
//                startActivity(intent);
                break;
            case R.id.layout_spot_chatroom:
//                RongIM.getInstance().startChatroom(SpotDetailInfoActivity.this, spotInfo.getObjectId(), spotInfo.getSpotName()+"聊天室");
                break;
            case R.id.layout_spot_enroll_info:
//                intent = new Intent(SpotDetailInfoActivity.this, EnrollUserDistributionActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_spot_guide:
//                intent = new Intent(SpotDetailInfoActivity.this, SpotGuideActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_spot_game:
//                intent = new Intent(SpotDetailInfoActivity.this, SpotGameActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_spot_enroll:
//                intent = new Intent(SpotDetailInfoActivity.this, PaymentOrderActivity.class);
                startActivity(intent);
                break;
        }
    }
}