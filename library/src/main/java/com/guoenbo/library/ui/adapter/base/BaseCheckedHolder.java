package com.guoenbo.library.ui.adapter.base;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;

public abstract class BaseCheckedHolder<T> extends BaseInfoViewHolder{

	public  CheckBox mCheckBox;
	private int checkedResId;

	public BaseCheckedHolder(View view, int checkedResId) {
		super(view);
		this.checkedResId = checkedResId;
		initView(view);
	}

	public void initView(View view) {
		mCheckBox = (CheckBox) view.findViewById(checkedResId);
	}
	
	public abstract void setValue(Context context, Object... object);
	
}
