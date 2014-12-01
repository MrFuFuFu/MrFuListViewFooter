package com.mrfu.listviewfooter.lib;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * @author Mr.傅 2014-11-23 下午8:25:46
 * @param <T>
 */
public abstract class LoadMoreAdapter<T> extends BaseAdapter {

	protected Context mContext;
	protected List<T> list;
	protected OnLoadMoreRequestListener loadMoreRequestListener;

	public LoadMoreAdapter(Context mContext, List<T> list) {
		this.mContext = mContext;
		this.list = list;
	}

	public LoadMoreAdapter(Context mContext, List<T> list, OnLoadMoreRequestListener loadMoreRequestListener) {
		this.mContext = mContext;
		this.list = list;
		this.loadMoreRequestListener = loadMoreRequestListener;
	}

	public List<T> getList() {
		return list;
	}

	public void append(List<T> list) {
		this.list.addAll(list);
		this.notifyDataSetChanged();
	}

	public void setList(List<T> list) {
		this.list = list;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		if (null != list) {
			return list.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return list.size();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		checkAndDispatchLoadMoreEvent(position);
		return getViewRelize(position, convertView, parent);
	}

	/**实现 getView 方法**/
	public abstract View getViewRelize(int position, View convertView, ViewGroup parent);

	public boolean isLoadMoreRequestListenerSet() {
		return (this.loadMoreRequestListener != null);
	}

	/**
	 * Sets the on load more request listener.
	 * 
	 * @param loadMoreRequestListener
	 *            Client can register for load-more events by passing reference
	 *            of {@link OnLoadMoreRequestListener}
	 */
	public void setOnLoadMoreRequestListener(OnLoadMoreRequestListener loadMoreRequestListener) {
		this.loadMoreRequestListener = loadMoreRequestListener;
	}

	/**
	 * Check and dispatch load more event, checks if its last position & also if
	 * listener is applied to adapter.
	 * 
	 * @param position
	 *            the position currently being drawn.
	 */
	private void checkAndDispatchLoadMoreEvent(int position) {
		if (position == getCount() - 1 && isLoadMoreRequestListenerSet()) {
			loadMoreRequestListener.onLoadMoreRequested();
		}
	}

	public interface OnLoadMoreRequestListener {
		/**
		 * On load more requested.
		 */
		public abstract void onLoadMoreRequested();

	}

}
