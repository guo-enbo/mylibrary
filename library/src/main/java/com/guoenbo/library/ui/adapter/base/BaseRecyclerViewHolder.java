package com.guoenbo.library.ui.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by leeandy007 on 16/9/18.
 */
public abstract class BaseRecyclerViewHolder<T> extends RecyclerView.ViewHolder {

    protected BaseRecyclerViewHolder(View view) {
        super(view);
        initView(view);
    }

    protected abstract void initView(View view);

    protected abstract void initData(Context context, T t, int position);

    protected abstract void BindEvent(Context context, T t, int position);


}
