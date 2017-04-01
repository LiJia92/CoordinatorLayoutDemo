package com.study.lijia.coordinatorlayoutdemo;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by lijia on 17-3-31.
 */

public class MyHeaderBehavior extends CoordinatorLayout.Behavior<LinearLayoutWithFling> {

    private View childA;    // Header A
    private View childB;    // Header B

    private int childAHeight;
    private int childBHeight;

    public MyHeaderBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, LinearLayoutWithFling child, View directTargetChild, View target, int nestedScrollAxes) {
        if (child != null) {
            if (child.getChildCount() == 2) {
                childA = child.getChildAt(0);
                childB = child.getChildAt(1);
                childAHeight = childA.getHeight();
                childBHeight = childB.getHeight();
            }
        }
        return target instanceof RecyclerView;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, LinearLayoutWithFling child, View target, int dx, int dy, int[] consumed) {
        if (dy > 0) { //表示向上滚动
            float trY = child.getY() - dy <= -childAHeight ? -childAHeight : (int) (child.getY() - dy);
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

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, LinearLayoutWithFling child, View target, float velocityX, float velocityY) {
        Log.e("TAG", "onNestedPreFling:" + velocityY);
        if (target instanceof RecyclerView) {
            int scrollY = getScrollY((RecyclerView) target);
            if (velocityY < 0 && scrollY == 0) {
                child.fling((int) velocityY);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
//
//    @Override
//    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, LinearLayoutWithFling child, View target, float velocityX, float velocityY, boolean consumed) {
//        Log.e("TAG", "onNestedFling:" + velocityY);
//        return false;
//    }
//
//    @Override
//    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, LinearLayoutWithFling child, View target) {
//        Log.e("TAG", "onStopNestedScroll");
//    }
//
//    @Override
//    public void onNestedScroll(CoordinatorLayout coordinatorLayout, LinearLayoutWithFling child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
//        Log.e("TAG", "onNestedScroll dyConsumed:" + dyConsumed + " dyUnconsumed:" + dyUnconsumed);
//    }
//
//    @Override
//    public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, LinearLayoutWithFling child, View directTargetChild, View target, int nestedScrollAxes) {
//        Log.e("TAG", "onNestedScrollAccepted");
//    }

    private int getScrollY(RecyclerView target) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) target.getLayoutManager();
        int position = layoutManager.findFirstVisibleItemPosition();
        View firstVisiableChildView = layoutManager.findViewByPosition(position);
        int itemHeight = firstVisiableChildView.getHeight();
        return (position) * itemHeight - firstVisiableChildView.getTop();
    }
}
