package com.brioal.jdrefresh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class TMActivity extends AppCompatActivity {
    private PtrFrameLayout mLayout;
    private ImageView mIvHead;
    private TextView mTvCount;
    private int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tm);
        mLayout = (PtrFrameLayout) findViewById(R.id.tm_layout);
        mIvHead = (ImageView) findViewById(R.id.tm_iv_head);
        mTvCount = (TextView) findViewById(R.id.tm_tv_count);

        mTvCount.setText("刷新的次数：" + mCount);
        //加载动画
        Glide.with(this).load(R.drawable.tm_mui_bike).asGif().into(mIvHead);

        mLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mCount++;
                        mTvCount.setText("刷新的次数：" + mCount);
                        mLayout.refreshComplete();
                    }
                }, 1500);
            }
        });
    }
}
