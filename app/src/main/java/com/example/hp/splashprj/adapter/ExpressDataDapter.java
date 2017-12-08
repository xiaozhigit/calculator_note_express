package com.example.hp.splashprj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hp.splashprj.R;

import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2017/11/23.
 */

public class ExpressDataDapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    private Map<String,Object> map;
    private List<Map<String,Object>> list;
    public ExpressDataDapter(Context context, List<Map<String,Object>> list){
       /// super();
        this.context=context;
        this.list=list;
        System.out.println("myDapter:"+list);

    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater=LayoutInflater.from(context);
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=layoutInflater.inflate(R.layout.item_layout,null);
            holder.tvTiem= (TextView) convertView.findViewById(R.id.item_time);
            holder.tvStatus= (TextView) convertView.findViewById(R.id.item_status);
            convertView.setTag(holder);
        }else{

            holder= (ViewHolder) convertView.getTag();
        }
        holder.tvTiem.setText((String)list.get(position).get("time"));
        holder.tvStatus.setText((String)list.get(position).get("status"));

        return convertView;
    }
    class ViewHolder{
        public TextView tvTiem;
        public TextView tvStatus;
    }

}
