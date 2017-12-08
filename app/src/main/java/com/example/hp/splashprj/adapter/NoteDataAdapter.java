package com.example.hp.splashprj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hp.splashprj.R;
import com.example.hp.splashprj.entity.Note;
import java.util.List;

/**
 * Created by hp on 2017/10/13.
 */

public class NoteDataAdapter extends BaseAdapter {
    private Context context;
    private List<Note> notes;
public NoteDataAdapter(Context context, List<Note> notes){
    this.context=context;
    this.notes=notes;
}
    @Override
    public int getCount() {
        return notes.size();
    }
public void removeAllItem(){
    notes.clear();
    notifyDataSetChanged();
}
//从list移除对象
    public void removeItem(int position){
        notes.remove(position);
        notifyDataSetChanged();
    }
    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.notes_row,null);
            viewHolder=new ViewHolder();
            viewHolder.tvId= (TextView) convertView.findViewById(R.id.note_id);
            viewHolder.tvTitle= (TextView) convertView.findViewById(R.id.note_title);
            viewHolder.tvContent= (TextView) convertView.findViewById(R.id.note_content);
            viewHolder.tvTime= (TextView) convertView.findViewById(R.id.note_time);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.tvId.setText(notes.get(position).getId()+"");
        viewHolder.tvTitle.setText(notes.get(position).getTitle());
        viewHolder.tvContent.setText(notes.get(position).getContent());
        viewHolder.tvTime.setText(notes.get(position).getTime());
        return convertView;
    }
    //ViewHolder内部类
    public static class ViewHolder{
        public TextView tvId;
        public TextView tvTitle;
        public TextView tvContent;
        public TextView tvTime;
    }
}
