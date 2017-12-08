package com.example.hp.splashprj.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.hp.splashprj.Activity.EditNoteActivity;
import com.example.hp.splashprj.R;
import com.example.hp.splashprj.adapter.NoteDataAdapter;
import com.example.hp.splashprj.db.DBManager;
import com.example.hp.splashprj.entity.Note;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2017/10/26.
 */

public class NoteFragment extends Fragment implements View.OnClickListener{
    private FloatingActionButton addBtn;
    private DBManager dm;
    private List<Note> noteDataList = new ArrayList<>();
    private NoteDataAdapter adapter;
    private ListView listView;
    private TextView emptyListTextView;
    long waitTime = 2000;
    long touchTime = 0;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
         view=inflater.inflate(R.layout.note_fragment,null);

        return  view;
    }

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    //初始化
    private void init() {
        dm = new DBManager(getContext());
        dm.readFromDB(noteDataList);
        listView = (ListView) view.findViewById(R.id.listView);
        addBtn = (FloatingActionButton) view.findViewById(R.id.add);
        //emptyListTextView = (TextView) view.findViewById(R.id.empty);
        addBtn.setOnClickListener(this);
        adapter = new NoteDataAdapter(getContext(),noteDataList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new NoteClickListener());
        listView.setOnItemLongClickListener(new NoteLongClickListener());
        setStatusBarColor();
        updateView();
    }

    //空数据更新
    private void updateView() {
        if (noteDataList.isEmpty()) {
            listView.setVisibility(View.GONE);
           // emptyListTextView.setVisibility(View.VISIBLE);
        } else {
            listView.setVisibility(View.VISIBLE);
           // emptyListTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main,menu);
     super.onCreateOptionsMenu(menu,inflater);
    }

    //设置状态栏颜色
    public void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getActivity().getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //创建状态栏的管理实例
        SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
        //激活状态栏设置
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setTintColor(Color.parseColor("#ff6cb506"));
    }

    //浮动按钮点击事件
    @Override
    public void onClick(View v) {
        Intent in = new Intent(getActivity(), EditNoteActivity.class);
        switch (v.getId()) {
            case R.id.add:
                startActivity(in);
                //in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                  //getActivity().finish();
                break;
            default:
                break;
        }
    }

    //listView单击事件
    private class NoteClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            NoteDataAdapter.ViewHolder viewHolder = (NoteDataAdapter.ViewHolder) view.getTag();
            String noteId = viewHolder.tvId.getText().toString().trim();
            Intent intent = new Intent(getActivity(), EditNoteActivity.class);
            intent.putExtra("id", Integer.parseInt(noteId));
            //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
           // getActivity().finish();
        }
    }

    //listView长按事件
    private class NoteLongClickListener implements AdapterView.OnItemLongClickListener {


        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view,final int position, long i) {
            final Note note = (Note) parent.getAdapter().getItem(position);
            if (note == null) {
                return true;
            }
            final int id = note.getId();
            new MaterialDialog.Builder(getActivity())
                    .content(R.string.are_you_sure).positiveText(R.string.delete)
                    .negativeText(R.string.cancel).callback(new MaterialDialog.ButtonCallback(){
                public void onPositive(MaterialDialog dialog){
                    DBManager.getInstance(getActivity()).deleteNote(id);
                    adapter.removeItem(position);
                    updateView();
                }
            }).show();
            return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
          /*  case R.id.action_about:
                MaterialDialog dialog=new MaterialDialog.Builder(this)
                        .title(R.string.about)
                        .customView(R.layout.dialog_webview,false)
                        .positiveText(android.R.string.ok)
                        .build();
                WebView webView= (WebView) dialog.getCustomView().findViewById(R.id.webview);
                webView.loadUrl("file:///android_asset/webview.html");
                dialog.show();
                break;*/
            case R.id.action_clean:
                new MaterialDialog.Builder(getActivity())
                        .content(R.string.are_you_sure)
                        .positiveText(R.string.clean)
                        .negativeText(R.string.cancel)
                        .callback(new MaterialDialog.ButtonCallback(){
                            public void onPositive(MaterialDialog dialog){
                                for(int id=0;id<100;id++)
                                    DBManager.getInstance(getActivity()).deleteNote(id);
                                adapter.removeAllItem();
                                updateView();
                            }
                        }).show();
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
    //按返回键时
    public void onBackPressed(){
        long currentTime=System.currentTimeMillis();
        if((currentTime-touchTime)>=waitTime){
            Toast.makeText(getContext(),R.string.exit,Toast.LENGTH_SHORT).show();
            touchTime=currentTime;
        }else{getActivity().finish();}
    }
}
