package com.yueba.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.yueba.R;
import com.yueba.adapter.ImageAdapter;
import com.yueba.base.BaseActivity;
import com.yueba.widget.NoScrollGridView;

public class SpotGuideActivity extends BaseActivity implements
        OnItemClickListener {

    private NoScrollGridView mSpotFoodImage, mSpotSiteImage,
            mSpotAccommodationImage, mSpotTrafficImage;
    private TextView tvTitleName;
    private int[] foodImages = { R.mipmap.food01, R.mipmap.food02,
            R.mipmap.food03, R.mipmap.food04, R.mipmap.food05,
            R.mipmap.food06 };
    private int[] activityImages = { R.mipmap.activity01,
            R.mipmap.activity02, R.mipmap.activity03,
            R.mipmap.activity04, R.mipmap.activity05, R.mipmap.activity06 };
    private int[] siteImages = { R.mipmap.site01, R.mipmap.site02,
            R.mipmap.site03, R.mipmap.site04, R.mipmap.site05,
            R.mipmap.site06, R.mipmap.site07, R.mipmap.site08,
            R.mipmap.site09 };
    private int[] accommodationImages = { R.mipmap.zs01, R.mipmap.zs02,
            R.mipmap.zs03, R.mipmap.zs04, R.mipmap.zs05 };
    private int[] trafficImages = { R.mipmap.lydb01, R.mipmap.lydb02,
            R.mipmap.lydb03 };
    private ImageAdapter foodAdapter, activityAdapter, siteAdapter,
            accommodationAdapter, trafficAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_guide);

        initView();

    }

    private void initView() {
        tvTitleName = (TextView) findViewById(R.id.titleText);
        tvTitleName.setText("景点攻略");

        mSpotFoodImage = (NoScrollGridView) findViewById(R.id.gv_spot_food);
        mSpotSiteImage = (NoScrollGridView) findViewById(R.id.gv_spot_site);
        mSpotAccommodationImage = (NoScrollGridView) findViewById(R.id.gv_spot_accommodation);
        mSpotTrafficImage = (NoScrollGridView) findViewById(R.id.gv_spot_traffic);

        mSpotFoodImage.setOnItemClickListener(this);
        mSpotSiteImage.setOnItemClickListener(this);
        mSpotAccommodationImage.setOnItemClickListener(this);
        mSpotTrafficImage.setOnItemClickListener(this);

        foodAdapter = new ImageAdapter(this, foodImages);
        siteAdapter = new ImageAdapter(this, siteImages);
        accommodationAdapter = new ImageAdapter(this, accommodationImages);
        trafficAdapter = new ImageAdapter(this, trafficImages);

        mSpotFoodImage.setAdapter(foodAdapter);
        mSpotSiteImage.setAdapter(siteAdapter);
        mSpotAccommodationImage.setAdapter(accommodationAdapter);
        mSpotTrafficImage.setAdapter(trafficAdapter);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
//        Intent intent = new Intent(this, ImageBrowserActivity2.class);
//        Bundle bundle = new Bundle();
//        switch (parent.getId()) {
//
//            case R.id.gv_spot_food:
//                bundle.putInt("position", position);
//                bundle.putIntArray("images", foodImages);
//                intent.putExtras(bundle);
//                break;
//            case R.id.gv_spot_site:
//                bundle.putInt("position", position);
//                bundle.putIntArray("images", siteImages);
//                intent.putExtras(bundle);
//                break;
//            case R.id.gv_spot_accommodation:
//                bundle.putInt("position", position);
//                bundle.putIntArray("images", accommodationImages);
//                intent.putExtras(bundle);
//                break;
//            case R.id.gv_spot_traffic:
//                bundle.putInt("position", position);
//                bundle.putIntArray("images", trafficImages);
//                intent.putExtras(bundle);
//                break;
//
//        }
//
//        startActivity(intent);

    }

}
