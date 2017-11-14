package com.guoenbo.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by guoenbo on 2016/1/13.
 */
public class NoScrollGridView extends GridView {

	public NoScrollGridView(Context context) {
	    super(context);
	  }

	  public NoScrollGridView(Context context, AttributeSet attrs) {
	    super(context, attrs);
	  }

	  public NoScrollGridView(Context context, AttributeSet attrs, int defStyle) {
	    super(context, attrs, defStyle);
	  }

	  @Override
	  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	    int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
	    super.onMeasure(widthMeasureSpec, expandSpec);
	  }


}
