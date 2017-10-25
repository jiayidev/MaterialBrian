package dev.brian.materialbrian.api.model;

import dev.brian.materialbrian.api.ApiCompleteListener;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at:2017/10/25 0025
 * Description:书架
 */

public interface IBookshelfModel {
    /**
     * @param listener 回调
     */
    void loadBookshelf(ApiCompleteListener listener);

    /**
     * 添加一个书架
     *
     * @param title    书架名称
     * @param remark   备注
     * @param listener 回调
     */
    void addBookshelf(String title, String remark, ApiCompleteListener listener);

    /**
     * 修改一个书架
     *
     * @param id       id
     * @param title    书架名称
     * @param remark   备注
     * @param listener 回调
     */
    void updateBookshelf(String id, String title, String remark, ApiCompleteListener listener);

    /**
     * 清空书架
     *
     * @param id       id
     * @param listener 回调
     */
    void deleteBookshelf(String id, ApiCompleteListener listener);

    /**
     * 取消订阅
     */
    void unSubscribe();
}
