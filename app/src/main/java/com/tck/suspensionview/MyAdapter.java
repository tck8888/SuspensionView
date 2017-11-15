package com.tck.suspensionview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tck on 2017/11/15.
 */

public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mStringList;

    public MyAdapter(Context context, List<String> stringList) {
        mContext = context;
        mStringList = stringList;
    }

    @Override
    public int getCount() {
        return mStringList == null ? 0 : mStringList.size();
    }

    @Override
    public Object getItem(int position) {
        return mStringList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
            holder = new MyViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }

        String s = mStringList.get(position);
        holder.mTitle.setText(s);
        return convertView;
    }

    class MyViewHolder {

        private TextView mTitle;

        public MyViewHolder(View convertView) {
            mTitle = (TextView) convertView.findViewById(R.id.text);
        }
    }

    public void update(List<String> list) {
        if (mStringList != null) {
            mStringList.clear();
        }
        mStringList.addAll(list);
        notifyDataSetChanged();

    }
}
