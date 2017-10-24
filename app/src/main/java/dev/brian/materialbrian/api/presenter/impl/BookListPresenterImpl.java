package dev.brian.materialbrian.api.presenter.impl;

import dev.brian.materialbrian.BaseApplication;
import dev.brian.materialbrian.R;
import dev.brian.materialbrian.api.ApiCompleteListener;
import dev.brian.materialbrian.api.model.IBookListModel;
import dev.brian.materialbrian.api.model.impl.BookListModelImpl;
import dev.brian.materialbrian.api.presenter.IBookListPresenter;
import dev.brian.materialbrian.api.view.IBookListView;
import dev.brian.materialbrian.bean.http.douban.BaseResponse;
import dev.brian.materialbrian.bean.http.douban.BookListResponse;
import dev.brian.materialbrian.utils.common.NetworkUtils;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at:2017/10/24 0024
 * Description:
 */

public class BookListPresenterImpl implements IBookListPresenter, ApiCompleteListener {
    private IBookListView mBookListView;
    private IBookListModel mBookListModel;

    public BookListPresenterImpl(IBookListView view) {
        mBookListView = view;
        mBookListModel = new BookListModelImpl();
    }

    /**
     * 加载数据
     */
    @Override
    public void loadBooks(String q, String tag, int start, int count, String fields) {
        if (!NetworkUtils.isConnected(BaseApplication.getApplication())) {
            mBookListView.showMessage(BaseApplication.getApplication().getString(R.string.poor_network));
            mBookListView.hideProgress();
            //            return;
        }
        mBookListView.showProgress();
        mBookListModel.loadBookList(q, tag, start, count, fields, this);
    }

    @Override
    public void cancelLoading() {
        mBookListModel.cancelLoading();
    }

    /**
     * 接口访问成功
     *
     * @param result 返回结果
     */
    @Override
    public void onComplected(Object result) {
        if (result instanceof BookListResponse) {
            int index = ((BookListResponse) result).getStart();
            if (index == 0) {
                mBookListView.refreshData(result);
            } else {
                mBookListView.addData(result);
            }
            mBookListView.hideProgress();
        }
    }

    /**
     * 请求失败
     *
     * @param msg 错误信息
     */
    @Override
    public void onFailed(BaseResponse msg) {
        mBookListView.hideProgress();
        mBookListView.showMessage(msg.getMsg());
    }
}
