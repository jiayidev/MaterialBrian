package dev.brian.materialbrian.api.presenter.impl;

import java.util.List;

import dev.brian.materialbrian.api.ApiCompleteListener;
import dev.brian.materialbrian.api.model.IBookListModel;
import dev.brian.materialbrian.api.model.IBookshelfModel;
import dev.brian.materialbrian.api.model.impl.BookshelfModelImpl;
import dev.brian.materialbrian.api.presenter.IBookshelfPresenter;
import dev.brian.materialbrian.api.view.IBookListView;
import dev.brian.materialbrian.bean.http.douban.BaseResponse;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at:2017/10/25 0025
 * Description:
 */

public class BookshelfPresenterImpl implements IBookshelfPresenter, ApiCompleteListener {
    private IBookListView iBookListView;
    private IBookshelfModel iBookshelfModel;

    public BookshelfPresenterImpl(IBookListView view) {
        this.iBookListView = view;
        iBookshelfModel = new BookshelfModelImpl();
    }

    @Override
    public void loadBookshelf() {
        iBookListView.showProgress();
        iBookshelfModel.loadBookshelf(this);
    }

    @Override
    public void addBookshelf(String title, String remark) {
        iBookListView.showProgress();
        iBookshelfModel.addBookshelf(title, remark, this);
    }

    @Override
    public void updateBookshelf(String id, String title, String remark) {
        iBookListView.showProgress();
        iBookshelfModel.updateBookshelf(id, title, remark, this);
    }

    @Override
    public void deleteBookshelf(String id) {
        iBookListView.showProgress();
        iBookshelfModel.deleteBookshelf(id, this);
    }

    @Override
    public void unSubscribe() {
        iBookshelfModel.unSubscribe();
    }

    @Override
    public void onComplected(Object result) {
        if (result instanceof List) {
            iBookListView.refreshData(result);
        } else if (result instanceof BaseResponse) {
            iBookListView.showMessage(((BaseResponse) result).getCode() + "|"
                    + ((BaseResponse) result).getMsg());
        }
        iBookListView.hideProgress();
    }

    @Override
    public void onFailed(BaseResponse msg) {
        iBookListView.showMessage(msg.getCode() + "|" + msg.getMsg());
        iBookListView.hideProgress();
    }
}
