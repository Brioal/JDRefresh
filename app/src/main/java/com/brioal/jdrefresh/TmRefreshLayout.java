package com.brioal.jdrefresh;

import android.content.Context;
import android.util.AttributeSet;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * email :brioal@foxmail.com
 * github : https://github.com/Brioal
 * Created by brioal on 17-1-9.
 */

public class TmRefreshLayout extends PtrFrameLayout {
    private TMRefreshHead mRefreshHead;

    public TmRefreshLayout(Context context) {
        this(context, null);
    }

    public TmRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRefreshHead = new TMRefreshHead(context);
        setHeaderView(mRefreshHead);
        addPtrUIHandler(mRefreshHead);
    }
}
