package dev.brian.materialbrian.api.model.impl;

import dev.brian.materialbrian.api.ApiCompleteListener;
import dev.brian.materialbrian.api.common.ServiceFactory;
import dev.brian.materialbrian.api.common.service.IBookService;
import dev.brian.materialbrian.api.model.IBookListModel;
import dev.brian.materialbrian.bean.http.douban.BaseResponse;
import dev.brian.materialbrian.bean.http.douban.BookListResponse;
import dev.brian.materialbrian.common.URL;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at:2017/10/24 0024
 * Description:
 */

public class BookListModelImpl implements IBookListModel {
    @Override
    public void loadBookList(String q, String tag, int start, int count, String fields,
                             ApiCompleteListener listener) {
        IBookService iBookService = ServiceFactory.createService(URL.HOST_URL_DOUBAN, IBookService.class);
        iBookService.getBookList(q, tag, start, count, fields)
                .subscribeOn(Schedulers.newThread())    //请求在新的线程中执行
                .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(new Subscriber<Response<BookListResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailed(new BaseResponse(404, e.getMessage()));
                    }

                    @Override
                    public void onNext(Response<BookListResponse> bookListResponse) {
                        if (bookListResponse.isSuccessful()) {
                            listener.onComplected(bookListResponse.body());
                        } else {
                            listener.onFailed(new BaseResponse(bookListResponse.code(), bookListResponse.message()));
                        }
                    }
                });
    }

    @Override
    public void cancelLoading() {

    }
}
