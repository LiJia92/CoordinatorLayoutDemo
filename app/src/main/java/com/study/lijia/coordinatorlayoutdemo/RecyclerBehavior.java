package com.study.lijia.coordinatorlayoutdemo;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by lijia on 17-3-31.
 */
public class RecyclerBehavior extends CoordinatorLayout.Behavior<RecyclerView> {

    private Context context;
    private View childA;    // Header A
    private View childB;    // Header B

    private int childAHeight;
    private int childBHeight;

    public RecyclerBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, RecyclerView child, View dependency) {
        if (dependency instanceof LinearLayout) {
            LinearLayout dependent = (LinearLayout) dependency;
            if (dependent.getChildCount() == 2) {
                childA = dependent.getChildAt(0);
                childB = dependent.getChildAt(1);
                childAHeight = childA.getHeight();
                childBHeight = childB.getHeight();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, RecyclerView child, View dependency) {
        int y = (int) (dependency.getY() + dependency.getBottom());
        Log.e("TAG", "y:" + y);
        int z;
        if (y > childBHeight) {
            child.setTranslationY(y);
        } else {
            if (dependency.getTag() != null) {
                int x = (int) child.getTranslationY();
                z = x - (int) (dependency.getTag());
                if (z < 0) {
                    z = 0;
                } else if (z > childBHeight + childAHeight) {
                    z = childAHeight + childBHeight;
                }
                Log.e("TAG", "z:" + z);
                child.setTranslationY(z);
            }
        }
        return true;
    }
}
