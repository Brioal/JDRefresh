package com.brioal.jdrefresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * email :brioal@foxmail.com
 * github : https://github.com/Brioal
 * Created by brioal on 17-1-9.
 */

public class JDRefreshHead extends FrameLayout implements PtrUIHandler {
    public static final int STATE_RESET = -1;//刷新重置
    public static final int STATE_PREPARE = 0;//刷新准备
    public static final int STATE_BEGIN = 1;//刷新开始
    public static final int STATE_FINISH = 2;//刷新将诶书

    private TextView mTvTip;//提示文本
    private ImageView mIvMan;//人
    private ImageView mIvGood;//货物
    private int mState;//状态

    public static final int MARGIN_RIGHT = 100;//人和货物的初始间隔
    private AnimationDrawable mAnimationDrawable;//动画


    public JDRefreshHead(@NonNull Context context) {
        super(context);
        initView();
    }

    private void initView() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.jd_head, this, false);
        mTvTip = (TextView) rootView.findViewById(R.id.jd_tv_tip);
        mIvMan = (ImageView) rootView.findViewById(R.id.jd_iv_man);
        mIvGood = (ImageView) rootView.findViewById(R.id.jd_iv_goods);
        addView(rootView);
    }

    //刷新重置
    @Override
    public void onUIReset(PtrFrameLayout frame) {
        mState = STATE_RESET;
    }

    //准备刷新
    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        mState = STATE_PREPARE;
    }

    //开始刷新
    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        mState = STATE_BEGIN;
        //隐藏商品logo，开启跑步动画
        mIvGood.setVisibility(GONE);
        mIvMan.setBackgroundResource(R.drawable.ic_jd_animation);
        mAnimationDrawable = (AnimationDrawable) mIvMan.getBackground();
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
    }

    //刷新完成
    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        mState = STATE_FINISH;
        mIvGood.setVisibility(VISIBLE);
        //停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        mIvMan.setBackgroundResource(R.mipmap.a2a);
    }

    //UI位置改变时回调
    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        //处理提醒文字
        switch (mState) {
            case STATE_RESET:
                //重置状态

                break;
            case STATE_PREPARE:
                //准备刷新
                //logo设置
                mIvMan.setAlpha(ptrIndicator.getCurrentPercent());
                mIvGood.setAlpha(ptrIndicator.getCurrentPercent());
                //人从远到近并且缩放
                LayoutParams mainParams = (LayoutParams) mIvMan.getLayoutParams();
                if (ptrIndicator.getCurrentPercent() < 1) {
                    float percent = ptrIndicator.getCurrentPercent();
                    //人和货物的缩放
                    mIvMan.setScaleX(percent);
                    mIvMan.setScaleY(percent);

                    mIvGood.setScaleX(percent);
                    mIvGood.setScaleY(percent);

                    int marginRight = (int) (MARGIN_RIGHT - MARGIN_RIGHT * percent);
                    mainParams.setMargins(0, 0, marginRight, 0);
                    mIvMan.setLayoutParams(mainParams);
                }

                if (ptrIndicator.getCurrentPercent() < 1.2) {
                    mTvTip.setText("下拉刷新...");
                } else {
                    mTvTip.setText("松开刷新...");
                }
                break;

            case STATE_BEGIN:
                //开始刷新
                mTvTip.setText("更新中...");
                break;
            case STATE_FINISH:
                //刷新完成
                mTvTip.setText("更新中...");
                break;

        }
    }
}
