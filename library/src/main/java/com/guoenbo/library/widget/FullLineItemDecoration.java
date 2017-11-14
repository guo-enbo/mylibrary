package com.guoenbo.library.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import com.qiji.fingertipfinancial.R;
import com.qiji.fingertipfinancial.common.MainApplication;
import com.qiji.fingertipfinancial.util.AndroidUtil;

/**
 * RecyclerView的实线分隔线
 * Created by guoenbo on 2017/9/7.
 */

public class FullLineItemDecoration extends RecyclerView.ItemDecoration {

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL; //横向列表
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;     //纵向列表
    private int leftMargin;   //左边距
    private int rightMargin;  //右边距
    private int mOrientation; //列表方向

    /**
     * 构造函数
     * 横向列表的虚线分隔符
     * @param orientation
     */
    public FullLineItemDecoration(int orientation) {
        if (orientation != LinearLayoutManager.HORIZONTAL){
            orientation = HORIZONTAL_LIST;
        }
        setOrientation(orientation);
    }

    /**
     * 构造函数
     * 纵向列表的虚线分隔符
     * @param leftMargin   分隔线距左边缘的距离单位 dp
     * @param rightMargin  分隔线距右边缘的距离单位 dp
     * 建议两边边距相同
     */
    public FullLineItemDecoration(int orientation, int leftMargin, int rightMargin) {
        this.leftMargin = leftMargin;
        this.rightMargin = rightMargin;
        setOrientation(VERTICAL_LIST);
    }

    /**
     * 设置列表方向
     * @param orientation
     */
    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }

    }

    /**
     * 纵向列表分界线
     * @param canvas
     * @param parent
     */
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        WindowManager wm = (WindowManager) MainApplication.getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();

        final int childCount = parent.getChildCount();
        //i < childCount-1;是使最后一个item不画分界线
        for (int i = 0; i < childCount-1; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            //以下计算主要用来确定绘制的位置
            final int top = child.getBottom() + params.bottomMargin;
            //绘制虚线
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(MainApplication.getContext().getResources().getColor(R.color.gray));
            paint.setStrokeWidth(2);
            Path path = new Path();
            path.moveTo(AndroidUtil.dip2px(leftMargin), top);
            path.lineTo(width - AndroidUtil.dip2px(rightMargin), top);
            canvas.drawPath(path, paint);
        }
    }

    /**
     * 横向列表分界线
     * @param canvas
     * @param parent
     */
    private void drawHorizontal(Canvas canvas, RecyclerView parent){

        final int childCount = parent.getChildCount();
        //i < childCount-1;是使最后一个item不画分界线
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            //以下计算主要用来确定绘制的位置
            final int right = child.getRight() + params.rightMargin;
            //绘制虚线
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(MainApplication.getContext().getResources().getColor(R.color.gray));
            paint.setStrokeWidth(2);
            Path path = new Path();
            path.moveTo(right, child.getTop());
            path.lineTo(right, child.getBottom());
            canvas.drawPath(path, paint);
        }
    }
}
