package com.guoenbo.library.ui.adapter.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.List;

/**
 * @author leeandy007
 * @Desc: 一级列表适配器基类
 * @version :
 * 
 */
public abstract class BaseInfoAdapter<T> extends BaseAdapter {

	protected Context _context;
	protected int _resId;
	protected List<T> _list;

	public BaseInfoAdapter(Context context, List<T> list, int resId) {
		this._context = context;
		this._list = list;
		this._resId = resId;
	}
	
	public List<T> getList() {
		return _list;
	}

	@Override
	public int getCount() {
		return _list.size();
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
	public Object getItem(int position) {
		return _list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BaseInfoViewHolder holder = null;
		if(convertView == null){
			convertView = View.inflate(_context, _resId, null);
			holder = initViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (BaseInfoViewHolder) convertView.getTag();
		}
		return dealView(_context, position, convertView, holder);
	}

	public abstract BaseInfoViewHolder initViewHolder(View view);

	public abstract View dealView(Context context, int position, View convertView, BaseInfoViewHolder holder);



}
