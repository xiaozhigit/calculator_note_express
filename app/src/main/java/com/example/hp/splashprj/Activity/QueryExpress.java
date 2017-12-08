package com.example.hp.splashprj.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hp.splashprj.StaticClass.L;
import com.example.hp.splashprj.Utils.HttpUtils;
import com.example.hp.splashprj.Utils.inter.AsyncResponce;
import com.example.hp.splashprj.R;
import com.example.hp.splashprj.adapter.ExpressDataDapter;

import java.util.List;
import java.util.Map;

public class QueryExpress extends Activity {
    private TextView tv1,tv_number,tv_type;
    private ListView listView;
    private List<Map<String,Object>> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_query_express);
        init();
    }

    public void init() {
        tv1 = (TextView) findViewById(R.id.tv1);
        tv_number = (TextView) findViewById(R.id.tv_number);
        tv_type= (TextView) findViewById(R.id.tv_type);
        listView = (ListView) findViewById(R.id.listView);
        tv1.setText(R.string.express_tvShow);
        Intent intent = getIntent();
        String exNum = intent.getStringExtra("expressNum");
        Log.d("得到的物流单号为：", exNum);
        String url = L.URL+exNum;
        try {

            HttpUtils httpUtils = new HttpUtils();
            httpUtils.execute(url);

            httpUtils.setOnAsyncResponse(new AsyncResponce() {
                @Override
                public void onDataReceivedSuccess(List<Map<String,Object>> result) {

                    ExpressDataDapter expressDataDapter = new ExpressDataDapter(getBaseContext(), result);
                    listView.setAdapter(expressDataDapter);
                    Map<String,Object> map=result.get(0);
                    String number=(String)map.get("number");
                    String type=(String)map.get("type");
                    Log.d("type",type);
                    if(TextUtils.isEmpty(type)){
                        tv_number.setText(R.string.express_tvNumber_error+number);
                        return ;
                       // tv_type.setText(R.string.express_tvType+parse(type));
                    }
                    tv_number.setText(R.string.express_tvNumber+number);
                    tv_type.setText(R.string.express_tvType+parse(type));
            }
                @Override
                public void onDataReceivedFailed() {
                    Log.d("TAG", "数据接收失败");

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int  parse(String o){
        switch(o){
           // case "DEPPON": return R.string.debang;
            case "ems": return R.string.ems;
            case "gto": return R.string.gto;
            case "htky": return R.string.htky;
            case "jd": return R.string.jd;
            case "JIAJI": return R.string.jiaji;
            case "FASTEXPRESS": return R.string.fastexpress;
            case "qfkd": return R.string.qfkd;
            case "SFEXPRESS": return R.string.sf;
            case "sto": return R.string.sto;
            case "SHENGHUI": return R.string.shengghui;
            case "sure": return R.string.sure;
            case "CHINAPOST": return R.string.chinapost;
            case "ttkdex": return R.string.ttkd;
            case "yto": return R.string.yto;
            case "yunda": return R.string.yunda;
            case "uc56": return R.string.uc56;
            case "ZJS": return R.string.zjs;
            case "CRE": return R.string.cre;
            default:break;
        }
        return R.string.empty;
    }
}