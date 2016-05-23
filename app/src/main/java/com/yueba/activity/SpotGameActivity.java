package com.yueba.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.yueba.R;
import com.yueba.adapter.SpotGameAdapter;
import com.yueba.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpotGameActivity extends BaseActivity {

    private ListView listView;
    private SpotGameAdapter adapter;
    private TextView tvTitleName;
    private List<Map<String, Object>> list;

    private String[] gameContent = { "撕名牌", "野外烧烤", "桌游", "极速时限", "盲人方阵" };
    private int[][] gameImages = {
            { R.mipmap.smp01, R.mipmap.smp02, R.mipmap.smp03 },
            { R.mipmap.ywsk01, R.mipmap.ywsk02, R.mipmap.ywsk03,
                    R.mipmap.ywsk04, R.mipmap.ywsk05 },
            { R.mipmap.zy01, R.mipmap.zy02, R.mipmap.zy03 },
            { R.mipmap.jssx01, R.mipmap.jssx02 },
            { R.mipmap.mrfz01, R.mipmap.mrfz02, R.mipmap.mrfz03,
                    R.mipmap.mrfz04, R.mipmap.mrfz05 } };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_game);
        tvTitleName = (TextView) findViewById(R.id.titleText);
        tvTitleName.setText("户外游戏");
        listView = (ListView) findViewById(R.id.lv_spot_game);
        list = new ArrayList<Map<String, Object>>();
        // 添加死数据
        setdata();
        adapter = new SpotGameAdapter(this, list);
        listView.setAdapter(adapter);

    }

    private void setdata() {
        for (int i = 0; i < gameContent.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("content", gameContent[i]);
            map.put("game", gameImages[i]);
            list.add(map);
        }

    }
}
