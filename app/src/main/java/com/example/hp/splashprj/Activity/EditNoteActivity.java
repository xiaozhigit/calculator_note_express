package com.example.hp.splashprj.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.splashprj.Fragment.NoteFragment;
import com.example.hp.splashprj.R;
import com.example.hp.splashprj.db.DBManager;
import com.example.hp.splashprj.entity.Note;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.Date;

import static android.widget.Toast.LENGTH_SHORT;

public class EditNoteActivity extends Activity implements View.OnClickListener{
    private EditText titleEt,contentEt;
    private FloatingActionButton saveBtn;
    private int noteID=-1;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_edit_note);
        init();
    }
//初始化
    private void init(){
        dbManager=new DBManager(this);
        titleEt= (EditText) findViewById(R.id.note_title);
        contentEt= (EditText) findViewById(R.id.note_content);
        saveBtn= (FloatingActionButton) findViewById(R.id.save);
        saveBtn.setOnClickListener(this);
        noteID=getIntent().getIntExtra("id",-1);
        if(noteID !=-1){
            showNoteData(noteID);
        }
 setStatusBarColor();
    }
    //设置状态栏颜色
    public void setStatusBarColor(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            Window window=getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        SystemBarTintManager tintManager=new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setTintColor(Color.parseColor("#ff6cb506"));
    }
    //显示更新的数据
    private void showNoteData(int id){
        Note note=dbManager.readData(id);
        titleEt.setText(note.getTitle());
        contentEt.setText(note.getContent());
        //控制光标
        Spannable spannable=titleEt.getText();
        Selection.setSelection(spannable,titleEt.getText().length());
    }
    @Override
    public void onClick(View v) {
        String title=titleEt.getText().toString();
        String content=contentEt.getText().toString();
        if(TextUtils.isEmpty(title)||TextUtils.isEmpty(content)){
            Toast.makeText(this,"未完整添加信息",LENGTH_SHORT).show();
            return ;
        }
        String time=getTime();
        if(noteID==-1){
            //默认添加
            dbManager.addToDB(title,content,time);
            Toast.makeText(this,R.string.dataSaveSuccess, LENGTH_SHORT).show();
        }else{
            //更新
            dbManager.updateNote(noteID,title,content,time);
        }
      //  Intent intent=new Intent(EditNoteActivity.this,NoteFragment.class);
      // startActivity(intent);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();

    }
    //得到时间
    private String getTime(){
        SimpleDateFormat format=new SimpleDateFormat("MM-dd HH:mm E");
        Date curDate=new Date();
        String str=format.format(curDate);
        return str;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
           /* case R.id.action_about:
                MaterialDialog dialog=new MaterialDialog.Builder(this)
                        .title(R.string.about)
                        .customView(R.layout.dialog_webview,false)
                        .positiveText(android.R.string.ok)
                        .build();
                WebView webView= (WebView) dialog.getCustomView().findViewById(R.id.webview);
                webView.loadUrl("file://android_asset/webview.html");
                dialog.show();
                break;*/
        }
        return super.onOptionsItemSelected(item);
    }
//按返回键时

    @Override
    public void onBackPressed() {
        //Intent intent=new Intent(EditNoteActivity.this,NoteFragment.class);
        //startActivity(intent);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.finish();
    }
}
