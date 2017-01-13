package com.an.aw.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.an.base.utils.NetBroadcastReceiverUtils;
import com.an.base.view.recyclerview.interfaces.OnItemClickListener;
import com.an.base.view.recyclerview.interfaces.OnLoadMoreListener;
import com.an.base.view.recyclerview.interfaces.OnNetWorkErrorListener;
import com.an.base.view.recyclerview.interfaces.OnRefreshListener;
import com.an.base.view.recyclerview.recyclerview.LRecyclerView;
import com.an.base.view.recyclerview.recyclerview.LRecyclerViewAdapter;
import com.an.base.view.recyclerview.recyclerview.ProgressStyle;
import com.an.base.view.swipelayout.ListViewDecoration;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by qydda on 2017/1/12.
 */

public class MoreFragment extends Fragment implements View.OnClickListener {
    /**
     * 服务器端一共多少条数据
     */
    private static final int TOTAL_COUNTER = 34;

    /**
     * 每一页展示多少条数据
     */
    private static final int REQUEST_COUNT = 10;

    private LRecyclerView mRecyclerView = null;

    private DataAdapter mDataAdapter = null;

    /**
     * 已经获取到多少条数据了
     */
    private static int mCurrentCounter = 0;

    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;

    private PreviewHandler mHandler = new PreviewHandler(this);

    private Context context;

    public MoreFragment(String context, Context mContext) {
        this.context = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.morefragement, container, false);

        mRecyclerView = (LRecyclerView) view.findViewById(R.id.list);
        //init data
        ArrayList<ItemModel> dataList = new ArrayList<>();
        ItemModel item1 = new ItemModel();
        ItemModel item2 = new ItemModel();
        ItemModel item3 = new ItemModel();
        ItemModel item4 = new ItemModel();
        ItemModel item5 = new ItemModel();
        ItemModel item6 = new ItemModel();
        ItemModel item7 = new ItemModel();
        ItemModel item8 = new ItemModel();
        ItemModel item9 = new ItemModel();
        ItemModel item10 = new ItemModel();
        ItemModel item11 = new ItemModel();
        ItemModel item12 = new ItemModel();
        item1.title = "SectionLayoutActivity";
        item2.title = "SectionAnimalActivity";
        item3.title = "SwipeMenuActivity=SwipeDeleteActivity";
        item4.title = "EndlessLinearLayoutActivity";
        item5.title = "MulItemLinearLayoutActivity";
        item6.title = "SwipeDeleteActivity";
        item7.title = "LinearLayoutSample";
        item8.title = "EndlessGridLayoutActivity";
        item9.title = "EndlessStaggeredGridLayoutActivity";
        item10.title = "CollapsingToolbarLayoutActivity";
        item11.title = "ExpandableRecyclerViewOneActivity";
        item12.title = "PartialRefreshActivity(局部刷新)";

        mCurrentCounter = dataList.size();

