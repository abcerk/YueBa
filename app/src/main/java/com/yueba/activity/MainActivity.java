package com.yueba.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yueba.R;
import com.yueba.fragment.HomeFragment;
import com.yueba.fragment.MessageFragment;
import com.yueba.fragment.SquartFragment;
import com.yueba.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnClickListener {


    private static final int TAB_HOME = 0;
    private static final int TAB_SQUART = 1;
    private static final int TAB_MESSAGE = 2;
    private static final int TAB_USER = 3;

    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;

    private LinearLayout mTabHome;
    private LinearLayout mTabSquart;
    private LinearLayout mTabHot;
    private LinearLayout mTabUser;

    private ImageView mImageHome;
    private ImageView mImageSquart;
    private ImageView mImageHot;
    private ImageView mImageUser;

    private TextView mTextHome;
    private TextView mTextSquart;
    private TextView mTextHot;
    private TextView mTextUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        mTabHome = (LinearLayout) findViewById(R.id.id_tab_home);
        mTabSquart = (LinearLayout) findViewById(R.id.id_tab_squart);
        mTabHot = (LinearLayout) findViewById(R.id.id_tab_hot);
        mTabUser = (LinearLayout) findViewById(R.id.id_tab_user);

        mImageHome = (ImageView) findViewById(R.id.id_img_home);
        mImageSquart = (ImageView) findViewById(R.id.id_img_squart);
        mImageHot = (ImageView) findViewById(R.id.id_img_hot);
        mImageUser = (ImageView) findViewById(R.id.id_img_user);

        mTextHome = (TextView) findViewById(R.id.id_text_home);
        mTextSquart = (TextView) findViewById(R.id.id_text_squart);
        mTextHot = (TextView) findViewById(R.id.id_text_hot);
        mTextUser = (TextView) findViewById(R.id.id_text_user);

        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        mTabHome.setOnClickListener(this);
        mTabSquart.setOnClickListener(this);
        mTabHot.setOnClickListener(this);
        mTabUser.setOnClickListener(this);

        mFragments = new ArrayList<Fragment>();
        HomeFragment homeFragment = new HomeFragment();
        SquartFragment squartFragment = new SquartFragment();
        MessageFragment messageFragment = new MessageFragment();
        UserFragment userFragment = new UserFragment();
        mFragments.add(homeFragment);
        mFragments.add(squartFragment);
        mFragments.add(messageFragment);
        mFragments.add(userFragment);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {

                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {

                return mFragments.get(arg0);
            }
        };

        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(3);

        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {

                int currentItem = mViewPager.getCurrentItem();
                setTab(currentItem);

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.id_tab_home:
                mViewPager.setCurrentItem(TAB_HOME);
                setTab(TAB_HOME);
                break;
            case R.id.id_tab_squart:
                mViewPager.setCurrentItem(TAB_SQUART);
                setTab(TAB_SQUART);
                break;
            case R.id.id_tab_hot:
                mViewPager.setCurrentItem(TAB_MESSAGE);
                setTab(TAB_MESSAGE);
                break;
            case R.id.id_tab_user:
                mViewPager.setCurrentItem(TAB_USER);
                setTab(TAB_USER);
                break;

        }
    }

    /**
     * 更改Tab选项的图片字体颜色
     */
    private void setTab(int item) {

        mImageHome.setImageResource(R.mipmap.menu_item_home_normal);
        mImageSquart.setImageResource(R.mipmap.menu_item_squart_normal);
        mImageHot.setImageResource(R.mipmap.menu_item_hot_normal);
        mImageUser.setImageResource(R.mipmap.menu_item_user_normal);

        switch (item) {
            case TAB_HOME:

                mImageHome.setImageResource(R.mipmap.menu_item_home_selected);
                setTextColor();
                mTextHome.setTextColor(getResources().getColor(
                        R.color.tab_text_selected));
                break;
            case TAB_SQUART:

                mImageSquart.setImageResource(R.mipmap.menu_item_squart_selected);
                setTextColor();
                mTextSquart.setTextColor(getResources().getColor(
                        R.color.tab_text_selected));
                break;
            case TAB_MESSAGE:

                mImageHot.setImageResource(R.mipmap.menu_item_hot_selected);
                setTextColor();
                mTextHot.setTextColor(getResources().getColor(
                        R.color.tab_text_selected));
                break;
            case TAB_USER:

                mImageUser.setImageResource(R.mipmap.menu_item_user_selected);
                setTextColor();
                mTextUser.setTextColor(getResources().getColor(
                        R.color.tab_text_selected));
                break;
        }

    }

    /**
     * 重设字体颜色
     */
    private void setTextColor() {
        mTextHome.setTextColor(Color.BLACK);
        mTextSquart.setTextColor(Color.BLACK);
        mTextHot.setTextColor(Color.BLACK);
        mTextUser.setTextColor(Color.BLACK);
    }

}
