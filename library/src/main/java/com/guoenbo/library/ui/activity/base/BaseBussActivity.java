package com.guoenbo.library.ui.activity.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guoenbo.library.R;
import com.guoenbo.library.common.Constants;
import com.guoenbo.library.util.StringUtil;


/**
 * Created by leeandy007 on 2017/3/11.
 */

public class BaseBussActivity extends BaseActivity {

    protected Toolbar mToolbar;
    protected boolean isShow = false; //是否显示Toolbar，默认不显示
    protected boolean canBack = false;
    protected TextView tv_title;
    protected ImageView ib_back;
    protected Button btn_right;
    protected LinearLayout linear_title_bar;
    protected Intent intent;

    /**
     * @Desc 侧滑，返回
     * */
    public enum Action{
        TOGGLE,
        BACK
    }

    /**
     * @Desc 是否显示Toolbar
     * */
    protected void isShowToolbarBar(boolean isShow){
        this.isShow = isShow;
    }

    protected void isCanBack(boolean canBack){
        this.canBack = canBack;
    }

    /**
     * @Desc 初始化布局
     * */
    @Override
    protected void setLayout(Bundle savedInstanceState) {

    }

    /**
     * @Desc 初始化控件
     * */
    @Override
    protected void initView() {
        if(isShow){
            linear_title_bar = (LinearLayout) findViewById(R.id.linear_title_bar);
            tv_title = (TextView) findViewById(R.id.tv_title);
            ib_back = (ImageView) findViewById(R.id.ib_back);
            btn_right = (Button) findViewById(R.id.btn_right);
        }
    }

    /**
     * @Desc 绑定控件事件
     * */
    @Override
    protected void BindEvent() {
        if(isShow){
            if (canBack){
                ib_back.setVisibility(View.VISIBLE);
                linear_title_bar.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                    }
                });
            }

        }
    }
    /**
     * @Desc 初始化数据
     * */
    @Override
    protected void initData() {
        intent = this.getIntent();
        if(null != intent){
            String title = intent.getStringExtra(Constants.KEY_TITLE);
            if(!StringUtil.isEmpty(title)){
                if(isShow){
                    tv_title.setText(title);
                }
            }
        }
    }

    /**
     * @Desc 带返回值跳转的数据的处理方法
     * @param requestCode 请求码
     * @param intent 取值载体
     * */
    @Override
    protected void doActivityResult(int requestCode, Intent intent) {

    }

    /**
     * @Desc  初始化toolbar
     * @param navigationResId 图片资源Id，0为不设置
     * @param logoResId 图片资源Id，0为不设置
     * @param title 标题
     * @param subTitle 子标题
     * */
    protected void initToolbar(int navigationResId, int logoResId, String title, String subTitle){
        if(isShow){
            if(navigationResId != 0){
                mToolbar.setNavigationIcon(navigationResId);
            }
            if(logoResId != 0){
                mToolbar.setLogo(logoResId);
            }
            if(!TextUtils.isEmpty(title)){
                mToolbar.setTitle(title);
                mToolbar.setTitleTextColor(Color.WHITE);
            }
            if(!TextUtils.isEmpty(subTitle)){
                mToolbar.setSubtitle(subTitle);
                mToolbar.setSubtitleTextColor(Color.WHITE);
            }
            setSupportActionBar(mToolbar);
        }
    }

    /**
     * @Desc Navigation的点击事件
     * @param action 侧滑或返回
     * @param listener 点击监听事件，侧滑操作需要自定义，返回操作设置为null
     * */
    protected void bindNavigationEvent(Action action, View.OnClickListener listener){
        switch (action){
            case TOGGLE:
                mToolbar.setNavigationOnClickListener(listener);
                break;
            case BACK:
                mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
                break;
        }

    }

    /**
     * @Desc 返回键操作
     */
    @Override
    public void onBackPressed() {
        //application.removeActivity(this);
        this.finish();
        animBack();
    }

    /**
     * @Desc 正常页面跳转
     * @param clszz 目标页面
     * @param bundle 传值载体
     * */
    protected void startActivity(Class clszz, Bundle bundle){
        Intent intent = new Intent(_context, clszz);
        if(bundle != null){
            intent.putExtras(bundle);
        }
        _context.startActivity(intent);
        animNext();
    }

    /**
     * @Desc 带返回值跳转
     * @param clszz 目标页面
     * @param bundle 传值
     * @param requestCode 请求码
     * */
    protected void startActivityForResult(Class clszz, Bundle bundle, int requestCode){
        Intent intent = new Intent(_context, clszz);
        intent.putExtras(bundle);
        _context.startActivityForResult(intent, requestCode);
        animNext();
    }

    /**
     * @Desc 页面跳转动画
     * */

    public void animNext(){
        /**<<<------右入左出*/
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    /**
     * @Desc 页面返回动画
     * */
    public void animBack(){
        /**------>>>左入右出*/
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
