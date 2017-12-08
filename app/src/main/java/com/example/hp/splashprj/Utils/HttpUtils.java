package com.example.hp.splashprj.Utils;

import android.os.AsyncTask;
import android.util.Log;

import com.example.hp.splashprj.Utils.inter.AsyncResponce;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2017/11/23.
 */

public class HttpUtils extends AsyncTask<String, Void, String> {
    public AsyncResponce asyncResponce;

    public void setOnAsyncResponse(AsyncResponce asyncResponse) {
        this.asyncResponce = asyncResponse;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null) {
            asyncResponce.onDataReceivedSuccess(parseJsonResponse(s));
        } else {
            asyncResponce.onDataReceivedFailed();
        }
    }

    @Override
    protected String doInBackground(String... params) {
        return getJsonByInternet(params[0]);
    }

    public String getJsonByInternet(String path) {
        try {
            URL url = new URL(path.trim());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(5000);
            if (urlConnection.getResponseCode() == 200) {
                //得到输入流
                InputStream inputStream = urlConnection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                    baos.flush();
                }
                return baos.toString("utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Map<String,Object>> parseJsonResponse(String response) {
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject jsonObject1 = jsonObject.getJSONObject("result");
          //  list.add(jsonObject1.getString("number"));
            map.put("number",jsonObject1.getString("number"));
           // list.add(jsonObject1.getString("type"));
            Log.d("number",jsonObject1.getString("number"));
            map.put("type",jsonObject1.getString("type"));
            Log.d("type",jsonObject1.getString("type"));
            list.add(map);
            JSONArray jsonArray = jsonObject1.getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                Map<String,Object> map1=new HashMap<>();
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                //list.add(jsonObject2.getString("time"));
                Log.d("time",jsonObject2.getString("time"));
                map1.put("time",jsonObject2.getString("time"));
                //list.add(jsonObject2.getString("status"));
                Log.d("status",jsonObject2.getString("status"));
                map1.put("status",jsonObject2.getString("status"));
                list.add(map1);
            }

          //  Log.d("TAG","数据添加成功，正在返回adapter");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
