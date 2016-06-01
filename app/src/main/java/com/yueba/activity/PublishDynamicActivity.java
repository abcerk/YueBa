package com.yueba.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.yueba.R;
import com.yueba.adapter.PublishDynamicGvAdapter;
import com.yueba.base.BaseActivity;
import com.yueba.entity.User;
import com.yueba.entity.UserDynamic;
import com.yueba.utils.PictureUtil;
import com.yueba.utils.TimeUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadBatchListener;

public class PublishDynamicActivity extends BaseActivity implements View.OnClickListener {

    private static final int MULTI_PICTURE_SELECT = 1;
    private List<String> picturesList;
    private PublishDynamicGvAdapter adapter;
    private GridView mGridView;
    private EditText mDynamicContent;
    private TextView tvPublish;
    private UserDynamic userDynamic;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_dynamic);
        user = BmobUser.getCurrentUser(this, User.class);
        tvPublish = (TextView) findViewById(R.id.tv_publish);
        tvPublish.setOnClickListener(this);
        mDynamicContent = (EditText) findViewById(R.id.edt_publish_dynamic_content);
        mGridView = (GridView) findViewById(R.id.gv_publish_dynamic_images);
        picturesList = new ArrayList<String>();

        adapter = new PublishDynamicGvAdapter(this, picturesList);
        mGridView.setAdapter(adapter);
        // 长按图片删除
        mGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                picturesList.remove(position);
                adapter.updateAdapter(picturesList);
                return true;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case MULTI_PICTURE_SELECT:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    final ArrayList<String> dataList = (ArrayList<String>) bundle
                            .getSerializable("dataList");
                    if (dataList != null) {
                        picturesList.addAll(dataList);
                        adapter.notifyDataSetChanged();
                    }
                }
                break;
        }
    }


    // 图片上传完成后上传文字信息
    public void uploadInfo(final UserDynamic userDynamic) {
        // 上传用户文字信息
        userDynamic.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(PublishDynamicActivity.this, "发布成功",
                        Toast.LENGTH_SHORT).show();
                //发布成功则用户的动态数加一
                User newUser = new User();
                newUser.setUserDynamicNum(user.getUserDynamicNum() + 1);
                newUser.update(PublishDynamicActivity.this, user.getObjectId(), new UpdateListener() {

                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFailure(int arg0, String arg1) {

                    }
                });

                //在acitvity销毁之前将数据传出去
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("newDynamic", userDynamic);
                intent.putExtras(bundle);
                setResult(1, intent);
                PublishDynamicActivity.this.finish();
            }

            @Override
            public void onFailure(int arg0, String arg1) {
                Toast.makeText(PublishDynamicActivity.this, "发布失败",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_publish:
                Toast.makeText(PublishDynamicActivity.this, "正在发布请稍后",
                        Toast.LENGTH_SHORT).show();
                // 发布动态
                userDynamic = new UserDynamic();
                userDynamic.setContent(mDynamicContent.getText().toString());
                userDynamic.setUser(user);
                userDynamic.setUserId(user.getObjectId());
                userDynamic.setCommentNum(0);
                userDynamic.setLikeNum(0);
                userDynamic.setTime(TimeUtil.getCurrentTime());

                //用户选择图片列表为空则直接发布文字信息
                if (picturesList.size() == 0) {
                    uploadInfo(userDynamic);
                } else {
                    String paths[] = new String[picturesList.size()];
                    for (int i = 0; i < picturesList.size(); i++) {
                        //将上传的图片进行压缩
                        paths[i] = PictureUtil.saveSmallPic(picturesList.get(i));

                    }
                    // 上传用户动态图片
                    Bmob.uploadBatch(PublishDynamicActivity.this, paths,
                            new UploadBatchListener() {

                                @Override
                                public void onSuccess(List<BmobFile> files,
                                                      List<String> urls) {
                                    if (urls.size() == picturesList.size()) {
                                        userDynamic
                                                .setImageUrls((ArrayList<String>) urls);
                                        uploadInfo(userDynamic);
                                    }
                                }

                                @Override
                                public void onProgress(int curIndex,
                                                       int curPercent, int total, int totalPercent) {

                                    Log.d("yueba:", curIndex + "----" + totalPercent);

                                }

                                @Override
                                public void onError(int arg0, String arg1) {
                                    Log.d("yueba:", arg0 + "----" + arg1);
                                }
                            });
                }
                break;
        }

    }
}
