package com.study.lijia.coordinatorlayoutdemo;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by lijia on 17-4-1.
 */

public class YourBehavior extends AppBarLayout.ScrollingViewBehavior {


    public YourBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof LinearLayout;
    }
}
