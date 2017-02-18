package com.brioal.jdrefresh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class JDActivity extends AppCompatActivity {
    private JDRefreshLayout mLayout;
    private TextView mTvCount;
    private int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jd);
        mLayout = (JDRefreshLayout) findViewById(R.id.jd_layout);
        mTvCount = (TextView) findViewById(R.id.jd_tv_count);
        mTvCount.setText("刷新的次数：" + mCount);

        mLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

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
