package com.an.aw.activity;

import android.content.Context;
import android.widget.TextView;

import com.an.base.view.recyclerview.ListBaseAdapter;
import com.an.base.view.recyclerview.SuperViewHolder;


/**
 * Created by Lzx on 2016/12/30.
 */

public class DataAdapter extends ListBaseAdapter<ItemModel> {

    public DataAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_recyclerview_text;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        ItemModel item = mDataList.get(position);

        TextView titleText = holder.getView(R.id.info_text);
        titleText.setText(item.title);
    }
}
