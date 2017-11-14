package com.guoenbo.library.util;

import android.widget.Toast;

import com.qiji.fingertipfinancial.common.MainApplication;

/**
 * @Desc Toast工具类
 */
public class ToastUtil {

    private static ToastUtil mToastUtil;
    private CharSequence oldMessage;
    private int oldResId;
    private static long time = 0L;

    public static ToastUtil getInstance(){
        if(mToastUtil == null){
            synchronized (ToastUtil.class) {
                if (mToastUtil == null) {
                    mToastUtil = new ToastUtil();
                }
            }
        }
        return mToastUtil;
    }


    public void Short(CharSequence message) {
        if (!message.equals(oldMessage)) { // 当显示的内容不一样时，即断定为不是同一个Toast
            Toast.makeText(MainApplication.getContext(), message, Toast.LENGTH_SHORT).show();
            time = System.currentTimeMillis();
        } else {
            // 显示内容一样时，只有间隔时间大于2秒时才显示
            if (System.currentTimeMillis() - time > 2000) {
                Toast.makeText(MainApplication.getContext(), message, Toast.LENGTH_SHORT).show();
                time = System.currentTimeMillis();
            }
        }
        oldMessage = message;
    }

    public void Short(int resId) {
        if (!(resId == oldResId)) { // 当显示的内容不一样时，即断定为不是同一个Toast
            Toast.makeText(MainApplication.getContext(), resId, Toast.LENGTH_SHORT).show();
            time = System.currentTimeMillis();
        } else {
            // 显示内容一样时，只有间隔时间大于2秒时才显示
            if (System.currentTimeMillis() - time > 2000) {
                Toast.makeText(MainApplication.getContext(), resId, Toast.LENGTH_SHORT).show();
                time = System.currentTimeMillis();
            }
        }
        oldResId = resId;
    }

    public void Long(CharSequence message) {
        if (!message.equals(oldMessage)) { // 当显示的内容不一样时，即断定为不是同一个Toast
            Toast.makeText(MainApplication.getContext(), message, Toast.LENGTH_LONG).show();
            time = System.currentTimeMillis();
        } else {
            // 显示内容一样时，只有间隔时间大于3.5秒时才显示
            if (System.currentTimeMillis() - time > 3500) {
                Toast.makeText(MainApplication.getContext(), message, Toast.LENGTH_LONG).show();
                time = System.currentTimeMillis();
            }
        }
        oldMessage = message;
    }
    public void Long(int resId) {
        if (!(resId == oldResId)) { // 当显示的内容不一样时，即断定为不是同一个Toast
            Toast.makeText(MainApplication.getContext(), resId, Toast.LENGTH_SHORT).show();
            time = System.currentTimeMillis();
        } else {
            // 显示内容一样时，只有间隔时间大于2秒时才显示
            if (System.currentTimeMillis() - time > 3500) {
                Toast.makeText(MainApplication.getContext(), resId, Toast.LENGTH_SHORT).show();
                time = System.currentTimeMillis();
            }
        }
        oldResId = resId;
    }




}
