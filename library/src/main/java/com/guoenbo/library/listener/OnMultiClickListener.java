package com.guoenbo.library.listener;

import android.view.View;

/**
 * 保证同一按钮在1秒内只会响应一次点击事件
 * Created by guoenbo on 2017/11/3.
 */
public abstract class OnMultiClickListener implements View.OnClickListener{
    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime;

    public abstract void onMultiClick(View v);

    @Override
    public void onClick(View v) {
        long curClickTime = System.currentTimeMillis();
        if((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            // 超过点击间隔后再将lastClickTime重置为当前点击时间
            lastClickTime = curClickTime;
            onMultiClick(v);
        }
    }
}
