package com.example.hp.splashprj.Utils.inter;

import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2017/11/24.
 */

public interface AsyncResponce {
    void onDataReceivedSuccess(List<Map<String, Object>> result);
    void onDataReceivedFailed();
}
