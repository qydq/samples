package com.an.aw.activity;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by qydda on 2017/1/12.
 */

public class MoreFragment extends Fragment implements View.OnClickListener {
    private Context mContext;
    private Button btn_one;
    private Button btn_two;
    private Button btn_three;
    private Button btn_four;
    String context;

    public MoreFragment(String context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        btn_one = (Button) view.findViewById(R.id.btn_one);
        btn_two = (Button) view.findViewById(R.id.btn_two);
        btn_three = (Button) view.findViewById(R.id.btn_three);
        btn_four = (Button) view.findViewById(R.id.btn_four);

        btn_one.setOnClickListener(this);
        btn_two.setOnClickListener(this);
        btn_three.setOnClickListener(this);
        btn_four.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one:
                TextView tvTips1 = (TextView) getActivity().findViewById(R.id.tvTips1);
                tvTips1.setText("11");
                tvTips1.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_two:
                TextView tvTips2 = (TextView) getActivity().findViewById(R.id.tvTips2);
                tvTips2.setText("99+");
                tvTips2.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_three:
                TextView tvTips3 = (TextView) getActivity().findViewById(R.id.tvTips3);
                tvTips3.setText("999+");
                tvTips3.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_four:
                ImageView mImageView = (ImageView) getActivity().findViewById(R.id.ivMore);
                mImageView.setVisibility(View.VISIBLE);
                break;
        }
    }
}