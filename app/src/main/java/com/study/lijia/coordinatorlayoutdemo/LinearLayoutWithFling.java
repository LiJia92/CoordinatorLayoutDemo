package com.study.lijia.coordinatorlayoutdemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.OverScroller;

/**
 * Created by lijia on 17-4-1.
 */

public class LinearLayoutWithFling extends LinearLayout {

    private OverScroller mScroller;
    private int mTopViewHeight;

    public LinearLayoutWithFling(Context context) {
        this(context, null);
    }

    public LinearLayoutWithFling(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinearLayoutWithFling(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setOrientation(LinearLayout.VERTICAL);
        mScroller = new OverScroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mTopViewHeight = getChildAt(0).getHeight();
    }

//    @Override
//    public void scrollTo(@Px int x, @Px int y) {
//        //限制滚动范围
//        if (y < 0) {
//            y = 0;
//        }
//        if (y > mTopViewHeight) {
//            y = mTopViewHeight;
//        }
//
//        super.scrollTo(x, y);
//    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            setY(mScroller.getCurrY());
            invalidate();
        }
    }

    public void fling(int velocityY) {
        mScroller.fling(0, getScrollY(), 0, velocityY, 0, 0, 0, mTopViewHeight);
        invalidate();
    }
}
