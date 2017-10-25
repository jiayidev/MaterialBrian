package dev.brian.materialbrian.api.model;

import dev.brian.materialbrian.api.ApiCompleteListener;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at:2017/10/24 0024
 * Description:
 */

public interface IBookListModel {
    /**
     * 获取图书接口
     *
     */
    void loadBookList(String q, String tag, int start, int count, String fields, ApiCompleteListener listener);

    /**
     * 取消加载数据
     */
    void cancelLoading();
}
