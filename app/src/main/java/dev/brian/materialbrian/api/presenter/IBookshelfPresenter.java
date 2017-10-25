package dev.brian.materialbrian.api.presenter;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at:2017/10/25 0025
 * Description:
 */

public interface IBookshelfPresenter {
    /**
     * 获取我的书架
     */
    void loadBookshelf();

    /**
     * 添加一个书架
     *
     * @param title  书架名称
     * @param remark 备注
     */
    void addBookshelf(String title, String remark);

    /**
     * 修改一个书架
     *
     * @param id     id
     * @param title  书架名称
     * @param remark 备注
     */
    void updateBookshelf(String id, String title, String remark);

    /**
     * 清空书架
     *
     * @param id id
     */
    void deleteBookshelf(String id);

    /**
     * 取消订阅
     */
    void unSubscribe();
}
