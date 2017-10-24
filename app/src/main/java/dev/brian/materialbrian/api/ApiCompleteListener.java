package dev.brian.materialbrian.api;

import dev.brian.materialbrian.bean.http.douban.BaseResponse;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at:2017/10/24 0024
 * Description: 网络请求回调接口
 */

public interface ApiCompleteListener {
    // 成功
    void onComplected(Object result);

    // 失败
    void onFailed(BaseResponse msg);
}
