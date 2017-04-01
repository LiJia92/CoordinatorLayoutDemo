package com.study.lijia.coordinatorlayoutdemo;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by lijia on 17-3-31.
 */

public class HeaderBehavior extends CoordinatorLayout.Behavior<View> {

    private View childA;    // Header A
    private View childB;    // Header B

    private int childAHeight;
    private int childBHeight;

    public HeaderBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        if (child instanceof LinearLayout) {
            LinearLayout dependent = (LinearLayout) child;
            if (dependent.getChildCount() == 2) {
                childA = dependent.getChildAt(0);
                childB = dependent.getChildAt(1);
                childAHeight = childA.getHeight();
                childBHeight = childB.getHeight();
            }
        }
        return target instanceof RecyclerView;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        if (dy > 0) { //表示向上滚动
            float trY = child.getY() - dy <= -childAHeight ? -childAHeight : child.getTranslationY() - dy;
            child.setY(trY);
            child.setTag(dy);
        } else if (dy < 0) { //向下滚动
            if (target instanceof RecyclerView) {
                int scrollY = getScrollY((RecyclerView) target);
                if (scrollY == 0) {
                    if (target.getTranslationY() < childBHeight) {
                        child.setTag(dy);
                    } else {
                        float trY = child.getY() - dy >= 0 ? 0 : child.getY() - dy;
                        child.setY(trY);
                        child.setTag(dy);
                    }
                }
            }
        }
    }

//    @Override
//    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY) {
//        ((NestedScrollView) child).fling((int)velocityY);
//        return true;
//    }

    private int getScrollY(RecyclerView target) {
        RecyclerView recyclerView = target;
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int position = layoutManager.findFirstVisibleItemPosition();
        View firstVisiableChildView = layoutManager.findViewByPosition(position);
        int itemHeight = firstVisiableChildView.getHeight();
        return (position) * itemHeight - firstVisiableChildView.getTop();
    }
}
