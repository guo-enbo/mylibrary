package com.guoenbo.library.widget;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/3/21.
 */
public class MyNestedScrollView extends NestedScrollView {

    private int downX;
    private int downY;
    private int mTouchSlop = 50;

    public MyNestedScrollView(Context context) {
        super(context);
        /*获得的是触发移动事件的最短距离，如果小于这个距离就不触发移动控件
        ViewConfiguration.get(context).getScaledTouchSlop(); */
        //mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MyNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        /*获得的是触发移动事件的最短距离，如果小于这个距离就不触发移动控件
        ViewConfiguration.get(context).getScaledTouchSlop(); */
        //mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MyNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /*获得的是触发移动事件的最短距离，如果小于这个距离就不触发移动控件
        ViewConfiguration.get(context).getScaledTouchSlop(); */
        //mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) e.getRawX();
                downY = (int) e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) e.getRawY();
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(e);
    }
}
