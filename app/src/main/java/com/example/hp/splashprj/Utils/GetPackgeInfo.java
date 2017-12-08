package com.example.hp.splashprj.Utils;

import android.content.Context;
import android.content.pm.PackageInfo;

import java.util.List;

/**
 * Created by hp on 2017/11/15.
 */

public class GetPackgeInfo {
    public static List<PackageInfo> getPackageInfo(Context context){
    //获取已安装apk列表
    List<PackageInfo> packageInfoList= context.getPackageManager().getInstalledPackages(0);
        return packageInfoList;
    }

}
