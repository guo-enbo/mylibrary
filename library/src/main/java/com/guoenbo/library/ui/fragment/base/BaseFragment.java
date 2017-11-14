package com.guoenbo.library.ui.fragment.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guoenbo.library.R;
import com.guoenbo.library.util.AndroidUtil;
import com.guoenbo.library.util.CacheUtil;
import com.umeng.analytics.MobclickAgent;


/**
 * @Describe
 * @Author leeandy007
 * @Date 2016-9-2 下午2:05:15
 */
@SuppressLint("ValidFragment")
public abstract class BaseFragment extends Fragment {

    protected Context _context;
    protected FragmentCallBack mCallBack;
    protected CacheUtil mCache;

    /**
     * Activity取Fragment所传递的值时调用的回调接口
     */
    public interface FragmentCallBack {

        /**
         * @param param Object...变参多个不固定个数不规定类型的返回结果
         * @DESC Activity中调用取出Fragment中的值
         **/
        public void setResult(Object... param);

    }

    public BaseFragment() {
        super();
    }

    /**
     * 设置碎片的布局
     */
    public abstract int getLayout();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(getLayout(), null);
        initView(v, savedInstanceState);
        BindEvent();
        initData();
        MobclickAgent.setScenarioType(_context, MobclickAgent.EScenarioType.E_UM_NORMAL);
        return v;
    }

    /**
     * 初始化界面
     */
    protected abstract void initView(View v, Bundle savedInstanceState);

    /**
     * 绑定界面数据
     */
    protected abstract void initData();

    /**
     * 绑定控件事件
     */
    protected abstract void BindEvent();

    /**
     * 声明Fragment实例，所创建的回调接口必须要在Activity中实现
     */
    @Override
    public void onAttach(Activity activity) {
        try {
            mCallBack = (FragmentCallBack) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement FragmentCallBack");
        }
        super.onAttach(activity);
    }

    /**
     * 声明Fragment实例，所创建的回调接口必须要在Activity中实现
     */
    @Override
    public void onAttach(Context context) {
        try {
            mCallBack = (FragmentCallBack) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement FragmentCallBack");
        }
        super.onAttach(context);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == Activity.RESULT_OK) {
            doActivityResult(requestCode, intent);
        }
    }

    /**
     * 带返回值跳转的数据的处理方法
     */
    protected abstract void doActivityResult(int requestCode, Intent intent);

    /**
     * @param clszz  目标页面
     * @param bundle 传值载体
     * @Desc 正常页面跳转
     */
    protected void startActivity(Class clszz, Bundle bundle) {
        Intent intent = new Intent(_context, clszz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        animNext();
    }

    /**
     * @param clszz       目标页面
     * @param bundle      传值
     * @param requestCode 请求码
     * @Desc 带返回值跳转
     */
    protected void startActivityForResult(Class clszz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(_context, clszz);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
        animNext();
    }

    /**
     * @Desc 页面跳转动画
     */

    public void animNext() {
        /**<<<------右入左出*/
        ((Activity) _context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    /**
     * @Desc 页面返回动画
     */
    public void animBack() {
        /**------>>>左入右出*/
        ((Activity) _context).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(AndroidUtil.getActOrFragName(_context));
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(AndroidUtil.getActOrFragName(_context));
    }


}
