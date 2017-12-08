package com.example.hp.splashprj.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by hp on 2017/10/18.
 */

public class SharedUtils {
    public static final String FILE="config";
    public static void putBooleanData(Context context,String key,boolean value){
        SharedPreferences sp=context.getSharedPreferences(FILE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }
    public static boolean getBooleanData(Context context,String key,boolean defaultvalue){

        SharedPreferences sp=context.getSharedPreferences(FILE,Context.MODE_PRIVATE);
        return sp.getBoolean(key,defaultvalue);
    }
}