        mDataAdapter = new DataAdapter(context);
        mDataAdapter.addAll(dataList);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mDataAdapter);

        mRecyclerView.setAdapter(mLRecyclerViewAdapter);

        ListViewDecoration divider = new ListViewDecoration();
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(divider);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.LineSpinFadeLoader);
        mRecyclerView.setArrowImageView(R.mipmap.base_refresh_head_arrow);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);

        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestData();
            }
        });

        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (mCurrentCounter < TOTAL_COUNTER) {
                    // loading more
                    requestData();
                } else {
                    //the end
                    mRecyclerView.setNoMore(true);
                }
            }
        });

        //设置头部加载颜色
        mRecyclerView.setHeaderViewColor(R.color.colorAccent, R.color.CommPauseFont, R.color.CommColorLineClicked);
        //设置底部加载颜色
        mRecyclerView.setFooterViewColor(R.color.colorAccent, R.color.CommPauseFont, R.color.CommColorLineClicked);
        //设置底部加载文字提示
        mRecyclerView.setFooterViewHint("拼命加载中", "我是有底线的", "网络不给力啊，点击再试一次吧");

        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ItemModel item = mDataAdapter.getDataList().get(position);
                Toast.makeText(getActivity(), item.title, Toast.LENGTH_SHORT).show();
            }

        });
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    /**
     * 模拟请求网络
     */
    private void requestData() {
        new Thread() {

            @Override
            public void run() {
                super.run();

                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //模拟一下网络请求失败的情况
                if (NetBroadcastReceiverUtils.isConnectedToInternet(context)) {
                    mHandler.sendEmptyMessage(-1);
                } else {
                    mHandler.sendEmptyMessage(-3);
                }
            }
        }.start();
    }

    private class PreviewHandler extends Handler {

        private WeakReference<MoreFragment> ref;

        PreviewHandler(MoreFragment activity) {
            ref = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final MoreFragment activity = ref.get();
            if (activity == null || activity.getActivity().isFinishing()) {
                return;
            }
            switch (msg.what) {

                case -1:
                    if (activity.mRecyclerView.isPulldownToRefresh()) {
                        activity.mDataAdapter.clear();
                        mCurrentCounter = 0;
                    }

                    int currentSize = activity.mDataAdapter.getItemCount();

                    ArrayList<ItemModel> dataList = new ArrayList<>();
                    ItemModel item1 = new ItemModel();
                    ItemModel item2 = new ItemModel();
                    ItemModel item3 = new ItemModel();
                    ItemModel item4 = new ItemModel();
                    ItemModel item5 = new ItemModel();
                    ItemModel item6 = new ItemModel();
                    ItemModel item7 = new ItemModel();
                    ItemModel item8 = new ItemModel();
                    ItemModel item9 = new ItemModel();
                    ItemModel item10 = new ItemModel();
                    ItemModel item11 = new ItemModel();
                    ItemModel item12 = new ItemModel();
                    item1.title = "SectionLayoutActivity";
                    item2.title = "SectionAnimalActivity";
                    item3.title = "SwipeMenuActivity";
                    item4.title = "EndlessLinearLayoutActivity";
                    item5.title = "MulItemLinearLayoutActivity";
                    item6.title = "SwipeDeleteActivity";
                    item7.title = "LinearLayoutSample";
                    item8.title = "EndlessGridLayoutActivity";
                    item9.title = "EndlessStaggeredGridLayoutActivity";
                    item10.title = "CollapsingToolbarLayoutActivity";
                    item11.title = "ExpandableRecyclerViewOneActivity";
                    item12.title = "PartialRefreshActivity(局部刷新)";

                    //模拟组装10个数据
                    ArrayList<ItemModel> newList = new ArrayList<>();

                    newList.add(item1);
                    newList.add(item2);
                    newList.add(item3);
                    newList.add(item4);
                    newList.add(item5);
                    newList.add(item6);
                    newList.add(item7);
                    newList.add(item8);
                    newList.add(item9);
                    newList.add(item10);
                    newList.add(item11);
                    newList.add(item12);

                    for (int i = 0; i < 10; i++) {
                        if (newList.size() + currentSize >= TOTAL_COUNTER) {
                            break;
                        }

                        ItemModel item = new ItemModel();
                        item.id = currentSize + i;
                        item.title = "item" + (item.id);

                        newList.add(item);
                    }


                    activity.addItems(newList);

                    if (activity.mRecyclerView.isPulldownToRefresh()) {
                        activity.mRecyclerView.refreshComplete();
                        activity.notifyDataSetChanged();
                    } else {
                        activity.mRecyclerView.loadMoreComplete();
                    }

                    break;
                case -2:
                    activity.notifyDataSetChanged();
                    break;
                case -3:
                    if (activity.mRecyclerView.isPulldownToRefresh()) {
                        activity.mRecyclerView.refreshComplete();
                        activity.notifyDataSetChanged();
                    } else {
                        activity.mRecyclerView.setOnNetWorkErrorListener(new OnNetWorkErrorListener() {
                            @Override
                            public void reload() {
                                requestData();
                            }
                        });
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void addItems(ArrayList<ItemModel> list) {
        mDataAdapter.addAll(list);
        mCurrentCounter += list.size();
    }

    private void notifyDataSetChanged() {
        mLRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().finish();
        }
        return true;
    }
}