package com.yueba.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.yueba.R;
import com.yueba.adapter.AlbumGridViewAdapter;
import com.yueba.base.BaseActivity;
import com.yueba.base.MyApplication;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class MultiPicSelectActivity extends BaseActivity implements View.OnClickListener {

    private GridView gridView;
    private ArrayList<String> dataList = new ArrayList<String>();
    private HashMap<String, ImageView> hashMap = new HashMap<String, ImageView>();
    private ArrayList<String> selectedDataList = new ArrayList<String>();
    private ProgressBar progressBar;
    private AlbumGridViewAdapter gridImageAdapter;
    private LinearLayout selectedImageLayout;
    private HorizontalScrollView scrollview;
    private TextView tvTitle, tvRight;
    private int size = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_pic_select);
        Intent intent = getIntent();
        size = intent.getIntExtra("num", 0);
        selectedDataList = new ArrayList<String>();
        init();
        initListener();
    }

    private void init() {
        tvTitle = (TextView) findViewById(R.id.titleText);
        tvRight = (TextView) findViewById(R.id.tv_publish);
        tvRight.setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.multi_pic_select_progressbar);
        progressBar.setVisibility(View.GONE);
        gridView = (GridView) findViewById(R.id.multi_pic_select_myGrid);
        gridImageAdapter = new AlbumGridViewAdapter(this, dataList,
                selectedDataList);
        gridView.setAdapter(gridImageAdapter);
        refreshData();
        selectedImageLayout = (LinearLayout) findViewById(R.id.multi_pic_selected_image_layout);
        scrollview = (HorizontalScrollView) findViewById(R.id.multi_pic_select_scrollview);
        initSelectImage();
    }

    private void initSelectImage() {
        if (selectedDataList == null)
            return;
        for (final String path : selectedDataList) {
            ImageView imageView = (ImageView) LayoutInflater.from(
                    MultiPicSelectActivity.this).inflate(
                    R.layout.item_choose_imageview, selectedImageLayout, false);
            selectedImageLayout.addView(imageView);
            hashMap.put(path, imageView);
            ImageSize imageSize = new ImageSize(100, 100);
            ImageLoader.getInstance().displayImage(path, imageView, imageSize);
            imageView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    removePath(path);
                    gridImageAdapter.notifyDataSetChanged();
                }
            });
        }
        setTitleBar("图片选择(" + selectedDataList.size() + "/" + size + ")", "完成");
    }

    private void setTitleBar(String title, String right) {
        tvTitle.setText(title);
        tvRight.setText(right);

    }

    private void initListener() {

        gridImageAdapter
                .setOnItemClickListener(new AlbumGridViewAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(final ToggleButton toggleButton,
                                            int position, final String path, boolean isChecked) {
                        if (selectedDataList.size() >= size) {
                            toggleButton.setChecked(false);
                            if (!removePath(path)) {
                                Toast.makeText(MultiPicSelectActivity.this,
                                        "只能选择" + size + "张图片",
                                        Toast.LENGTH_SHORT).show();
                            }
                            return;
                        }
                        ViewGroup pGroup = (ViewGroup) toggleButton.getParent();
                        final ImageView check = (ImageView) pGroup
                                .findViewById(R.id.check);
                        if (isChecked) {
                            check.setVisibility(View.VISIBLE);
                            if (!hashMap.containsKey(path)) {
                                ImageView imageView = (ImageView) LayoutInflater
                                        .from(MultiPicSelectActivity.this)
                                        .inflate(
                                                R.layout.item_choose_imageview,
                                                selectedImageLayout, false);
                                selectedImageLayout.addView(imageView);
                                imageView.postDelayed(new Runnable() {

                                    @Override
                                    public void run() {

                                        int off = selectedImageLayout
                                                .getMeasuredWidth()
                                                - scrollview.getWidth();
                                        if (off > 0) {
                                            scrollview.smoothScrollTo(off, 0);
                                        }
                                    }
                                }, 100);

                                hashMap.put(path, imageView);
                                selectedDataList.add(path);
                                // ImageManager.from(MultiPicSelectActivity.this).displayImage(imageView,
                                // path,R.drawable.empty_photo,100,100);
                                ImageLoader.getInstance().displayImage(
                                        "file://" + path,
                                        imageView,
                                        MyApplication.getInstance().getOptions(
                                                R.mipmap.empty_photo));
                                imageView
                                        .setOnClickListener(new View.OnClickListener() {

                                            @Override
                                            public void onClick(View v) {
                                                toggleButton.setChecked(false);
                                                removePath(path);
                                                check.setVisibility(View.GONE);
                                            }
                                        });
                                setTitleBar("图片选择(" + selectedDataList.size() + "/" + size + ")",
                                        "完成");
                            }
                        } else {
                            check.setVisibility(View.GONE);
                            removePath(path);
                        }
                    }
                });
    }

    private boolean removePath(String path) {
        if (hashMap.containsKey(path)) {
            selectedImageLayout.removeView(hashMap.get(path));
            hashMap.remove(path);
            removeOneData(selectedDataList, path);
            setTitleBar("图片选择(" + selectedDataList.size() + "/" + size + ")",
                    "完成");
            return true;
        } else {
            return false;
        }
    }

    private void removeOneData(ArrayList<String> arrayList, String s) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).equals(s)) {
                arrayList.remove(i);
                return;
            }
        }
    }

    private void refreshData() {

        new AsyncTask<Void, Void, ArrayList<String>>() {

            @Override
            protected void onPreExecute() {
                progressBar.setVisibility(View.VISIBLE);
                super.onPreExecute();
            }

            @Override
            protected ArrayList<String> doInBackground(Void... params) {
                ArrayList<String> listDirlocal = listAlldir();
                return listDirlocal;
            }

            protected void onPostExecute(ArrayList<String> tmpList) {

                if (MultiPicSelectActivity.this == null
                        || MultiPicSelectActivity.this.isFinishing()) {
                    return;
                }
                progressBar.setVisibility(View.GONE);
                dataList.clear();
                dataList.addAll(tmpList);
                gridImageAdapter.notifyDataSetChanged();
                return;

            }

            ;

        }.execute();

    }

    /**
     * 获取图库图片所有路径
     *
     * @return
     */
    private ArrayList<String> listAlldir() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        Uri uri = intent.getData();
        ArrayList<String> list = new ArrayList<String>();
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, proj, null, null, null);
        while (cursor.moveToNext()) {
            String path = cursor.getString(0);
            list.add(new File(path).getAbsolutePath());
        }
        return list;
    }

    @Override
    public void onBackPressed() {
        finish();
        // super.onBackPressed();
    }

    @Override
    public void finish() {
        // TODO Auto-generated method stub
        super.finish();
        // ImageManager.from(AlbumActivity.this).recycle(dataList);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // ContentResolver resolver = getContentResolver();
        if (requestCode == 1) {
            if (data != null) {
                Uri originalUri = data.getData();
                try {
                    // byte[]
                    // mContent=SelectPicUtil.readStream(resolver.openInputStream(Uri.parse(originalUri.toString())));
                    String[] proj = {MediaColumns.DATA};
                    Cursor actualimagecursor = managedQuery(originalUri, proj,
                            null, null, null);
                    int actual_image_column_index = actualimagecursor
                            .getColumnIndexOrThrow(MediaColumns.DATA);
                    actualimagecursor.moveToFirst();
                    String picAddr = actualimagecursor
                            .getString(actual_image_column_index);
                    selectedDataList.add(picAddr);
                    initSelectImage();
                } catch (Exception e) {
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_publish:
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("dataList", selectedDataList);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}
