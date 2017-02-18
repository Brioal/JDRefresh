package com.brioal.jdrefresh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class NormalActivity extends AppCompatActivity {
    private PtrFrameLayout mLayout;
    private TextView mTextView;
    private int mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        mLayout = (PtrFrameLayout) findViewById(R.id.jd_layout);
        mTextView = (TextView) findViewById(R.id.jd_tv_content);
        mTextView.setText("刷新次数:" + mCount);

        mLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mLayout.refreshComplete();
                        mCount++;
                        mTextView.setText("刷新次数:" + mCount);
                    }
                }, 1500);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                // 默认实现，根据实际情况做改动
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

    }
}
