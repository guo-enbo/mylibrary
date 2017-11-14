package com.guoenbo.library.ui.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

/**
 * Created by leeandy007 on 16/9/18.
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    protected Context _context;
    protected List<T> _list;
    protected int _resId;

    public BaseRecyclerAdapter(Context context, List<T> list, int resId){
        this._context = context;
        this._list = list;
        this._resId = resId;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(_context, _resId, null);
        return initViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return _list.size();
    }

    public List<T> getList() {
        return _list;
    }

    public T getItem(int position){
        return _list.get(position);
    }

    public void replaceBean(int position , T t){
        _list.set(position, t);
        this.notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        _list.remove(position);
        this.notifyDataSetChanged();
    }

    public void clearAll() {
        _list.clear();
        this.notifyDataSetChanged();
    }

    public void add(List<T> beans) {
        _list.addAll(beans);
        this.notifyDataSetChanged();
    }

    public void refresh(){
        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        dealView(_context, position, holder);
    }

    /**
     * 初始化ViewHolder
     * */
    public abstract BaseRecyclerViewHolder initViewHolder(View view);

    /**
     * 处理数据显示到
     * */
    public abstract void dealView(Context context, int position, BaseRecyclerViewHolder holder);


}
