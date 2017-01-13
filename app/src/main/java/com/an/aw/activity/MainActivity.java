package com.an.aw.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView topBar;

    private RelativeLayout tabHome;
    private RelativeLayout tabNeiyi;
    private RelativeLayout tabMore;
    private RelativeLayout tabLove;

    private ImageView ivMore;
    private TextView tvTips1, tvTips2, tvTips3;

    private FrameLayout ly_content;

    private Fragment f1, f2, f3, f4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        bindView();
    }

    //UI组件初始化与事件绑定
    private void bindView() {
        topBar = (TextView) this.findViewById(R.id.txt_top);

        tabHome = (RelativeLayout) this.findViewById(R.id.tabHome);
        tabNeiyi = (RelativeLayout) this.findViewById(R.id.tabNeiyi);
        tabLove = (RelativeLayout) this.findViewById(R.id.tabLove);
        tabMore = (RelativeLayout) this.findViewById(R.id.tabMore);

        ly_content = (FrameLayout) findViewById(R.id.fragment_container);

        tvTips1 = (TextView) findViewById(R.id.tvTips1);
        tvTips2 = (TextView) findViewById(R.id.tvTips2);
        tvTips3 = (TextView) findViewById(R.id.tvTips3);
        ivMore = (ImageView) findViewById(R.id.ivMore);

        tabHome.setOnClickListener(this);
        tabMore.setOnClickListener(this);
        tabLove.setOnClickListener(this);
        tabNeiyi.setOnClickListener(this);

        topBar.setOnClickListener(this);

        tabHome.performClick();
    }

    //隐藏所有Fragment并重置
    public void hideAllFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //重置所有文本的选中状态
        tabHome.setSelected(false);
        tabMore.setSelected(false);
        tabNeiyi.setSelected(false);
        tabLove.setSelected(false);
        if (f1 != null) {
            transaction.hide(f1);
        }
        if (f2 != null) {
            transaction.hide(f2);
        }
        if (f3 != null) {
            transaction.hide(f3);
        }
        if (f4 != null) {
            transaction.hide(f4);
        }
        if (position == 1) {
            tvTips1.setVisibility(View.INVISIBLE);
            tabHome.setSelected(true);
            topBar.setText(R.string.home);
            if (f1 == null) {
                f1 = new HomeFragment("第一个Fragment");
                transaction.add(R.id.fragment_container, f1);
            } else {
                transaction.show(f1);
            }
        }
        if (position == 2) {
            tvTips2.setVisibility(View.INVISIBLE);
            tabNeiyi.setSelected(true);
            topBar.setText(R.string.neiyi);
            if (f2 == null) {
                f2 = new NeiYiFragment("第二个Fragment");
                transaction.add(R.id.fragment_container, f2);
            } else {
                transaction.show(f2);
            }
        }
        if (position == 3) {
            tvTips3.setVisibility(View.INVISIBLE);
            topBar.setText(R.string.love);
            tabLove.setSelected(true);
            if (f3 == null) {
                f3 = new LoveFragment("第三个Fragment");
                transaction.add(R.id.fragment_container, f3);
            } else {
                transaction.show(f3);
            }
        }
        if (position == 4) {
            ivMore.setVisibility(View.INVISIBLE);
            topBar.setText(R.string.more);
            tabMore.setSelected(true);
            if (f4 == null) {
                f4 = new MoreFragment("第四个Fragment",MainActivity.this);
                transaction.add(R.id.fragment_container, f4);
            } else {
                transaction.show(f4);
            }
        }
        transaction.commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tabHome:
                hideAllFragment(1);
                break;
            case R.id.tabNeiyi:
                hideAllFragment(2);
                break;
            case R.id.tabLove:
                hideAllFragment(3);
                break;
            case R.id.tabMore:
                hideAllFragment(4);
                break;
            case R.id.txt_top:
                Toast.makeText(getApplicationContext(), "click top", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}