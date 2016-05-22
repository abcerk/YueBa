package com.yueba.widget;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yueba.R;
import com.yueba.activity.LoginActivity;
import com.yueba.base.MyApplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

/**
 * ViewPager实现的轮播图自定义视图
 *
 */
public class ImageScrollView extends FrameLayout {

	// private int[] imageResIds = { R.drawable.a, R.drawable.b, R.drawable.c,
	// R.drawable.d }; // 图片资源ID

	private List<ImageView> imageViewList; // 轮播图片的imageview
	private List<View> dotViewList;// 轮播图片下方 点imageview
	private ViewPager viewPager;
	private Context context;
	private ArrayList<String> imageUrls;
	private int currentItem = 0; // 当前显示的图片位置
	private boolean flag = false; // 判断是否为引导界面

	private ScheduledExecutorService scheduledExecutorService; // 定时任务

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			setCurrentItem();
		}

	};

	public ImageScrollView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	public ImageScrollView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	public ImageScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		this.context = context;
		imageViewList = new ArrayList<ImageView>();
		dotViewList = new ArrayList<View>();
	}

	/**
	 * 处理Page的切换逻辑
	 */
	private void setCurrentItem() {

		if (currentItem == imageViewList.size()) {
			currentItem = 0;
		}
		viewPager.setCurrentItem(currentItem, false);// 取消动画s
	}

	/**
	 * 开始轮播切换图片
	 */
	private void startPlay() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleAtFixedRate(new ImageScrollTask(), 3,
				4, TimeUnit.SECONDS);
	}

	/**
	 * 停止轮播切换图片
	 */
	private void stopPlay() {
		scheduledExecutorService.shutdown();
	}

	public void initView(Context context, ArrayList<String> imageUrls) {

		flag = false;
		LayoutInflater.from(context).inflate(R.layout.widget_scroll_image,
				this, true);
		this.imageUrls = imageUrls;
		// for (int imageId : imageResIds) {
		// ImageView imageView = new ImageView(context);
		// imageView.setImageResource(imageId);
		// imageView.setScaleType(ScaleType.FIT_XY);
		// imageViewList.add(imageView);
		// }
		for (String image : imageUrls) {
			ImageView imageView = new ImageView(context);
			ImageLoader.getInstance().displayImage(
					image,
					imageView,
					MyApplication.getInstance().getOptions(
							R.mipmap.bg_pic_loading));
			imageView.setScaleType(ScaleType.FIT_XY);
			imageViewList.add(imageView);
		}

		dotViewList.add(findViewById(R.id.v_dot1));
		dotViewList.add(findViewById(R.id.v_dot2));
		dotViewList.add(findViewById(R.id.v_dot3));
		dotViewList.add(findViewById(R.id.v_dot4));

		viewPager = (ViewPager) findViewById(R.id.view_pager);
		viewPager.setFocusable(true);

		viewPager.setAdapter(new MyPagerAdapter());
		viewPager.setOnPageChangeListener(new MyPagerChangeListener());

		startPlay();
	}

	public void initView(Context context, int[] imageResIds) {

		flag = true;
		LayoutInflater.from(context).inflate(
				R.layout.widget_scroll_image_guide, this, true);
		for (int imageId : imageResIds) {
			ImageView imageView = new ImageView(context);
			imageView.setImageResource(imageId);
			imageView.setScaleType(ScaleType.FIT_XY);
			imageViewList.add(imageView);
		}

		dotViewList.add(findViewById(R.id.v_dot1));
		dotViewList.add(findViewById(R.id.v_dot2));
		dotViewList.add(findViewById(R.id.v_dot3));
		dotViewList.add(findViewById(R.id.v_dot4));

		viewPager = (ViewPager) findViewById(R.id.view_pager);
		viewPager.setFocusable(true);

		viewPager.setAdapter(new MyPagerAdapter());
		viewPager.setOnPageChangeListener(new MyPagerChangeListener());
	}

	/**
	 * ViewPager的适配器
	 *
	 */
	private class MyPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imageViewList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			// TODO Auto-generated method stub
			((ViewPager) container).removeView(imageViewList.get(position
					% imageViewList.size()));
		}

		@Override
		public Object instantiateItem(View container, int position) {
			// TODO Auto-generated method stub
			((ViewGroup) container).addView(imageViewList.get(position
					% imageViewList.size()));
			if (!flag) {
//				// 为图片做点击事件
//				final int pos = position % imageViewList.size();
//				imageViewList.get(position % imageViewList.size())
//						.setOnClickListener(new OnClickListener() {
//
//							@Override
//							public void onClick(View v) {
//								// TODO Auto-generated method stub
//								Intent intent = new Intent(context,
//										ImageBrowserActivity.class);
//								Bundle bundle = new Bundle();
//								bundle.putStringArrayList("imageUrls",
//										imageUrls);
//								bundle.putInt("position", pos);
//								intent.putExtras(bundle);
//								context.startActivity(intent);
//							}
//						});
			}
			return imageViewList.get(position % imageViewList.size());

		}

	}

	/**
	 * ViewPager监听器
	 *
	 */
	private class MyPagerChangeListener implements OnPageChangeListener {

		private int oldPosition = 0;

		/* state: 0空闲，1是滑行中，2加载完毕 */
		@Override
		public void onPageScrollStateChanged(int state) {
			// TODO Auto-generated method stub

			switch (state) {
				case 1:
					if (!flag) {
						stopPlay();
					}
					break;
				case 0:
					setCurrentItem();
					if (!flag) {
						startPlay();
					}
					break;
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int position) {
			// TODO Auto-generated method stub

			currentItem = position;
			dotViewList.get(oldPosition).setBackgroundResource(
					R.mipmap.dot_gray);
			dotViewList.get(position).setBackgroundResource(R.mipmap.dot_red);
			oldPosition = position;

		}

	}

	/**
	 * 轮播切换图片任务
	 */
	private class ImageScrollTask implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			synchronized (viewPager) {
				currentItem++;
				handler.obtainMessage().sendToTarget();
			}
		}

	}

}
