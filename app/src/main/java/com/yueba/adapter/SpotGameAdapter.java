package com.yueba.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yueba.R;
import com.yueba.utils.ViewHolder;
import com.yueba.widget.NoScrollGridView;

import java.util.List;
import java.util.Map;

/**
 * Created by abcerk on 2016/5/23.
 */
public class SpotGameAdapter extends BaseAdapter {

    private Context context;
    private List<Map<String, Object>> list;

    public SpotGameAdapter(Context context, List<Map<String, Object>> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_spot_game_listview, null);
        }

        TextView mGameContent = ViewHolder.get(convertView, R.id.tv_spot_game_content);
        NoScrollGridView mGameImages = ViewHolder.get(convertView, R.id.gv_spot_game_images);

        Map<String, Object> map = list.get(position);

        mGameContent.setText(map.get("content").toString());
        final int[] gameImages = (int[]) map.get("game");
        ImageAdapter imageAdapter = new ImageAdapter(context, gameImages);
        mGameImages.setAdapter(imageAdapter);
        mGameImages.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
//                Intent intent = new Intent(context, ImageBrowserActivity2.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("position", position);
//                bundle.putIntArray("images", gameImages);
//                intent.putExtras(bundle);
//                context.startActivity(intent);
            }
        });

        return convertView;
    }

}
