package com.brioal.jdrefresh;

import android.content.Context;
import android.util.AttributeSet;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * email :brioal@foxmail.com
 * github : https://github.com/Brioal
 * Created by brioal on 17-1-9.
 */

public class JDRefreshLayout extends PtrFrameLayout {
    private JDRefreshHead mRefreshHead;

    public JDRefreshLayout(Context context) {
        this(context, null);
    }

    public JDRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRefreshHead = new JDRefreshHead(context);
        setHeaderView(mRefreshHead);
        addPtrUIHandler(mRefreshHead);
    }
}
